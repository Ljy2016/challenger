package com.azadljy.challenger;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.azadljy.challenger.impl.BoxingFrescoLoader;
import com.azadljy.challenger.util.DbUtil;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.speedystone.greendaodemo.db.DaoMaster;
import com.speedystone.greendaodemo.db.DaoSession;

/**
 * @author azad
 */
public class App extends Application {

    private DaoSession daoSession;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initGreenDao();
        DbUtil.init(this);
        Fresco.initialize(this);
        IBoxingMediaLoader loader = new BoxingFrescoLoader(instance);
        BoxingMediaLoader.getInstance().init(loader);
    }


    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "challenge.db");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }


    public DaoSession getDaoSession() {
        return daoSession;
    }


    public static App getInstance() {
        return instance;
    }

}
