package com.dc.androidrepository.refreshrv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dc.androidrepository.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsyncListDifferActivity extends AppCompatActivity implements View.OnClickListener {

    private List<UserBean> mList;
    private AsyncListDifferAdapter mAdapter;
    private String[] jobArray = {"Android", "Java", "UI", "产品", "运营", "测试"};
    private Random mRandom = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_list_differ);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        Button button = findViewById(R.id.button);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button.setOnClickListener(this);

        mList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mList.add(new UserBean(i, "用户" + i, jobArray[0]));
        }
        mAdapter = new AsyncListDifferAdapter( this);
        mAdapter.setDatas(mList);
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        List<UserBean> newDatas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String desc;
            if (i % 3 == 0) {
                desc = jobArray[mRandom.nextInt(jobArray.length)];
            } else {
                desc = jobArray[0];
            }
            newDatas.add(new UserBean(i, "用户" + i, desc));
        }
        if (mList.size() % 2 == 0) {
            newDatas.add(2, new UserBean(100, "用户" + 100, jobArray[0]));
        }
        mList = newDatas;
        mAdapter.setDatas(mList);
//        mAdapter.notifyDataSetChanged();
    }
}