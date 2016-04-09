package com.giangnd_svmc.ha18.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.giangnd_svmc.ha18.R;
import com.giangnd_svmc.ha18.app.BaseFragment;
import com.giangnd_svmc.ha18.entity.Student;

import java.util.ArrayList;

/**
 * Created by admin on 4/9/2016.
 */
public class ListClassFragment extends BaseFragment {
    private GridView gridView;
    private MyAdapter adapter;
    private ArrayList<Student> listClass = new ArrayList<>();

    @Override
    protected int getLayoutResIdContentView() {
        return R.layout.fragment_list_class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        gridView = (GridView) rootView.findViewById(R.id.gridView);
        listClass.add(new Student("name"));
        listClass.add(new Student("name"));
        listClass.add(new Student("name"));
        listClass.add(new Student("name"));
        listClass.add(new Student("name"));
        adapter = new MyAdapter(getActivity(), listClass);
        gridView.setAdapter(adapter);
        Toast.makeText(getActivity(), listClass.get(0).name, Toast.LENGTH_SHORT).show();

    }

    private class MyAdapter extends BaseAdapter {
        Context mContext;
        ArrayList<Student> classArrayList;
        LayoutInflater inflater;

        public MyAdapter(Context context, ArrayList<Student> categories) {
            this.mContext = context;
            this.classArrayList = categories;
            inflater = LayoutInflater.from(this.mContext);
        }

        @Override
        public int getCount() {
            return classArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder myViewHolder;
            if (convertView == null) {
<<<<<<< HEAD
                convertView = inflater.inflate(R.layout.item_student, null);
=======
                convertView = inflater.inflate(R.layout.item_class, null);
>>>>>>> origin/master
                myViewHolder = new MyViewHolder();
                convertView.setTag(myViewHolder);
            } else {
                myViewHolder = (MyViewHolder) convertView.getTag();
            }
            myViewHolder.tvClassName = (TextView) convertView.findViewById(R.id.tv_ClassName);

            myViewHolder.tvClassName.setText(classArrayList.get(position).name);
            return convertView;
        }

        private class MyViewHolder {
            TextView tvClassName;
        }
    }
}
