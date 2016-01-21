package me.chunyu.spike.springrainnews.injectors.modules;

import android.app.Application;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.chunyu.spike.springrainnews.NewsApplication;

/**
 * App的模块
 * <p>
 * Created by wangchenlong on 16/1/21.
 */
@Module
public class AppModule {
    private final NewsApplication mApplication;

    public AppModule(NewsApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    protected Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    protected Resources provideResources() {
        return mApplication.getResources();
    }
}
