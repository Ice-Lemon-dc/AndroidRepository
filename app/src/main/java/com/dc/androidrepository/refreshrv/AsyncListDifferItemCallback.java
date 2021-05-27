package com.dc.androidrepository.refreshrv;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

public class AsyncListDifferItemCallback extends DiffUtil.ItemCallback<UserBean> {

    @Override
    public boolean areItemsTheSame(@NonNull UserBean oldItem, @NonNull UserBean newItem) {
        return oldItem.id == newItem.id;
    }

    @Override
    public boolean areContentsTheSame(@NonNull UserBean oldItem, @NonNull UserBean newItem) {
        if (!TextUtils.equals(oldItem.name, newItem.name)) {
            return false;
        }
        return TextUtils.equals(oldItem.job, newItem.job);
    }

    @Nullable
    @Override
    public Object getChangePayload(@NonNull UserBean oldItem, @NonNull UserBean newItem) {
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
