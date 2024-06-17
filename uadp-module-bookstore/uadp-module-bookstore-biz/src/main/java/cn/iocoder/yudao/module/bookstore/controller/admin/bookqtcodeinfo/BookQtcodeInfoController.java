package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.BookQtcodeInfoPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.BookQtcodeInfoRespVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.BookQtcodeInfoSaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodeinfo.BookQtcodeInfoDO;
import cn.iocoder.yudao.module.bookstore.service.bookqtcodeinfo.BookQtcodeInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 图书二维码信息")
@RestController
@RequestMapping("/infra/book-qtcode-info")
@Validated
public class BookQtcodeInfoController {

    @Resource
    private BookQtcodeInfoService bookQtcodeInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建图书二维码信息")
    @PreAuthorize("@ss.hasPermission('infra:book-qtcode-info:create')")
    public CommonResult<Long> createBookQtcodeInfo(@Valid @RequestBody BookQtcodeInfoSaveReqVO createReqVO) {
        return success(bookQtcodeInfoService.createBookQtcodeInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新图书二维码信息")
    @PreAuthorize("@ss.hasPermission('infra:book-qtcode-info:update')")
    public CommonResult<Boolean> updateBookQtcodeInfo(@Valid @RequestBody BookQtcodeInfoSaveReqVO updateReqVO) {
        bookQtcodeInfoService.updateBookQtcodeInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除图书二维码信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('infra:book-qtcode-info:delete')")
    public CommonResult<Boolean> deleteBookQtcodeInfo(@RequestParam("id") Long id) {
        bookQtcodeInfoService.deleteBookQtcodeInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得图书二维码信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('infra:book-qtcode-info:query')")
    public CommonResult<BookQtcodeInfoRespVO> getBookQtcodeInfo(@RequestParam("id") Long id) {
        BookQtcodeInfoDO bookQtcodeInfo = bookQtcodeInfoService.getBookQtcodeInfo(id);
        bookQtcodeInfo.setDtcodeContext(bookQtcodeInfoService.genQrCode(bookQtcodeInfo.getDtcodeAddress()));
        return success(BeanUtils.toBean(bookQtcodeInfo, BookQtcodeInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得图书二维码信息分页")
    @PreAuthorize("@ss.hasPermission('infra:book-qtcode-info:query')")
    public CommonResult<PageResult<BookQtcodeInfoRespVO>> getBookQtcodeInfoPage(@Valid @ParameterObject BookQtcodeInfoPageReqVO pageReqVO) {
        //查询全部数据
        if(pageReqVO.getPageNo() <= 0 ){
            pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        }
        PageResult<BookQtcodeInfoDO> pageResult = bookQtcodeInfoService.getBookQtcodeInfoPage(pageReqVO);
        pageResult.getList().forEach(vo -> {
            vo.setDtcodeContext(bookQtcodeInfoService.genQrCode(vo.getDtcodeAddress()));
        });
        return success(BeanUtils.toBean(pageResult, BookQtcodeInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出图书二维码信息 Excel")
    @PreAuthorize("@ss.hasPermission('infra:book-qtcode-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBookQtcodeInfoExcel(@Valid BookQtcodeInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BookQtcodeInfoDO> list = bookQtcodeInfoService.getBookQtcodeInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "图书二维码信息.xls", "数据", BookQtcodeInfoRespVO.class,
                        BeanUtils.toBean(list, BookQtcodeInfoRespVO.class));
    }

}