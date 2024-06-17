package cn.iocoder.yudao.module.bookstore.service.sourceinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceinfo.vo.ExtraSourceInfoSaveReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceinfo.vo.SourceInfoAuditReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceinfo.vo.SourceInfoPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceinfo.vo.SourceInfoSaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.sourceinfo.SourceInfoDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 资源信息 Service 接口
 *
 * @author 管理员
 */
public interface SourceInfoService {

    /**
     * 创建资源信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSourceInfo(@Valid SourceInfoSaveReqVO createReqVO);

    /**
     * 创建资源信息(存储地址列表)
     *
     * @param extraSourceInfoSaveReqVO 资源信息
     * @return 编号
     */
    void createExtraSourceInfo(@Valid ExtraSourceInfoSaveReqVO extraSourceInfoSaveReqVO);

    /**
     * 创建资源信息
     *
     * @param createReqVO 创建信息列表
     * @return 编号
     */
    void createSourceInfos(@Valid List<SourceInfoSaveReqVO> createReqVO);

    /**
     * 审核资源信息
     *
     * @param auditReqVO 资源信息
     * @return 编号
     */
    void audit(@Valid SourceInfoAuditReqVO auditReqVO);


    /**
     * 更新资源信息
     *
     * @param updateReqVO 更新信息
     */
    void updateSourceInfo(@Valid SourceInfoSaveReqVO updateReqVO);

    /**
     * 删除资源信息
     *
     * @param id 编号
     */
    void deleteSourceInfo(Long id);

    /**
     * 获得资源信息
     *
     * @param id 编号
     * @return 资源信息
     */
    SourceInfoDO getSourceInfo(Long id);

    /**
     * 获得资源信息分页
     *
     * @param pageReqVO 分页查询
     * @return 资源信息分页
     */
    PageResult<SourceInfoDO> getSourceInfoPage(SourceInfoPageReqVO pageReqVO);

}