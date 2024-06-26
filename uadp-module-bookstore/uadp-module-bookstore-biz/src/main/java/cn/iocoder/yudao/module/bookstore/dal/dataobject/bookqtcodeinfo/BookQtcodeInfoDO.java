package cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodeinfo;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 图书二维码信息 DO
 *
 * @author 管理员
 */
@TableName("infra_book_qtcode_info")
@KeySequence("infra_book_qtcode_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookQtcodeInfoDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 二维码名称
     */
    private String dtcodeName;
    /**
     * 所属项目编号
     */
    private Long itemId;
    /**
     * 二维码地址
     */
    private String dtcodeAddress;
    /**
     * 二维码内容(图片流)
     */
    private String dtcodeContext;
    /**
     * 二维码分类
     */
    private String dtcodeCategory;
    /**
     * 二维码编号
     */
//    private String dtcodeId;
    /**
     * 章节编号
     */
    private Long chapterId;

    /**
     * 图书编号
     */
    private Long bookNo;

    /**
     * 部门标识
     */
    private Long deptId;
    /**
     * 适用场景
     */
//    private String applicaScens;

}