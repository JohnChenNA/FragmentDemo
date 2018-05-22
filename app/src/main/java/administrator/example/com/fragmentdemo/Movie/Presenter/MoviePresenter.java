package administrator.example.com.fragmentdemo.Movie.Presenter;

import administrator.example.com.fragmentdemo.Bean.MovieBean;
import administrator.example.com.fragmentdemo.Bean.NewsBean;
import administrator.example.com.fragmentdemo.Http.Api;
import administrator.example.com.fragmentdemo.Movie.Model.IMovieModel;
import administrator.example.com.fragmentdemo.Movie.Model.IOnLoadListener;
import administrator.example.com.fragmentdemo.Movie.Model.MovieModel;
import administrator.example.com.fragmentdemo.Movie.View.IMovieView;
import administrator.example.com.fragmentdemo.News.FgNewsFragment;

/**
 * Created by apple on 18/5/22.
 */

public class MoviePresenter implements IMoviePresenter, IOnLoadListener {
    private IMovieModel iMovieModel;
    private IMovieView iMovieView;

    public MoviePresenter(IMovieView iNewsView) {
        this.iMovieView = iNewsView;
        this.iMovieModel = new MovieModel();
    }


    @Override
    public void success(MovieBean movieBean) {
        iMovieView.hideDialog();
        if (movieBean != null) {
            iMovieView.showMovie(movieBean);
        }

    }

    @Override
    public void fail(String error) {
        iMovieView.hideDialog();
        iMovieView.showErrorMsg(error);
    }

    @Override
    public void loadMovie(String total) {
        iMovieView.showDialog();
        iMovieModel.loadMovie(total,this);
    }
}
