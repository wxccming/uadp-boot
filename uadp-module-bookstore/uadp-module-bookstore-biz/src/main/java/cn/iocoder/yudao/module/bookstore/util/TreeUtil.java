package cn.iocoder.yudao.module.bookstore.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookchapter.BookChapterDO;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class TreeUtil {

    /**
     * 对象List转为Tree树形结构
     *
     * @param entityList       传进来的泛型List
     * @param primaryFieldName 主键名称
     * @param parentFieldName  父级字段名称
     * @return
     */
    public static List<Map<String, Object>> listToTree(List<Map<String, Object>> entityList, String primaryFieldName, String parentFieldName) {
        //返回的map Tree树形结构
        List<Map<String, Object>> treeMap = new ArrayList<>();
        //将传进的参数entityList转为MapList
        List<Map<String, Object>> listMap = JSON.parseObject(JSON.toJSONString(entityList), List.class);
        //声明一个map用来存listMap中的对象，key为对象id，value为对象本身
        Map<String, Map<String, Object>> entityMap = new Hashtable<>();
        //循环listMap把map对象put到entityMap中去
        listMap.forEach(map -> entityMap.put(map.get(primaryFieldName).toString(), map));
        //循环listMap进行Tree树形结构组装
        listMap.forEach(map -> {
            //获取map的pid
            Object pid = map.get(parentFieldName);
            //判断pid是否为空或者为0，为空说明是最顶级，直接add到返回的treeMap中去
            if (pid == null || StringUtils.equals(pid.toString(), "0")) {
                treeMap.add(map);
            } else {
                //如果pid不为空也不为0，是子集
                // 根据当前map的pid获取上级 parentMap
                Map<String, Object> parentMap = entityMap.get(pid.toString());
                if (parentMap == null) { //如果parentMap为空，则说明当前map没有父级，当前map就是顶级
                    treeMap.add(map);
                } else {
                    //如果parentMap不为空，则当前map为parentMap的子级
                    //取出parentMap的所有子级的List集合
                    List<Map<String, Object>> children = (List<Map<String, Object>>) parentMap.get("children");
                    if (children == null) {  //判断子级集合是否为空，为空则新创建List
                        children = new ArrayList<>();
                        parentMap.put("children", children);
                    }
                    //把当前map对象add到parentMap的子级List中去
                    children.add(map);
                    /**
                     * 因为parentMap是从entityMap中get出来的，
                     * 而entityMap中的value又是来自于listMap对象，
                     * 所以parentMap和entityMap中的value的地址都是指向listMap中的对象，
                     * 所以parentMap的children和entityMap中的value的children改变时，都会改变listMap中的对象，
                     * 这里涉及到了地址、指针，就不多说了。
                     */
                }
            }
        });
        return treeMap;
    }


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
        return listToTree(lists,"id", "chapterPid");
    }
}
