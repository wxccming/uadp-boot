package cn.iocoder.yudao.module.bookstore.dal.dataobject.sourceaudithistory;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 资源审核记录 DO
 *
 * @author 超级管理员
 */
@TableName("infra_sourceaudit_history")
@KeySequence("infra_sourceaudit_history_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SourceauditHistoryDO extends BaseDO {

    /**
     * 自增编号
     */
    @TableId
    private Long id;
    /**
     * 资源编号
     */
    private Long sourceId;
    /**
     * 资源名称
     */
    private String sourceName;
    /**
     * 审核人
     */
    private String auditor;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 审核状态
     */
    private String auditStatus;
    /**
     * 审核意见
     */
    private String auditMsg;
    /**
     * 部门ID
     */
    private Long deptId;

}