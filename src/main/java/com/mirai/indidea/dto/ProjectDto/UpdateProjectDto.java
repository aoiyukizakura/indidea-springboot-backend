package com.mirai.indidea.dto.ProjectDto;

import com.mirai.indidea.entity.Category;
import com.mirai.indidea.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateProjectDto {

    private int id;
    private String title;
    private String subtitle;
    private String address;
    private String pic;
    private String video;
    private String story;
    private Integer targetpoint;
    private Date targetdate;
    private Date perdate;
    private User owner;
    private String publishlink;
    private String publishtitle;
    private int categoryId;

}
