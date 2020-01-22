package com.mirai.indidea.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "project", schema = "indidea", catalog = "")
public class Project {
    private int id;
    private String title;
    private String subtitle;
    private Integer category;
    private String address;
    private String pic;
    private String video;
    private String story;
    private Integer targetpoint;
    private Date targetdate;
    private Date perdate;
    private int userid;
    private String publishlink;
    private String publishtitle;
    private Date createdat;
    private Date updatedat;
    private Integer status;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 125)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "subtitle", nullable = true, length = 225)
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Basic
    @Column(name = "category", nullable = true)
    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "pic", nullable = true, length = 100)
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Basic
    @Column(name = "video", nullable = true, length = 100)
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Basic
    @Column(name = "story", nullable = true, length = -1)
    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    @Basic
    @Column(name = "targetpoint", nullable = true)
    public Integer getTargetpoint() {
        return targetpoint;
    }

    public void setTargetpoint(Integer targetpoint) {
        this.targetpoint = targetpoint;
    }

    @Basic
    @Column(name = "targetdate", nullable = true)
    public Date getTargetdate() {
        return targetdate;
    }

    public void setTargetdate(Date targetdate) {
        this.targetdate = targetdate;
    }

    @Basic
    @Column(name = "perdate", nullable = true)
    public Date getPerdate() {
        return perdate;
    }

    public void setPerdate(Date perdate) {
        this.perdate = perdate;
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
    @Column(name = "publishlink", nullable = true, length = 100)
    public String getPublishlink() {
        return publishlink;
    }

    public void setPublishlink(String publishlink) {
        this.publishlink = publishlink;
    }

    @Basic
    @Column(name = "publishtitle", nullable = true, length = 15)
    public String getPublishtitle() {
        return publishtitle;
    }

    public void setPublishtitle(String publishtitle) {
        this.publishtitle = publishtitle;
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
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project that = (Project) o;

        if (id != that.id) return false;
        if (userid != that.userid) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (subtitle != null ? !subtitle.equals(that.subtitle) : that.subtitle != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (pic != null ? !pic.equals(that.pic) : that.pic != null) return false;
        if (video != null ? !video.equals(that.video) : that.video != null) return false;
        if (story != null ? !story.equals(that.story) : that.story != null) return false;
        if (targetpoint != null ? !targetpoint.equals(that.targetpoint) : that.targetpoint != null) return false;
        if (targetdate != null ? !targetdate.equals(that.targetdate) : that.targetdate != null) return false;
        if (perdate != null ? !perdate.equals(that.perdate) : that.perdate != null) return false;
        if (publishlink != null ? !publishlink.equals(that.publishlink) : that.publishlink != null) return false;
        if (publishtitle != null ? !publishtitle.equals(that.publishtitle) : that.publishtitle != null) return false;
        if (createdat != null ? !createdat.equals(that.createdat) : that.createdat != null) return false;
        if (updatedat != null ? !updatedat.equals(that.updatedat) : that.updatedat != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (subtitle != null ? subtitle.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (pic != null ? pic.hashCode() : 0);
        result = 31 * result + (video != null ? video.hashCode() : 0);
        result = 31 * result + (story != null ? story.hashCode() : 0);
        result = 31 * result + (targetpoint != null ? targetpoint.hashCode() : 0);
        result = 31 * result + (targetdate != null ? targetdate.hashCode() : 0);
        result = 31 * result + (perdate != null ? perdate.hashCode() : 0);
        result = 31 * result + userid;
        result = 31 * result + (publishlink != null ? publishlink.hashCode() : 0);
        result = 31 * result + (publishtitle != null ? publishtitle.hashCode() : 0);
        result = 31 * result + (createdat != null ? createdat.hashCode() : 0);
        result = 31 * result + (updatedat != null ? updatedat.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
