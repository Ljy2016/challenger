package com.azadljy.challenger.adapter;

import android.graphics.Color;

import androidx.annotation.Nullable;

import com.azadljy.challenger.R;
import com.azadljy.challenger.model.ChallengeModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ChallengeAdapter extends BaseQuickAdapter<ChallengeModel, BaseViewHolder> {


    public ChallengeAdapter(int layoutResId, @Nullable List<ChallengeModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChallengeModel item) {
        helper.setText(R.id.tv_item_challenge_list_name, item.getGoal());
        float process = item.getProcess();
        if (process == 100) {
            helper.setText(R.id.tv_item_challenge_list_process, "success");
            helper.setTextColor(R.id.tv_item_challenge_list_process, Color.parseColor("#7fff00"));
        } else if (process >= 50) {
            helper.setText(R.id.tv_item_challenge_list_process, process + "%");
            helper.setTextColor(R.id.tv_item_challenge_list_process, Color.parseColor("#008000"));
        } else if (process >= 10) {
            helper.setText(R.id.tv_item_challenge_list_process, process + "%");
            helper.setTextColor(R.id.tv_item_challenge_list_process, Color.parseColor("#ffd700"));
        } else {
            helper.setText(R.id.tv_item_challenge_list_process, process + "%");
            helper.setTextColor(R.id.tv_item_challenge_list_process, Color.parseColor("#ff4500"));
        }
    }
}
