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

        // Log信息插值器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        // 公共密匙插值器
        MarvelSigningInterceptor signingInterceptor = new MarvelSigningInterceptor(
                BuildConfig.MARVEL_PUBLIC_KEY, BuildConfig.MARVEL_PRIVATE_KEY);

        // 在OkHttp3.0中添加差值器
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(signingInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();

        // 返回数据反序列化的过滤处理
        MarvelResultsDeserializer deserializer =
                new MarvelResultsDeserializer<AvengersCharacter>();

        // 定制的Gson反序列化
        Gson customGsonInstance = new GsonBuilder()
                .registerTypeAdapter(
                        new TypeToken<List<AvengersCharacter>>() {
                        }.getType(),
                        deserializer)
                .create();

        // 添加Host, 添加Gson解析, 添加RxJava适配器, 添加OKHttp.
        Retrofit retrofitAdapter = new Retrofit.Builder()
                .baseUrl(MarvelService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create(customGsonInstance))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        // mMarvelService是服务器接口, marvelApiAdapter是Retrofit对象
        mMarvelService = retrofitAdapter.create(MarvelService.class);
    }

    @Override
    public Observable<List<AvengersCharacter>> getCharacters(int currentOffset) {
        // 调用接口, 返回Observable信息
        return mMarvelService.getCharacters(currentOffset);
    }
}
