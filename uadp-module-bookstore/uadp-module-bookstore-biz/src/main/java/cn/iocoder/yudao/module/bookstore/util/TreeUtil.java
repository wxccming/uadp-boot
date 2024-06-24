package cn.iocoder.yudao.module.bookstore.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.iocoder.yudao.framework.common.util.collection.TreeUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo.BookChapterRespVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookchapter.BookChapterDO;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class TreeUtil {
    /**
     * 对象List转为Tree树形结构
     *
     * @param entityList       传进来的泛型List
     * @return
     */
    public static List<BookChapterRespVO> listToTree(List<BookChapterDO> entityList) {
        List<Map<String, Object>> lists = new ArrayList<>();
        List<BookChapterRespVO> result = new ArrayList<>();
        entityList.forEach(bookChapterDO ->{
            JSONObject entries = JSONUtil.parseObj(bookChapterDO);
            lists.add(entries);
        });
        List<Map<String, Object>> maps = TreeUtils.listToTree(lists, "id", "chapterPid");
        maps.forEach(e -> {
            result.add(BeanUtils.toBean(e, BookChapterRespVO.class));
        });
        return result;
    }
}
