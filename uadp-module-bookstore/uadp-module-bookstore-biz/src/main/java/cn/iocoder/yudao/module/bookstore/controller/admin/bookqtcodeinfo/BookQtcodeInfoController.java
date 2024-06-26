package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.BookQtcodeInfoPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.BookQtcodeInfoRespVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.ExtraBookQtcodeInfoSaveReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.SimpleBookQtcodeSourceVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodeinfo.BookQtcodeInfoDO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodesource.BookQtcodeSourceDO;
import cn.iocoder.yudao.module.bookstore.service.bookqtcodeinfo.BookQtcodeInfoService;
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
import java.util.Objects;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 图书二维码信息")
@RestController
@RequestMapping("/infra/book-qtcode-info")
@Validated
public class BookQtcodeInfoController {

    @Resource
    private BookQtcodeInfoService bookQtcodeInfoService;

/*
    @PostMapping("/create")
    @Operation(summary = "创建图书二维码信息")
    //@PreAuthorize("@ss.hasPermission('infra:book-qtcode-info:create')")
    public CommonResult<Long> createBookQtcodeInfo(@Valid @RequestBody BookQtcodeInfoSaveReqVO createReqVO) {
        return success(bookQtcodeInfoService.createBookQtcodeInfo(createReqVO));
    }
*/

    @PostMapping("/saveResources")
    @Operation(summary = "保存图书二维码信息和资源信息")
    public CommonResult<Long> saveBookQtcodeInfoAndQtcodeResource(@Valid @RequestBody ExtraBookQtcodeInfoSaveReqVO reqVO) {
        return success(bookQtcodeInfoService.saveBookQtcodeInfoAndQtcodeResource(reqVO));
    }

    @PostMapping("/updateResources")
    @Operation(summary = "更新图书二维码信息和资源信息")
    public CommonResult<Long> updateBookQtcodeInfoAndQtcodeResource(@Valid @RequestBody ExtraBookQtcodeInfoSaveReqVO reqVO) {
        return success(bookQtcodeInfoService.updateBookQtcodeInfoAndQtcodeResource(reqVO));
    }

/*
    @PutMapping("/update")
    @Operation(summary = "更新图书二维码信息")
    //@PreAuthorize("@ss.hasPermission('infra:book-qtcode-info:update')")
    public CommonResult<Boolean> updateBookQtcodeInfo(@Valid @RequestBody BookQtcodeInfoSaveReqVO updateReqVO) {
        bookQtcodeInfoService.updateBookQtcodeInfo(updateReqVO);
        return success(true);
    }
*/

    @DeleteMapping("/delete")
    @Operation(summary = "删除图书二维码信息")
    @Parameter(name = "id", description = "编号", required = true)
    //@PreAuthorize("@ss.hasPermission('infra:book-qtcode-info:delete')")
    public CommonResult<Boolean> deleteBookQtcodeInfo(@RequestParam("id") Long id) {
        bookQtcodeInfoService.deleteBookQtcodeInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得图书二维码信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('infra:book-qtcode-info:query')")
    public CommonResult<BookQtcodeInfoRespVO> getBookQtcodeInfo(@RequestParam("id") Long id) {
        BookQtcodeInfoDO bookQtcodeInfo = bookQtcodeInfoService.getBookQtcodeInfo(id);
        List<BookQtcodeSourceDO> bookQtcodeSources = bookQtcodeInfoService.selectListByDtcodeId(id);
        BookQtcodeInfoRespVO bookQtcodeInfoRespVO = BeanUtils.toBean(bookQtcodeInfo, BookQtcodeInfoRespVO.class);
        List<SimpleBookQtcodeSourceVO> simpleBookQtcodeSources = BeanUtils.toBean(bookQtcodeSources, SimpleBookQtcodeSourceVO.class);
        bookQtcodeInfoRespVO.setSimpleBookQtcodeSourceVO(simpleBookQtcodeSources);
        return success(bookQtcodeInfoRespVO);
    }

    @GetMapping("/get-bybook-qtcode")
    @Operation(summary = "根据图书编号获取二维码信息")
    @Parameter(name = "itemId", description = "项目编号", required = true)
    @Parameter(name = "bookNo", description = "图书编号", required = true)
    //@PreAuthorize("@ss.hasPermission('infra:book-qtcode-info:query')")
    public CommonResult<BookQtcodeInfoRespVO> getBookQtcodeInfoBybookNo(@RequestParam("itemId") Long itemId, @RequestParam("bookNo") Long bookNo) {
        BookQtcodeInfoDO bookQtcodeInfo = bookQtcodeInfoService.getBookQtcodeInfoByBookNo(bookNo,itemId);
        return success(BeanUtils.toBean(bookQtcodeInfo, BookQtcodeInfoRespVO.class));
    }

    @GetMapping("/get-bychapterId-qtcode")
    @Operation(summary = "根据章节编号获取二维码信息")
    @Parameter(name = "bookNo", description = "图书编号", required = true)
    @Parameter(name = "chapterId", description = "章节编号", required = true)
    @Parameter(name = "itemId", description = "项目编号", required = true)
    //@PreAuthorize("@ss.hasPermission('infra:book-qtcode-info:query')")
    public CommonResult<BookQtcodeInfoRespVO> getBookQtcodeInfoByChapterId(@RequestParam("itemId") Long itemId,
                                                                           @RequestParam("bookNo") Long bookNo,
                                                                           @RequestParam("chapterId") Long chapterId) {
        BookQtcodeInfoDO bookQtcodeInfo = bookQtcodeInfoService.getBookQtcodeInfoByChapterId(itemId,bookNo,chapterId);
        if(!Objects.isNull(bookQtcodeInfo)){
            List<BookQtcodeSourceDO> bookQtcodeSources = bookQtcodeInfoService.selectListByDtcodeId(bookQtcodeInfo.getId());
            BookQtcodeInfoRespVO bookQtcodeInfoRespVO = BeanUtils.toBean(bookQtcodeInfo, BookQtcodeInfoRespVO.class);
            List<SimpleBookQtcodeSourceVO> simpleBookQtcodeSources = BeanUtils.toBean(bookQtcodeSources, SimpleBookQtcodeSourceVO.class);
            bookQtcodeInfoRespVO.setSimpleBookQtcodeSourceVO(simpleBookQtcodeSources);
            return success(bookQtcodeInfoRespVO);
        }
        return success(BeanUtils.toBean(bookQtcodeInfo, BookQtcodeInfoRespVO.class));
    }


    @GetMapping("/page")
    @Operation(summary = "获得图书二维码信息分页")
    //@PreAuthorize("@ss.hasPermission('infra:book-qtcode-info:query')")
    public CommonResult<PageResult<BookQtcodeInfoRespVO>> getBookQtcodeInfoPage(@Valid @ParameterObject BookQtcodeInfoPageReqVO pageReqVO) {
        //查询全部数据
        if(pageReqVO.getPageNo() <= 0 ){
            pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        }
        PageResult<BookQtcodeInfoDO> pageResult = bookQtcodeInfoService.getBookQtcodeInfoPage(pageReqVO);
        //pageResult.getList().forEach(vo -> vo.setDtcodeContext(bookQtcodeInfoService.genQrCode(vo.getDtcodeAddress())));
        List<BookQtcodeInfoRespVO> bookQtcodeInfoRespVOS = CollectionUtils.convertList(pageResult.getList(), vo -> {
            List<BookQtcodeSourceDO> bookQtcodeSources = bookQtcodeInfoService.selectListByDtcodeId(vo.getId());
            List<SimpleBookQtcodeSourceVO> simpleBookQtcodeSources = BeanUtils.toBean(bookQtcodeSources, SimpleBookQtcodeSourceVO.class);
            BookQtcodeInfoRespVO bookQtcodeInfoRespVO = BeanUtils.toBean(vo, BookQtcodeInfoRespVO.class);
            if (simpleBookQtcodeSources != null) {
                bookQtcodeInfoRespVO.setSimpleBookQtcodeSourceVO(simpleBookQtcodeSources);
            }
            return bookQtcodeInfoRespVO;
        });
        return success(new PageResult<>(bookQtcodeInfoRespVOS, pageResult.getTotal()));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出图书二维码信息 Excel")
    //@PreAuthorize("@ss.hasPermission('infra:book-qtcode-info:export')")
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