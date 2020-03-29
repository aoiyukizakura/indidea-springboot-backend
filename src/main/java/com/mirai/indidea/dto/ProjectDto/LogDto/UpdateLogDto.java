package com.mirai.indidea.dto.ProjectDto.LogDto;

import lombok.Data;

import java.util.Objects;

@Data
public class UpdateLogDto {
    private String content;
    private String title;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateLogDto that = (UpdateLogDto) o;
        return Objects.equals(content, that.content) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, title);
    }
}
