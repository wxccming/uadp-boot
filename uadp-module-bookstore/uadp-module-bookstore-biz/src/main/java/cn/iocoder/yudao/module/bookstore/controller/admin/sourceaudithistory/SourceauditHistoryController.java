package cn.iocoder.yudao.module.bookstore.controller.admin.sourceaudithistory;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceaudithistory.vo.SourceauditHistoryPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceaudithistory.vo.SourceauditHistoryRespVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.sourceaudithistory.vo.SourceauditHistorySaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.sourceaudithistory.SourceauditHistoryDO;
import cn.iocoder.yudao.module.bookstore.service.sourceaudithistory.SourceauditHistoryService;
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


@Tag(name = "管理后台 - 资源审核记录")
@RestController
@RequestMapping("/infra/sourceaudit-history")
@Validated
public class SourceauditHistoryController {

    @Resource
    private SourceauditHistoryService sourceauditHistoryService;

    @PostMapping("/create")
    @Operation(summary = "创建资源审核记录")
    @PreAuthorize("@ss.hasPermission('infra:sourceaudit-history:create')")
    public CommonResult<Long> createSourceauditHistory(@Valid @RequestBody SourceauditHistorySaveReqVO createReqVO) {
        return success(sourceauditHistoryService.createSourceauditHistory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资源审核记录")
    @PreAuthorize("@ss.hasPermission('infra:sourceaudit-history:update')")
    public CommonResult<Boolean> updateSourceauditHistory(@Valid @RequestBody SourceauditHistorySaveReqVO updateReqVO) {
        sourceauditHistoryService.updateSourceauditHistory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资源审核记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('infra:sourceaudit-history:delete')")
    public CommonResult<Boolean> deleteSourceauditHistory(@RequestParam("id") Long id) {
        sourceauditHistoryService.deleteSourceauditHistory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资源审核记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('infra:sourceaudit-history:query')")
    public CommonResult<SourceauditHistoryRespVO> getSourceauditHistory(@RequestParam("id") Long id) {
        SourceauditHistoryDO sourceauditHistory = sourceauditHistoryService.getSourceauditHistory(id);
        return success(BeanUtils.toBean(sourceauditHistory, SourceauditHistoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资源审核记录分页")
    @PreAuthorize("@ss.hasPermission('infra:sourceaudit-history:query')")
    public CommonResult<PageResult<SourceauditHistoryRespVO>> getSourceauditHistoryPage(@Valid @ParameterObject SourceauditHistoryPageReqVO pageReqVO) {
        //查询全部数据
        if(pageReqVO.getPageNo() <= 0 ){
            pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        }
        PageResult<SourceauditHistoryDO> pageResult = sourceauditHistoryService.getSourceauditHistoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SourceauditHistoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资源审核记录 Excel")
    @PreAuthorize("@ss.hasPermission('infra:sourceaudit-history:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSourceauditHistoryExcel(@Valid SourceauditHistoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SourceauditHistoryDO> list = sourceauditHistoryService.getSourceauditHistoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资源审核记录.xls", "数据", SourceauditHistoryRespVO.class,
                        BeanUtils.toBean(list, SourceauditHistoryRespVO.class));
    }

}