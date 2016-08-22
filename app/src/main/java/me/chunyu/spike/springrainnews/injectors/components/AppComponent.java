package me.chunyu.spike.springrainnews.injectors.components;

import javax.inject.Singleton;

import dagger.Component;
import me.chunyu.spike.springrainnews.NewsApplication;
import me.chunyu.spike.springrainnews.injectors.modules.AppModule;
import me.chunyu.spike.springrainnews.injectors.modules.AppScope;

/**
 * App的组件
 * <p>
 * Created by wangchenlong on 16/1/21.
 */
@Singleton
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
