package com.dc.androidrepository.base;

/**
 * 多条目布局
 *
 * @author Lemon
 * @param <T> 数据实体类
 */
public interface MultiTypeSupport<T> {

    /**
     * 获取布局id
     *
     * @param t T
     * @return 布局id
     */
    int getLayoutId(T t);
}
