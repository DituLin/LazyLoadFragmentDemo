package atu.com.lazyloadfragmentdemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import atu.com.lazyloadfragmentdemo.R;

/**
 * Created by atu on 2017/9/20.
 */

public class VideoFragment extends BaseFragment{

    TextView tvContent;

    @Override
    protected int setLayoutResource() {
        return R.layout.fragment_index;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        tvContent = rootView.findViewById(R.id.tv_content);
    }

    @Override
    protected void lazyLoadData() {
        super.lazyLoad();
        Log.d("Fragment:","VideoFragment");
        tvContent.setText("this is VideoFragment");
    }
}
