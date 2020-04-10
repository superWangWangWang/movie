package cn.ddssbb.movie.service.impl;

import cn.ddssbb.movie.domain.Danmaku;
import cn.ddssbb.movie.domain.MovieDetail;
import cn.ddssbb.movie.domain.MovieSrc;
import cn.ddssbb.movie.mapper.MovieMapper;
import cn.ddssbb.movie.service.MovieService;
import cn.ddssbb.movie.vo.MovieVO;
import cn.ddssbb.movie.vo.PageVo;
import cn.ddssbb.movie.vo.ResultVO;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieMapper movieMapper;

    /**
     * 处理首页
     * @param
     * @return
     */
    @Override
    public ModelAndView toIndex() {
        ModelAndView mv = new ModelAndView();
        //获取推荐视频
        PageHelper.startPage(1,8);
        List<MovieDetail> recommend = movieMapper.getRecommend();
        mv.addObject("recommend",recommend);

        //获取日韩动漫信息，视图模型 movieListJP
        PageHelper.startPage(1,10);
        String category = "日韩动漫";
        List<MovieDetail> movieListJP = movieMapper.getMovieDetailByCategory(category);
        mv.addObject("movieListJP",movieListJP);
        //取得前10的视频
        mv = top10(mv, category);
        //获取国产动漫信息，视图模型 movieListCH
        PageHelper.startPage(1,10);
        category = "国产动漫";
        List<MovieDetail> movieListCH = movieMapper.getMovieDetailByCategory(category);
        mv.addObject("movieListCH",movieListCH);
        //取得前10的视频
        mv = top10(mv, category);

        //获取欧美动漫信息，视图模型 movieListEN
        PageHelper.startPage(1,10);
        category = "欧美动漫";
        List<MovieDetail> movieListEN = movieMapper.getMovieDetailByCategory(category);
        mv.addObject("movieListEN",movieListEN);
        //取得前10的视频
        mv = top10(mv, category);
        //获取影片的数量
        Integer count = movieMapper.getMovieCount();
        System.out.println("==========="+count);
        mv.addObject("count",count);
        //获取当日的更新视频
        List<MovieDetail> todayMovie = getTodayMovieByCategory("");
        //List<MovieDetail> todayMovie = getTodayMovie();
        mv.addObject("todayMovie",todayMovie);
        //获取当日日韩更新
        List<MovieDetail> todayJP = getTodayMovieByCategory("日韩动漫");
        List<MovieDetail> todayCH = getTodayMovieByCategory("国产动漫");
        List<MovieDetail> todayEN = getTodayMovieByCategory("欧美动漫");
        mv.addObject("todayJP",todayJP);
        mv.addObject("todayCH",todayCH);
        mv.addObject("todayEN",todayEN);
        mv.setViewName("public/index");
        return mv;
    }

    @Override
    public ModelAndView toDetail(String id) {
        ModelAndView mv = new ModelAndView();
        //查询数据库 根据id查
        MovieDetail movieDetail = movieMapper.getMovieDetailByMovieId(id);
        if (movieDetail != null){
            mv.setViewName("public/detail");
            MovieVO movieVO = new MovieVO();


            BeanUtils.copyProperties(movieDetail,movieVO);
            mv.addObject("movieVO",movieVO);
            //获取播放线路和链接
            List<MovieSrc> movie_src_1 = movieMapper.getSrcListByMovieIdOrderPartDesc("movie_src_1",id);
            List<MovieSrc> movie_src_2 = movieMapper.getSrcListByMovieIdOrderPartDesc("movie_src_2",id);
            List<MovieSrc> movie_src_3 = movieMapper.getSrcListByMovieIdOrderPartDesc("movie_src_3",id);
            mv.addObject("src1",movie_src_1);
            mv.addObject("src2",movie_src_2);
            mv.addObject("src3",movie_src_3);
            //System.out.println(movie_src_1);
            //System.out.println(movie_src_2);
            //System.out.println(movie_src_3);
            //获取推荐视频
            PageHelper.startPage(1,8);
            List<MovieDetail> recommend = movieMapper.getRecommend();
            mv.addObject("recommend",recommend);
            //获取影片的数量
            Integer count = movieMapper.getMovieCount();
            System.out.println("==========="+count);
            mv.addObject("count",count);
            //获取当日的更新视频
            List<MovieDetail> todayMovie = getTodayMovieByCategory("");
            mv.addObject("todayMovie",todayMovie);
        }else {
            //非法id，不存在对应的电影详情
            mv.setViewName("public/err");
        }

        return mv;
    }

    @Override
    public ModelAndView toPlay(String id, String line) {
        ModelAndView mv = new ModelAndView();

        //获取推荐视频
        PageHelper.startPage(1,8);
        List<MovieDetail> recommend = movieMapper.getRecommend();
        mv.addObject("recommend",recommend);

        MovieSrc movieSrc;
        //根据线路和id查询播放链接的信息
        if ("1".equals(line)){
            movieSrc = movieMapper.getMovieSrcDetailBySrcId("movie_src_1",id);
            mv.addObject("line","1");
        }else if("2".equals(line)){
            movieSrc = movieMapper.getMovieSrcDetailBySrcId("movie_src_2",id);
            mv.addObject("line","2");
        }else if("3".equals(line)){
            movieSrc = movieMapper.getMovieSrcDetailBySrcId("movie_src_3",id);
            mv.addObject("line","3");
        }else {
            mv.setViewName("public/err");
            return mv;
        }
        String movieId = movieSrc.getMovieId();
        MovieDetail movieDetail = movieMapper.getMovieDetailByMovieId(movieId);
        MovieVO movieVO = new MovieVO();
        BeanUtils.copyProperties(movieDetail,movieVO);

        movieVO.setMovieSrc(movieSrc);
        mv.addObject("movieVO",movieVO);
        //System.out.println(movieVO);

        //获取播放线路和链接
        List<MovieSrc> movie_src_1 = movieMapper.getSrcListByMovieIdOrderPartDesc("movie_src_1",movieId);
        List<MovieSrc> movie_src_2 = movieMapper.getSrcListByMovieIdOrderPartDesc("movie_src_2",movieId);
        List<MovieSrc> movie_src_3 = movieMapper.getSrcListByMovieIdOrderPartDesc("movie_src_3",movieId);
        mv.addObject("src1",movie_src_1);
        mv.addObject("src2",movie_src_2);
        mv.addObject("src3",movie_src_3);

        mv.setViewName("public/play");
        return mv;
    }


    @Override
    public ModelAndView search(String name) {
        ModelAndView mv = new ModelAndView();
        List<MovieDetail> list = movieMapper.getMovieDetailLikeNameOrderUpdateTimeDesc(name);
        PageInfo pageInfo = new PageInfo(list);
        System.out.println(pageInfo);
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    @Override
    public Integer getMovieCountLikeName(String name) {
        return movieMapper.getMovieCountLikeName(name);
    }

    @Override
    public Integer getMovieCountByCategory(PageVo pageVo) {
        String type = pageVo.getType();
        String category;
        if ("ch".equals(type)){
            category = "国产动漫";
        }else if ("jp".equals(type)){
            category = "日韩动漫";
        }else if ("en".equals(type)){
            category = "欧美动漫";
        }else {
            category = "";
        }

        if (category!=""){
            //PageHelper.startPage(1,42);
            Integer total = movieMapper.getMovieCountByCategory(category);
            return total;
        }else {
           return -1;
        }

    }

    public ModelAndView top10(ModelAndView mv,String category){
        PageHelper.startPage(1,10);
        List<MovieDetail> top10Movie = movieMapper.getMovieDetailByCategoryOrderScoreDesc(category);
        if (category.equals("日韩动漫")){
            mv.addObject("top10JP",top10Movie);
        }else if (category.equals("国产动漫")){
            mv.addObject("top10CH",top10Movie);
        }else {
            mv.addObject("top10EN",top10Movie);
        }
        return mv;
    }

    /**
     * 保存弹幕
     * @param danmaku
     * @return
     */
    @Transient
    public String saveDanmaku(Danmaku danmaku,String ip){
        //String ip = httpServletRequest.getRemoteAddr();
        ResultVO rv = new ResultVO();
        if ("0:0:0:0:0:0:0:1".equals(ip)){
            ip = "127.0.0.1";
        }
        danmaku.setIp(ip);
        //需要查询是否存在该视频
        String id = danmaku.getId();
        if (id.indexOf("-")!=-1){//查到id中含有-
            //分割，查询数据库是不是有这部片子且有这个分集，正常则允许插入弹幕，需要注册控制到时候再说
            String[] ids = id.split("-");//视频id-线路-part(集数)
            String tableName = "";
            if (ids.length==3){
                String movieId = ids[0];//视频id
                String line = ids[1];//线路
                String part = ids[2];//分集
                //根据视频id和分集查询
                if ("1".equals(line)){
                    tableName = "movie_src_1";
                }else if ("2".equals(line)){
                    tableName = "movie_src_2";
                }else if ("3".equals(line)){
                    tableName = "movie_src_3";
                }else {
                    //失败的状态码为1
                    rv.setCode(1);
                }
                if (tableName!=""){
                    //检测插入弹幕可行性
                    MovieSrc res = checkDanmakuExist(tableName, movieId, part);
                    if (res == null){
                        rv.setCode(1);
                    }else {
                        //可以插入
                        Integer insertLine = movieMapper.saveDanmaku(danmaku);
                        if (insertLine > 0){
                            //成功的状态码为0
                            rv.setCode(0);
                        }else {
                            //失败的状态码为1
                            rv.setCode(1);
                        }
                    }
                }
            }else {
                //失败的状态码为1
                rv.setCode(1);
            }
        }else {
            //失败的状态码为1
            rv.setCode(1);
        }
//{"code":0,"data":{"_id":"5e79be2220943d0011e97f7a","player":"183f6653124c13ca6b924d021c233f52","author":"DIYgod","time":0,"text":"7777","color":16777215,"type":0,"ip":"27.42.98.18","referer":"https://www.moerats.com/archives/838/","date":1585036834936,"__v":0}}
        return JSON.toJSONString(rv);
    }

    /**
     * 根据id获取弹幕
     * @param id
     * @return
     */
    @Override
    public List getDanmakuById(String id) {
        List<Danmaku> list = movieMapper.getDanmakuById(id);
        List res = new ArrayList();
        List commonDanmaku = new ArrayList();//公告弹幕
        commonDanmaku.add(1);//出现时间
        commonDanmaku.add(1);//滚动还是什么 0=滚动 1=顶部 2=底部
        commonDanmaku.add(15024726);//颜色
        commonDanmaku.add("admin");
        commonDanmaku.add("公告：请不要相信视频中出现的任意广告");
        res.add(commonDanmaku);


        for (Danmaku d:list) {
            ////时间，弹幕类型（顶部），颜色，内容
            List tem = new ArrayList<>();
            tem.add(d.getTime());
            tem.add(d.getType());
            tem.add(d.getColor());
            tem.add("");
            tem.add(d.getText());
            res.add(tem);
        }

        return res;
    }

    @Override
    public MovieSrc checkDanmakuExist(String tableNmae, String movieId, String part) {
        MovieSrc movieSrc = movieMapper.checkDanmakuExist(tableNmae, movieId, part);

        return movieSrc;
    }

    @Override
    public List<MovieDetail> getTodayMovieByCategory(String category) {
        return movieMapper.getTodayMovieByCategory(category);
    }

    /**
     * 根据影片名查询影片详情
     * @param name
     * @return
     */
    @Override
    public List getMovieDetailLikeName(String name) {
        System.out.println(name);
        List<MovieDetail> movies = movieMapper.getMovieDetailLikeNameOrderUpdateTimeDesc(name);

        //PageInfo<MovieDetail> pageInfo = new PageInfo<>(movies);
        return movies;
    }

    @Override
    public ModelAndView getMovieDetailByCategory(PageVo pageVo) {
        ModelAndView mv = new ModelAndView();
        String type = pageVo.getType();
        Integer pageNum = pageVo.getPageNum();
        String category;
        if ("ch".equals(type)){
            category = "国产动漫";
        }else if ("jp".equals(type)){
            category = "日韩动漫";
        }else if ("en".equals(type)){
            category = "欧美动漫";
        }else {
            category = "";
        }
        if (category==""){
            mv.setViewName("/public/err");
        }else {
            PageHelper.startPage(pageNum,42);
            List<MovieDetail> list = movieMapper.getMovieDetailByCategory(category);
            mv.addObject("movieList",list);
            mv.setViewName("/common/table_category");

        }

        return mv;
    }


}
