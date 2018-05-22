package administrator.example.com.fragmentdemo.News.Presenter;

import administrator.example.com.fragmentdemo.Bean.NewsBean;
import administrator.example.com.fragmentdemo.Http.Api;
import administrator.example.com.fragmentdemo.News.FgNewsFragment;
import administrator.example.com.fragmentdemo.News.Model.INewsModel;
import administrator.example.com.fragmentdemo.News.Model.IOnLoadListener;
import administrator.example.com.fragmentdemo.News.Model.NewsModel;
import administrator.example.com.fragmentdemo.News.View.INewsView;

/**
 * Created by apple on 18/5/22.
 */

public class NewsPresenter implements INewsPresenter,IOnLoadListener {
    private INewsModel iNewsModel;
    private INewsView iNewsView;

    public NewsPresenter(INewsView iNewsView){
        this.iNewsView = iNewsView;
        this.iNewsModel = new NewsModel();
    }

    @Override
    public void loadNews(int type, int starPage) {
        iNewsView.showDialog();
        switch (type){
            case FgNewsFragment.NEWS_TYPE_TOP:
                iNewsModel.loadNews("headline",starPage, Api.HEADLINE_ID,
                        this);
                break;

            case FgNewsFragment.NEW_TYPE_NBA:
                iNewsModel.loadNews("list",starPage, Api.NBA_ID,
                        this);
                break;

            case FgNewsFragment.NEWS_TYPE_JOKES:
                iNewsModel.loadNews("list",starPage, Api.JOKE_ID,
                        this);
                break;
        }

    }

    @Override
    public void success(NewsBean newsBean) {
        iNewsView.hideDialog();
        if (newsBean!=null){
            iNewsView.showNews(newsBean);
        }

    }

    @Override
    public void fail(String error) {
        iNewsView.hideDialog();
        iNewsView.showErrorMsg(error);
    }
}
