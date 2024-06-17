package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeitem;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeitem.vo.BookQtcodeItemPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeitem.vo.BookQtcodeItemRespVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeitem.vo.BookQtcodeItemSaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodeitem.BookQtcodeItemDO;
import cn.iocoder.yudao.module.bookstore.service.bookqtcodeitem.BookQtcodeItemService;
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

@Tag(name = "管理后台 - 二维码项目")
@RestController
@RequestMapping("/infra/book-qtcode-item")
@Validated
public class BookQtcodeItemController {

    @Resource
    private BookQtcodeItemService bookQtcodeItemService;

    @PostMapping("/create")
    @Operation(summary = "创建二维码项目")
    @PreAuthorize("@ss.hasPermission('infra:book-qtcode-item:create')")
    public CommonResult<Long> createBookQtcodeItem(@Valid @RequestBody BookQtcodeItemSaveReqVO createReqVO) {
        return success(bookQtcodeItemService.createBookQtcodeItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新二维码项目")
    @PreAuthorize("@ss.hasPermission('infra:book-qtcode-item:update')")
    public CommonResult<Boolean> updateBookQtcodeItem(@Valid @RequestBody BookQtcodeItemSaveReqVO updateReqVO) {
        bookQtcodeItemService.updateBookQtcodeItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除二维码项目")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('infra:book-qtcode-item:delete')")
    public CommonResult<Boolean> deleteBookQtcodeItem(@RequestParam("id") Long id) {
        bookQtcodeItemService.deleteBookQtcodeItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得二维码项目")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('infra:book-qtcode-item:query')")
    public CommonResult<BookQtcodeItemRespVO> getBookQtcodeItem(@RequestParam("id") Long id) {
        BookQtcodeItemDO bookQtcodeItem = bookQtcodeItemService.getBookQtcodeItem(id);
        return success(BeanUtils.toBean(bookQtcodeItem, BookQtcodeItemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得二维码项目分页")
    @PreAuthorize("@ss.hasPermission('infra:book-qtcode-item:query')")
    public CommonResult<PageResult<BookQtcodeItemRespVO>> getBookQtcodeItemPage(@Valid @ParameterObject BookQtcodeItemPageReqVO pageReqVO) {
        //查询全部数据
        if(pageReqVO.getPageNo() <= 0 ){
            pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        }
        PageResult<BookQtcodeItemDO> pageResult = bookQtcodeItemService.getBookQtcodeItemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BookQtcodeItemRespVO.class));
    }

    @GetMapping("/page-all")
    @Operation(summary = "获得二维码项目所有数据")
    @PreAuthorize("@ss.hasPermission('infra:book-qtcode-item:query')")
    public CommonResult<PageResult<BookQtcodeItemRespVO>> getAllBookQtcodeItem(@Valid @ParameterObject BookQtcodeItemPageReqVO pageReqVO) {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<BookQtcodeItemDO> pageResult = bookQtcodeItemService.getBookQtcodeItemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BookQtcodeItemRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出二维码项目 Excel")
    @PreAuthorize("@ss.hasPermission('infra:book-qtcode-item:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBookQtcodeItemExcel(@Valid BookQtcodeItemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BookQtcodeItemDO> list = bookQtcodeItemService.getBookQtcodeItemPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "二维码项目.xls", "数据", BookQtcodeItemRespVO.class,
                        BeanUtils.toBean(list, BookQtcodeItemRespVO.class));
    }

}