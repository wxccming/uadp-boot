package cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.BookChapterPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.BookChapterReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.BookChapterRespVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.BookChapterSaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookchapter.BookChapterDO;
import cn.iocoder.yudao.module.bookstore.service.bookchapter.BookChapterService;
import cn.iocoder.yudao.module.bookstore.util.TreeUtil;
import cn.iocoder.yudao.module.infra.convert.DictCovert;
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
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 图书章节")
@RestController
@RequestMapping("/infra/book-chapter")
@Validated
//@DictCovert
public class BookChapterController {

    @Resource
    private BookChapterService bookChapterService;

    @PostMapping("/create")
    @Operation(summary = "创建图书章节")
    //@PreAuthorize("@ss.hasPermission('infra:book-chapter:create')")
    public CommonResult<Long> createBookChapter(@Valid @RequestBody BookChapterSaveReqVO createReqVO) {
        return success(bookChapterService.createBookChapter(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新图书章节")
    //@PreAuthorize("@ss.hasPermission('infra:book-chapter:update')")
    public CommonResult<Boolean> updateBookChapter(@Valid @RequestBody BookChapterSaveReqVO updateReqVO) {
        bookChapterService.updateBookChapter(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除图书章节")
    @Parameter(name = "id", description = "编号", required = true)
    //@PreAuthorize("@ss.hasPermission('infra:book-chapter:delete')")
    public CommonResult<Boolean> deleteBookChapter(@RequestParam("id") Long id) {
        bookChapterService.deleteBookChapter(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得图书章节")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('infra:book-chapter:query')")
    public CommonResult<BookChapterRespVO> getBookChapter(@RequestParam("id") Long id) {
        BookChapterDO bookChapter = bookChapterService.getBookChapter(id);
        return success(BeanUtils.toBean(bookChapter, BookChapterRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得图书章节分页")
    //@PreAuthorize("@ss.hasPermission('infra:book-chapter:query')")
    public CommonResult<PageResult<BookChapterRespVO>> getBookChapterPage(@Valid @ParameterObject BookChapterPageReqVO pageReqVO) {
        //查询全部数据
        if(pageReqVO.getPageNo() <= 0 ){
            pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        }
        PageResult<BookChapterDO> pageResult = bookChapterService.getBookChapterPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BookChapterRespVO.class));
    }

    @GetMapping("/page-tree")
    @Operation(summary = "获得图书章节,以树型展示，分页接口-后续废弃")
    public CommonResult<PageResult<BookChapterRespVO>> getBookChapterPageTree(@Valid @ParameterObject BookChapterPageReqVO pageReqVO) {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BookChapterDO> bookChapters = bookChapterService.getBookChapterPage(pageReqVO).getList();
        List<BookChapterRespVO> result = TreeUtil.listToTree(bookChapters);
        PageResult<BookChapterRespVO> mapPageResult = new PageResult<>(result, (long) result.size());
        return success(mapPageResult);
    }

    @GetMapping("/book-tree")
    @Operation(summary = "获得某本图书下所有章节,以树型展示")
    public CommonResult<List<BookChapterRespVO>> getAllBookChapterTree(@Valid @RequestParam("bookNo") Long bookNo) {
        List<BookChapterDO> bookChapters = bookChapterService.getBookChapterList(bookNo);
        bookChapters.sort(Comparator.comparing(BookChapterDO::getId));
        List<BookChapterRespVO> result = TreeUtil.listToTree(bookChapters);
        return success(BeanUtils.toBean(result,BookChapterRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出图书章节 Excel")
    //@PreAuthorize("@ss.hasPermission('infra:book-chapter:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBookChapterExcel(@Valid BookChapterPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BookChapterDO> list = bookChapterService.getBookChapterPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "图书章节.xls", "数据", BookChapterRespVO.class,
                        BeanUtils.toBean(list, BookChapterRespVO.class));
    }

}