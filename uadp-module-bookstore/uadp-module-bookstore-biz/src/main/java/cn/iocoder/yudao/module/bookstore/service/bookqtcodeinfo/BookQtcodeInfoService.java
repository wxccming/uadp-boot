package cn.iocoder.yudao.module.bookstore.service.bookqtcodeinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.BookQtcodeInfoPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.BookQtcodeInfoSaveReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.ExtraBookQtcodeInfoSaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodeinfo.BookQtcodeInfoDO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodesource.BookQtcodeSourceDO;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

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

    /**
     * 保存图书二维码信息和资源信息
     *
     * @param reqVO 二维码信息和资源信息
     */
    Long saveBookQtcodeInfoAndQtcodeResource(@Valid ExtraBookQtcodeInfoSaveReqVO reqVO);


    /**
     * 更新图书二维码信息和资源信息
     *
     * @param reqVO 二维码信息和资源信息
     */
    Long updateBookQtcodeInfoAndQtcodeResource(@Valid ExtraBookQtcodeInfoSaveReqVO reqVO);

    /**
     * 根据二维码ID获取图书信息
     *
     * @param dtcodeId 编号
     * @return 图书二维码信息
     */
    List<BookQtcodeSourceDO> selectListByDtcodeId(Long dtcodeId);

    /**
     * 根据图书编号获取图书信息
     *
     * @param bookNo 图书编号
     * @param itemId 项目编号
     * @return 图书二维码信息
     */
    BookQtcodeInfoDO getBookQtcodeInfoByBookNo(Long bookNo,Long itemId);

    /**
     * 根据图书章节获取图书信息
     *
     * @param itemId 项目编号
     * @param bookNo 图书编号
     * @param chapterId 章节编号
     * @return 图书二维码信息
     */
    BookQtcodeInfoDO getBookQtcodeInfoByChapterId(Long itemId, Long bookNo, Long chapterId);

}