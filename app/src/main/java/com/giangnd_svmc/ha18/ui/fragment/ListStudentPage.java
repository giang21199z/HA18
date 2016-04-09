package com.giangnd_svmc.ha18.ui.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.giangnd_svmc.ha18.R;
import com.giangnd_svmc.ha18.app.BaseActivity;
import com.giangnd_svmc.ha18.app.BasePage;
import com.giangnd_svmc.ha18.entity.Student;

import java.util.ArrayList;

/**
 * Created by admin on 4/9/2016.
 */
public class ListStudentPage extends BasePage {
    public String name = "Môn Học";

    public ListStudentPage(BaseActivity activity) {
        super(activity);
        init();
    }

    private void init() {
//        content.findViewById()
    }

    @Override
    protected int getContentId() {
        return R.layout.page_list_student;
    }

    private class ListAdapter extends BaseAdapter {
        Context mContext;
        ArrayList<Student> studentArrayList;
        LayoutInflater inflater;

        public ListAdapter(Context context, ArrayList<Student> students) {
            this.mContext = context;
            this.studentArrayList = students;
            inflater = LayoutInflater.from(this.mContext);
        }

        @Override
        public int getCount() {
            return studentArrayList.size();
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
                convertView = inflater.inflate(R.layout.item_class, null);
                myViewHolder = new MyViewHolder();
                convertView.setTag(myViewHolder);
            } else {
                myViewHolder = (MyViewHolder) convertView.getTag();
            }
            myViewHolder.tvClassName = (TextView) convertView.findViewById(R.id.tv_ClassName);

            myViewHolder.tvClassName.setText(studentArrayList.get(position).name);
            return convertView;
        }

        private class MyViewHolder {
            TextView tvClassName;
        }
    }
}
