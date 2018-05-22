package administrator.example.com.fragmentdemo.News.Model;

import administrator.example.com.fragmentdemo.Bean.NewsBean;

/**
 * Created by apple on 18/5/22.
 */

public interface IOnLoadListener {
    void success(NewsBean newsBean);
    void fail (String error);
}
