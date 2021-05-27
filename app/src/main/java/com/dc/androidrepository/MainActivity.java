package com.dc.androidrepository;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dc.androidrepository.base.ItemClickListener;
import com.dc.androidrepository.base.RecyclerCommonAdapter;
import com.dc.androidrepository.base.ViewHolder;
import com.dc.androidrepository.refreshrv.AsyncListDifferActivity;
import com.dc.androidrepository.refreshrv.DiffUtilActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lemon
 */
public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView mRecyclerView;

    private List<String> mDatas;

    private List<Class<?>> mClasses;

    private RecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatas = new ArrayList<>();
        mClasses = new ArrayList<>();
        mDatas.add("DiffUtil刷新RecyclerView");
        mDatas.add("AsyncListDiffer刷新RecyclerView");

        mClasses = Arrays.asList(DiffUtilActivity.class, AsyncListDifferActivity.class);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAdapter = new RecyclerAdapter(this, mDatas);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.setItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        Class<?> c = mClasses.get(position);
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    private static class RecyclerAdapter extends RecyclerCommonAdapter<String> {

        public RecyclerAdapter(Context context, List<String> datas) {
            super(context, datas, R.layout.item_rv_main);
        }

        @Override
        protected void convert(ViewHolder holder, String s, int position) {
            holder.setText(R.id.textview, s);
        }
    }
}