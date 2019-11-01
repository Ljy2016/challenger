package com.azadljy.challenger.page.challenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.azadljy.challenger.BaseActivity;
import com.azadljy.challenger.R;
import com.azadljy.challenger.model.ChallengeModel;
import com.azadljy.challenger.util.CommonUtils;
import com.azadljy.challenger.util.DbUtil;
import com.azadljy.pleasantlibrary.utils.datapicker.CustomDatePickerTwo;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public class AddChallengeActivity extends BaseActivity {
    private QMUITopBar mTopBar;
    private EditText et_challenge_title;
    private EditText et_challenge_goal;
    private EditText et_challenge_time;
    private EditText et_challenge_describe;
    private CustomDatePickerTwo picker;
    private QMUIRoundButton btn_challenge_data;
    private TagFlowLayout flow_layout_reward;
    private String title;
    private String goal;
    private String describe;
    private String uuid;
    private int time;
    private String startDate = "";
    private String endDate = "";
    private GregorianCalendar calendar = new GregorianCalendar();
    private List<String> rewardsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_challenge);
        initTopBra();
        initView();
        initData();
    }

    @Override
    public void initTopBra() {
        super.initTopBra();
        mTopBar = findViewById(R.id.top_bar);
        mTopBar.setBackgroundColor(ContextCompat.getColor(this, R.color.cl_f6a623));
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTopBar.setTitle("AddChallenge");
        mTopBar.addRightTextButton("Done", R.id.qmui_topbar_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    ChallengeModel challengeModel = new ChallengeModel();
                    challengeModel.setUuid(uuid);
                    challengeModel.setTitle(title);
                    challengeModel.setGoal(goal);
                    challengeModel.setStartDate(startDate);
                    challengeModel.setEndDate(endDate);
                    challengeModel.setTotalTimes(time);
                    challengeModel.setDescribe(describe);
                    DbUtil.addChallenge(challengeModel);
                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void initView() {
        super.initView();
        et_challenge_title = findViewById(R.id.et_challenge_title);
        et_challenge_goal = findViewById(R.id.et_challenge_goal);
        et_challenge_time = findViewById(R.id.et_challenge_time);
        btn_challenge_data = findViewById(R.id.btn_challenge_data);
        et_challenge_describe = findViewById(R.id.et_challenge_describe);
        flow_layout_reward = findViewById(R.id.flow_layout_reward);

        btn_challenge_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataPickerDialog();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        startDate = calendar.get(GregorianCalendar.YEAR) + "-" + (calendar.get(GregorianCalendar.MONTH) + 1) + "-" + calendar.get(GregorianCalendar.DAY_OF_MONTH);
        GregorianCalendar calendar1 = CommonUtils.getAfterDays(calendar, 7);
        endDate = calendar1.get(GregorianCalendar.YEAR) + "-" + (calendar.get(GregorianCalendar.MONTH) + 1) + "-" + calendar.get(GregorianCalendar.DAY_OF_MONTH);
        uuid = UUID.randomUUID().toString();
        rewardsList.add("1");
        rewardsList.add("2");
        rewardsList.add("3");
        rewardsList.add("4");
        rewardsList.add("+");

        flow_layout_reward.setAdapter(new TagAdapter<String>(rewardsList) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.item_flow_reward,
                        parent, false);
                tv.setText(o);

                return tv;
            }
        });

        flow_layout_reward.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (position == rewardsList.size() - 1) {
                    Intent intent = new Intent(mContext, AddRewardsActivity.class);
                    intent.putExtra("pId", uuid);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }


    /**
     * 时间选择对话框
     */
    private void showDataPickerDialog() {
        picker = new CustomDatePickerTwo(mContext, new CustomDatePickerTwo.ResultHandler() {
            @Override
            public void handle(String startTime, String endTime) {

                try {
                    if (CommonUtils.stringToLong(startTime, "yyyy-MM-dd") > CommonUtils.stringToLong(endTime, "yyyy-MM-dd")) {
                        Toast.makeText(getApplicationContext(), "开始时间不能大于结束时间",
                                Toast.LENGTH_LONG).show();
                    } else {
                        startDate = startTime;
                        endDate = endTime;
                        btn_challenge_data.setText(startDate + "——" + endDate);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "时间选择有误",
                            Toast.LENGTH_LONG).show();
                }

            }
        }, "2000-01-01", "2030-12-12");
        picker.show(startDate, endDate);
    }

    private String message;

    public boolean checkData() {
        message = "";
        title = et_challenge_title.getText().toString();
        goal = et_challenge_goal.getText().toString();
        try {
            time = Integer.parseInt(et_challenge_time.getText().toString());
        } catch (Exception e) {
            time = 0;
        }

        describe = et_challenge_describe.getText().toString();
        if (TextUtils.isEmpty(title)) {
            message = "请输入标题";
        } else if (TextUtils.isEmpty(goal)) {
            message = "请输入目标";
        } else if (btn_challenge_data.getText().toString().equals("选择期限")) {
            message = "请选择期限";
        } else if (time == 0) {
            message = "请输入总用时";
        } else if (TextUtils.isEmpty(describe)) {
            message = "请输入描述";
        } else {
            message = "添加成功";
            return true;
        }

        return false;
    }





}
