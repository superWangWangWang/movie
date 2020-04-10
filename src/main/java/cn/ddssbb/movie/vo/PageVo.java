package cn.ddssbb.movie.vo;

public class PageVo {
    String type;
    String name;
    Integer pageNum;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "PageVo{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", pageNum=" + pageNum +
                '}';
    }
}
