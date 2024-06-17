package cn.iocoder.yudao.module.bookstore.dal.mysql.sourceaudithistory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceaudithistory.vo.SourceauditHistoryPageReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.sourceaudithistory.SourceauditHistoryDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资源审核记录 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface SourceauditHistoryMapper extends BaseMapperX<SourceauditHistoryDO> {

    default PageResult<SourceauditHistoryDO> selectPage(SourceauditHistoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SourceauditHistoryDO>()
                .eqIfPresent(SourceauditHistoryDO::getSourceId, reqVO.getSourceId())
                .likeIfPresent(SourceauditHistoryDO::getSourceName, reqVO.getSourceName())
                .eqIfPresent(SourceauditHistoryDO::getAuditor, reqVO.getAuditor())
                .betweenIfPresent(SourceauditHistoryDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(SourceauditHistoryDO::getAuditStatus, reqVO.getAuditStatus())
                .eqIfPresent(SourceauditHistoryDO::getAuditMsg, reqVO.getAuditMsg())
                .betweenIfPresent(SourceauditHistoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SourceauditHistoryDO::getId));
    }

}