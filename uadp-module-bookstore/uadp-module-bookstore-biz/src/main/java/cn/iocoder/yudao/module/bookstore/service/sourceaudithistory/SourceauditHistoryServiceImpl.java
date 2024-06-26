package cn.iocoder.yudao.module.bookstore.service.sourceaudithistory;

import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceaudithistory.vo.SourceauditHistoryPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceaudithistory.vo.SourceauditHistorySaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.sourceaudithistory.SourceauditHistoryDO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.sourceinfo.SourceInfoDO;
import cn.iocoder.yudao.module.bookstore.dal.mysql.sourceaudithistory.SourceauditHistoryMapper;
import cn.iocoder.yudao.module.infra.enums.BookStoreErrorCodeConstants;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 资源审核记录 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class SourceauditHistoryServiceImpl implements SourceauditHistoryService {

    @Resource
    private SourceauditHistoryMapper sourceauditHistoryMapper;

    @Override
    public Long createSourceauditHistory(SourceauditHistorySaveReqVO createReqVO) {
        // 插入
        SourceauditHistoryDO sourceauditHistory = BeanUtils.toBean(createReqVO, SourceauditHistoryDO.class);
        sourceauditHistory.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        sourceauditHistoryMapper.insert(sourceauditHistory);
        // 返回
        return sourceauditHistory.getId();
    }

    @Override
    public void updateSourceauditHistory(SourceauditHistorySaveReqVO updateReqVO) {
        // 校验存在
        validateSourceauditHistoryExists(updateReqVO.getId());
        // 更新
        SourceauditHistoryDO updateObj = BeanUtils.toBean(updateReqVO, SourceauditHistoryDO.class);
        sourceauditHistoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteSourceauditHistory(Long id) {
        // 校验存在
        validateSourceauditHistoryExists(id);
        // 删除
        sourceauditHistoryMapper.deleteById(id);
    }

    private void validateSourceauditHistoryExists(Long id) {
        if (sourceauditHistoryMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(BookStoreErrorCodeConstants.SOURCEAUDIT_HISTORY_NOT_EXISTS);
        }
    }

    @Override
    public SourceauditHistoryDO getSourceauditHistory(Long id) {
        return sourceauditHistoryMapper.selectById(id);
    }

    @Override
    public PageResult<SourceauditHistoryDO> getSourceauditHistoryPage(SourceauditHistoryPageReqVO pageReqVO) {
        return sourceauditHistoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SourceauditHistoryDO> getSourceauditHistoryBySourceId(Long sourceId) {
        return sourceauditHistoryMapper.selectList(new LambdaQueryWrapperX<SourceauditHistoryDO>()
                .eqIfPresent(SourceauditHistoryDO::getSourceId,sourceId)
                .orderByDesc(SourceauditHistoryDO::getId));
    }
}