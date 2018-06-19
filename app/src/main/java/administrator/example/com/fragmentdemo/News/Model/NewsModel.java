package administrator.example.com.fragmentdemo.News.Model;

import administrator.example.com.fragmentdemo.Bean.NewsBean;
import administrator.example.com.fragmentdemo.Http.Api;
import administrator.example.com.fragmentdemo.Http.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 18/5/22.
 */

public class NewsModel implements INewsModel {
    @Override
    public void loadNews(final String hostType,final int starPage, final String id,
                         final IOnLoadListener iOnLoadListener) {

        RetrofitHelper retrofitHelper= new RetrofitHelper(Api.NEW_HOST);
//        retrofitHelper.getNews(hostType,id,starPage).enqueue(new Callback<NewsBean>(){
//            @Override
//            public void onResponse(Call<NewsBean> call, Response<NewsBean>response){
//                if (response.isSuccessful()){
//                    iOnLoadListener.success(response.body());
//                }else {
//                    iOnLoadListener.fail("");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NewsBean> call, Throwable t) {
//                iOnLoadListener.fail(t.toString());
//            }
//        });
        retrofitHelper.getNews(hostType,id,starPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iOnLoadListener.fail(e.getMessage());
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        if (starPage!=0){
                            iOnLoadListener.loadMoreSuccess(newsBean);
                        }else {
                            iOnLoadListener.success(newsBean);
                        }
                    }
                });
        }
    }




