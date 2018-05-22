package administrator.example.com.fragmentdemo.News.Model;

/**
 * Created by apple on 18/5/22.
 */

public interface INewsModel {
    void loadNews(String hostType,
        int starPage,
        String id,
        IOnLoadListener iOnLoadListener);
}
