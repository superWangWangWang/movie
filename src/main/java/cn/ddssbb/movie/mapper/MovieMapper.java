package cn.ddssbb.movie.mapper;

        import cn.ddssbb.movie.domain.Danmaku;
        import cn.ddssbb.movie.domain.MovieDetail;
        import cn.ddssbb.movie.domain.MovieSrc;
        import com.github.pagehelper.Page;
        import org.apache.ibatis.annotations.Insert;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Select;

        import java.util.List;

@Mapper
public interface MovieMapper {
    /**
     * 获取数据库的影片的数量（根据movie_detail表）
     * @return
     */
    @Select("SELECT COUNT(*) AS `count` FROM `movie_detail`")
    Integer getMovieCount();

    /**
     * 根据视频名查询视频详情-》模糊查询
     * @param name
     * @return
     */
    @Select("select * from `movie_detail` where `name` LIKE CONCAT('%',#{name},'%') order by `update_time` desc")
    List<MovieDetail> getMovieDetailLikeNameOrderUpdateTimeDesc(String name);

    /**
     *
     * @param name
     * @return
     */
    @Select("select count(*) as `count` from `movie_detail` where `name` like concat('%',#{name},'%')")
    Integer getMovieCountLikeName(String name);
    /**
     * 根据分类查当日的更新视频
     * @param category
     * @return
     */
    @Select("select * from `movie_detail` where DATE(update_time) = CURDATE() and `category` LIKE CONCAT('%',#{category},'%')")
    List<MovieDetail> getTodayMovieByCategory(String category);
    /**
     * 根据分类的类目名（日韩动漫、国产动漫、欧美动漫）查询片子详情,更新时间倒序
     * @param category
     * @return
     */
    @Select("select * from movie_detail where `category` = #{category} and `show` = 1 order by update_time desc")
    List<MovieDetail> getMovieDetailByCategory(String category);

    /**
     * 根据视频id查询视频详情表信息
     * @param movieId
     * @return
     */
    @Select("select * from movie_detail where `id` = #{movieId} and `show` = 1")
    MovieDetail getMovieDetailByMovieId(String movieId);

    /**
     * 获取推荐视频，根据评分高->低
     * @return
     */
    @Select("select * from movie_detail where `show` = 1 order by score desc")
    List<MovieDetail> getRecommend();

    /**
     * 根据类目（日韩动漫、国产动漫、欧美动漫）查询类目下的视频详情，排序根据评分高->低
     * @param category
     * @return
     */
    @Select("select * from movie_detail where category = #{category} and `show` = 1 order by score desc")
    List<MovieDetail> getMovieDetailByCategoryOrderScoreDesc(String category);
    @Select("select count(*) from `movie_detail` where `category` = #{category}")
    Integer getMovieCountByCategory(String category);
    /**
     * 根据视频id，以及播放表名，查询播放链接，根据part部分倒序
     * @param tableName
     * @param movieId
     * @return
     */
    @Select("select * from ${tableName} where `movie_id` = #{movieId} and `show` = 1 order by part desc")
    List<MovieSrc> getSrcListByMovieIdOrderPartDesc(String tableName,String movieId);

    /**
     * 根据表名和播放链接id查询播放链接详情
     * @param tableName
     * @param srcId
     * @return
     */
    @Select("select * from ${tableName} where `id` = #{srcId} and `show` = 1")
    MovieSrc getMovieSrcDetailBySrcId(String tableName,String srcId);

    /**
     * 新增弹幕
     * @param danmaku
     * @return
     */
    @Insert("Insert into `movie_danmaku` (`id`,`time`,`text`,`color`,`type`,`ip`) values (#{id},#{time},#{text},#{color},#{type},#{ip})")
    public Integer saveDanmaku(Danmaku danmaku);

    /**
     * 根据弹幕id获取弹幕
     * @param id
     * @return
     */
    @Select("select * from `movie_danmaku` where `id` = #{id} and `show` = 1")
    List<Danmaku> getDanmakuById(String id);

    /**
     * 检测弹幕是否可插入，排除非法插入不存在的视频以及其分集的弹幕，
     * @param tableName
     * @param movieId
     * @param part
     * @return
     */
    @Select("select * from ${tableName} where `movie_id` = #{movieId} and `part` = #{part}")
    MovieSrc checkDanmakuExist(String tableName,String movieId,String part);
}
