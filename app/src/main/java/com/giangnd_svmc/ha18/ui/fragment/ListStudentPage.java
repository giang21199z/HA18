package com.giangnd_svmc.ha18.ui.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.giangnd_svmc.ha18.R;
import com.giangnd_svmc.ha18.app.BaseActivity;
import com.giangnd_svmc.ha18.app.BasePage;
import com.giangnd_svmc.ha18.entity.Attendences;
import com.giangnd_svmc.ha18.entity.JsonParser;
import com.giangnd_svmc.ha18.entity.MyClass;
import com.giangnd_svmc.ha18.entity.MyUtils;
import com.giangnd_svmc.ha18.entity.Student;
import com.giangnd_svmc.ha18.entity.Subject;
import com.giangnd_svmc.ha18.entity.Teacher;
import com.giangnd_svmc.ha18.glide.GlideCircleTransform;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by admin on 4/9/2016.
 */
public class ListStudentPage extends BasePage implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ListAdapter adapter;
    private Teacher teacher;
    private MyClass myClass;
    private Subject subject;
    private ArrayList<Student> listStudent = new ArrayList<>();

    public ListStudentPage(BaseActivity activity, Teacher teacher, MyClass myClass, Subject subject) {
        super(activity);
        this.teacher = teacher;
        this.myClass = myClass;
        this.subject = subject;
        init();
    }

    private void init() {
        listView = (ListView) content.findViewById(R.id.listView);
        listStudent = new ArrayList<>();
        adapter = new ListAdapter(activity, listStudent);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        loadData();
    }

    @Override
    protected int getContentId() {
        return R.layout.page_list_student;
    }

    public void loadData() {
        new LoadAttendences().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    class LoadAttendences extends AsyncTask {
        JSONObject jsonObject;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            JsonParser jsonParser = new JsonParser();
            Log.e("TAG", MyUtils.urlListAllStudentOfClass(myClass.id), null);
            jsonObject = jsonParser.getJsonFromUrl(MyUtils.urlListAllStudentOfClass(myClass.id));
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (jsonObject == null) {

            } else
                try {
                    int success = jsonObject.getInt(MyUtils.TAG_SUCCESS);
                    if (success == 1) {
                        listStudent = new ArrayList<>();
                        JSONArray arr = jsonObject.getJSONArray(MyUtils.TAG_STUDIENS);
                        for (int i = 0; i < arr.length(); i++) {
                            Student student = new Student();
                            JSONObject jsonObject = (JSONObject) arr.get(i);
                            student.id = jsonObject.getInt(MyUtils.TAG_STUDIENT_ID);
                            student.name = jsonObject.getString(MyUtils.TAG_STUDIENT_NAME);
                            student.code = jsonObject.getString(MyUtils.TAG_STUDIENT_CODE);
                            student.birthDay = jsonObject.getString(MyUtils.TAG_STUDIENT_BIRTHDAY);
                            student.address = jsonObject.getString(MyUtils.TAG_STUDIENT_ADDRESS);
                            student.description = jsonObject.getString(MyUtils.TAG_STUDIENT_DESCRIPTION);
                            student.setImageStudent(jsonObject.getString(MyUtils.TAG_STUDIENT_IMAGE));
                            listStudent.add(student);
                        }
                    } else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            adapter.updateUI(listStudent);
        }
    }

    private class ListAdapter extends BaseAdapter {
        Context mContext;
        ArrayList<Student> listStudent;
        LayoutInflater inflater;

        public ListAdapter(Context context, ArrayList<Student> list) {
            this.mContext = context;
            this.listStudent = list;
            inflater = LayoutInflater.from(this.mContext);
        }

        @Override
        public int getCount() {
            return listStudent.size();
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
            myViewHolder.imageStudent = (ImageView) convertView.findViewById(R.id.imageStudent);
            myViewHolder.tvName.setText(listStudent.get(position).name);
            myViewHolder.cvAttendance.setVisibility(View.GONE);
            Glide.with(activity.getBaseContext())
                    .load(listStudent.get(position).getImageStudent())
                    .centerCrop()
                    .transform(new GlideCircleTransform(activity.getBaseContext()))
                    .error(R.drawable.account)
                    .into(myViewHolder.imageStudent);
            return convertView;
        }

        public void updateUI(ArrayList<Student> listAttendences) {
            this.listStudent = listAttendences;
            notifyDataSetChanged();
        }

        private class MyViewHolder {
            ImageView imageStudent;
            TextView tvName;
            CardView cvAttendance;
            TextView tvAttendance;
        }
    }
}
