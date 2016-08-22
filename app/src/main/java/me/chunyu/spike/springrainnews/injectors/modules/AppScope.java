package me.chunyu.spike.springrainnews.injectors.modules;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 主要的域
 * <p>
 * Created by wangchenlong on 16/7/28.
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
