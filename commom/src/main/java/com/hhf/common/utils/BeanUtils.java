package com.hhf.common.utils;

import com.hhf.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 扩展spring的BeanUtils
 * @author xman
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtils.class);

    /**
     * 对象属性拷贝，支持忽略groovy 类的class和metaClass属性
     *
     * @param source           源对象
     * @param target           目标对象
     */
    public static void copyProperties(Object source, Object target) {
        copyProperties(source, target, new String[]{"class","metaClass"});
    }

    /**
     * 对象属性拷贝，支持忽略groovy 类的class和metaClass属性
     *
     * @param source           源对象
     * @param target           目标对象
     * @param ignoreProperties 需要忽略的属性名称
     */
    public static void copyProperties(Object source, Object target, String[] ignoreProperties) {
        if ((source == null) || (target == null)) {
            return;
        }
        //对groovy对象中的特殊属性class和metaClass过滤
        List<String> ignoreList = new ArrayList();
        if(ignoreProperties == null || ignoreProperties.length == 0){
            ignoreList.addAll(CollectionUtils.arrayToList(new String[]{"class","metaClass"}));
        }else{
            ignoreList.addAll(CollectionUtils.arrayToList(ignoreProperties));
            ignoreList.add("class");
            ignoreList.add("metaClass");
        }
        String[] newIgnoreProperties = new String[ignoreList.size()];
        newIgnoreProperties = (String[]) ignoreList.toArray(newIgnoreProperties);
        org.springframework.beans.BeanUtils.copyProperties(source, target, newIgnoreProperties);
    }

    /**
     * map转为javabean
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) throws IllegalAccessException, InstantiationException {
        if (map == null) {
            return null;
        }

        T obj = beanClass.newInstance();

        Field[] fields = beanClass.getDeclaredFields();
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            //忽略static属性和final属性
            if(Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)){
                continue;
            }

            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, obj, map.get(field.getName()));
        }

        return obj;
    }

    /**
     *  转换集合
     * @param sourceList 要转换的集合
     * @param clazz
     * @param <E> 要转换的集合类型
     * @param <T> 转换后的集合类型
     * @return
     */
    public static <E,T> List<T> copyList(Collection<E> sourceList, Class<T> clazz){
        if(CollectionUtils.isEmpty(sourceList)){
            return Collections.<T> emptyList();
        }
        List<T> list = new ArrayList<>(sourceList.size());
        sourceList.forEach(
                it -> {
                    try {
                        T target = clazz.newInstance();
                        BeanUtils.copyProperties(it, target);
                        list.add(target);
                    } catch (Exception e) {
                        LOGGER.error("创建类型:{}失败"+clazz.getSimpleName());
                        throw new BusinessException("创建类型: "+ clazz.getSimpleName() + "失败");
                    }
                }
        );
        return list;
    }
    /**
     * javabean转为map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if(obj == null){
            return null;
        }

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<>(declaredFields.length);

        for (Field field : declaredFields) {
            ReflectionUtils.makeAccessible(field);
            map.put(field.getName(), ReflectionUtils.getField(field, obj));
        }

        return map;
    }

    /**
     * 对象属性拷贝,忽略空值
     *
     * @param source           源对象
     * @param target           目标对象
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        if ((source == null) || (target == null)) {
            return;
        }
        copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * 获取对象空值的属性
     *
     * @param source           对象
     */
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null||"".equals(srcValue)) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}