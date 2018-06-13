package administrator.example.com.fragmentdemo.Live;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.security.PublicKey;

import administrator.example.com.fragmentdemo.R;

/**
 * Created by apple on 18/6/13.
 */

public class FgLiveFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fg_live,container,false);
    }

    @Override
    public void onViewCreated(View view ,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
    }

}
