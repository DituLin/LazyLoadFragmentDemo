package atu.com.lazyloadfragmentdemo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import atu.com.lazyloadfragmentdemo.fragment.IndexFragment;
import atu.com.lazyloadfragmentdemo.fragment.TrackFragment;
import atu.com.lazyloadfragmentdemo.fragment.UserFragment;
import atu.com.lazyloadfragmentdemo.fragment.VideoFragment;

public class MainActivity extends AppCompatActivity {

    private int[] mIconUnSelectIds = {
            R.mipmap.ic_tab_index, R.mipmap.ic_tab_track,
            R.mipmap.ic_tab_video, R.mipmap.ic_tab_user};
    private int[] mIconSelectIds = {
            R.mipmap.ic_tab_index_select, R.mipmap.ic_tab_track_select,
            R.mipmap.ic_tab_video_select, R.mipmap.ic_tab_user_select};


    private ArrayList<String> mTitleList;//页卡标题集合
    private ArrayList<Fragment> mFragments;
    private ArrayList<CustomTabEntity> mTabEntities;

    ViewPager vp;
    CommonTabLayout tabMain;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initView();

    }


    /**
     * 初始化 title 和 fragment
     */
    private void init() {
        mTitleList = new ArrayList<>();
        mTitleList.add("首页");
        mTitleList.add("轨迹");
        mTitleList.add("视频");
        mTitleList.add("我的");

        mFragments = new ArrayList<>();
        mFragments.add(new IndexFragment());
        mFragments.add(new TrackFragment());
        mFragments.add(new VideoFragment());
        mFragments.add(new UserFragment());
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.view_pager);
        tabMain = (CommonTabLayout) findViewById(R.id.tab_main);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),mTitleList);
        vp.setAdapter(adapter);
        vp.setOffscreenPageLimit(3);
        initTab();
    }

    /**
     * 初始化 tab
     */
    private void initTab() {
        mTabEntities = new ArrayList<>();
        for (int i = 0; i < mIconSelectIds.length; i++) {
            mTabEntities.add(new TabEntity(mTitleList.get(i), mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        tabMain.setTabData(mTabEntities);

        tabMain.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabMain.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
