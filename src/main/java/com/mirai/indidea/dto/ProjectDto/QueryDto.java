package com.mirai.indidea.dto.ProjectDto;

import lombok.Data;

@Data
public class QueryDto {
    private Integer projectId;
    private Integer flag; //0获取编辑的项目信息1获取正常的项目

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
