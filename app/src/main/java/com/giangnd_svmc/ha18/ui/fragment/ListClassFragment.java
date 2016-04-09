package com.giangnd_svmc.ha18.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.giangnd_svmc.ha18.R;
import com.giangnd_svmc.ha18.app.BaseFragment;
import com.giangnd_svmc.ha18.entity.JsonParser;
import com.giangnd_svmc.ha18.entity.MyClass;
import com.giangnd_svmc.ha18.entity.MyUtils;
import com.giangnd_svmc.ha18.entity.Student;
import com.giangnd_svmc.ha18.entity.Teacher;
import com.giangnd_svmc.ha18.ui.activity.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by admin on 4/9/2016.
 */
public class ListClassFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private MyAdapter adapter;
    private Teacher teacher;
    private ArrayList<MyClass> listClass = new ArrayList<>();

    public ListClassFragment(Teacher teacher) {
        this.teacher = teacher;
    }

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
        listClass= new ArrayList<>();
        adapter = new MyAdapter(getActivity(), listClass);
        gridView.setAdapter(adapter);
        new LoadClass().execute();
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ClassFragment classFragment = new ClassFragment(teacher, listClass.get(position));
        replaceFragment(classFragment, classFragment.getTag(), classFragment.getTag());
    }

    class LoadClass extends AsyncTask {
        JSONObject jsonObject;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            JsonParser jsonParser = new JsonParser();
            jsonObject = jsonParser.getJsonFromUrl(MyUtils.urlListClass(teacher.id));
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
                        JSONArray arr = jsonObject.getJSONArray(MyUtils.TAG_CLASS);
                        for (int i = 0; i < arr.length(); i++) {
                            MyClass myClass = new MyClass();
                            JSONObject jsonObject = (JSONObject) arr.get(i);
                            myClass.id = jsonObject.getInt(MyUtils.TAG_CLASS_ID);
                            myClass.name = jsonObject.getString(MyUtils.TAG_CLASS_NAME);
                            listClass.add(myClass);
                        }
                    } else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            adapter.updateUI(listClass);
        }
    }

    private class MyAdapter extends BaseAdapter {
        Context mContext;
        ArrayList<MyClass> classArrayList;
        LayoutInflater inflater;

        public MyAdapter(Context context, ArrayList<MyClass> arrayList) {
            this.mContext = context;
            this.classArrayList = arrayList;
            inflater = LayoutInflater.from(this.mContext);
        }

        public void updateUI(ArrayList<MyClass> list) {
            this.classArrayList = list;
            notifyDataSetChanged();

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
                convertView = inflater.inflate(R.layout.item_class, null);
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
