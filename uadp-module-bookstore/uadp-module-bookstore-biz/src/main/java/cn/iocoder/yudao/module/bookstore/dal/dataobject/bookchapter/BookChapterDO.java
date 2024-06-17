package cn.iocoder.yudao.module.bookstore.dal.dataobject.bookchapter;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 图书章节 DO
 *
 * @author 管理员
 */
@TableName("infra_book_chapter")
@KeySequence("infra_book_chapter_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookChapterDO extends BaseDO {

    /**
     * 章节编号
     */
    @TableId
    private Long id;
    /**
     * 章节名称
     */
    private String chapterName;
    /**
     * 父节点ID(上级章节ID)章节名称
     */
    private String chapterPid;
    /**
     * 父章节名称
     */
    private String chapterPtitle;
    /**
     * 图书编号
     */
    private Long bookNo;
    /**
     * 章节等级
     */
    private Long depth;
    /**
     * 是否最小节点
     */
    private String isLeaf;
    /**
     * 排序
     */
    private String chapterSeq;
    /**
     * 状态
     */
    private String chapterStatus;

    /**
     * 部门标识
     */
    private Long deptId;

}