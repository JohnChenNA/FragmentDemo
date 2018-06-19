package administrator.example.com.fragmentdemo.Video;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


import administrator.example.com.fragmentdemo.Bean.TodayContentBean;
import administrator.example.com.fragmentdemo.Bean.VideoUrlBean;
import administrator.example.com.fragmentdemo.R;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by admin on 2018/6/11.
 */

public class ItemVideoAdapter extends RecyclerView.Adapter<ItemVideoAdapter.ViewHolder> {
    private List<TodayContentBean> objects = new ArrayList<TodayContentBean>();
    private  List<String> videoList = new ArrayList<>();
    private Context context;

    public ItemVideoAdapter(Context context){
        this.context = context;
    }

    public void setData(List<TodayContentBean> objects,List<String> videoList){
        this.objects = objects;
        this.videoList = videoList;
    }
    public void addData(List<TodayContentBean> newObjects){
        objects.addAll(newObjects);
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_video,parent, false);

            return new ItemVideoAdapter.ViewHolder(view);

        }else{
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.footer,parent,false);
            return new FooterHolder(view);

        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

            TodayContentBean bean = objects.get(position);

            holder.videoPlayer.setUp(videoList.get(position),
                    JZVideoPlayerStandard.SCREEN_WINDOW_LIST,
                    bean.getTitle());

            Glide.with(context)
                    .load(bean.getVideo_detail_info().getDetail_video_large_image().getUrl())
                    .into(holder.videoPlayer.thumbImageView);


    }

    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public int getItemCount() {
        return objects.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private JZVideoPlayerStandard videoPlayer;

        public ViewHolder(View view){
            super(view);
            videoPlayer = (JZVideoPlayerStandard) view.findViewById(R.id.video_player);
        }
    }
    protected class FooterHolder extends ViewHolder{
        public FooterHolder(View itemView){
            super(itemView);
        }
    }

}
