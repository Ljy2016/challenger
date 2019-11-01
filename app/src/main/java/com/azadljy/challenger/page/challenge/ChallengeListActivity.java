package com.azadljy.challenger.page.challenge;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.azadljy.challenger.BaseActivity;
import com.azadljy.challenger.R;
import com.azadljy.challenger.adapter.ChallengeAdapter;
import com.azadljy.challenger.adapter.RewardAdapter;
import com.azadljy.challenger.model.ChallengeModel;
import com.azadljy.challenger.util.DbUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.speedystone.greendaodemo.db.ChallengeModelDao;

import java.util.ArrayList;
import java.util.List;

public class ChallengeListActivity extends BaseActivity {

    private QMUITopBar mTopBar;
    private RecyclerView recyclerView;
    private ChallengeAdapter challengeAdapter;
    private List<ChallengeModel> challengeModels;
    private ChallengeModelDao challengeModelDao;
    private FloatingActionButton floating_btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_list);
        recyclerView = findViewById(R.id.rv_challenge);
        floating_btn_add = findViewById(R.id.floating_btn_add);
        mTopBar = findViewById(R.id.topbar);
        initTopBra();
        initData();
        initAdapter();
        initListener();
    }

    @Override
    public void initTopBra() {
        mTopBar.setBackgroundColor(ContextCompat.getColor(this, R.color.cl_f6a623));
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTopBar.setTitle("Challenge");
    }

    @Override
    public void initData() {
        challengeModels = new ArrayList<>();
        challengeModels.addAll(DbUtil.loadAllChallenge());
    }


    private void initAdapter() {
        challengeAdapter = new ChallengeAdapter(R.layout.item_challenge_list, challengeModels);
        challengeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }
        });
        recyclerView.setAdapter(challengeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void initListener() {
        floating_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(mContext, AddChallengeActivity.class), 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                refreshData();
            }
        }
    }

    private void refreshData() {
        challengeModels.clear();
        challengeModels.addAll(DbUtil.loadAllChallenge());
        challengeAdapter.notifyDataSetChanged();
    }

}
