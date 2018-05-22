package administrator.example.com.fragmentdemo.Movie.Model;

/**
 * Created by apple on 18/5/22.
 */

public interface IMovieModel {
    void loadMovie(String total,
                  IOnLoadListener iOnLoadListener);
}
