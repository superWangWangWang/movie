package cn.ddssbb.movie.service;

import cn.ddssbb.movie.domain.Danmaku;
import cn.ddssbb.movie.domain.MovieDetail;
import cn.ddssbb.movie.domain.MovieSrc;
import cn.ddssbb.movie.vo.PageVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface MovieService {
    ModelAndView toIndex();//跳转到首页
    ModelAndView toDetail(String id);//跳转到视频详情页
    ModelAndView toPlay(String id,String line);//跳转到播放页


    ModelAndView search(String name);
    Integer getMovieCountLikeName(String name);
    Integer getMovieCountByCategory(PageVo pageVo);
    String saveDanmaku(Danmaku danmaku,String ip);//保存弹幕
    List getDanmakuById(String id);//获取弹幕
    MovieSrc checkDanmakuExist(String tableNmae,String movieId,String part);//检查插入的弹幕影片是否存在
    List<MovieDetail> getTodayMovieByCategory(String category);
    List<MovieDetail> getMovieDetailLikeName(String name);//根据影片名查询影片详情
    ModelAndView getMovieDetailByCategory(PageVo pageVo);

}
