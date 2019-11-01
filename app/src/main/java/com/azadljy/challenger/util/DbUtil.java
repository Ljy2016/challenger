package com.azadljy.challenger.util;

import android.app.Application;
import android.util.Log;

import com.azadljy.challenger.App;
import com.azadljy.challenger.model.ChallengeModel;
import com.azadljy.challenger.model.RewardModel;
import com.speedystone.greendaodemo.db.ChallengeModelDao;
import com.speedystone.greendaodemo.db.RewardModelDao;

import java.util.List;

public class DbUtil {
    private static App app;

    public static void init(App a) {
        app = a;
    }

    public static void addChallenge(ChallengeModel challengeModel) {
        if (app != null) {
            app.getDaoSession().getChallengeModelDao().insert(challengeModel);
        } else {
            throw new NullPointerException("Call init() before user DbUtil");
        }
    }

    public static List<ChallengeModel> loadAllChallenge() {
        if (app != null) {
            return app.getDaoSession().getChallengeModelDao().loadAll();
        } else {
            throw new NullPointerException("Call init() before user DbUtil");
        }
    }

    public static List<RewardModel> loadRewardsByPid(String pId) {


        if (app != null) {
            return app.getDaoSession().getRewardModelDao().queryBuilder().where(RewardModelDao.Properties.ChallengeId.eq(pId)).list();
        } else {
            throw new NullPointerException("Call init() before user DbUtil");
        }
    }


}
