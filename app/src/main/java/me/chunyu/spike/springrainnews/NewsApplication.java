package me.chunyu.spike.springrainnews;

import android.app.Application;

import me.chunyu.spike.springrainnews.injectors.components.AppComponent;
import me.chunyu.spike.springrainnews.injectors.components.AppGraph;

/**
 * 项目应用
 * <p>
 * Created by wangchenlong on 16/1/21.
 */
public class NewsApplication extends Application {
    private static NewsApplication sApplication;
    private static AppGraph sAppGraph;

    @Override public void onCreate() {
        super.onCreate();
        sApplication = this;
        buildComponentAndInject();
    }

    public static AppGraph component() {
        return sAppGraph;
    }

    // 构建组件和注入
    private static void buildComponentAndInject() {
        sAppGraph = AppComponent.Initializer.init(sApplication);
    }
}
