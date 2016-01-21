/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package me.chunyu.spike.springrainnews.mvp.presenters;

import me.chunyu.spike.springrainnews.mvp.views.BaseView;

/**
 * 展示页面
 * <p>
 * Created by wangchenlong on 16/1/21.
 */
public interface BasePresenter {
    void onCreate();

    void onResume();

    void onStop();

    void onDestroy();

    void attachView(BaseView v);
}
