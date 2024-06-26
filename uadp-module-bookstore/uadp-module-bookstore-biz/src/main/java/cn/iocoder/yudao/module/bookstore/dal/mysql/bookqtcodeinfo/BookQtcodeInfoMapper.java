package cn.iocoder.yudao.module.bookstore.dal.mysql.bookqtcodeinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.BookQtcodeInfoPageReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodeinfo.BookQtcodeInfoDO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodesource.H5BookQtcodeSourceDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 图书二维码信息 Mapper
 *
 * @author 管理员
 */
@Mapper
public interface BookQtcodeInfoMapper extends BaseMapperX<BookQtcodeInfoDO> {

    default PageResult<BookQtcodeInfoDO> selectPage(BookQtcodeInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BookQtcodeInfoDO>()
                .likeIfPresent(BookQtcodeInfoDO::getDtcodeName, reqVO.getDtcodeName())
                .eqIfPresent(BookQtcodeInfoDO::getItemId, reqVO.getItemId())
                .eqIfPresent(BookQtcodeInfoDO::getDtcodeAddress, reqVO.getDtcodeAddress())
                .eqIfPresent(BookQtcodeInfoDO::getDtcodeContext, reqVO.getDtcodeContext())
                .betweenIfPresent(BookQtcodeInfoDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(BookQtcodeInfoDO::getDtcodeCategory, reqVO.getDtcodeCategory())
                .eqIfPresent(BookQtcodeInfoDO::getChapterId, reqVO.getChapterId())
                .eqIfPresent(BookQtcodeInfoDO::getBookNo, reqVO.getBookNo())
                .orderByDesc(BookQtcodeInfoDO::getId));
    }

    default BookQtcodeInfoDO selectQtByChapterId(Long chapterId) {
        return selectOne(BookQtcodeInfoDO::getChapterId, chapterId);
    }
    BookQtcodeInfoDO selectQtByBookNo(@Param("book_no") Long book_no,@Param("item_id") Long item_id);

}