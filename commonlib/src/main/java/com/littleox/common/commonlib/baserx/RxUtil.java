package com.littleox.common.commonlib.baserx;

import android.text.TextUtils;

import com.littleox.common.commonlib.R;
import com.littleox.common.commonlib.baseapp.BaseApplication;
import com.littleox.common.commonlib.basebean.BaseResponse;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by codeest on 2016/8/3.
 */
public class RxUtil
{

    /**
     * 统一线程处理调度
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper()
    {    //compose简化线程
        return new FlowableTransformer<T, T>()
        {
            @Override
            public Flowable<T> apply(Flowable<T> flowable)
            {
                return flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 没有基类  直接处理返回结果
     *
     * @param <T>
     */
    public static <T> FlowableTransformer<T, T> handleResponseResult()
    {   //compose判断结果
        return new FlowableTransformer<T, T>()
        {
            @Override
            public Publisher<T> apply(Flowable<T> response)
            {
                return response.flatMap(new Function<T, Publisher<T>>()
                {
                    @Override
                    public Publisher<T> apply(T t) throws Exception
                    {
                        if (t != null)
                        {
                            return createData(t);
                        }
                        else
                        {
                            return Flowable.error(new AppException(BaseApplication.getAppContext().getString(R.string.server_excception), "-1"));
                        }
                    }
                });
            }
        };
    }


    /**
     * 返回三个个参数(result)的 返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<BaseResponse<T>, T> handleBaseResult()
    {   //compose判断结果
        return new FlowableTransformer<BaseResponse<T>, T>()
        {
            @Override
            public Flowable<T> apply(Flowable<BaseResponse<T>> httpResponseFlowable)
            {
                return httpResponseFlowable.flatMap(new Function<BaseResponse<T>, Flowable<T>>()
                {
                    @Override
                    public Flowable<T> apply(BaseResponse<T> result)
                    {
                        if (result.success())
                        {//返回有数据的结果
                            return createData(result.getData());
                        }
                        else if (!TextUtils.equals(result.getCode(), "1000"))
                        {
                            return Flowable.error(new AppException(result.getCode(), result.getCode()));
                        }
                        else
                        {
                            return Flowable.error(new AppException(BaseApplication.getAppContext().getString(R.string.server_excception), result.getCode()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 自定义 -- 创建
     *
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t)
    {
        return Flowable.create(new FlowableOnSubscribe<T>()
        {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception
            {
                try
                {
                    emitter.onNext(t);
                    emitter.onComplete();
                }
                catch (Exception e)
                {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);//BUFFER--缓存策略，LATEST,DROP--需要什么就发射什么数据
    }

    /** 倒计时操作 **/
    public static Observable<Integer> countdown(int time)
    {
        if (time < 0)
        {
            time = 0;
        }

        final int countTime = time;
        return Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread()).map(increaseTime -> countTime - increaseTime.intValue()).take(countTime + 1);
    }

}
