package administrator.example.com.fragmentdemo.Video.Model;

/**
 * Created by admin on 2018/6/11.
 */

public interface IVideoModel {
    void loadVideo(String category, IVideoLoadListener iVideoLoadListener);
    void loadCity();
}
