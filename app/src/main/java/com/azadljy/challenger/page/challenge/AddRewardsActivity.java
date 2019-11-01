package com.azadljy.challenger.page.challenge;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.azadljy.challenger.App;
import com.azadljy.challenger.BaseActivity;
import com.azadljy.challenger.R;
import com.azadljy.challenger.adapter.RewardAdapter;
import com.azadljy.challenger.model.PictureModel;
import com.azadljy.challenger.model.RewardModel;
import com.azadljy.challenger.util.DbUtil;
import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class AddRewardsActivity extends BaseActivity {
    private static final int REQUEST_CODE = 1024;
    private RecyclerView recyclerView;
    private List<RewardModel> rewardModelList;
    private RewardAdapter rewardAdapter;
    private FloatingActionButton floating_btn_add_reward;
    private String pId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rewards);
        initTopBra();
        initView();
        initData();
        initListener();
    }

    @Override
    public void initTopBra() {
        QMUITopBar mTopBar = findViewById(R.id.top_bar);
        mTopBar.setBackgroundColor(ContextCompat.getColor(this, R.color.cl_f6a623));
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTopBar.setTitle("AddRewards");
        mTopBar.addRightTextButton("Done", R.id.qmui_topbar_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        floating_btn_add_reward = findViewById(R.id.floating_btn_add_reward);
        recyclerView = findViewById(R.id.rv_rewards);

    }


    @Override
    public void initData() {
        super.initData();
        pId = getIntent().getStringExtra("pId");
        rewardModelList = DbUtil.loadRewardsByPid(pId);
        rewardAdapter = new RewardAdapter(R.layout.item_add_rewards, rewardModelList);
        rewardAdapter.addPictureListener(new RewardAdapter.PictureListener() {
            @Override
            public void add() {
                choosePictures();
            }

            @Override
            public void delete() {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(rewardAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
        floating_btn_add_reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PictureModel> pictureModels = new ArrayList<>();
                PictureModel pictureModel = new PictureModel();
                pictureModel.setPicDescribe("123");
                pictureModel.setPictureUrl("https://azadljy.oss-cn-shenzhen.aliyuncs.com/challenge/pictures/8326cffc1e178a82a45a9187fc03738da977e8b7.jpg");
                pictureModels.add(pictureModel);
                RewardModel rewardModel = new RewardModel();
                rewardModel.setChallengeId(pId);
                rewardModel.setId(UUID.randomUUID().toString());
                rewardModel.__setDaoSession(App.getInstance().getDaoSession());
                rewardModel.setPictureModels(pictureModels);
                rewardModelList.add(rewardModel);
                rewardAdapter.notifyDataSetChanged();
            }
        });
    }

    public void choosePictures() {
        //进入选择图片的页面
        BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).needCamera(R.mipmap.icon_camera).needGif();
        Boxing.of(config).withIntent(this, BoxingActivity.class).start(this, REQUEST_CODE);
//        String cachePath = BoxingFileHelper.getCacheDir(this);
//        if (TextUtils.isEmpty(cachePath)) {
//            Toast.makeText(getApplicationContext(), R.string.boxing_storage_deny, Toast.LENGTH_SHORT).show();
//            return;
//        }
//        Uri destUri = new Uri.Builder()
//                .scheme("file")
//                .appendPath(cachePath)
//                .appendPath(String.format(Locale.US, "%s.png", System.currentTimeMillis()))
//                .build();
//        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).needCamera(R.mipmap.icon_camera).withCropOption(new BoxingCropOption(destUri));
//        Boxing.of(singleCropImgConfig).withIntent(this, BoxingActivity.class).start(this, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                final ArrayList<BaseMedia> medias = Boxing.getResult(data);
                for (BaseMedia media : medias) {
                    PictureModel pictureModel = new PictureModel();
                    pictureModel.setPictureUrl("file://"+media.getPath());
                    Log.e(TAG, "onActivityResult: " + media.getPath());
                    rewardModelList.get(0).getPictureModels().add(pictureModel);
                }
                rewardAdapter.notifyDataSetChanged();
            }
        }
    }
}
