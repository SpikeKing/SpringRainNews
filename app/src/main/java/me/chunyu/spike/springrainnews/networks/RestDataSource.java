/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package me.chunyu.spike.springrainnews.networks;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import me.chunyu.spike.springrainnews.BuildConfig;
import me.chunyu.spike.springrainnews.mvp.models.AvengersCharacter;
import me.chunyu.spike.springrainnews.networks.deserializers.MarvelResultsDeserializer;
import me.chunyu.spike.springrainnews.networks.interceptors.MarvelSigningInterceptor;
import me.chunyu.spike.springrainnews.networks.services.MarvelService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;

public class RestDataSource {

    private final MarvelService mMarvelService;

    public RestDataSource() {
//        OkHttpClient client = new OkHttpClient().Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        MarvelSigningInterceptor signingInterceptor = new MarvelSigningInterceptor(
                BuildConfig.MARVEL_PUBLIC_KEY, BuildConfig.MARVEL_PRIVATE_KEY);

//        client.interceptors().add(signingInterceptor);
//        client.interceptors().add(loggingInterceptor);
//        client.newBuilder().addInterceptor(signingInterceptor).build();
//        client.newBuilder().addInterceptor(loggingInterceptor).build();

        // OkHttp3.0的使用方式
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(signingInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();

        Gson customGsonInstance = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<List<AvengersCharacter>>() {
                        }.getType(),
                        new MarvelResultsDeserializer<AvengersCharacter>())
                .create();

        Retrofit marvelApiAdapter = new Retrofit.Builder()
                .baseUrl(MarvelService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create(customGsonInstance))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        mMarvelService = marvelApiAdapter.create(MarvelService.class);
    }

    public Observable<List<AvengersCharacter>> getCharacters(int currentOffset) {
        return mMarvelService.getCharacters(currentOffset)
                .doOnError(throwable -> {
                    Log.e("DEBUG-WCL: ", throwable.getMessage());
                });
    }
}
