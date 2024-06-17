package cn.iocoder.yudao.module.bookstore.service.sourceinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceinfo.vo.ExtraSourceInfoSaveReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceinfo.vo.SourceInfoAuditReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceinfo.vo.SourceInfoPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceinfo.vo.SourceInfoSaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.sourceaudithistory.SourceauditHistoryDO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.sourceinfo.SourceInfoDO;
import cn.iocoder.yudao.module.bookstore.dal.mysql.sourceaudithistory.SourceauditHistoryMapper;
import cn.iocoder.yudao.module.bookstore.dal.mysql.sourceinfo.SourceInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.infra.enums.BookStoreErrorCodeConstants.SOURCE_INFO_NOT_EXISTS;

/**
 * 资源信息 Service 实现类
 *
 * @author 管理员
 */
@Service
@Validated
public class SourceInfoServiceImpl implements SourceInfoService {

    @Resource
    private SourceInfoMapper sourceInfoMapper;

    @Resource
    SourceauditHistoryMapper sourceauditHistoryMapper;

    @Override
    public Long createSourceInfo(SourceInfoSaveReqVO createReqVO) {
        // 插入
        SourceInfoDO sourceInfo = BeanUtils.toBean(createReqVO, SourceInfoDO.class);
        sourceInfo.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        sourceInfoMapper.insert(sourceInfo);
        // 返回
        return sourceInfo.getId();
    }

    @Override
    public void createExtraSourceInfo(ExtraSourceInfoSaveReqVO extraSourceInfoSaveReqVO) {
        Collection<SourceInfoDO> entities = new ArrayList<>();
        extraSourceInfoSaveReqVO.getStorageInfos().forEach(vo -> {
            SourceInfoDO sourceInfo = BeanUtils.toBean(extraSourceInfoSaveReqVO, SourceInfoDO.class);
            sourceInfo.setStorageAddress(vo.getStorageUrl());
            sourceInfo.setFormat(vo.getFormat());
            sourceInfo.setSize(vo.getSize());
            sourceInfo.setSizeUnit(vo.getSizeUnit());
            sourceInfo.setSourceName(vo.getSourceName());
            sourceInfo.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
            //资源默认为未审核状态
            sourceInfo.setSourceStatus("0");
            entities.add(sourceInfo);
        });
        sourceInfoMapper.insertBatch(entities);
    }

    @Override
    public void createSourceInfos(List<SourceInfoSaveReqVO> createReqVO) {
        Collection<SourceInfoDO> entities = new ArrayList<>();
        createReqVO.forEach(vo -> {
            SourceInfoDO sourceInfo = BeanUtils.toBean(vo, SourceInfoDO.class);
            sourceInfo.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
            entities.add(sourceInfo);
        });
        sourceInfoMapper.insertBatch(entities);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void audit(SourceInfoAuditReqVO auditReqVO) {
        // 校验存在
        SourceInfoDO sourceInfo = getSourceInfo(auditReqVO.getId());
        sourceInfo.setAuditStatus(auditReqVO.getAuditStatus());
        // 更新
        sourceInfoMapper.updateById(sourceInfo);
        //插入审核记录表
        SourceauditHistoryDO sourceauditHistoryDO = new SourceauditHistoryDO();
        sourceauditHistoryDO.setSourceId(auditReqVO.getId());
        sourceauditHistoryDO.setSourceName(sourceInfo.getSourceName());
        sourceauditHistoryDO.setAuditor(auditReqVO.getAuditor());
        sourceauditHistoryDO.setAuditMsg(auditReqVO.getAuditMsg());
        sourceauditHistoryDO.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        sourceauditHistoryMapper.insert(sourceauditHistoryDO);
    }

    @Override
    public void updateSourceInfo(SourceInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateSourceInfoExists(updateReqVO.getId());
        // 更新
        SourceInfoDO updateObj = BeanUtils.toBean(updateReqVO, SourceInfoDO.class);
        sourceInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteSourceInfo(Long id) {
        // 校验存在
        validateSourceInfoExists(id);
        // 删除
        sourceInfoMapper.deleteById(id);
    }

    private void validateSourceInfoExists(Long id) {
        if (sourceInfoMapper.selectById(id) == null) {
            throw exception(SOURCE_INFO_NOT_EXISTS);
        }
    }

    @Override
    public SourceInfoDO getSourceInfo(Long id) {
        return sourceInfoMapper.selectById(id);
    }

    @Override
    public PageResult<SourceInfoDO> getSourceInfoPage(SourceInfoPageReqVO pageReqVO) {
        return sourceInfoMapper.selectPage(pageReqVO);
    }

}