package administrator.example.com.fragmentdemo.Video;


import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.List;

import administrator.example.com.fragmentdemo.Bean.TodayContentBean;
import administrator.example.com.fragmentdemo.Bean.VideoUrlBean;
import administrator.example.com.fragmentdemo.R;
import administrator.example.com.fragmentdemo.Video.Presenter.IVideoPresenter;
import administrator.example.com.fragmentdemo.Video.Presenter.VideoPresenter;
import administrator.example.com.fragmentdemo.Video.View.IVideoView;

public class FgVideoFragment extends Fragment implements IVideoView {

    private IVideoPresenter iVideoPresenter;
    private RecyclerView rv_video;
    private ItemVideoAdapter itemVideoAdapter;
    private SwipeRefreshLayout srl_video;
    private LinearLayoutManager layoutManager;
    private VideoPresenter videoPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_video,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iVideoPresenter = new VideoPresenter(this);
        rv_video = view.findViewById(R.id.rv_video);
        srl_video = view.findViewById(R.id.srl_video);
        srl_video.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        iVideoPresenter.loadVideo();
        srl_video.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iVideoPresenter.loadVideo();
            }
        });
        itemVideoAdapter = new ItemVideoAdapter(getActivity());
        rv_video.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState==RecyclerView.SCROLL_STATE_IDLE &&
                        (layoutManager.findLastVisibleItemPosition()+1)==layoutManager.getItemCount()){
                    loadMore();
                }
            }
        });
    }

    @Override
    public void showVideo(List<TodayContentBean> todayContentBeans, List<String> videoList) {
        itemVideoAdapter.setData(todayContentBeans, videoList);
        rv_video.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        rv_video.setAdapter(itemVideoAdapter);
        layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false);
        rv_video.setLayoutManager(layoutManager);
    }

    @Override
    public void hideDialog() {
        srl_video.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        srl_video.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(Throwable throwable) {
        Toast.makeText(getContext(), "加载出错:"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMoreVideo(VideoUrlBean videoUrlBean) {
       itemVideoAdapter.addData(VideoUrlBean.);

    }


    private void loadMore(){
        iVideoPresenter.loadVideo();


    }
}
