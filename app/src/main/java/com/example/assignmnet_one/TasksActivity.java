package com.example.assignmnet_one;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {
    private ImageButton btn;
    private ListView listview;
    private ListView listviewcomp;
    private ArrayAdapter adapter;
    private Spinner spn;

    Intent mainact;
    Intent addtask;
    private TextView txt;
    private ImageButton clearcomp;
    public static final String DATA = "DATA";
    public static final String COMPLETED = "COMPLETED";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    ArrayList<String> arr = new ArrayList<String>();
    ArrayList<String> arrcomp = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        setupViews();
        setupSharedPrefs();
        checkprefs();
    }

    public void setupViews() {
        mainact = new Intent(this, MainActivity.class);
        addtask = new Intent(this, AddTask.class);
        btn = findViewById(R.id.backbtn);
        listview = findViewById(R.id.listt);
        spn = findViewById(R.id.spn1);
        listviewcomp = findViewById(R.id.listt2);
        txt=findViewById(R.id.txtcomp);
        clearcomp=findViewById(R.id.deletebtn);
    }

    public void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    public void checkprefs() {
        Gson gson = new Gson();
        String str = prefs.getString(DATA, "");
        String strcomp = prefs.getString(COMPLETED, "");
        if (!str.equals("")) {
            TheTask[] tasks = gson.fromJson(str, TheTask[].class);

            for (int i = 0; i <tasks.length; i++) {
                arr.add("Name: " + tasks[i].getName() + " => Due Date: " + tasks[i].getDate() + "\n" + tasks[i].getDesc()
                        + ", => Done = " + tasks[i].isDone());
            }
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, arr);
            listview.setAdapter(adapter);

        }


        if(!strcomp.equals("")) {

            TheTask[] tasks = gson.fromJson(strcomp, TheTask[].class);

            for (int i = 0; i <tasks.length; i++) {
                arrcomp.add("Name: " + tasks[i].getName() + " => Due Date: " + tasks[i].getDate() + "\n" + tasks[i].getDesc()
                            + ", => Done = " + tasks[i].isDone());
            }
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrcomp);
            listviewcomp.setAdapter(adapter);
            Toast.makeText(this,strcomp.toString(),Toast.LENGTH_LONG).show();
            if(!strcomp.equals("[]")){
            listviewcomp.setVisibility(View.VISIBLE);
            txt.setVisibility(View.VISIBLE);
            clearcomp.setVisibility(View.VISIBLE);}
    }

}

    public void update() {
        Gson gson = new Gson();
        String str = prefs.getString(DATA, "");
        String strco = prefs.getString(COMPLETED, "");
        if (!str.equals("")) {
            TheTask[] tasks = gson.fromJson(str, TheTask[].class);
            if (tasks.length > 0) {
                int len = 0;
                int j = 0;
                for (int i = 0; i < tasks.length; i++) {
                    if (listview.isItemChecked(i)) {
                        continue;

                    } else {
                        len += 1;
                    }
                }
                TheTask[] newtask = new TheTask[len];
                TheTask[] comptask = new TheTask[tasks.length-len];
                int c=0;
                for (int i = 0; i <tasks.length ; i++) {
                    if (listview.isItemChecked(i)) {
                        tasks[i].setDone(true);
                        comptask[c]=tasks[i];
                        c+=1;
                    } else {
                        newtask[j] = tasks[i];
                        j+=1;
                    }

                }

            if(strco!=null) {
                TheTask[] prevcomptask = gson.fromJson(strco, TheTask[].class);
                int p = 0;
                if (prevcomptask != null) {
                    if (comptask.length == 0) {
                        p = prevcomptask.length;
                    } else {
                        p = prevcomptask.length + comptask.length;
                    }
                    TheTask[] last = new TheTask[p];
                    for (int i = 0; i < prevcomptask.length; i++) {
                        last[i] = prevcomptask[i];
                    }
                    int j1 = 0;
                    for (int i = prevcomptask.length; i < last.length; i++) {
                        last[i] = comptask[j1];
                        j1 += 1;
                    }
                    String comptaskSttring = gson.toJson(last);
                    editor.putString(COMPLETED, comptaskSttring);

                }
                else{
                    String comptaskSttring = gson.toJson(comptask);
                    editor.putString(COMPLETED, comptaskSttring);
                }

            }
                String taskSttring = gson.toJson(newtask);
                editor.putString(DATA, taskSttring);

                editor.commit();
            }


        }


    }
    public void backfromtasks(View view) {
        startActivity(mainact);
        update();
    }
    public void addNewTask(View view) {
        startActivity(addtask);
        update();
    }

    public void deleteCompletedTasks(View view) {
        String x="";
        editor.putString(COMPLETED, x);
        editor.commit();
        listviewcomp.setVisibility(View.GONE);
        txt.setVisibility(View.GONE);
        clearcomp.setVisibility(View.GONE);

    }
}
