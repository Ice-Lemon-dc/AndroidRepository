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
import androidx.recyclerview.widget.RecyclerView;

import com.dc.androidrepository.R;
import com.dc.androidrepository.base.ViewHolder;

import java.util.List;

public class DiffUtilAdapter extends RecyclerView.Adapter<DiffUtilAdapter.DiffUtilViewHolder> {

    private List<UserBean> mList;
    private LayoutInflater mLayoutInflater;

    public DiffUtilAdapter(List<UserBean> list, Context context) {
        mList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DiffUtilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new DiffUtilViewHolder(mLayoutInflater.inflate(R.layout.item_rv_diff, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DiffUtilAdapter.DiffUtilViewHolder viewHolder, int position) {
        UserBean userBean = mList.get(position);
        viewHolder.tvName.setText(userBean.name);
        viewHolder.tvDesc.setText(userBean.job);
        Log.e("---===", "position:" + position);
    }

    @Override
    public void onBindViewHolder(@NonNull DiffUtilAdapter.DiffUtilViewHolder holder, int position, @NonNull List<Object> payloads) {
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
        return mList.size();
    }

    public void setDatas(List<UserBean> newList) {
        mList = newList;
    }

    public static class DiffUtilViewHolder extends ViewHolder {
        TextView tvName;
        TextView tvDesc;

        public DiffUtilViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDesc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
