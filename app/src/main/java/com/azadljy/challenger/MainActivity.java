package com.azadljy.challenger;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.azadljy.challenger.page.challenge.ChallengeListActivity;
import com.azadljy.pleasantlibrary.adapter.SimpleAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private SimpleAdapter simpleAdapter;
    private List<SimpleAdapter.SimpleModel> simpleModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_main);
        initData();
        initAdapter();
    }


    @Override
    public void initData() {
        simpleModels = new ArrayList<>();
        SimpleAdapter.SimpleModel model = new SimpleAdapter.SimpleModel();
        model.setName("目标");
        model.setIcon(R.color.cl_6ea1fa);
        simpleModels.add(model);
        SimpleAdapter.SimpleModel model1 = new SimpleAdapter.SimpleModel();
        model1.setName("历程");
        model1.setIcon(R.color.cl_4296ff);
        simpleModels.add(model1);
    }

    private void initAdapter() {
        simpleAdapter = new SimpleAdapter(R.layout.item_simple_vertical, simpleModels);
        simpleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mContext, ChallengeListActivity.class));
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }
        });
        recyclerView.setAdapter(simpleAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));

    }
}
