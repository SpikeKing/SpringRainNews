package me.chunyu.spike.springrainnews.injectors.modules;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.chunyu.spike.springrainnews.NewsApplication;
import me.chunyu.spike.springrainnews.mvp.models.Repository;
import me.chunyu.spike.springrainnews.networks.RestDataSource;

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
    protected Context provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    protected Resources provideResources() {
        return mApplication.getResources();
    }

    @Provides
    @Singleton
    protected Repository provideDataRepository(RestDataSource restDataSource) {
        return restDataSource;
    }
}
