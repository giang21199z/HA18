package com.giangnd_svmc.ha18.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.giangnd_svmc.ha18.R;
import com.giangnd_svmc.ha18.app.BaseFragment;
import com.giangnd_svmc.ha18.entity.Teacher;

import java.util.ArrayList;

/**
 * Created by Manh on 4/9/16.
 */
public class ListTeacherFragment extends BaseFragment {
    private ListView listView;
    private MyAdapter adapter;
    private ArrayList<Teacher> listTeacher = new ArrayList<>();

    @Override
    protected int getLayoutResIdContentView() {
        return R.layout.fragment_list_teacher;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        listView = (ListView) rootView.findViewById(R.id.listview_teacher);
        adapter = new MyAdapter(getActivity(), listTeacher);
        listView.setAdapter(adapter);
        Toast.makeText(getActivity(), listTeacher.get(0).getName(), Toast.LENGTH_SHORT).show();
    }

    private class MyAdapter extends BaseAdapter {
        Context mContext;
        ArrayList<Teacher> teacherArrayList;
        LayoutInflater inflater;

        public MyAdapter(Context context, ArrayList<Teacher> list) {
            this.mContext = context;
            this.teacherArrayList = list;
            inflater = LayoutInflater.from(this.mContext);
        }

        @Override
        public int getCount() {
            return teacherArrayList.size();
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
                convertView = inflater.inflate(R.layout.item_teacher, null);
                myViewHolder = new MyViewHolder();
                convertView.setTag(myViewHolder);
            } else {
                myViewHolder = (MyViewHolder) convertView.getTag();
            }
            myViewHolder.textviewTeacherName = (TextView) convertView.findViewById(R.id.textview_teacher);

            myViewHolder.textviewTeacherName.setText(teacherArrayList.get(position).getName());
            return convertView;
        }
        private class MyViewHolder {
            TextView textviewTeacherName;
        }
    }
}
