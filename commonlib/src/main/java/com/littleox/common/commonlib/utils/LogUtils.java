package com.littleox.common.commonlib.utils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * 如果用于android平台，将信息记录到“LogCat”。如果用于java平台，将信息记录到“Console”
 * 使用logger封装
 */
public class LogUtils
{
    public static boolean DEBUG_ENABLE = true;// 是否调试模式

    /**
     * 在application调用初始化
     */
    public static void logInit(boolean debug)
    {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder().showThreadInfo(false)  // 是否显示线程信息，默认为ture
                .methodCount(0)
                .tag("commonApp")   // 每个日志的全局标记。默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        DEBUG_ENABLE = debug;
    }

    public static void logd(String message, Object... args)
    {
        if (DEBUG_ENABLE)
        {
            Logger.d(message, args);
        }
    }

    public static void logd(Object message)
    {
        if (DEBUG_ENABLE)
        {
            Logger.d(message);
        }
    }

    public static void loge(Throwable throwable, String message, Object... args)
    {
        if (DEBUG_ENABLE)
        {
            Logger.e(throwable, message, args);
        }
    }

    public static void loge(String message, Object... args)
    {
        if (DEBUG_ENABLE)
        {
            Logger.e(message, args);
        }
    }

    public static void logi(String message, Object... args)
    {
        if (DEBUG_ENABLE)
        {
            Logger.i(message, args);
        }
    }

    public static void logv(String message, Object... args)
    {
        if (DEBUG_ENABLE)
        {
            Logger.v(message, args);
        }
    }

    public static void logw(String message, Object... args)
    {
        if (DEBUG_ENABLE)
        {
            Logger.v(message, args);
        }
    }

    public static void logwtf(String message, Object... args)
    {
        if (DEBUG_ENABLE)
        {
            Logger.wtf(message, args);
        }
    }

    public static void logjson(String message)
    {
        if (DEBUG_ENABLE)
        {
            Logger.json(message);
        }
    }

    public static void logxml(String message)
    {
        if (DEBUG_ENABLE)
        {
            Logger.xml(message);
        }
    }
}
