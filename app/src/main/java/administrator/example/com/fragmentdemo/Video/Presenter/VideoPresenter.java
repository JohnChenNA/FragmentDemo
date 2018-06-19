package administrator.example.com.fragmentdemo.Video.Presenter;

import android.util.Base64;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.CRC32;

import administrator.example.com.fragmentdemo.Bean.TodayContentBean;
import administrator.example.com.fragmentdemo.Bean.VideoUrlBean;
import administrator.example.com.fragmentdemo.Video.Model.IVideoLoadListener;
import administrator.example.com.fragmentdemo.Video.Model.IVideoModel;
import administrator.example.com.fragmentdemo.Video.Model.VideoModel;
import administrator.example.com.fragmentdemo.Video.View.IVideoView;

/**
 * Created by admin on 2018/6/11.
 */

public class VideoPresenter implements IVideoPresenter, IVideoLoadListener {

    private IVideoModel iVideoModel;
    private IVideoView iVideoView;

    public VideoPresenter(IVideoView iVideoView) {
        this.iVideoView = iVideoView;
        this.iVideoModel = new VideoModel();
    }

    @Override
    public void loadVideo() {


        iVideoView.showDialog();
        iVideoModel.loadVideo("video", this);
    }

    @Override
    public void videoUrlSuccess(List<VideoUrlBean> mainUrlBeans, List<TodayContentBean> contentBeans) {
        List<String> videoList = new ArrayList<>();
        iVideoView.hideDialog();
        for (int i = 0; i < mainUrlBeans.size(); i++) {
            String mainUrl = mainUrlBeans.get(i).getData().getVideo_list().getVideo_1().getMain_url();
            final String url1 = (new String(Base64.decode(mainUrl.getBytes(), Base64.DEFAULT)));
            videoList.add(url1);
        }
        iVideoView.showVideo(contentBeans, videoList);
    }

    @Override
    public void fail(Throwable throwable) {
        iVideoView.hideDialog();
        iVideoView.showErrorMsg(throwable);
    }

    public static String getVideoContentApi(String videoid) {
        String VIDEO_HOST = "http://ib.365yg.com";
        String VIDEO_URL = "/video/urls/v/1/toutiao/mp4/%s?r=%s";
        String r = getRandom();
        String s = String.format(VIDEO_URL, videoid, r);
        CRC32 crc32 = new CRC32();
        crc32.update(s.getBytes());
        String crcString = crc32.getValue() + "";
        String url = VIDEO_HOST + s + "&s=" + crcString;
        return url;
    }

    public static String getRandom() {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

    public static TodayContentBean getTodayContentBean(String content) {
        Gson gson = new Gson();
        TodayContentBean bean = gson.fromJson(content, TodayContentBean.class);
        return bean;
    }
    @Override
    public void loadMoreSuccess(VideoUrlBean videoUrlBean){
        iVideoView.hideDialog();
        iVideoView.showMoreVideo(videoUrlBean);
    }
}
