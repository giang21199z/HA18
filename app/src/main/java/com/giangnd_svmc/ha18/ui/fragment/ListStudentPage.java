package com.giangnd_svmc.ha18.ui.fragment;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private ListView listView;
    private ListAdapter adapter;

    private ArrayList<Student> listStudent = new ArrayList<>();

    public ListStudentPage(BaseActivity activity) {
        super(activity);
        init();
    }

    private void init() {
        listView = (ListView) content.findViewById(R.id.listView);
        Student a = new Student("a");
        a.attendance = 1;
        listStudent.add(a);
        adapter = new ListAdapter(activity, listStudent);
        listView.setAdapter(adapter);
        Toast.makeText(activity, listStudent.get(0).name, Toast.LENGTH_SHORT).show();    }

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
                convertView = inflater.inflate(R.layout.item_student, null);
                myViewHolder = new MyViewHolder();
                convertView.setTag(myViewHolder);
            } else {
                myViewHolder = (MyViewHolder) convertView.getTag();
            }
            myViewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_Name);
            myViewHolder.tvAttendance = (TextView) convertView.findViewById(R.id.tv_Attendance);
            myViewHolder.cvAttendance = (CardView) convertView.findViewById(R.id.cv_Attendance);
            switch (studentArrayList.get(position).attendance) {
                case 0:
                    myViewHolder.cvAttendance.setCardBackgroundColor(activity.getResources().getColor(R.color.colorAbsent));
                    myViewHolder.tvAttendance.setText("Absent");
                    break;
                case 1:
                    myViewHolder.cvAttendance.setCardBackgroundColor(activity.getResources().getColor(R.color.colorPresent));

                    myViewHolder.tvAttendance.setText("Present");
                    break;
                case 2:
                    myViewHolder.cvAttendance.setCardBackgroundColor(R.color.colorUnrecorded);
                    myViewHolder.tvAttendance.setText("Unrecorded");
                    break;
            }
            return convertView;
        }

        private class MyViewHolder {
            TextView tvName;
            CardView cvAttendance;
            TextView tvAttendance;
        }
    }
}
