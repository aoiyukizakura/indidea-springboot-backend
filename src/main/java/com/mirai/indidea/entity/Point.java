package com.mirai.indidea.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "point", schema = "indidea", catalog = "")
public class Point {
    private int id;
    private Date createdat;
    private Date updatedat;
    private int point;
    private int status;
    private int userid;
    private String serialnumber;
    private String ordernumber;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Basic
    @Column(name = "userid", nullable = false)
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

        Point that = (Point) o;

        if (id != that.id) return false;
        if (point != that.point) return false;
        if (status != that.status) return false;
        if (userid != that.userid) return false;
        if (createdat != null ? !createdat.equals(that.createdat) : that.createdat != null) return false;
        if (updatedat != null ? !updatedat.equals(that.updatedat) : that.updatedat != null) return false;
        if (serialnumber != null ? !serialnumber.equals(that.serialnumber) : that.serialnumber != null) return false;
        if (ordernumber != null ? !ordernumber.equals(that.ordernumber) : that.ordernumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (createdat != null ? createdat.hashCode() : 0);
        result = 31 * result + (updatedat != null ? updatedat.hashCode() : 0);
        result = 31 * result + point;
        result = 31 * result + status;
        result = 31 * result + userid;
        result = 31 * result + (serialnumber != null ? serialnumber.hashCode() : 0);
        result = 31 * result + (ordernumber != null ? ordernumber.hashCode() : 0);
        return result;
    }
}
