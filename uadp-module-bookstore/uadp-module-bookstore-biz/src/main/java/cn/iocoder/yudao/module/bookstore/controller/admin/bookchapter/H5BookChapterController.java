package cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.BookChapterPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.BookChapterRespVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.BookChapterSaveReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.H5BookChapterRespVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookchapter.BookChapterDO;
import cn.iocoder.yudao.module.bookstore.service.bookchapter.BookChapterService;
import cn.iocoder.yudao.module.bookstore.util.TreeUtil;
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
import java.util.Comparator;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - H5-图书章节")
@RestController
@RequestMapping("/infra/h5-book-chapter")
@Validated
@DictCovert
public class H5BookChapterController {

    @Resource
    private BookChapterService bookChapterService;

    @GetMapping("/h5-get")
    @Operation(summary = "获得图书章节")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('infra:book-chapter:query')")
    public CommonResult<H5BookChapterRespVO> getBookChapter(@RequestParam("id") Long id) {
        BookChapterDO bookChapter = bookChapterService.getBookChapter(id);
        return success(BeanUtils.toBean(bookChapter, H5BookChapterRespVO.class));
    }

    @GetMapping("/h5-book-tree")
    @Operation(summary = "获得某本图书下所有章节,以树型展示")
    public CommonResult<List<H5BookChapterRespVO>> getAllBookChapterTree(@Valid @RequestParam("bookNo") Long bookNo) {
        List<BookChapterDO> bookChapters = bookChapterService.getBookChapterList(bookNo);
        bookChapters.sort(Comparator.comparing(BookChapterDO::getId));
        List<BookChapterRespVO> result = TreeUtil.listToTree(bookChapters);
        return success(BeanUtils.toBean(result,H5BookChapterRespVO.class));
    }

}