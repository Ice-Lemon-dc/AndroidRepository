package com.dc.androidrepository.base;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mSparseArray = new SparseArray<>();

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mSparseArray = new SparseArray<>();
    }

    /**
     * 从ItemView获取里面的View
     *
     * @param viewId int
     * @return View
     */
    public <T extends View> T getView(int viewId) {
        View view = mSparseArray.get(viewId);
        // 使用缓存减少findViewById的次数
        if (view == null) {
            view = itemView.findViewById(viewId);
            mSparseArray.put(viewId, view);
        }
        return (T)view;
    }

    /**
     * 设置文本
     *
     * @param viewId int
     * @param text   CharSequence
     * @return ViewHolder
     */
    public ViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 设置本地图片
     *
     * @param viewId     int
     * @param resourceId int
     * @return ViewHolder
     */
    public ViewHolder setImageResource(int viewId, int resourceId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }

    /**
     * 设置第三方、网络图片
     *
     * @param viewId      int
     * @param imageLoader HolderImageLoader
     * @return ViewHolder
     */
    public ViewHolder setImagePath(int viewId, String path, HolderImageLoader imageLoader) {
        ImageView imageView = getView(viewId);
        imageLoader.loadImage(imageView, path);
        return this;
    }

    /**
     * 设置View的Visibility
     *
     * @param viewId     int
     * @param visibility int
     * @return ViewHolder
     */
    public ViewHolder setViewVisibility(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }
}
