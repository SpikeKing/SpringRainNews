package me.chunyu.spike.springrainnews.mvp.models;

import java.util.List;

import rx.Observable;

/**
 * 请求的库
 * <p>
 * Created by wangchenlong on 16/1/21.
 */
public interface Repository {
    Observable<List<AvengersCharacter>> getCharacters(int offset);
}
