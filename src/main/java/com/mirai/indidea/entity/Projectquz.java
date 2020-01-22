package com.mirai.indidea.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "projectquz", schema = "indidea", catalog = "")
public class Projectquz {
    private int id;
    private int quserid;
    private int projectid;
    private String quzcontent;
    private String ancontent;
    private Integer auserid;
    private Date createdat;
    private Date updatedat;
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
    @Column(name = "quserid", nullable = false)
    public int getQuserid() {
        return quserid;
    }

    public void setQuserid(int quserid) {
        this.quserid = quserid;
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
    @Column(name = "quzcontent", nullable = false, length = 125)
    public String getQuzcontent() {
        return quzcontent;
    }

    public void setQuzcontent(String quzcontent) {
        this.quzcontent = quzcontent;
    }

    @Basic
    @Column(name = "ancontent", nullable = true, length = 500)
    public String getAncontent() {
        return ancontent;
    }

    public void setAncontent(String ancontent) {
        this.ancontent = ancontent;
    }

    @Basic
    @Column(name = "auserid", nullable = true)
    public Integer getAuserid() {
        return auserid;
    }

    public void setAuserid(Integer auserid) {
        this.auserid = auserid;
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

        Projectquz that = (Projectquz) o;

        if (id != that.id) return false;
        if (quserid != that.quserid) return false;
        if (projectid != that.projectid) return false;
        if (status != that.status) return false;
        if (quzcontent != null ? !quzcontent.equals(that.quzcontent) : that.quzcontent != null) return false;
        if (ancontent != null ? !ancontent.equals(that.ancontent) : that.ancontent != null) return false;
        if (auserid != null ? !auserid.equals(that.auserid) : that.auserid != null) return false;
        if (createdat != null ? !createdat.equals(that.createdat) : that.createdat != null) return false;
        if (updatedat != null ? !updatedat.equals(that.updatedat) : that.updatedat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + quserid;
        result = 31 * result + projectid;
        result = 31 * result + (quzcontent != null ? quzcontent.hashCode() : 0);
        result = 31 * result + (ancontent != null ? ancontent.hashCode() : 0);
        result = 31 * result + (auserid != null ? auserid.hashCode() : 0);
        result = 31 * result + (createdat != null ? createdat.hashCode() : 0);
        result = 31 * result + (updatedat != null ? updatedat.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }
}
