package me.chunyu.spike.springrainnews.injectors.components;

import dagger.Component;
import me.chunyu.spike.springrainnews.NewsApplication;
import me.chunyu.spike.springrainnews.injectors.modules.AppModule;

/**
 * App的组件
 * <p>
 * Created by wangchenlong on 16/1/21.
 */
@Component(modules = AppModule.class)
public interface AppComponent extends AppGraph {
    final class Initializer {
        private Initializer() {
        } // No instances.

        // 初始化组件
        public static AppComponent init(NewsApplication app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .build();
        }
    }
}
