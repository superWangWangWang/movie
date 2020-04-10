package cn.ddssbb.movie.vo;

import java.util.List;

public class ResultVO {
    private Integer code;
    private List data;

    @Override
    public String toString() {
        return "ResultVO{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
