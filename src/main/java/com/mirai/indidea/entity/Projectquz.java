package com.mirai.indidea.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "projectquz", schema = "indidea", catalog = "")
public class Projectquz {
    private int id;
    private User quser;
    private Project project;
    private String quzcontent;
    private String ancontent;
    private Timestamp createdat;
    private Timestamp updatedat;
    private int status = 0;

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
    public User getQuser() {
        return quser;
    }

    public void setQuser(User quser) {
        this.quser = quser;
    }

    @ManyToOne
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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

        Projectquz that = (Projectquz) o;

        if (id != that.id) return false;
        if (quser != that.quser) return false;
        if (project != that.project) return false;
        if (status != that.status) return false;
        if (quzcontent != null ? !quzcontent.equals(that.quzcontent) : that.quzcontent != null) return false;
        if (ancontent != null ? !ancontent.equals(that.ancontent) : that.ancontent != null) return false;
        if (createdat != null ? !createdat.equals(that.createdat) : that.createdat != null) return false;
        if (updatedat != null ? !updatedat.equals(that.updatedat) : that.updatedat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (quser != null ? quser.hashCode() : 0);
        result = 31 * result + (project != null ? project.hashCode() : 0);
        result = 31 * result + (quzcontent != null ? quzcontent.hashCode() : 0);
        result = 31 * result + (ancontent != null ? ancontent.hashCode() : 0);
        result = 31 * result + (createdat != null ? createdat.hashCode() : 0);
        result = 31 * result + (updatedat != null ? updatedat.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }
}
