package cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.BookChapterRespVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.H5BookChapterRespVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.H5BookRespVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookchapter.BookChapterDO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookinfo.BookInfoDO;
import cn.iocoder.yudao.module.bookstore.service.bookchapter.BookChapterService;
import cn.iocoder.yudao.module.bookstore.service.bookinfo.BookInfoService;
import cn.iocoder.yudao.module.bookstore.util.TreeUtil;
import cn.iocoder.yudao.module.infra.convert.DictCovert;
import cn.iocoder.yudao.module.infra.convert.UserCovert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - H5-图书章节")
@RestController
@RequestMapping("/infra/h5-book-chapter")
@Validated
@DictCovert
@UserCovert
public class H5BookChapterController {

    @Resource
    private BookChapterService bookChapterService;

    @Resource
    private BookInfoService bookInfoService;

    @GetMapping("/h5-get")
    @Operation(summary = "获得图书章节")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('infra:book-chapter:query')")
    public CommonResult<H5BookChapterRespVO> getBookChapter(@RequestParam("id") Long id) {
        BookChapterDO bookChapter = bookChapterService.getBookChapter(id);
        return success(BeanUtils.toBean(bookChapter, H5BookChapterRespVO.class));
    }

    @GetMapping("/h5-book-detail")
    @Operation(summary = "获得图书详情")
    @Parameter(name = "book_no", description = "编号", required = true)
    //@PreAuthorize("@ss.hasPermission('infra:book-chapter:query')")
    public CommonResult<H5BookRespVO> getBookDetail(@RequestParam("book_no") Long book_no) {
        BookInfoDO bookInfo = bookInfoService.getBookInfo(book_no);
        return success(BeanUtils.toBean(bookInfo, H5BookRespVO.class));
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