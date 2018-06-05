package administrator.example.com.fragmentdemo.Movie.Model;

        import administrator.example.com.fragmentdemo.Bean.MovieBean;
        import administrator.example.com.fragmentdemo.Bean.NewsBean;

/**
 * Created by apple on 18/5/22.
 */

public interface IOnLoadListener {
    void success(MovieBean movieBean);
    void fail(Throwable e);
}
