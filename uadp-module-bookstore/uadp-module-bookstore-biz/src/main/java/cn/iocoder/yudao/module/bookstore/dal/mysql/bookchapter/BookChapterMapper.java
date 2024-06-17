package cn.iocoder.yudao.module.bookstore.dal.mysql.bookchapter;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.BookChapterPageReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookchapter.BookChapterDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图书章节 Mapper
 *
 * @author 管理员
 */
@Mapper
public interface BookChapterMapper extends BaseMapperX<BookChapterDO> {

    default PageResult<BookChapterDO> selectPage(BookChapterPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BookChapterDO>()
                .likeIfPresent(BookChapterDO::getChapterName, reqVO.getChapterName())
                .eqIfPresent(BookChapterDO::getChapterPid, reqVO.getChapterPid())
                .eqIfPresent(BookChapterDO::getChapterPtitle, reqVO.getChapterPtitle())
                .eqIfPresent(BookChapterDO::getBookNo, reqVO.getBookNo())
                .eqIfPresent(BookChapterDO::getDepth, reqVO.getDepth())
                .eqIfPresent(BookChapterDO::getIsLeaf, reqVO.getIsLeaf())
                .eqIfPresent(BookChapterDO::getChapterSeq, reqVO.getChapterSeq())
                .eqIfPresent(BookChapterDO::getChapterStatus, reqVO.getChapterStatus())
                .betweenIfPresent(BookChapterDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BookChapterDO::getId));
    }

}