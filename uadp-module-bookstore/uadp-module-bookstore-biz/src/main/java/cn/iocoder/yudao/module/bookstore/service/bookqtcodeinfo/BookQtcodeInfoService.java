package cn.iocoder.yudao.module.bookstore.service.bookqtcodeinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.BookQtcodeInfoPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.BookQtcodeInfoSaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodeinfo.BookQtcodeInfoDO;

import javax.validation.Valid;

/**
 * 图书二维码信息 Service 接口
 *
 * @author 管理员
 */
public interface BookQtcodeInfoService {

    /**
     * 创建图书二维码信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBookQtcodeInfo(@Valid BookQtcodeInfoSaveReqVO createReqVO);

    /**
     * 更新图书二维码信息
     *
     * @param updateReqVO 更新信息
     */
    void updateBookQtcodeInfo(@Valid BookQtcodeInfoSaveReqVO updateReqVO);

    /**
     * 删除图书二维码信息
     *
     * @param id 编号
     */
    void deleteBookQtcodeInfo(Long id);

    /**
     * 获得图书二维码信息
     *
     * @param id 编号
     * @return 图书二维码信息
     */
    BookQtcodeInfoDO getBookQtcodeInfo(Long id);

    /**
     * 获得图书二维码信息分页
     *
     * @param pageReqVO 分页查询
     * @return 图书二维码信息分页
     */
    PageResult<BookQtcodeInfoDO> getBookQtcodeInfoPage(BookQtcodeInfoPageReqVO pageReqVO);


    /**
     * 生成二维码
     *
     * @param dtcodeAddress 二维码内容
     * @return 二维码 base64码
     */
    String genQrCode(String dtcodeAddress);

}