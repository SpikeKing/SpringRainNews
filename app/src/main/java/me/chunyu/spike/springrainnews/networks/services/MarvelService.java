package me.chunyu.spike.springrainnews.networks.services;

import java.util.List;

import me.chunyu.spike.springrainnews.mvp.models.AvengersCharacter;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 网络请求
 * <p>
 * Created by wangchenlong on 16/1/21.
 */
public interface MarvelService {
    String END_POINT = "http://gateway.marvel.com/";
    String PARAM_API_KEY = "apikey";
    String PARAM_HASH = "hash";
    String PARAM_TIMESTAMP = "ts";

    @GET("/v1/public/characters")
    Observable<List<AvengersCharacter>> getCharacters(@Query("offset") int offset);
}