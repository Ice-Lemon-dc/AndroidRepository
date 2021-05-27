package com.dc.androidrepository.refreshrv;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/**
 * @author Lemon
 */
public class DiffUtilCallback extends DiffUtil.Callback {

    private List<UserBean> oldList;
    private List<UserBean> newList;

    public DiffUtilCallback(List<UserBean> oldList, List<UserBean> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        UserBean oldItem = oldList.get(oldItemPosition);
        UserBean newItem = newList.get(newItemPosition);
        return oldItem.id == newItem.id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        // areItemsTheSame 返回true时才会调用该方法
        UserBean oldItem = oldList.get(oldItemPosition);
        UserBean newItem = newList.get(newItemPosition);
        if (!TextUtils.equals(oldItem.name, newItem.name)) {
            return false;
        }
        return TextUtils.equals(oldItem.job, newItem.job);
    }


    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // areContentsTheSame返回false时会调用该方法，该方法实现控件级别的刷新，
        // 返回值传到RecyclerView adapter中三个参数的onBindViewHolder方法中
        UserBean oldItem = oldList.get(oldItemPosition);
        UserBean newItem = newList.get(newItemPosition);
        Bundle bundle = new Bundle();
        if (!TextUtils.equals(oldItem.name, newItem.name)) {
            bundle.putString("name", newItem.name);
        }
        if (!TextUtils.equals(oldItem.job, newItem.job)) {
            bundle.putString("job", newItem.job);
        }
        return bundle;
    }
}
