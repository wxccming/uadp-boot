package cn.iocoder.yudao.module.system.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.iocoder.yudao.framework.common.util.collection.TreeUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.controller.admin.dept.vo.dept.DeptSimpleRespVO;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListConvertTreeUtil {
    /**
     * 对象List转为Tree树形结构 (DeptDO)
     *
     * @param entityList       传进来的泛型List
     * @return
     */
    public static List<DeptSimpleRespVO> listToTree(List<DeptDO> entityList) {
        List<Map<String, Object>> lists = new ArrayList<>();
        entityList.forEach(bookChapterDO ->{
            JSONObject entries = JSONUtil.parseObj(bookChapterDO);
            lists.add(entries);
        });
        List<Map<String, Object>> maps =  TreeUtils.listToTree(lists,"id", "parentId");
        List<DeptSimpleRespVO> result = new ArrayList<>();
        maps.forEach(e -> {
            result.add(BeanUtils.toBean(e, DeptSimpleRespVO.class));
        });
        return result;
    }
}
