package cn.iocoder.yudao.module.bookstore.config;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.infra.convert.Dict;
import cn.iocoder.yudao.module.system.dal.dataobject.dict.DictDataDO;
import cn.iocoder.yudao.module.system.dal.mysql.dict.DictDataMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class DictConfiguration {
 
    /**
     * 使用个缓存，避免列表数据多次进行字典查询
     */
    private static ThreadLocal<Map<String,Map<String,String>>> cache  = ThreadLocal.withInitial(ConcurrentHashMap::new);
 
    //mapper查询数据库，查询字典数字对应的文字
    @Resource
    private DictDataMapper dictDataMapper;

    private static DictConfiguration dictUtil;

    @PostConstruct
    public void init() {
        dictUtil = this;
        dictUtil.dictDataMapper = this.dictDataMapper;
    }


    public static Object parseResult(Object result) {
        //判断空
        if (Objects.isNull(result)) {
            return null;
        }
        try {
            if (!(result instanceof CommonResult)) {
                parseDict(result);
                return result;
            }
            //此处应该都是CommonResult, 出现异常应该catch住，不能影响主流程
            CommonResult commonResult = (CommonResult) result;
            Object data = commonResult.getData();
            //判断结果类型
            if (data instanceof List) {
                //LIST类型
                List<Object> list = Lists.newArrayList();
                for (Object obj : (List<Object>) data) {
                    list.add(parseDict(obj));
                }
                commonResult.setData(list);
                return commonResult;
            } else if (data instanceof PageResult) {
                //自定义的分页返回结果集类型  实际结果在 list字段中。 处理和LIST一致
                PageResult tableDataInfo = (PageResult) data;
                List<Object> list = Lists.newArrayList();
                for (Object obj : tableDataInfo.getList()) {
                    list.add(parseDict(obj));
                }
                //分页数据中 重新放入结果
                tableDataInfo.setList(list);
                commonResult.setData(tableDataInfo);
                return commonResult;
            } else if (data instanceof PageResult) {
                //自定义的分页返回结果集类型  实际结果在 list字段中。 处理和LIST一致
                PageResult tableDataInfo = (PageResult) data;
                List<Object> list = Lists.newArrayList();
                for (Object obj : tableDataInfo.getList()) {
                    list.add(parseDict(obj));
                }
                //分页数据中 重新放入结果
                tableDataInfo.setList(list);
                commonResult.setData(tableDataInfo);
                return commonResult;
            } else {
                //单实例对象
                commonResult.setData(parseDict(data));
                return commonResult;
            }
        }catch (Exception e){
            //出现异常应该catch住，不能影响主流程
            log.error("转换字典值为描述信息失败：",e);
            return result;
        }
    }
 
    /**
     * 字典转换
     *
     * @param obj
     */
    private static Object parseDict(Object obj) throws NoSuchFieldException, IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        //非空判断
        if (ObjectUtil.isEmpty(fields)) {
            return null;
        }
        for (Field field : fields) {
            field.setAccessible(true);
            Object key = field.getName();
            if(StringUtils.equals("children",String.valueOf(key)) && !Objects.isNull(field.get(obj))){
                List lists = (List)field.get(obj);
                lists.forEach(e -> {
                    parseResult(e);
                });
            }
            //判断每一个字典是否有Dict注解
            if (Objects.nonNull(field.getAnnotation(Dict.class))) {
                handleDict(obj, field);
            }
        }
        return obj;
    }
 
 
    /**
     * 处理字典注解的字段
     *
     * @param obj
     * @param field
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static void handleDict(Object obj, Field field) throws NoSuchFieldException, IllegalAccessException {
        Dict dict = field.getAnnotation(Dict.class);
        String dictTypeName = dict.dictTypeName();
        String suffix = dict.suffix();
        Map<String, String> dictitemValMap = getDictmap(dictTypeName);
        Field name = obj.getClass().getDeclaredField(field.getName() + suffix);
        field.setAccessible(true);
        Object key = field.get(obj);
        if(!Objects.isNull(key)){
            name.setAccessible(true);
            String value = dictitemValMap.get(key.toString());
            if(StringUtils.isNotBlank(value)){
                name.set(obj, value);
            }
        }
        name.setAccessible(false);
        field.setAccessible(false);
    }
 
    private static Map<String, String> getDictmap(String dictTypeName) {
        Map<String, Map<String, String>> dictMap = cache.get();
        if(dictMap.containsKey(dictTypeName)){
            return dictMap.get(dictTypeName);
        }
        List<DictDataDO> dictDataDOS = dictUtil.dictDataMapper.selectList(DictDataDO::getDictType, dictTypeName);
        Map<String, String> dictItemMap = new HashMap<>();
        if (CollectionUtils.isEmpty(dictDataDOS)) {
            return dictItemMap;
        }
        for (DictDataDO dictDataDO : dictDataDOS) {
            dictItemMap.put(dictDataDO.getValue(), dictDataDO.getLabel());
        }
        dictMap.put(dictTypeName,dictItemMap);
        cache.set(dictMap);
        return dictItemMap;
    }
 
 
    /**
     * 清除缓存
     */
    public static void clearCache(){
        cache.remove();
    }
 
}