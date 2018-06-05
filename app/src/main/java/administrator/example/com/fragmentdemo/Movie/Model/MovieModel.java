package administrator.example.com.fragmentdemo.Movie.Model;

import android.graphics.Movie;
import android.util.Log;

import administrator.example.com.fragmentdemo.Bean.MovieBean;
import administrator.example.com.fragmentdemo.Bean.NewsBean;
import administrator.example.com.fragmentdemo.Http.Api;
import administrator.example.com.fragmentdemo.Http.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 18/5/22.
 */

public class MovieModel implements IMovieModel {



    @Override
    public void loadMovie(String total, final IOnLoadListener iOnLoadListener) {
        RetrofitHelper retrofitHelper= new RetrofitHelper(Api.API_URL);
//        retrofitHelper.getMovie(total).enqueue(new Callback<MovieBean>(){
//            @Override
//            public void onResponse(Call<MovieBean> call, Response<MovieBean>response){
//                if (response.isSuccessful()){
//                    iOnLoadListener.success(response.body());
//                    Log.i("onResponse", "onResponse: "+response.body());
//                }else {
//                    iOnLoadListener.fail("");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieBean> call, Throwable t) {
//                iOnLoadListener.fail(t.toString());
//            }
//        });

        retrofitHelper.getMovie(total)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iOnLoadListener.fail(e);
                    }

                    @Override
                    public void onNext(MovieBean movieBean) {
                        iOnLoadListener.success(movieBean);
                    }
                });
    }
}
