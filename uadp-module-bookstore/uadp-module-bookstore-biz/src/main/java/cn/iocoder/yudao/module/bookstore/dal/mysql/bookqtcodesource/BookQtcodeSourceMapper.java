package cn.iocoder.yudao.module.bookstore.dal.mysql.bookqtcodesource;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource.vo.BookQtcodeSourcePageReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodesource.BookQtcodeSourceDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 二维码资源 Mapper
 *
 * @author 管理员
 */
@Mapper
public interface BookQtcodeSourceMapper extends BaseMapperX<BookQtcodeSourceDO> {

    default PageResult<BookQtcodeSourceDO> selectPage(BookQtcodeSourcePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BookQtcodeSourceDO>()
                .likeIfPresent(BookQtcodeSourceDO::getSourceName, reqVO.getSourceName())
                .eqIfPresent(BookQtcodeSourceDO::getSourceId, reqVO.getSourceId())
                .eqIfPresent(BookQtcodeSourceDO::getSourceForm, reqVO.getSourceForm())
                .eqIfPresent(BookQtcodeSourceDO::getApplicaScens, reqVO.getApplicaScens())
                .betweenIfPresent(BookQtcodeSourceDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(BookQtcodeSourceDO::getDtcodeId, reqVO.getDtcodeId())
                .eqIfPresent(BookQtcodeSourceDO::getBookNo, reqVO.getBookNo())
                .eqIfPresent(BookQtcodeSourceDO::getChapterId, reqVO.getChapterId())
                .orderByDesc(BookQtcodeSourceDO::getId));
    }

    default List<BookQtcodeSourceDO> selectListByDtcodeId(Long dtcodeId) {
        return selectList(BookQtcodeSourceDO::getDtcodeId, dtcodeId);
    }

    @Delete("DELETE FROM infra_book_qtcode_source WHERE dtcode_id = #{id}")
    Integer deleteByDtcodeId(@Param("id") Long id);
}