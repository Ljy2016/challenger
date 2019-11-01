package com.azadljy.challenger.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.speedystone.greendaodemo.db.DaoSession;
import com.speedystone.greendaodemo.db.PictureModelDao;
import com.speedystone.greendaodemo.db.ExperienceModelDao;

@Entity
public class ExperienceModel {
    @Id
    private String id;
    private String thoughts;
    private String challengeId;
    private Date date;

    @ToMany(referencedJoinProperty = "pId")
    @OrderBy("date ASC")
    private List<PictureModel> pictureModels;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 199716965)
    private transient ExperienceModelDao myDao;

    @Generated(hash = 301001174)
    public ExperienceModel(String id, String thoughts, String challengeId, Date date) {
        this.id = id;
        this.thoughts = thoughts;
        this.challengeId = challengeId;
        this.date = date;
    }

    @Generated(hash = 153013913)
    public ExperienceModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThoughts() {
        return this.thoughts;
    }

    public void setThoughts(String thoughts) {
        this.thoughts = thoughts;
    }

    public String getChallengeId() {
        return this.challengeId;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 648595957)
    public List<PictureModel> getPictureModels() {
        if (pictureModels == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PictureModelDao targetDao = daoSession.getPictureModelDao();
            List<PictureModel> pictureModelsNew = targetDao
                    ._queryExperienceModel_PictureModels(id);
            synchronized (this) {
                if (pictureModels == null) {
                    pictureModels = pictureModelsNew;
                }
            }
        }
        return pictureModels;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1781748355)
    public synchronized void resetPictureModels() {
        pictureModels = null;
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



    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1508262699)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getExperienceModelDao() : null;
    }

}
