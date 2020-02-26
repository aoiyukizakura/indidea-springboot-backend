package com.mirai.indidea.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "log", schema = "indidea", catalog = "")
public class Log {
    private int id;
    private String title;
    private String content;
    private int userid;
    private int projectid;
    private Integer number;
    private int status;
    private Date createdat;
    private Date updatedat;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 55)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content", nullable = false, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "userid", nullable = false)
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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
    @Column(name = "number", nullable = true)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log log = (Log) o;

        if (id != log.id) return false;
        if (userid != log.userid) return false;
        if (projectid != log.projectid) return false;
        if (status != log.status) return false;
        if (title != null ? !title.equals(log.title) : log.title != null) return false;
        if (content != null ? !content.equals(log.content) : log.content != null) return false;
        if (number != null ? !number.equals(log.number) : log.number != null) return false;
        if (createdat != null ? !createdat.equals(log.createdat) : log.createdat != null) return false;
        if (updatedat != null ? !updatedat.equals(log.updatedat) : log.updatedat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + userid;
        result = 31 * result + projectid;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (createdat != null ? createdat.hashCode() : 0);
        result = 31 * result + (updatedat != null ? updatedat.hashCode() : 0);
        return result;
    }
}
