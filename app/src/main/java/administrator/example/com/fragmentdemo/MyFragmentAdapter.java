package administrator.example.com.fragmentdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2018/5/17.
 */

public class MyFragmentAdapter  extends FragmentPagerAdapter{

    private List<Fragment> fragments = new ArrayList<>();
    private  List<String> fragmentTitles = new ArrayList<>();

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public  MyFragmentAdapter(FragmentManager fm, List<Fragment> fragments,
                              List<String> fragmentTitles){
        super(fm);
        this.fragments = fragments;
        this.fragmentTitles = fragmentTitles;

    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position){
        if (fragmentTitles!=null){
            return fragmentTitles.get(position);
        }else{
            return "";
        }
    }
}
