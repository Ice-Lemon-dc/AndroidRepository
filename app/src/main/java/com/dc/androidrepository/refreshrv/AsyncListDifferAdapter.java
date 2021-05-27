package com.dc.androidrepository.refreshrv;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;

import com.dc.androidrepository.R;
import com.dc.androidrepository.base.ViewHolder;

import java.util.List;

public class AsyncListDifferAdapter extends RecyclerView.Adapter<AsyncListDifferAdapter.AsyncListDifferViewHolder> {

    private LayoutInflater mLayoutInflater;
    private AsyncListDiffer<UserBean> mAsyncListDiffer;

    public AsyncListDifferAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mAsyncListDiffer = new AsyncListDiffer<>(this, new AsyncListDifferItemCallback());
    }

    @NonNull
    @Override
    public AsyncListDifferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new AsyncListDifferViewHolder(mLayoutInflater.inflate(R.layout.item_rv_diff, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AsyncListDifferAdapter.AsyncListDifferViewHolder viewHolder, int position) {
        UserBean userBean = mAsyncListDiffer.getCurrentList().get(position);
        viewHolder.tvName.setText(userBean.name);
        viewHolder.tvDesc.setText(userBean.job);
        Log.e("---===", "position:" + position);
    }

    @Override
    public void onBindViewHolder(@NonNull AsyncListDifferAdapter.AsyncListDifferViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            // getChangePayload 返回值不为null
            Bundle bundle = (Bundle) payloads.get(0);
            String name = bundle.getString("name");
            if (!TextUtils.isEmpty(name)) {
                holder.tvName.setText(name);
            }
            String job = bundle.getString("job");
            if (!TextUtils.isEmpty(job)) {
                holder.tvDesc.setText(job);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mAsyncListDiffer.getCurrentList().size();
    }

    public void setDatas(List<UserBean> newList) {
        mAsyncListDiffer.submitList(newList);
    }

    public static class AsyncListDifferViewHolder extends ViewHolder {
        TextView tvName;
        TextView tvDesc;

        public AsyncListDifferViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDesc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
