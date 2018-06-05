package administrator.example.com.fragmentdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import administrator.example.com.fragmentdemo.Bean.MovieBean;

/**
 * Created by apple on 18/6/5.
 */

public class ItemMovieTopAdapter extends RecyclerView.Adapter<ItemMovieTopAdapter.ViewHolder> {
    private List<MovieBean.SubjectsBean> objects = new ArrayList<MovieBean.SubjectsBean>();

    private Context context;

    public ItemMovieTopAdapter(Context context) {

        this.context = context;
    }
    public void setData(List<MovieBean.SubjectsBean> objects){
        this.objects = objects;
    }
    @Override
    public ItemMovieTopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_top, parent, false);
        return new ItemMovieTopAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MovieBean.SubjectsBean bean=objects.get(position);
        if (bean==null){
            return;
        }
        Glide.with(context)
                .load(bean.getImages().getSmall())
                .into(holder.ivMovieTop);
        holder.tvMovieTop.setText(bean.getTitle());

    }



    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivMovieTop;
        private TextView tvMovieTop;

        public ViewHolder(View view) {
            super(view);
            ivMovieTop = (ImageView) view.findViewById(R.id.iv_movie_top);
            tvMovieTop = (TextView) view.findViewById(R.id.tv_movie_top);
        }
    }
}
