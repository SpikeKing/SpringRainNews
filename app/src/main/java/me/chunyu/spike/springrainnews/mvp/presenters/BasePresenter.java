package me.chunyu.spike.springrainnews.mvp.presenters;

import me.chunyu.spike.springrainnews.mvp.views.BaseView;

/**
 * 展示页面
 * <p>
 * Created by wangchenlong on 16/1/21.
 */
@SuppressWarnings("EmptyMethod")
public interface BasePresenter {
    void onCreate();

    void onResume();

    void onStop();

    void onDestroy();

    void attachView(BaseView v);
}
