package cn.iocoder.yudao.module.bookstore.service.bookinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookinfo.vo.BookInfoPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookinfo.vo.BookInfoSaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookinfo.BookInfoDO;

import jakarta.validation.Valid;

/**
 * 图书信息 Service 接口
 *
 * @author 管理员
 */
public interface BookInfoService {

    /**
     * 创建图书信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBookInfo(@Valid BookInfoSaveReqVO createReqVO);

    /**
     * 更新图书信息
     *
     * @param updateReqVO 更新信息
     */
    void updateBookInfo(@Valid BookInfoSaveReqVO updateReqVO);

    /**
     * 删除图书信息
     *
     * @param id 编号
     */
    void deleteBookInfo(Long id);

    /**
     * 获得图书信息
     *
     * @param id 编号
     * @return 图书信息
     */
    BookInfoDO getBookInfo(Long id);

    /**
     * 获得图书信息分页
     *
     * @param pageReqVO 分页查询
     * @return 图书信息分页
     */
    PageResult<BookInfoDO> getBookInfoPage(BookInfoPageReqVO pageReqVO);

}