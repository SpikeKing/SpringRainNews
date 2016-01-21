package me.chunyu.spike.springrainnews.mvp.views;

import java.util.List;

import me.chunyu.spike.springrainnews.mvp.models.AvengersCharacter;

/**
 * 主页的Views
 * <p>
 * Created by wangchenlong on 16/1/21.
 */
public interface MainView extends BaseView {
    void setListData(List<AvengersCharacter> characters);
}
