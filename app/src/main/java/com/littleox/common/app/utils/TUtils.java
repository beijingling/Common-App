package com.littleox.common.app.utils;

import com.littleox.common.commonlib.utils.LogUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by lingbeijing on 2020/9/2.
 */
public class TUtils {
    public static <T> T getT(Object obj, int index) {
        try {
            ParameterizedType type = (ParameterizedType) obj.getClass().getGenericSuperclass();
            Type actualTypeArgument = type.getActualTypeArguments()[index];
            return ((Class<T>) actualTypeArgument).newInstance();
        } catch (IllegalAccessException e) {
            LogUtils.loge("TUtils", e);
        } catch (InstantiationException e) {
            LogUtils.loge("TUtils", e);
        } catch (ClassCastException e){
            LogUtils.loge("TUtils", e);
        }
        return null;
    }
}
