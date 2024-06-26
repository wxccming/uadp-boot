package cn.iocoder.yudao.module.bookstore.config;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.infra.convert.Dict;
import cn.iocoder.yudao.module.infra.convert.User;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.dept.DeptMapper;
import cn.iocoder.yudao.module.system.dal.mysql.user.AdminUserMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class UserInfoConvertConfiguration {
 
    /**
     * 使用个缓存
     */
    private static ThreadLocal<Map<String,String>> cache  = ThreadLocal.withInitial(ConcurrentHashMap::new);
 
    //mapper查询数据库，查询字典数字对应的文字
    @Resource
    private AdminUserMapper adminUserMapper;

    @Resource
    private DeptMapper deptMapper;

    private static UserInfoConvertConfiguration dictUtil;

    @PostConstruct
    public void init() {
        dictUtil = this;
        dictUtil.adminUserMapper = this.adminUserMapper;
        dictUtil.deptMapper = this.deptMapper;
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
            //判断每一个字典是否有User注解
            if (Objects.nonNull(field.getAnnotation(User.class))) {
                handleUser(obj, field);
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
    private static void handleUser(Object obj, Field field) throws NoSuchFieldException, IllegalAccessException {
        User user = field.getAnnotation(User.class);
        String typeName = user.type();
        Field name = obj.getClass().getDeclaredField(field.getName());
        field.setAccessible(true);
        Object key = field.get(obj);
        name.setAccessible(true);
        if(!Objects.isNull(key) && StringUtils.isNotBlank(key.toString())){
            Map<String,String> userMap = cache.get();
            String cache_key = typeName+"_"+key;
            if(userMap.containsKey(cache_key)){
                name.set(obj, userMap.get(cache_key));
            }else{
                if(StringUtils.equalsIgnoreCase("dept_id",typeName)){
                    DeptDO deptDO = dictUtil.deptMapper.selectById(Long.parseLong(key.toString()));
                    if(!Objects.isNull(deptDO)){
                        name.set(obj, deptDO.getName());
                        cache.get().put(cache_key,deptDO.getName());
                    }
                }else if(StringUtils.equalsIgnoreCase("user_id",typeName)){
                    AdminUserDO adminUserDO = dictUtil.adminUserMapper.selectById(Long.parseLong(key.toString()));
                    if(!Objects.isNull(adminUserDO)){
                        name.set(obj, adminUserDO.getNickname());
                        cache.get().put(cache_key,adminUserDO.getNickname());
                    }
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