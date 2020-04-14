package com.mirai.indidea.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "apply", schema = "indidea", catalog = "")
public class Apply {
    private int id;
    private int status = 1;
    private Timestamp createdat;
    private Timestamp updatedat;
    private User user;
    private String editby;

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
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Basic
    @Column(name = "editby", nullable = true)
    public String getEditby() {
        return editby;
    }

    public void setEditby(String editby) {
        this.editby = editby;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apply apply = (Apply) o;

        if (id != apply.id) return false;
        if (status != apply.status) return false;
        if (user != apply.user) return false;
        if (createdat != null ? !createdat.equals(apply.createdat) : apply.createdat != null) return false;
        if (updatedat != null ? !updatedat.equals(apply.updatedat) : apply.updatedat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + status;
        result = 31 * result + (createdat != null ? createdat.hashCode() : 0);
        result = 31 * result + (updatedat != null ? updatedat.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
