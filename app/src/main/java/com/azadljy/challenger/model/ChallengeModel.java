package com.azadljy.challenger.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.speedystone.greendaodemo.db.DaoSession;
import com.speedystone.greendaodemo.db.ExperienceModelDao;
import com.speedystone.greendaodemo.db.RewardModelDao;
import com.speedystone.greendaodemo.db.ChallengeModelDao;

@Entity
public class ChallengeModel {


    @Id
    private String uuid;
    
    private String title;
    private String goal;

    private String startDate;

    private String endDate;

    private int totalTimes;

    private String status;

    private String describe;

    private float process;


    @ToMany(referencedJoinProperty = "challengeId")
    @OrderBy("date ASC")
    private List<RewardModel> rewardModels;

    @ToMany(referencedJoinProperty = "challengeId")
    @OrderBy("date ASC")
    private List<ExperienceModel> ExperienceModel;

    private String thoughts;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1967105892)
    private transient ChallengeModelDao myDao;

    @Generated(hash = 1830553430)
    public ChallengeModel(String uuid, String title, String goal, String startDate, String endDate,
            int totalTimes, String status, String describe, float process, String thoughts) {
        this.uuid = uuid;
        this.title = title;
        this.goal = goal;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalTimes = totalTimes;
        this.status = status;
        this.describe = describe;
        this.process = process;
        this.thoughts = thoughts;
    }

    @Generated(hash = 395903145)
    public ChallengeModel() {
    }



    public String getGoal() {
        return this.goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getTotalTimes() {
        return this.totalTimes;
    }

    public void setTotalTimes(int totalTimes) {
        this.totalTimes = totalTimes;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThoughts() {
        return this.thoughts;
    }

    public void setThoughts(String thoughts) {
        this.thoughts = thoughts;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1451608311)
    public List<RewardModel> getRewardModels() {
        if (rewardModels == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RewardModelDao targetDao = daoSession.getRewardModelDao();
            List<RewardModel> rewardModelsNew = targetDao._queryChallengeModel_RewardModels(uuid);
            synchronized (this) {
                if (rewardModels == null) {
                    rewardModels = rewardModelsNew;
                }
            }
        }
        return rewardModels;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 750401363)
    public synchronized void resetRewardModels() {
        rewardModels = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2040635489)
    public List<ExperienceModel> getExperienceModel() {
        if (ExperienceModel == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ExperienceModelDao targetDao = daoSession.getExperienceModelDao();
            List<ExperienceModel> ExperienceModelNew = targetDao._queryChallengeModel_ExperienceModel(uuid);
            synchronized (this) {
                if (ExperienceModel == null) {
                    ExperienceModel = ExperienceModelNew;
                }
            }
        }
        return ExperienceModel;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 743273401)
    public synchronized void resetExperienceModel() {
        ExperienceModel = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }


    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 2039836747)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getChallengeModelDao() : null;
    }

    public float getProcess() {
        return this.process;
    }

    public void setProcess(float process) {
        this.process = process;
    }

    public String getDescribe() {
        return this.describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


}
