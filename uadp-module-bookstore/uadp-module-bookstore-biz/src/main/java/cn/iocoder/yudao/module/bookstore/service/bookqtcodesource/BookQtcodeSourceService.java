package cn.iocoder.yudao.module.bookstore.service.bookqtcodesource;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource.vo.BookQtcodeSourcePageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource.vo.BookQtcodeSourceSaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodesource.BookQtcodeSourceDO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodesource.H5BookQtcodeSourceDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 二维码资源 Service 接口
 *
 * @author 管理员
 */
public interface BookQtcodeSourceService {

    /**
     * 创建二维码资源
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBookQtcodeSource(@Valid BookQtcodeSourceSaveReqVO createReqVO);

    /**
     * 更新二维码资源
     *
     * @param updateReqVO 更新信息
     */
    void updateBookQtcodeSource(@Valid BookQtcodeSourceSaveReqVO updateReqVO);

    /**
     * 删除二维码资源
     *
     * @param id 编号
     */
    void deleteBookQtcodeSource(Long id);

    /**
     * 获得二维码资源
     *
     * @param id 编号
     * @return 二维码资源
     */
    BookQtcodeSourceDO getBookQtcodeSource(Long id);

    /**
     * 获得二维码资源分页
     *
     * @param pageReqVO 分页查询
     * @return 二维码资源分页
     */
    PageResult<BookQtcodeSourceDO> getBookQtcodeSourcePage(BookQtcodeSourcePageReqVO pageReqVO);


    /**
     * 保存二维码资源列表
     *
     * @param reqVOs 资源信息列表
     * @return 编号
     */
    void saveBookQtcodeSourceList(List<BookQtcodeSourceSaveReqVO> reqVOs);

    /**
     * 根据章节编号获取所有的资源信息
     *
     * @param chapter_id 章节编号
     * @return 二维码资源分页
     */
    List<H5BookQtcodeSourceDO> selectQtSourceList(Long item_id,Long chapter_id,String applica_scens);

}