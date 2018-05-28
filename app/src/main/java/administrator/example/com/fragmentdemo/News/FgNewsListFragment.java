package administrator.example.com.fragmentdemo.News;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;
import java.util.TimerTask;

import administrator.example.com.fragmentdemo.Bean.NewsBean;
import administrator.example.com.fragmentdemo.ItemNewsAdapter;
import administrator.example.com.fragmentdemo.News.Presenter.NewsPresenter;
import administrator.example.com.fragmentdemo.News.View.INewsView;
import administrator.example.com.fragmentdemo.R;

public class FgNewsListFragment extends Fragment implements INewsView {

    private int type;

    private NewsPresenter presenter;
    private SwipeRefreshLayout srl_news;
    private ItemNewsAdapter adapter;
    private TextView tv_news_list;
    private RecyclerView rv_news;
    private List<NewsBean.Bean> newsBeanList;

    public static FgNewsListFragment newInstance(int type){
        Bundle args = new Bundle();
        FgNewsListFragment fragment = new FgNewsListFragment();
        args.putInt("type",type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_news_list, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type = getArguments().getInt("type");

        srl_news = view.findViewById(R.id.srl_news);
        srl_news.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        presenter = new NewsPresenter(this);

        adapter = new ItemNewsAdapter(getActivity());
        rv_news = view.findViewById(R.id.rv_news);
        tv_news_list = view.findViewById(R.id.tv_news_list);
        srl_news.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadNews(type , 0);

            }
        });
        presenter.loadNews(type,0);
//        switch (type){
//            case FgNewsFragment.NEWS_TYPE_TOP:
//                tv_news.setText("Top");
//
//                break;
//            case FgNewsFragment.NEW_TYPE_NBA:
//                tv_news.setText("nba");
//                break;
//            case FgNewsFragment.NEWS_TYPE_JOKES:
//                tv_news.setText("joke");
//                break;
        }
        public void showNews(final NewsBean newsBean){
//        getActivity().runOnUiThread(new TimerTask() {
//            @Override
//            public void run() {
//                switch (type){
//                    case FgNewsFragment.NEWS_TYPE_TOP:
//                        news.setText(newsBean.getTop().get(0).getTitle()+" "+ newsBean.getTop()
//                        .get(0).getMtime());
//                        break;
//                    case FgNewsFragment.NEW_TYPE_NBA:
//                        tv_news.setText(newsBean.getNba().get(0).getTitle()+" "+ newsBean.getNba()
//                                .get(0).getMtime());
//                        break;
//                    case FgNewsFragment.NEWS_TYPE_JOKES:
//                        tv_news.setText(newsBean.getJoke().get(0).getTitle()+" "+ newsBean.getJoke()
//                                .get(0).getMtime());
//                        break;
//                }
//            }
//        });
            switch (type){
                case FgNewsFragment.NEWS_TYPE_TOP:
                    newsBeanList = newsBean.getTop();
                    break;
                case FgNewsFragment.NEW_TYPE_NBA:
                    newsBeanList = newsBean.getNba();
                    break;
                case FgNewsFragment.NEWS_TYPE_JOKES:
                    newsBeanList = newsBean.getJoke();
                    break;
            }
            adapter.setData(newsBeanList);
            rv_news.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.VERTICAL,false));
            rv_news.setAdapter(adapter);
            tv_news_list.setVisibility(View.GONE);

    }
    @Override
    public void hideDialog(){
            srl_news.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        srl_news.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(String error) {
        tv_news_list.setText("加载失败"+error);
    }


}
