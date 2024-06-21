package cn.iocoder.yudao.module.bookstore.service.bookchapter;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.BookChapterPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.BookChapterSaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookchapter.BookChapterDO;
import cn.iocoder.yudao.module.bookstore.dal.mysql.bookchapter.BookChapterMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.infra.enums.BookStoreErrorCodeConstants.BOOK_CHAPTER_NOT_EXISTS;

/**
 * 图书章节 Service 实现类
 *
 * @author 管理员
 */
@Service
@Validated
public class BookChapterServiceImpl implements BookChapterService {

    @Resource
    private BookChapterMapper bookChapterMapper;

    @Override
    public Long createBookChapter(BookChapterSaveReqVO createReqVO) {
        // 插入
        BookChapterDO bookChapter = BeanUtils.toBean(createReqVO, BookChapterDO.class);
        bookChapter.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        bookChapterMapper.insert(bookChapter);
        // 返回
        return bookChapter.getId();
    }

    @Override
    public void updateBookChapter(BookChapterSaveReqVO updateReqVO) {
        // 校验存在
        validateBookChapterExists(updateReqVO.getId());
        // 更新
        BookChapterDO updateObj = BeanUtils.toBean(updateReqVO, BookChapterDO.class);
        bookChapterMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBookChapter(Long id) {
        // 校验存在
        validateBookChapterExists(id);
        // 删除
        bookChapterMapper.deleteById(id);
        //删除子章节
        bookChapterMapper.deleteByChapterPid(id);

    }

    private void validateBookChapterExists(Long id) {
        if (bookChapterMapper.selectById(id) == null) {
            throw exception(BOOK_CHAPTER_NOT_EXISTS);
        }
    }

    @Override
    public BookChapterDO getBookChapter(Long id) {
        return bookChapterMapper.selectById(id);
    }

    @Override
    public PageResult<BookChapterDO> getBookChapterPage(BookChapterPageReqVO pageReqVO) {
        return bookChapterMapper.selectPage(pageReqVO);
    }

}