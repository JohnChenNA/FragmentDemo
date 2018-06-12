package administrator.example.com.fragmentdemo.Video.View;

import java.util.List;

import administrator.example.com.fragmentdemo.Bean.TodayContentBean;

/**
 * Created by admin on 2018/6/11.
 */

public interface IVideoView {
    void showVideo(List<TodayContentBean> todayContentBeans,List<String> videoList);
    void hideDialog();
    void showDialog();
    void showErrorMsg(Throwable throwable);

}
