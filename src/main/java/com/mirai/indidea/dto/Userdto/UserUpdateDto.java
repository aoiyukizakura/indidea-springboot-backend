package com.mirai.indidea.dto.Userdto;

import lombok.Data;

@Data
public class UserUpdateDto {
    private String username;
    private String password;
    private String website;
    private String address;
    private String des;
    private String vanityname;
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getVanityname() {
        return vanityname;
    }

    public void setVanityname(String vanityname) {
        this.vanityname = vanityname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
