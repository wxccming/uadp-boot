package cn.iocoder.yudao.module.bookstore.service.bookinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookinfo.vo.BookInfoPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookinfo.vo.BookInfoSaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookinfo.BookInfoDO;
import cn.iocoder.yudao.module.bookstore.dal.mysql.bookinfo.BookInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.infra.enums.BookStoreErrorCodeConstants.BOOK_INFO_NOT_EXISTS;

/**
 * 图书信息 Service 实现类
 *
 * @author 管理员
 */
@Service
@Validated
public class BookInfoServiceImpl implements BookInfoService {

    @Resource
    private BookInfoMapper bookInfoMapper;

    @Override
    public Long createBookInfo(BookInfoSaveReqVO createReqVO) {
        // 插入
        BookInfoDO bookInfo = BeanUtils.toBean(createReqVO, BookInfoDO.class);
        bookInfo.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        bookInfoMapper.insert(bookInfo);
        // 返回
        return bookInfo.getId();
    }

    @Override
    public void updateBookInfo(BookInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateBookInfoExists(updateReqVO.getId());
        // 更新
        BookInfoDO updateObj = BeanUtils.toBean(updateReqVO, BookInfoDO.class);
        bookInfoMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBookInfo(Long id) {
        // 校验存在
        validateBookInfoExists(id);
        // 删除
        bookInfoMapper.deleteById(id);
        //删除章节
        bookInfoMapper.deleteBookChapterByBookNo(id);
    }

    private void validateBookInfoExists(Long id) {
        if (bookInfoMapper.selectById(id) == null) {
            throw exception(BOOK_INFO_NOT_EXISTS);
        }
    }

    @Override
    public BookInfoDO getBookInfo(Long id) {
        return bookInfoMapper.selectById(id);
    }

    @Override
    public PageResult<BookInfoDO> getBookInfoPage(BookInfoPageReqVO pageReqVO) {
        return bookInfoMapper.selectPage(pageReqVO);
    }

}