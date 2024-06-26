package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource.vo.BookQtcodeSourceRespVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource.vo.H5BookQtcodeSourceRespVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodesource.BookQtcodeSourceDO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodesource.H5BookQtcodeSourceDO;
import cn.iocoder.yudao.module.bookstore.service.bookqtcodesource.BookQtcodeSourceService;
import cn.iocoder.yudao.module.infra.convert.DictCovert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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
    @Operation(summary = "根据章节编号、适用场景获取资源信息")
    @Parameter(name = "item_id", description = "项目编号", required = true)
    @Parameter(name = "applica_scens", description = "场景", required = false)
    @Parameter(name = "chapter_id", description = "图书编号", required = true)
    //@PreAuthorize("@ss.hasPermission('infra:book-qtcode-source:query')")
    public CommonResult<List<H5BookQtcodeSourceRespVO>> getH5BookQtcodeSourcePage(@Valid @RequestParam("item_id") Long item_id,
                                                                                  @RequestParam("chapter_id") Long chapter_id,
                                                                                  @RequestParam(value="applica_scens",required=false) String applica_scens) {
        List<H5BookQtcodeSourceDO> h5BookQtcodeSourceS = bookQtcodeSourceService.selectQtSourceList(item_id,chapter_id,applica_scens);
        return success(BeanUtils.toBean(h5BookQtcodeSourceS, H5BookQtcodeSourceRespVO.class));
    }
}