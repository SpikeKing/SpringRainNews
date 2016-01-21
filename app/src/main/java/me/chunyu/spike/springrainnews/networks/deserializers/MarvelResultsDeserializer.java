package me.chunyu.spike.springrainnews.networks.deserializers;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;

public class MarvelResultsDeserializer<T> implements JsonDeserializer<List<T>> {
    @Override
    public List<T> deserialize(JsonElement je, Type typeOfT,
                               JsonDeserializationContext context) throws JsonParseException {
        // 转换Json的数据, 获取内部有用的信息
        JsonElement results = je.getAsJsonObject().get("data")
                .getAsJsonObject().get("results");
        return new Gson().fromJson(results, typeOfT);
    }
}