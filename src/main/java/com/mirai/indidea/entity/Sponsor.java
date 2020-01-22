package com.mirai.indidea.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "sponsor", schema = "indidea", catalog = "")
public class Sponsor {
    private int id;
    private int sponsorid;
    private int projectid;
    private int point;
    private Date createdat;
    private Date updatedat;
    private Integer rewardid;
    private int status;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sponsorid", nullable = false)
    public int getSponsorid() {
        return sponsorid;
    }

    public void setSponsorid(int sponsorid) {
        this.sponsorid = sponsorid;
    }

    @Basic
    @Column(name = "projectid", nullable = false)
    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    @Basic
    @Column(name = "point", nullable = false)
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Basic
    @Column(name = "createdat", nullable = true)
    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    @Basic
    @Column(name = "updatedat", nullable = true)
    public Date getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }

    @Basic
    @Column(name = "rewardid", nullable = true)
    public Integer getRewardid() {
        return rewardid;
    }

    public void setRewardid(Integer rewardid) {
        this.rewardid = rewardid;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sponsor that = (Sponsor) o;

        if (id != that.id) return false;
        if (sponsorid != that.sponsorid) return false;
        if (projectid != that.projectid) return false;
        if (point != that.point) return false;
        if (status != that.status) return false;
        if (createdat != null ? !createdat.equals(that.createdat) : that.createdat != null) return false;
        if (updatedat != null ? !updatedat.equals(that.updatedat) : that.updatedat != null) return false;
        if (rewardid != null ? !rewardid.equals(that.rewardid) : that.rewardid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + sponsorid;
        result = 31 * result + projectid;
        result = 31 * result + point;
        result = 31 * result + (createdat != null ? createdat.hashCode() : 0);
        result = 31 * result + (updatedat != null ? updatedat.hashCode() : 0);
        result = 31 * result + (rewardid != null ? rewardid.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }
}
