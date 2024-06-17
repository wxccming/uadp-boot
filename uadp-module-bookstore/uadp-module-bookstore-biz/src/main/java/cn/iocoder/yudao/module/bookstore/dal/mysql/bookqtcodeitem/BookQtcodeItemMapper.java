package cn.iocoder.yudao.module.bookstore.dal.mysql.bookqtcodeitem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeitem.vo.BookQtcodeItemPageReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodeitem.BookQtcodeItemDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 二维码项目 Mapper
 *
 * @author 管理员
 */
@Mapper
public interface BookQtcodeItemMapper extends BaseMapperX<BookQtcodeItemDO> {

    default PageResult<BookQtcodeItemDO> selectPage(BookQtcodeItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BookQtcodeItemDO>()
                .likeIfPresent(BookQtcodeItemDO::getItemName, reqVO.getItemName())
                .eqIfPresent(BookQtcodeItemDO::getSourceOrigin, reqVO.getSourceOrigin())
                .eqIfPresent(BookQtcodeItemDO::getBookNo, reqVO.getBookNo())
                .eqIfPresent(BookQtcodeItemDO::getRemarks, reqVO.getRemarks())
                .eqIfPresent(BookQtcodeItemDO::getDtcodeStatus, reqVO.getDtcodeStatus())
                .betweenIfPresent(BookQtcodeItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BookQtcodeItemDO::getId));
    }

}