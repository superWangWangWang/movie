package cn.ddssbb.movie.controller;

import cn.ddssbb.movie.domain.Danmaku;
import cn.ddssbb.movie.domain.MovieDetail;
import cn.ddssbb.movie.domain.MovieSrc;
import cn.ddssbb.movie.service.impl.MovieServiceImpl;
import cn.ddssbb.movie.vo.MovieVO;
import cn.ddssbb.movie.vo.PageVo;
import cn.ddssbb.movie.vo.ResultVO;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.Base64Decoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class Public {
    @Autowired
    MovieServiceImpl movieServiceImpl;
    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mv = movieServiceImpl.toIndex();
        return mv;
    }
    @GetMapping("/detail")
    public ModelAndView detail(String id){
        ModelAndView mv = movieServiceImpl.toDetail(id);
        return mv;
    }

    @GetMapping("/play")
    public ModelAndView play(String id,String line){
        ModelAndView mv = movieServiceImpl.toPlay(id, line);
        return mv;
    }
    @GetMapping("getDanmaku")
    @ResponseBody
    public String getDanmaku(String id){
        System.out.println(id);
        List list = movieServiceImpl.getDanmakuById(id);
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setData(list);
        return JSON.toJSONString(resultVO);
    }
    @PostMapping("saveDanmaku")
    @ResponseBody
    public String saveDanmaku(@RequestBody Danmaku danmaku, HttpServletRequest httpServletRequest){
        String ip = httpServletRequest.getRemoteAddr();
////{"code":0,"data":{"_id":"5e79be2220943d0011e97f7a","player":"183f6653124c13ca6b924d021c233f52","author":"DIYgod","time":0,"text":"7777","color":16777215,"type":0,"ip":"27.42.98.18","referer":"https://www.moerats.com/archives/838/","date":1585036834936,"__v":0}}
        String json = movieServiceImpl.saveDanmaku(danmaku, ip);
        return json;
    }
    @RequestMapping("/toSearch")
    public ModelAndView toSearch(PageVo pageVo){
        ModelAndView mv = new ModelAndView();
        String name = pageVo.getName();
        if (!name.isEmpty()){

            Integer total = movieServiceImpl.getMovieCountLikeName(name);
            mv.addObject("total",total);
        }else {
            mv.addObject("total",0);
        }
        mv.setViewName("/public/search");

        //mv.addObject("name",name);
        return mv;
    }
    @RequestMapping("/search")
    public ModelAndView search(PageVo pageVo){
        System.out.println("===========search");
        String name = pageVo.getName();
        System.out.println("-----"+name);
        if (name.isEmpty()){
            ModelAndView mv = new ModelAndView();
            mv.setViewName("/others/search_null");
            return mv;
        }else {
            PageHelper.startPage(pageVo.getPageNum(),10);
            ModelAndView mv = movieServiceImpl.search(name);//查到的结果放进了mv   pageInfo
            mv.setViewName("/common/table_search");
            return mv;
        }


    }
    @RequestMapping("toCategory")
    public ModelAndView toCategory(PageVo pageVo){
        ModelAndView mv = new ModelAndView();
        String type = pageVo.getType();

        //查询总数渲染模板
        Integer total = movieServiceImpl.getMovieCountByCategory(pageVo);
        if (total==-1){
            mv.setViewName("/public/err");
        }else {
            mv.addObject("total",total);
            mv.setViewName("/public/category");
            if ("jp".equals(type)){
                mv.addObject("category","日韩动漫");
            }else if ("ch".equals(type)){
                mv.addObject("category","国产动漫");
            }else{
                mv.addObject("category","欧美动漫");
            }
        }
        return mv;
    }
    @RequestMapping("category")
    public ModelAndView category(PageVo pageVo){
        //根据category查
        ModelAndView mv = movieServiceImpl.getMovieDetailByCategory(pageVo);
        return mv;
    }

}
