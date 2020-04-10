package cn.ddssbb.movie.domain;

import java.util.Date;

public class MovieSrc {
    //id           int(11)
    private Integer No;
    //src_id       varchar(32)  片名+集名+src的32位md5
    private String id;
    //src_name     varchar(64)  例如 第01集
    private String name;
    //src          varchar(512) 视频地址
    private String src;
    //movie_name   varchar(64)  对应的电影名
    private String movieName;
    //movie_id     varchar(32)  电影名的32位md5
    private String movieId;
    //type         varchar(16)  类型 m3u8,mp4
    private String type;
    //from         varchar(32)  来源
    private String from;
    //part         int(4)       序号用于排序
    private Integer part;
    //show         int(1)       是否展示
    private Integer show;
    //create_time  timestamp    创建时间
    private Date createTime;
    //update_time  timestamp    视频地址的更新时间
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

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Integer getPart() {
        return part;
    }

    public void setPart(Integer part) {
        this.part = part;
    }

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
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
        return "MovieSrc{" +
                "No=" + No +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", src='" + src + '\'' +
                ", movieName='" + movieName + '\'' +
                ", movieId='" + movieId + '\'' +
                ", type='" + type + '\'' +
                ", from='" + from + '\'' +
                ", part=" + part +
                ", show=" + show +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
