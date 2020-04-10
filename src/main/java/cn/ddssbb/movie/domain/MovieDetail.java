package cn.ddssbb.movie.domain;

import java.util.Date;

public class MovieDetail {
//id            int(11)
    private Integer No;
//movie_id      varchar(32)     片名md5-32位
    private String id;
//movie_name    varchar(64)     片名
    private String name;
//actor         varchar(255)    主要演员
    private String actor;
//director      varchar(32)     导演
    private String director;
//area          varchar(16)     地区
    private String area;
//language      varchar(16)     语言
    private String language;
//publish_time  varchar(16)     发行时间
    private String publishTime;
//remarks       varchar(16)     片子备注：连载中，已完结
    private String remarks;
//show          int(1)          片子状态，1=显示0=不显示
    private Integer show;
//desc          varchar(1024)   简介
    private String desc;
//cover         varchar(128)    本地封面图片地址
    private String cover;
//category      varchar(16)     类别--日韩，欧美，国产
    private String category;
//from          varchar(16)     采集来源
    private String from;
//score         decimal(3,1)    评分
    private Double score;
//create_time   timestamp       入库时间
    private Date createTime;
//update_time   timestamp       数据更新时间
    private Date updateTime;

    public Integer getNo() {
        return No;
    }

    public void setNo(Integer no) {
        No = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MovieDetail{" +
                "No=" + No +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", actor='" + actor + '\'' +
                ", director='" + director + '\'' +
                ", area='" + area + '\'' +
                ", language='" + language + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", remarks='" + remarks + '\'' +
                ", show=" + show +
                ", desc='" + desc + '\'' +
                ", cover='" + cover + '\'' +
                ", category='" + category + '\'' +
                ", from='" + from + '\'' +
                ", score=" + score +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
