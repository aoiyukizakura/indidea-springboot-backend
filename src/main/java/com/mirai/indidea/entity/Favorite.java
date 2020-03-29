package com.mirai.indidea.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "favorite", schema = "indidea", catalog = "")
public class Favorite {
    private int id;
    private User user;
    private Project project;
    private Timestamp createdat;
    private Timestamp updatedat;
    private int status = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Basic
    @Column(name = "createdat", nullable = true)
    @CreatedDate
    public Timestamp getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Timestamp createdat) {
        this.createdat = createdat;
    }

    @Basic
    @Column(name = "updatedat", nullable = true)
    @LastModifiedDate
    public Timestamp getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Timestamp updatedat) {
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

        Favorite favorite = (Favorite) o;

        if (id != favorite.id) return false;
        if (user != favorite.user) return false;
        if (project != favorite.project) return false;
        if (status != favorite.status) return false;
        if (createdat != null ? !createdat.equals(favorite.createdat) : favorite.createdat != null) return false;
        if (updatedat != null ? !updatedat.equals(favorite.updatedat) : favorite.updatedat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (project != null ? project.hashCode() : 0);
        result = 31 * result + (createdat != null ? createdat.hashCode() : 0);
        result = 31 * result + (updatedat != null ? updatedat.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }
}
