package administrator.example.com.fragmentdemo.Movie;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import administrator.example.com.fragmentdemo.Bean.MovieBean;

import administrator.example.com.fragmentdemo.ItemMovieOnAdapter;
import administrator.example.com.fragmentdemo.ItemMovieTopAdapter;
import administrator.example.com.fragmentdemo.Movie.Presenter.MoviePresenter;
import administrator.example.com.fragmentdemo.Movie.View.IMovieView;
import administrator.example.com.fragmentdemo.R;


public class FgMovieFragment extends Fragment implements IMovieView {

    private MoviePresenter moviesPresenter;
    private RecyclerView rv_movie_on;
    private RecyclerView rv_movie_top;
    private SwipeRefreshLayout srl_movie;
    private ItemMovieOnAdapter movieOnAdapter;
    private ItemMovieTopAdapter movieTopAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_movie, null);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_movie_top = view.findViewById(R.id.rv_movie_top);
        moviesPresenter = new MoviePresenter(this);
        srl_movie = view.findViewById(R.id.srl_movie);
        rv_movie_on = view.findViewById(R.id.rv_movie_hot);
        movieOnAdapter = new ItemMovieOnAdapter(getActivity());
        movieTopAdapter = new ItemMovieTopAdapter(getActivity());
        srl_movie.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        moviesPresenter.loadMovie("in_theaters");
        moviesPresenter.loadMovie("top250");
        srl_movie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesPresenter.loadMovie("in_theaters");
                moviesPresenter.loadMovie("top250");
            }
        });
    }

    @Override
    public void showMovie(MovieBean movieBean) {
        if(movieBean.getTotal()==250) {
            movieTopAdapter.setData(movieBean.getSubjects());
            rv_movie_top.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
            rv_movie_top.setAdapter(movieTopAdapter);

        }else{
            movieOnAdapter.setData(movieBean.getSubjects());
            rv_movie_on.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_movie_on.setAdapter(movieOnAdapter);
        }
    }

    @Override
    public void hideDialog() {
        srl_movie.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        srl_movie.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(Throwable throwable) {
        Toast.makeText(getContext(), "加载出错:"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }


}

