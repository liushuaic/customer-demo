package com.jk.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
    private static final long serialVersionUID = 7576003686041816350L;
    private String userid;
    private String username;
    private Date createdatetime;
    private Date modifydatetime;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public Date getModifydatetime() {
        return modifydatetime;
    }

    public void setModifydatetime(Date modifydatetime) {
        this.modifydatetime = modifydatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return userid.equals(user.userid);
    }

    @Override
    public int hashCode() {
        return userid.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", createdatetime=" + createdatetime +
                ", modifydatetime=" + modifydatetime +
                '}';
    }
}
