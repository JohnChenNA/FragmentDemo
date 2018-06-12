package administrator.example.com.fragmentdemo.Video.Model;

import java.util.List;

import administrator.example.com.fragmentdemo.Bean.TodayContentBean;
import administrator.example.com.fragmentdemo.Bean.VideoUrlBean;

/**
 * Created by admin on 2018/6/11.
 */

public interface IVideoLoadListener {
    void videoUrlSuccess(List<VideoUrlBean> videoUrlBeans, List<TodayContentBean> contentBeans);
    void fail(Throwable throwable);
}
