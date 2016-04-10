package com.giangnd_svmc.ha18.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cocosw.bottomsheet.BottomSheet;
import com.giangnd_svmc.ha18.R;
import com.giangnd_svmc.ha18.app.BaseFragment;
import com.giangnd_svmc.ha18.entity.JsonParser;
import com.giangnd_svmc.ha18.entity.MyClass;
import com.giangnd_svmc.ha18.entity.MyUtils;
import com.giangnd_svmc.ha18.entity.Subject;
import com.giangnd_svmc.ha18.entity.Teacher;
import com.giangnd_svmc.ha18.glide.ImageUploadHelper;
import com.giangnd_svmc.ha18.ui.activity.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by admin on 4/9/2016.
 */
public class ClassFragment extends BaseFragment implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;
    private EditText edSearch;
    private LinearLayout btnResetText, btnSearch;
    private Teacher teacher;
    private MyClass myClass;
    private static final int CAMERA_REQUEST = 1888;
    private ArrayList<Subject> subjectArrayList = new ArrayList<>();
    private FloatingActionButton fab;
    private MessageHandler messageHandler;
    private ArrayList<String> listResult;

    public ClassFragment() {

    }

    public ClassFragment(Teacher teacher, MyClass myClass) {
        this.teacher = teacher;
        this.myClass = myClass;
        messageHandler = new MessageHandler();
    }

    @Override
    protected int getLayoutResIdContentView() {
        return R.layout.fragment_class;
    }

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        edSearch = (EditText) rootView.findViewById(R.id.searchView);
        btnSearch = (LinearLayout) rootView.findViewById(R.id.btn_Search);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        adapter = new MyViewPagerAdapter(subjectArrayList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        new LoadSubject().execute();
        final MainActivity activity = (MainActivity) getActivity();
        activity.toolbar.setTitle(this.myClass.name);
        activity.toolbar.setNavigationIcon(R.drawable.keyboard_backspace);
        activity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    class LoadSubject extends AsyncTask {
        JSONObject jsonObject;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            JsonParser jsonParser = new JsonParser();
            jsonObject = jsonParser.getJsonFromUrl(MyUtils.urlListSubject(teacher.id, myClass.id));
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
                        subjectArrayList = new ArrayList<>();
                        JSONArray arr = jsonObject.getJSONArray(MyUtils.TAG_SUBJECT);

                        for (int i = 0; i < arr.length(); i++) {
                            Subject subject = new Subject();
                            JSONObject jsonObject = (JSONObject) arr.get(i);
                            subject.id = jsonObject.getInt(MyUtils.TAG_SUBJECT_ID);
                            subject.name = jsonObject.getString(MyUtils.TAG_SUBJECT_NAME);
                            Log.e("TAG", subject.name, null);
                            subjectArrayList.add(subject);
                        }
                    } else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            adapter.updateUI(subjectArrayList);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_Search) {

        }
        if (id == R.id.fab) {
            new BottomSheet.Builder(getBaseActivity()).title("Attendences").grid().sheet(R.menu.menu_sheet).listener(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case R.id.hand:
                            Toast.makeText(getBaseActivity().getBaseContext(), "A", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.camera:
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST);
                            break;
                    }
                }
            }).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            try {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                ImageUploadHelper imageUploadHelper = new ImageUploadHelper(getActivity(), messageHandler);
                imageUploadHelper.uploadImage(photo);

            } catch (Exception ae) {
                Snackbar.make(getBaseActivity().getCurrentFocus(), "Try Again !", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    private class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            switch (msg.what) {
                case 1:
                    try {
                        JSONArray jsonArray = new JSONArray(bundle.getString("json"));
                        String status = jsonArray.get(0).toString();
                        if (status.equals("success")) {
                            JSONArray arr = jsonArray.getJSONArray(1);
                            listResult = new ArrayList<>();
                            for (int i = 0; i < arr.length(); i++) {
                                listResult.add(arr.get(i).toString());
                            }
                            AttendenceFragment attendenceFragment = new AttendenceFragment(teacher, myClass, subjectArrayList.get(tabLayout.getSelectedTabPosition()), listResult);
                            replaceFragment(attendenceFragment, attendenceFragment.getTag(), attendenceFragment.getTag());

                        } else
                            Snackbar.make(getBaseActivity().getCurrentFocus(), "Try Again !", Snackbar.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    Snackbar.make(getBaseActivity().getCurrentFocus(), "Try Again 11 !", Snackbar.LENGTH_LONG).show();
                    break;

            }
            super.handleMessage(msg);
        }
    }

    class MyViewPagerAdapter extends PagerAdapter {
        private ArrayList<ListStudentPage> listPage = new ArrayList<>();
        private ArrayList<Subject> subjectArrayList = new ArrayList<>();

        public MyViewPagerAdapter(ArrayList<Subject> list) {
            subjectArrayList = list;

        }

        @Override
        public int getCount() {
            return this.subjectArrayList.size();
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View layout = null;
            if (position >= listPage.size()) {
                ListStudentPage attendencesPage = new ListStudentPage(getBaseActivity(), teacher, myClass, subjectArrayList.get(position));
                listPage.add(position, attendencesPage);
                layout = listPage.get(position).getContent();
            } else layout = listPage.get(position).getContent();
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
            return subjectArrayList.get(position).name;
        }

        public void updateUI(ArrayList<Subject> subjectArrayList) {
            this.subjectArrayList = subjectArrayList;
            notifyDataSetChanged();
        }
    }
}
