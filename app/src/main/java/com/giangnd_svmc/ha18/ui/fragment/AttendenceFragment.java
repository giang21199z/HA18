package com.giangnd_svmc.ha18.ui.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.giangnd_svmc.ha18.R;
import com.giangnd_svmc.ha18.app.BaseFragment;
import com.giangnd_svmc.ha18.entity.Attendences;
import com.giangnd_svmc.ha18.entity.JsonParser;
import com.giangnd_svmc.ha18.entity.MyClass;
import com.giangnd_svmc.ha18.entity.MyUtils;
import com.giangnd_svmc.ha18.entity.Subject;
import com.giangnd_svmc.ha18.entity.Teacher;
import com.giangnd_svmc.ha18.glide.GlideCircleTransform;
import com.giangnd_svmc.ha18.ui.activity.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by admin on 4/10/2016.
 */
public class AttendenceFragment extends BaseFragment {
    private Teacher teacher;
    private MyClass myClass;
    private Subject subject;
    private ArrayList<Attendences> listAttendences = new ArrayList<>();
    private ListAdapter adapter;
    private ListView listView;
    private ArrayList<String> listResult;


    @Override
    protected int getLayoutResIdContentView() {
        return R.layout.page_list_student;
    }

    public AttendenceFragment(Teacher teacher, MyClass myClass, Subject subject, ArrayList<String> list) {
        this.teacher = teacher;
        this.myClass = myClass;
        this.subject = subject;
        this.listResult = list;
    }

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        listView = (ListView) rootView.findViewById(R.id.listView);
        listAttendences = new ArrayList<>();
        adapter = new ListAdapter(getBaseActivity().getBaseContext(), listAttendences);
        listView.setAdapter(adapter);
        new LoadAttendences().execute();
        final MainActivity activity = (MainActivity) getActivity();
        activity.toolbar.setTitle("Attendence");
        activity.toolbar.setNavigationIcon(R.drawable.keyboard_backspace);
        activity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
            jsonObject = jsonParser.getJsonFromUrl(MyUtils.urlListAttendences(teacher.id, myClass.id, subject.id));
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
                        listAttendences = new ArrayList<>();
                        JSONArray arr = jsonObject.getJSONArray(MyUtils.TAG_ATTENDENCES);
                        Log.e("TAG", arr.length() + "", null);
                        for (int i = 0; i < arr.length(); i++) {
                            Attendences attendence = new Attendences();
                            JSONObject jsonObject = (JSONObject) arr.get(i);
                            attendence.id = jsonObject.getInt(MyUtils.TAG_ATTENDENCES_ID);
                            attendence.status = jsonObject.getInt(MyUtils.TAG_ATTENDENCES_STATUS);
                            attendence.student_id = jsonObject.getInt(MyUtils.TAG_ATTENDENCES_STUDENT_ID);
                            attendence.student_code = jsonObject.getString(MyUtils.TAG_ATTENDENCES_STUDENT_CODE);
                            attendence.student_name = jsonObject.getString(MyUtils.TAG_ATTENDENCES_STUDENT_NAME);
                            attendence.setImageStudent(jsonObject.getString(MyUtils.TAG_ATTENDENCES_STUDENT_IMAGE));
                            listAttendences.add(attendence);
                        }
                        for (int i = 0; i < listAttendences.size(); i++) {
                            listAttendences.get(i).status = 0;
                            for (int j = 0; j < listResult.size(); j++) {
                                Log.e("TAG", listResult.get(j), null);
                                if ((String.valueOf(listAttendences.get(i).student_id).equals(listResult.get(j)))) {
                                    listAttendences.get(i).status = 1;
                                    Log.d("FOO",listResult.get(j));
                                }
                            }
                        }
                    } else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            adapter.updateUI(listAttendences);
        }
    }

    private class ListAdapter extends BaseAdapter {
        Context mContext;
        ArrayList<Attendences> listAttendences;
        LayoutInflater inflater;

        public ListAdapter(Context context, ArrayList<Attendences> list) {
            this.mContext = context;
            this.listAttendences = list;
            inflater = LayoutInflater.from(this.mContext);
        }

        @Override
        public int getCount() {
            return listAttendences.size();
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
            myViewHolder.tvName.setText(listAttendences.get(position).student_name);
            switch (listAttendences.get(position).status) {
                case 0:
                    myViewHolder.cvAttendance.setCardBackgroundColor(getBaseActivity().getResources().getColor(R.color.colorAbsent));
                    myViewHolder.tvAttendance.setText("Absent");
                    break;
                case 1:
                    myViewHolder.cvAttendance.setCardBackgroundColor(getBaseActivity().getResources().getColor(R.color.colorPresent));

                    myViewHolder.tvAttendance.setText("Present");
                    break;
                case 2:
                    myViewHolder.cvAttendance.setCardBackgroundColor(getBaseActivity().getResources().getColor(R.color.colorUnrecorded));
                    myViewHolder.tvAttendance.setText("Unrecorded");
                    break;
            }
            Glide.with(getBaseActivity().getBaseContext())
                    .load(listAttendences.get(position).getImageStudent())
                    .centerCrop()
                    .transform(new GlideCircleTransform(getBaseActivity().getBaseContext()))
                    .error(R.drawable.account)
                    .into(myViewHolder.imageStudent);
            return convertView;
        }

        public void updateUI(ArrayList<Attendences> listAttendences) {
            this.listAttendences = listAttendences;
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
