package administrator.example.com.fragmentdemo.Movie.View;

import administrator.example.com.fragmentdemo.Bean.MovieBean;
import administrator.example.com.fragmentdemo.Bean.NewsBean;

/**
 * Created by apple on 18/5/22.
 */

public interface IMovieView {
    void showMovie(MovieBean movieBean);
    void hideDialog();
    void showDialog();
    void showErrorMsg(Throwable throwable);
}
