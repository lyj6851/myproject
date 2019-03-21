package com.hhf.common.utils;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author huhaifeng
 * Json工具类
 */
public class JacksonUtils {

    private static Logger logger = LoggerFactory.getLogger(JacksonUtils.class);
    private static ObjectMapper objectMapper = null;
    public static final String DATEFORMAT_YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    private JacksonUtils() {
        throw new IllegalAccessError("工具类不能通过构造器初始化！");
    }

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, false);
        objectMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.enable(new MapperFeature[]{MapperFeature.SORT_PROPERTIES_ALPHABETICALLY});
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return objectMapper;
    }

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            String[] beanNames = null;
            if (SpringContextHolder.getApplicationContext() != null) {
                beanNames = SpringContextHolder.getBeanNamesForType(ObjectMapper.class);
            }

            if (beanNames != null && beanNames.length > 0) {
                logger.debug("使用Spring容器中的ObjectMapper:{}", beanNames[0]);
                objectMapper = (ObjectMapper)SpringContextHolder.getBean(beanNames[0], ObjectMapper.class);
            } else {
                logger.debug("使用JacksonUtils中的ObjectMapper");
                objectMapper = getDefaultObjectMapper();
            }
        }

        return objectMapper;
    }

    public static ObjectMapper getDefaultMapperCopy() {
        return getObjectMapper().copy();
    }

    public static String obj2json(Object obj) {
        try {
            return getObjectMapper().writeValueAsString(obj);
        } catch (Exception var2) {
            throw new RuntimeException("对象转JSON时发生异常:" + var2.getMessage(), var2);
        }
    }

    public static String obj2json(Object obj, DateFormat df) {
        try {
            ObjectMapper newObjectMapper = getObjectMapper().copy();
            return newObjectMapper.writer(df).writeValueAsString(obj);
        } catch (Exception var3) {
            throw new RuntimeException("对象转JSON时发生异常:" + var3.getMessage(), var3);
        }
    }

    public static <T> T json2pojo(String jsonStr, Class<T> clazz) {
        try {
            return getObjectMapper().readValue(jsonStr, clazz);
        } catch (Exception var3) {
            throw new RuntimeException("JSON 转对象时发生异常:" + var3.getMessage() + ",json报文：" + jsonStr, var3);
        }
    }

    public static <T> T json2pojo(String jsonStr, TypeReference<T> typeReference) {
        try {
            return getObjectMapper().readValue(jsonStr, typeReference);
        } catch (Exception var3) {
            throw new RuntimeException("JSON 转对象时发生异常:" + var3.getMessage() + ",json报文：" + jsonStr, var3);
        }
    }

    public static Map<String, Object> json2map(String jsonStr) {
        try {
            return (Map)getObjectMapper().readValue(jsonStr, Map.class);
        } catch (Exception var2) {
            throw new RuntimeException("JSON 转Map<String,Object>时发生异常:" + var2.getMessage() + ",json报文：" + jsonStr, var2);
        }
    }

    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) {
        try {
            Map<String, Map<String, Object>> map = (Map)getObjectMapper().readValue(jsonStr, new TypeReference<Map<String, T>>() {
            });
            if (CollectionUtils.isEmpty(map)) {
                return Collections.emptyMap();
            } else {
                Map<String, T> result = new HashMap(map.size());
                Iterator var4 = map.entrySet().iterator();

                while(var4.hasNext()) {
                    Map.Entry<String, Map<String, Object>> entry = (Map.Entry)var4.next();
                    result.put(entry.getKey(), map2pojo((Map)entry.getValue(), clazz));
                }

                return result;
            }
        } catch (Exception var6) {
            throw new RuntimeException("JSON 转Map时发生异常:" + var6.getMessage() + ",json报文：" + jsonStr, var6);
        }
    }

    public static List<Map<String, Object>> json2listWithMap(String jsonArrayStr) {
        try {
            List<Map<String, Object>> list = (List)getObjectMapper().readValue(jsonArrayStr, new TypeReference<List<Map<String, Object>>>() {
            });
            return list;
        } catch (Exception var2) {
            throw new RuntimeException("JSON 转List时发生异常:" + var2.getMessage() + ",json报文：" + jsonArrayStr, var2);
        }
    }

    public static <T> List<T> json2listWithBean(String jsonArrayStr, Class<T> clazz) {
        try {
            List list;
            if (!ClassUtils.isPrimitiveOrWrapper(clazz) && !ClassUtils.isAssignable(String.class, clazz) && !ClassUtils.isAssignable(List.class, clazz) && !ClassUtils.isAssignable(Set.class, clazz) && !ClassUtils.isAssignable(Map.class, clazz)) {
                list = (List)getObjectMapper().readValue(jsonArrayStr, new TypeReference<List<T>>() {
                });
                List<T> result = new ArrayList();
                Iterator var4 = list.iterator();

                while(var4.hasNext()) {
                    Map<String, Object> map = (Map)var4.next();
                    result.add(map2pojo(map, clazz));
                }

                return result;
            } else {
                list = (List)getObjectMapper().readValue(jsonArrayStr, new TypeReference<List<T>>() {
                });
                return list;
            }
        } catch (Exception var6) {
            throw new RuntimeException("JSON 转List时发生异常:" + var6.getMessage() + ",json报文：" + jsonArrayStr, var6);
        }
    }

    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return getObjectMapper().convertValue(map, clazz);
    }
}
