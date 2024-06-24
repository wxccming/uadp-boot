package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource.vo.BookQtcodeSourcePageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource.vo.BookQtcodeSourceRespVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource.vo.BookQtcodeSourceSaveReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource.vo.H5BookQtcodeSourceRespVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodesource.BookQtcodeSourceDO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodesource.H5BookQtcodeSourceDO;
import cn.iocoder.yudao.module.bookstore.service.bookqtcodesource.BookQtcodeSourceService;
import cn.iocoder.yudao.module.infra.convert.DictCovert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - H5-二维码接口")
@RestController
@RequestMapping("/infra/h5-book-qtcode-source")
@Validated
@DictCovert
public class H5BookQtcodeSourceController {

    @Resource
    private BookQtcodeSourceService bookQtcodeSourceService;

    @GetMapping("/h5-get")
    @Operation(summary = "获得二维码资源")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('infra:book-qtcode-source:query')")
    public CommonResult<BookQtcodeSourceRespVO> getH5BookQtcodeSource(@RequestParam("id") Long id) {
        BookQtcodeSourceDO bookQtcodeSource = bookQtcodeSourceService.getBookQtcodeSource(id);
        return success(BeanUtils.toBean(bookQtcodeSource, BookQtcodeSourceRespVO.class));
    }


    @GetMapping("/h5-list-source")
    @Operation(summary = "根据章节编号获取资源信息")
    //@PreAuthorize("@ss.hasPermission('infra:book-qtcode-source:query')")
    public CommonResult<List<H5BookQtcodeSourceRespVO>> getH5BookQtcodeSourcePage(@Valid @RequestParam("chapter_id") Long chapter_id) {
        List<H5BookQtcodeSourceDO> h5BookQtcodeSourceS = bookQtcodeSourceService.selectQtSourceList(chapter_id);
        return success(BeanUtils.toBean(h5BookQtcodeSourceS, H5BookQtcodeSourceRespVO.class));
    }
}