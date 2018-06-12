package administrator.example.com.fragmentdemo.Http;

import administrator.example.com.fragmentdemo.Bean.MovieBean;
import administrator.example.com.fragmentdemo.Bean.NewsBean;
import administrator.example.com.fragmentdemo.Bean.TodayBean;
import administrator.example.com.fragmentdemo.Bean.VideoUrlBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by apple on 18/5/22.
 */

public interface RetrofitService {
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<NewsBean> getNews(@Path("type") String type,
                           @Path("id") String id,
                           @Path("startPage") int startPage);

    /*
    * https://api.douban.com/v2/movie/in_theaters*/
    @GET("movie/{total}")
    Observable<MovieBean> getMovie(@Path("total") String total);

    @GET("news/feed/v51/")
    Observable<TodayBean> getToday(@Query("category") String category);

    @GET
    Observable<VideoUrlBean> getVideoUrl(@Url String url);
}
