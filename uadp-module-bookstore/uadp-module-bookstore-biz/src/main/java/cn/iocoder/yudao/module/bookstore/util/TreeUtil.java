package cn.iocoder.yudao.module.bookstore.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.iocoder.yudao.framework.common.util.collection.TreeUtils;
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
    public static List<Map<String, Object>> listToTree(List<BookChapterDO> entityList) {
        List<Map<String, Object>> lists = new ArrayList<>();
        entityList.forEach(bookChapterDO ->{
            JSONObject entries = JSONUtil.parseObj(bookChapterDO);
            lists.add(entries);
        });
        return TreeUtils.listToTree(lists,"id", "chapterPid");
    }
}
