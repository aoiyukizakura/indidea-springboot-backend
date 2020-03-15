package com.mirai.indidea.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "reward", schema = "indidea", catalog = "")
public class Reward {
    private int id;
    private String name;
    private int status = 1;
    private Integer point;
    private String des;
    private Date expected;
    private String items;
    private Project project;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 125)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "point", nullable = true)
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Basic
    @Column(name = "des", nullable = true, length = 225)
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Basic
    @Column(name = "expected", nullable = true)
    public Date getExpected() {
        return expected;
    }

    public void setExpected(Date expected) {
        this.expected = expected;
    }

    @Basic
    @Column(name = "items", nullable = true, length = 1000)
    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    @ManyToOne
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reward reward = (Reward) o;

        if (id != reward.id) return false;
        if (status != reward.status) return false;
        if (project != reward.project) return false;
        if (name != null ? !name.equals(reward.name) : reward.name != null) return false;
        if (point != null ? !point.equals(reward.point) : reward.point != null) return false;
        if (des != null ? !des.equals(reward.des) : reward.des != null) return false;
        if (expected != null ? !expected.equals(reward.expected) : reward.expected != null) return false;
        if (items != null ? !items.equals(reward.items) : reward.items != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (point != null ? point.hashCode() : 0);
        result = 31 * result + (des != null ? des.hashCode() : 0);
        result = 31 * result + (expected != null ? expected.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (project != null ? project.hashCode() : 0);
        return result;
    }
}
