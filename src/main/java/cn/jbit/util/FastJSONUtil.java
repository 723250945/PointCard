package cn.jbit.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * FastJSON序列化类
 */
public class FastJSONUtil {
    /**
     * FastJSON序列化对象
     * @param result
     * @return
     */
    public static String serialize(Object result) {
        return JSON.toJSONString(result);
    }

    /**
     * FastJSON反序列化获得对象
     * @param json 返回得json数据
     * @param clazz 普通对象得类
     * @param modelType 集合对象得类
     * @return
     */
    public static Object deserialize(String json, Class clazz, Class modelType) {
        //返回结果是List对象
        if (clazz.isAssignableFrom(List.class)) {
            return JSON.parseArray(json, modelType);
        }
        //返回结果是普通对象
        return JSON.parseObject(json, clazz);
    }
}
