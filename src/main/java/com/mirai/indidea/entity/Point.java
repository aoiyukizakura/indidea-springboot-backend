package com.mirai.indidea.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "point", schema = "indidea", catalog = "")
public class Point {
    private int id;
    private Timestamp createdat;
    private Timestamp updatedat;
    private int point;
    private int status = 1;
    private User user;
    private String serialnumber;
    private String ordernumber;

    public Point() {
    }

    public Point(int point, User user, String order) {
        this.point = point;
        this.user = user;
        this.ordernumber = order;
    }

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
    @Column(name = "point", nullable = false)
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Basic
    @Column(name = "serialnumber", nullable = true, length = 50)
    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    @Basic
    @Column(name = "ordernumber", nullable = true, length = 50)
    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point1 = (Point) o;

        if (id != point1.id) return false;
        if (point != point1.point) return false;
        if (status != point1.status) return false;
        if (user != point1.user) return false;
        if (createdat != null ? !createdat.equals(point1.createdat) : point1.createdat != null) return false;
        if (updatedat != null ? !updatedat.equals(point1.updatedat) : point1.updatedat != null) return false;
        if (serialnumber != null ? !serialnumber.equals(point1.serialnumber) : point1.serialnumber != null)
            return false;
        if (ordernumber != null ? !ordernumber.equals(point1.ordernumber) : point1.ordernumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (createdat != null ? createdat.hashCode() : 0);
        result = 31 * result + (updatedat != null ? updatedat.hashCode() : 0);
        result = 31 * result + point;
        result = 31 * result + status;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (serialnumber != null ? serialnumber.hashCode() : 0);
        result = 31 * result + (ordernumber != null ? ordernumber.hashCode() : 0);
        return result;
    }
}
