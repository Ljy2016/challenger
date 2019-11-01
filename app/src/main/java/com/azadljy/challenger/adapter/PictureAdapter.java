package com.azadljy.challenger.adapter;

import android.graphics.Color;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

import com.azadljy.challenger.R;
import com.azadljy.challenger.model.ChallengeModel;
import com.azadljy.challenger.model.PictureModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class PictureAdapter extends BaseQuickAdapter<PictureModel, BaseViewHolder> {


    public PictureAdapter(int layoutResId, @Nullable List<PictureModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PictureModel item) {

        SimpleDraweeView simpleDraweeView = helper.getView(R.id.simple_drawee_view_item_picture);
        Uri uri = Uri.parse(item.getPictureUrl());
        Log.e(TAG, "convert: "+uri.getPath());
        simpleDraweeView.setImageURI(uri);
    }
}
