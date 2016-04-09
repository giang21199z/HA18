package com.giangnd_svmc.ha18.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.giangnd_svmc.ha18.R;
import com.giangnd_svmc.ha18.app.BaseActivity;
import com.giangnd_svmc.ha18.app.BaseFragment;
import com.giangnd_svmc.ha18.entity.Student;

import java.util.ArrayList;

/**
 * Created by admin on 4/9/2016.
 */
public class ClassFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;

    @Override
    protected int getLayoutResIdContentView() {
        return R.layout.fragment_class;
    }

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        ArrayList<ListStudentPage> listStudentPages = new ArrayList<>();
        ListStudentPage listStudentPage = new ListStudentPage(getBaseActivity());
        listStudentPages.add(listStudentPage);
        ListStudentPage listStudentPagse = new ListStudentPage(getBaseActivity());
        listStudentPages.add(listStudentPagse);
        adapter = new MyViewPagerAdapter(getBaseActivity(),listStudentPages);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    class MyViewPagerAdapter extends PagerAdapter {
        private ArrayList<ListStudentPage> listPage = new ArrayList<>();

        public MyViewPagerAdapter(BaseActivity activity, ArrayList<ListStudentPage> list) {
            listPage = list;
        }

        @Override
        public int getCount() {
            return listPage.size();
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View layout = null;
            if (listPage.get(position) != null)
                layout = listPage.get(position).getContent();
            else {
                ListStudentPage listStudentPage = new ListStudentPage(getBaseActivity());
                listPage.add(position, listStudentPage);
                layout = listPage.get(position).getContent();

            }
            container.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            listPage.get(position).destroy();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return listPage.get(position).name;
        }
    }
}