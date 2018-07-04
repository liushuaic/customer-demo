package com.jk.model;

import java.io.Serializable;

/**
 *左侧菜单树
 */
public class TreeModel implements Serializable{

    private static final long serialVersionUID = -4597802531260322271L;
    private String id;
    private String pid;
    private String text;
    private String href;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreeModel)) return false;

        TreeModel treeModel = (TreeModel) o;

        return id.equals(treeModel.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "TreeModel{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", text='" + text + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
