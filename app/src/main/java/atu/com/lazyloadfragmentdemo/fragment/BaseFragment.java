package atu.com.lazyloadfragmentdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by atu on 2017/9/20.
 */

public abstract class BaseFragment  extends Fragment{

    protected View rootView;
    // 标志位，标志已经初始化完成，因为setUserVisibleHint是在onCreateView之前调用的，
    // 在视图未初始化的时候，在lazyLoad当中就使用的话，就会有空指针的异常
    private boolean isPrepared;
    //标志当前页面是否可见
    private boolean isVisible;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(setLayoutResource(), null, false);
            init(savedInstanceState);
            init();
            Log.d("BaseFragment:","rootView");
        }
        Log.d("BaseFragment:","onCreateView");
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("BaseFragment:","onViewCreated");
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //懒加载
        if (getUserVisibleHint()) {
            isVisible = true;

            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {

    }

    protected void lazyLoadData() {

    }

    protected void lazyLoad() {
        //不可见或未初始化完成
        if (!isVisible || !isPrepared) {
            return;
        }
        //数据请求
        lazyLoadData();
    }

    protected abstract int setLayoutResource();

    protected abstract void init(Bundle savedInstanceState);

    protected abstract void init();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
