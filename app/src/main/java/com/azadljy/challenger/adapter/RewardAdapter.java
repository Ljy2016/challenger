package com.azadljy.challenger.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azadljy.challenger.R;
import com.azadljy.challenger.model.ChallengeModel;
import com.azadljy.challenger.model.RewardModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author azad
 */
public class RewardAdapter extends BaseQuickAdapter<RewardModel, BaseViewHolder> {

    private PictureListener pictureListener;

    public interface PictureListener {
        void add();

        void delete();
    }


    public RewardAdapter(int layoutResId, @Nullable List<RewardModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RewardModel item) {
        helper.setText(R.id.et_item_add_reward_name, item.getName());
        helper.setText(R.id.et_item_add_reward_time_frame, item.getTimeFrame() + "");
        helper.setText(R.id.et_item_add_reward_describe, item.getGeneralDescription());
        LinearLayoutManager ms = new LinearLayoutManager(helper.itemView.getContext());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView recyclerView = helper.getView(R.id.rv_item_add_reward_pictures);
        recyclerView.setLayoutManager(ms);
        PictureAdapter pictureAdapter = new PictureAdapter(R.layout.item_picture_list, item.getPictureModels());
        pictureAdapter.addFooterView(getFootView(recyclerView));
        recyclerView.setAdapter(pictureAdapter);
    }


    private View getFootView(ViewGroup root) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_picture_list_foot, root, false);
        ImageView ivAddPicture = view.findViewById(R.id.iv_item_picture_list_foot_add);
        ivAddPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pictureListener != null) {
                    pictureListener.add();
                }
            }
        });
        return view;
    }


    public void addPictureListener(PictureListener pictureListener) {
        this.pictureListener = pictureListener;
    }
}
