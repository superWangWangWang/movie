package cn.ddssbb.movie.domain;

public class Danmaku {
    private String id;//电影名id
    private Double time;//出现时间
    private String text;//内容
    private Integer color;//字体颜色
    private Integer type;//类型 弹幕位置（顶部=1，滚动=0，底部=2）
    private String author;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Danmaku{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", text='" + text + '\'' +
                ", color=" + color +
                ", type=" + type +
                ", author='" + author + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
