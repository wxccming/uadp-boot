package cn.iocoder.yudao.module.bookstore.service.bookqtcodesource;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource.vo.BookQtcodeSourcePageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource.vo.BookQtcodeSourceSaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodesource.BookQtcodeSourceDO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodesource.H5BookQtcodeSourceDO;
import cn.iocoder.yudao.module.bookstore.dal.mysql.bookqtcodesource.BookQtcodeSourceMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.infra.enums.BookStoreErrorCodeConstants.BOOK_QTCODE_SOURCE_NOT_EXISTS;

/**
 * 二维码资源 Service 实现类
 *
 * @author 管理员
 */
@Service
@Validated
public class BookQtcodeSourceServiceImpl implements BookQtcodeSourceService {

    @Resource
    private BookQtcodeSourceMapper bookQtcodeSourceMapper;

    @Override
    public Long createBookQtcodeSource(BookQtcodeSourceSaveReqVO createReqVO) {
        // 插入
        BookQtcodeSourceDO bookQtcodeSource = BeanUtils.toBean(createReqVO, BookQtcodeSourceDO.class);
        bookQtcodeSource.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        bookQtcodeSourceMapper.insert(bookQtcodeSource);
        // 返回
        return bookQtcodeSource.getId();
    }

    @Override
    public void updateBookQtcodeSource(BookQtcodeSourceSaveReqVO updateReqVO) {
        // 校验存在
        validateBookQtcodeSourceExists(updateReqVO.getId());
        // 更新
        BookQtcodeSourceDO updateObj = BeanUtils.toBean(updateReqVO, BookQtcodeSourceDO.class);
        bookQtcodeSourceMapper.updateById(updateObj);
    }

    @Override
    public void deleteBookQtcodeSource(Long id) {
        // 校验存在
        validateBookQtcodeSourceExists(id);
        // 删除
        bookQtcodeSourceMapper.deleteById(id);
    }

    private void validateBookQtcodeSourceExists(Long id) {
        if (bookQtcodeSourceMapper.selectById(id) == null) {
            throw exception(BOOK_QTCODE_SOURCE_NOT_EXISTS);
        }
    }

    @Override
    public BookQtcodeSourceDO getBookQtcodeSource(Long id) {
        return bookQtcodeSourceMapper.selectById(id);
    }

    @Override
    public PageResult<BookQtcodeSourceDO> getBookQtcodeSourcePage(BookQtcodeSourcePageReqVO pageReqVO) {
        return bookQtcodeSourceMapper.selectPage(pageReqVO);
    }

    @Override
    public void saveBookQtcodeSourceList(List<BookQtcodeSourceSaveReqVO> reqVOs) {
        Collection<BookQtcodeSourceDO> entities = new ArrayList<>();
        reqVOs.forEach(vo -> {
            BookQtcodeSourceDO bookQtcodeSource = BeanUtils.toBean(vo, BookQtcodeSourceDO.class);
            bookQtcodeSource.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
            entities.add(bookQtcodeSource);
        });
        //批量插入数据
        bookQtcodeSourceMapper.insertBatch(entities);
    }

    @Override
    public List<H5BookQtcodeSourceDO> selectQtSourceList(Long chapter_id) {
        return bookQtcodeSourceMapper.selectQtSourceList(chapter_id);
    }
}