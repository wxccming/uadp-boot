package cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodeitem;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 二维码项目 DO
 *
 * @author 管理员
 */
@TableName("infra_book_qtcode_item")
@KeySequence("infra_book_qtcode_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookQtcodeItemDO extends BaseDO {

    /**
     * 项目编号
     */
    @TableId
    private Long id;
    /**
     * 项目编号
     */
//    private String itemId;
    /**
     * 项目名称
     */
    private String itemName;
    /**
     * 资源来源
     */
    private String sourceOrigin;
    /**
     * 图书编号
     */
    private Long bookNo;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 状态
     */
    private String dtcodeStatus;

    /**
     * 部门标识
     */
    private Long deptId;
}