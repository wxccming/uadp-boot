package cn.iocoder.yudao.module.bookstore.service.sourceaudithistory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceaudithistory.vo.SourceauditHistoryPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceaudithistory.vo.SourceauditHistorySaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.sourceaudithistory.SourceauditHistoryDO;

import jakarta.validation.Valid;
import java.util.List;

/**
 * 资源审核记录 Service 接口
 *
 * @author 超级管理员
 */
public interface SourceauditHistoryService {

    /**
     * 创建资源审核记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSourceauditHistory(@Valid SourceauditHistorySaveReqVO createReqVO);

    /**
     * 更新资源审核记录
     *
     * @param updateReqVO 更新信息
     */
    void updateSourceauditHistory(@Valid SourceauditHistorySaveReqVO updateReqVO);

    /**
     * 删除资源审核记录
     *
     * @param id 编号
     */
    void deleteSourceauditHistory(Long id);

    /**
     * 获得资源审核记录
     *
     * @param id 编号
     * @return 资源审核记录
     */
    SourceauditHistoryDO getSourceauditHistory(Long id);

    /**
     * 获得资源审核记录分页
     *
     * @param pageReqVO 分页查询
     * @return 资源审核记录分页
     */
    PageResult<SourceauditHistoryDO> getSourceauditHistoryPage(SourceauditHistoryPageReqVO pageReqVO);

    /**
     * 根据资源ID获得资源审核记录分页
     *
     * @param pageReqVO 分页查询
     * @return 资源审核记录分页
     */
    List<SourceauditHistoryDO> getSourceauditHistoryBySourceId(Long sourceId);

}