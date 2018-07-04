package com.jk.model;

import java.io.Serializable;
import java.util.List;

public class Organization implements Serializable{
    private static final long serialVersionUID = -8192774428358860581L;
    private String id;
    private String ocode;  //  编码
    private String text; //  组织名称
    private String onickname; //简称
    private String pid;         // 父节点
    private String otype;          // 分类
    private String ofunctionary;  //负责人
    private String ocommand;    //副主管用户关联id
    private String otelphone;    // 电话
    private String otel;    // 内线
    private String ofax;    //  传真
    private String opostcode;    //邮编
    private String ourl;    //网址
    private String oaddress;    //地址
    private String ooptions;    //选项
    private String oinfor;    //  描述  （备注）
    private String oorder;    //  排序
    private List<Organization> children;
    private Integer statesss;//状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOcode() {
        return ocode;
    }

    public void setOcode(String ocode) {
        this.ocode = ocode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOnickname() {
        return onickname;
    }

    public void setOnickname(String onickname) {
        this.onickname = onickname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOtype() {
        return otype;
    }

    public void setOtype(String otype) {
        this.otype = otype;
    }

    public String getOfunctionary() {
        return ofunctionary;
    }

    public void setOfunctionary(String ofunctionary) {
        this.ofunctionary = ofunctionary;
    }

    public String getOcommand() {
        return ocommand;
    }

    public void setOcommand(String ocommand) {
        this.ocommand = ocommand;
    }

    public String getOtelphone() {
        return otelphone;
    }

    public void setOtelphone(String otelphone) {
        this.otelphone = otelphone;
    }

    public String getOtel() {
        return otel;
    }

    public void setOtel(String otel) {
        this.otel = otel;
    }

    public String getOfax() {
        return ofax;
    }

    public void setOfax(String ofax) {
        this.ofax = ofax;
    }

    public String getOpostcode() {
        return opostcode;
    }

    public void setOpostcode(String opostcode) {
        this.opostcode = opostcode;
    }

    public String getOurl() {
        return ourl;
    }

    public void setOurl(String ourl) {
        this.ourl = ourl;
    }

    public String getOaddress() {
        return oaddress;
    }

    public void setOaddress(String oaddress) {
        this.oaddress = oaddress;
    }

    public String getOoptions() {
        return ooptions;
    }

    public void setOoptions(String ooptions) {
        this.ooptions = ooptions;
    }

    public String getOinfor() {
        return oinfor;
    }

    public void setOinfor(String oinfor) {
        this.oinfor = oinfor;
    }

    public String getOorder() {
        return oorder;
    }

    public void setOorder(String oorder) {
        this.oorder = oorder;
    }

    public List<Organization> getChildren() {
        return children;
    }

    public void setChildren(List<Organization> children) {
        this.children = children;
    }

    public Integer getStatesss() {
        return statesss;
    }

    public void setStatesss(Integer statesss) {
        this.statesss = statesss;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;

        Organization that = (Organization) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


    @Override
    public String toString() {
        return "Organization{" +
                "id='" + id + '\'' +
                ", ocode='" + ocode + '\'' +
                ", text='" + text + '\'' +
                ", onickname='" + onickname + '\'' +
                ", pid='" + pid + '\'' +
                ", otype='" + otype + '\'' +
                ", ofunctionary='" + ofunctionary + '\'' +
                ", ocommand='" + ocommand + '\'' +
                ", otelphone='" + otelphone + '\'' +
                ", otel='" + otel + '\'' +
                ", ofax='" + ofax + '\'' +
                ", opostcode='" + opostcode + '\'' +
                ", ourl='" + ourl + '\'' +
                ", oaddress='" + oaddress + '\'' +
                ", ooptions='" + ooptions + '\'' +
                ", oinfor='" + oinfor + '\'' +
                ", oorder='" + oorder + '\'' +
                ", children=" + children +
                ", statesss=" + statesss +
                '}';
    }
}
