package cn.iocoder.yudao.module.bookstore.config;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.infra.convert.UserId;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.user.AdminUserMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class UserIdConfiguration {
 
    /**
     * 使用个缓存
     */
    private static ThreadLocal<Map<String,String>> cache  = ThreadLocal.withInitial(ConcurrentHashMap::new);
 
    //mapper查询数据库，查询字典数字对应的文字
    @Resource
    private AdminUserMapper adminUserMapper;

    private static UserIdConfiguration dictUtil;

    @PostConstruct
    public void init() {
        dictUtil = this;
        dictUtil.adminUserMapper = this.adminUserMapper;
    }


    public static Object parseResult(Object result) throws Exception {
        //判断空
        if (Objects.isNull(result)) {
            return null;
        }
        if (!(result instanceof CommonResult)) {
            return result;
        }
        //此处应该都是CommonResult
        try {
            CommonResult commonResult = (CommonResult) result;
            Object data = commonResult.getData();
            //判断结果类型
            if (data instanceof List) {
                //LIST类型
                List<Object> list = Lists.newArrayList();
                for (Object obj : (List<Object>) data) {
                    list.add(parseUserId(obj));
                }
                commonResult.setData(list);
                return commonResult;
            } else if (data instanceof PageResult) {
                //自定义的分页返回结果集类型  实际结果在 list字段中。 处理和LIST一致
                PageResult tableDataInfo = (PageResult) data;
                List<Object> list = Lists.newArrayList();
                for (Object obj : tableDataInfo.getList()) {
                    list.add(parseUserId(obj));
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
                    list.add(parseUserId(obj));
                }
                //分页数据中 重新放入结果
                tableDataInfo.setList(list);
                commonResult.setData(tableDataInfo);
                return commonResult;
            } else {
                //单实例对象
                commonResult.setData(parseUserId(data));
                return commonResult;
            }
        }catch (Exception e){
             //出现异常应该catch住，不能影响主流程
            log.error("将userId转为名称失败：",e);
            return result;
        }
    }
 
    /**
     * 字典转换
     *
     * @param obj
     */
    private static Object parseUserId(Object obj) throws NoSuchFieldException, IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        //非空判断
        if (ObjectUtil.isEmpty(fields)) {
            return null;
        }
        for (Field field : fields) {
            //判断每一个字典是否有Dict注解
            if (Objects.nonNull(field.getAnnotation(UserId.class))) {
                handleUserId(obj, field);
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
    private static void handleUserId(Object obj, Field field) throws NoSuchFieldException, IllegalAccessException {
        Field name = obj.getClass().getDeclaredField(field.getName());
        field.setAccessible(true);
        Object key = field.get(obj);
        name.setAccessible(true);
        if(!Objects.isNull(key) && StringUtils.isNotBlank(key.toString())){
            Map<String,String> userIdMap = cache.get();
            if(userIdMap.containsKey(key.toString())){
                name.set(obj, userIdMap.get(key.toString()));
            }else{
                AdminUserDO adminUserDO = dictUtil.adminUserMapper.selectById(Long.parseLong(key.toString()));
                if(!Objects.isNull(adminUserDO)){
                    name.set(obj, adminUserDO.getNickname());
                    cache.get().put(key.toString(),adminUserDO.getNickname());
                }
            }
        }
        name.setAccessible(false);
        field.setAccessible(false);
    }

    /**
     * 清除缓存
     */
    public static void clearCache(){
        cache.remove();
    }
 
}