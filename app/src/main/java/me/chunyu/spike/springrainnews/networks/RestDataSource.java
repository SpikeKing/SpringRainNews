package me.chunyu.spike.springrainnews.networks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import javax.inject.Inject;

import me.chunyu.spike.springrainnews.BuildConfig;
import me.chunyu.spike.springrainnews.mvp.models.AvengersCharacter;
import me.chunyu.spike.springrainnews.mvp.models.Repository;
import me.chunyu.spike.springrainnews.networks.deserializers.MarvelResultsDeserializer;
import me.chunyu.spike.springrainnews.networks.interceptors.MarvelSigningInterceptor;
import me.chunyu.spike.springrainnews.networks.services.MarvelService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * 数据源
 *
 * @author wangchenlong
 */
public class RestDataSource implements Repository {

    private final MarvelService mMarvelService;

    @Inject
    public RestDataSource() {
        // Log信息
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        // 公私密匙
        MarvelSigningInterceptor signingInterceptor = new MarvelSigningInterceptor(
                BuildConfig.MARVEL_PUBLIC_KEY, BuildConfig.MARVEL_PRIVATE_KEY);

        // OkHttp3.0的使用方式
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(signingInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();

        // 选择人物信息
        Gson customGsonInstance = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<List<AvengersCharacter>>() {
                        }.getType(),
                        new MarvelResultsDeserializer<AvengersCharacter>())
                .create();

        // 适配器
        Retrofit marvelApiAdapter = new Retrofit.Builder()
                .baseUrl(MarvelService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create(customGsonInstance))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        // 服务
        mMarvelService = marvelApiAdapter.create(MarvelService.class);
    }

    // 返回人物信息
    @Override
    public Observable<List<AvengersCharacter>> getCharacters(int currentOffset) {
        return mMarvelService.getCharacters(currentOffset);
    }
}
