package me.chunyu.spike.springrainnews.mvp.presenters;

import javax.inject.Inject;

import me.chunyu.spike.springrainnews.mvp.models.Repository;
import me.chunyu.spike.springrainnews.mvp.views.BaseView;
import me.chunyu.spike.springrainnews.mvp.views.MainView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 主页的展示逻辑
 * <p>
 * Created by wangchenlong on 16/1/21.
 */
public class MainPresenter implements BasePresenter {

    private final Repository mRepository; // 网络库

    private MainView mMainView; // 主页

    private Subscription mCharactersSubscription; // 订阅成员

    @Inject
    public MainPresenter(Repository repository) {
        mRepository = repository;
    }

    @Override public void onCreate() {
        loadData();
    }

    private void loadData() {
        mCharactersSubscription = mRepository.getCharacters(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(avengersCharacters -> {
                    mMainView.setListData(avengersCharacters);
                });
    }

    @Override public void onResume() {

    }

    @Override public void onStop() {
        mCharactersSubscription.unsubscribe();
    }

    @Override public void onDestroy() {

    }

    @Override public void attachView(BaseView v) {
        mMainView = (MainView) v; // 绑定视图
    }



}
