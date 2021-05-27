package com.dc.androidrepository.refreshrv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dc.androidrepository.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RecyclerView刷新（为防止数据过多主线程耗时最好使用AsyncListDiffer进行异步刷新）
 * @author Lemon
 */
public class DiffUtilActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private Button mButton;
    private List<UserBean> mList;

    private DiffUtilAdapter mAdapter;

    private String[] jobArray = {"Android", "Java", "UI", "产品", "运营", "测试"};
    private Random mRandom = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_util);

        mRecyclerView = findViewById(R.id.recycler_view);
        mButton = findViewById(R.id.button);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mButton.setOnClickListener(this);

        mList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mList.add(new UserBean(i, "用户" + i, jobArray[0]));
        }
        mAdapter = new DiffUtilAdapter(mList, this);
        mRecyclerView.setAdapter(mAdapter);
    }

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
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallback(mList, newDatas));
        mList = newDatas;
        mAdapter.setDatas(mList);
        diffResult.dispatchUpdatesTo(mAdapter);
//        mAdapter.notifyDataSetChanged();
    }
}