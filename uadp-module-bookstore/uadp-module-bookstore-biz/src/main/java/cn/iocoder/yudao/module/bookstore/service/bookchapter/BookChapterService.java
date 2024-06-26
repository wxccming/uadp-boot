package cn.iocoder.yudao.module.bookstore.service.bookchapter;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.BookChapterPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.BookChapterReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.BookChapterSaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookchapter.BookChapterDO;

import jakarta.validation.Valid;
import java.util.List;

/**
 * 图书章节 Service 接口
 *
 * @author 管理员
 */
public interface BookChapterService {

    /**
     * 创建图书章节
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBookChapter(@Valid BookChapterSaveReqVO createReqVO);

    /**
     * 更新图书章节
     *
     * @param updateReqVO 更新信息
     */
    void updateBookChapter(@Valid BookChapterSaveReqVO updateReqVO);

    /**
     * 删除图书章节
     *
     * @param id 编号
     */
    void deleteBookChapter(Long id);

    /**
     * 获得图书章节
     *
     * @param id 编号
     * @return 图书章节
     */
    BookChapterDO getBookChapter(Long id);

    /**
     * 获得图书章节分页
     *
     * @param pageReqVO 分页查询
     * @return 图书章节分页
     */
    PageResult<BookChapterDO> getBookChapterPage(BookChapterPageReqVO pageReqVO);

    /**
     * 获得图书所有章节
     *
     * @return 图书所有章节
     */
    List<BookChapterDO> getBookChapterList(Long bookNo);

}