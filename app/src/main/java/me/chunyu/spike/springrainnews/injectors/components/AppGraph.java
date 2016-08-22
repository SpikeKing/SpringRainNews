package me.chunyu.spike.springrainnews.injectors.components;

import me.chunyu.spike.springrainnews.injectors.modules.AppScope;
import me.chunyu.spike.springrainnews.uis.activities.MainActivity;
import me.chunyu.spike.springrainnews.uis.adapters.MainListAdapter;

/**
 * App的类图
 * <p>
 * Created by wangchenlong on 16/1/21.
 */
public interface AppGraph {
    void inject(MainActivity mainActivity); // 注入MainActivity

    void inject(MainListAdapter mainListAdapter); // 注入MainListAdapter
}
