package administrator.example.com.fragmentdemo.Movie;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

import administrator.example.com.fragmentdemo.Bean.MovieBean;
import administrator.example.com.fragmentdemo.Movie.Presenter.MoviePresenter;
import administrator.example.com.fragmentdemo.Movie.View.IMovieView;
import administrator.example.com.fragmentdemo.R;


public class FgMovieFragment extends Fragment  implements IMovieView{
    private int type;
    private TextView tv_movie;
    private MoviePresenter moviePresenter;
    private SwipeRefreshLayout srl_movie;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_movie, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviePresenter=new MoviePresenter(this);
        tv_movie=view.findViewById(R.id.tv_movie);
        srl_movie=view.findViewById(R.id.srl_movie);
        srl_movie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviePresenter.loadMovie("in_theaters");
            }
        });

    }

    @Override
    public void showMovie(MovieBean movieBean) {
        tv_movie.setText(movieBean.getSubjects().get(0).getTitle()+movieBean.getSubjects().get(0).getDirectors());
    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showDialog() {

            srl_movie.setRefreshing(false);

    }

    @Override
    public void showErrorMsg(String error) {

    }
}
