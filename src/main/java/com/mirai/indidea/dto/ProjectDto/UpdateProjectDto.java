package com.mirai.indidea.dto.ProjectDto;

import com.mirai.indidea.entity.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UpdateProjectDto {

    private Integer id;
    private String title;
    private String subtitle;
    private String address;
    private String pic;
    private String video;
    private String story;
    private Integer targetpoint;
    private Date targetdate;
    private Date perdate;
    private String publishlink;
    private String publishtitle;
    private Integer categoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Integer getTargetpoint() {
        return targetpoint;
    }

    public void setTargetpoint(Integer targetpoint) {
        this.targetpoint = targetpoint;
    }

    public Date getTargetdate() {
        return targetdate;
    }

    public void setTargetdate(Date targetdate) {
        this.targetdate = targetdate;
    }

    public Date getPerdate() {
        return perdate;
    }

    public void setPerdate(Date perdate) {
        this.perdate = perdate;
    }

    public String getPublishlink() {
        return publishlink;
    }

    public void setPublishlink(String publishlink) {
        this.publishlink = publishlink;
    }

    public String getPublishtitle() {
        return publishtitle;
    }

    public void setPublishtitle(String publishtitle) {
        this.publishtitle = publishtitle;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
