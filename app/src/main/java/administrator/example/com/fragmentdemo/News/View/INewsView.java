package administrator.example.com.fragmentdemo.News.View;

import administrator.example.com.fragmentdemo.Bean.NewsBean;

/**
 * Created by apple on 18/5/22.
 */

public interface INewsView {
    void showNews(NewsBean newsBean);
    void hideDialog();
    void showDialog();
    void showErrorMsg(String error);
    void showMoreNews(NewsBean newsBean);
}
