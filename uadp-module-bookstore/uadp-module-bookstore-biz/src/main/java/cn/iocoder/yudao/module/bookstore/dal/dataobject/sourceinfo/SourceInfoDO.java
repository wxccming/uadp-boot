package cn.iocoder.yudao.module.bookstore.dal.dataobject.sourceinfo;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 资源信息 DO
 *
 * @author 管理员
 */
@TableName("infra_source_info")
@KeySequence("infra_source_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SourceInfoDO extends BaseDO {

    /**
     * 资源编号
     */
    @TableId
    private Long id;
    /**
     * 资源编号
     */
//    private String sourceId;
    /**
     * 资源名称
     */
    private String sourceName;
    /**
     * 资源形式
     */
    private String sourceForm;
    /**
     * 资源来源
     */
    private String sourceOrigin;
    /**
     * 资源地址
     */
    private String sourceUrl;
    /**
     * 资源状态
     */
    private String sourceStatus;
    /**
     * 学段
     */
    private String period;
    /**
     * 年级
     */
    private String grade;
    /**
     * 学科
     */
    private String subject;
    /**
     * 版本
     */
    private String edition;
    /**
     * 册次
     */
    private String volume;
    /**
     * 章节
     */
    private String chapter;
    /**
     * 知识编目
     */
    private String sourceKnnm;
    /**
     * 适用对象
     */
    private String applicaObjects;
    /**
     * 适用场景
     */
    private String applicaScens;
    /**
     * 格式
     */
    private String format;
    /**
     * 文件大小
     */
    private Integer size;
    /**
     * 大小单位
     */
    private String sizeUnit;
    /**
     * 标签
     */
    private String lableTag;
    /**
     * 备注
     */
    private String remaks;
    /**
     * 存储地址
     */
    private String storageAddress;
    /**
     * 备用字段1
     */
    private String spareFiled1;
    /**
     * 备用字段2
     */
    private String spareFiled2;
    /**
     * 上传用户(与创建人一致)
     */
    private String uploadUserId;
    /**
     * 审核用户1
     */
    private String auditUser1Id;
    /**
     * 审核用户2
     */
    private String auditUser2Id;

    /**
     * 审核状态(0-未审核	|1-审核不通过|	2-审核通过)
     */
    private String auditStatus;

    /**
     * 部门标识
     */
    private Long deptId;
}