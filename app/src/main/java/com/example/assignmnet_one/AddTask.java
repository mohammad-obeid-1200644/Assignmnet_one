package com.example.assignmnet_one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Size;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class AddTask extends AppCompatActivity {
Intent mainact;
Intent tasksact;
private EditText nameoftask;
private DatePicker datePicker;
private EditText descr;
private Spinner spinner;
private ImageButton addbtn;
private TextView txtbtn;
private SharedPreferences prefs;
private SharedPreferences.Editor editor;
public static final String DATA="DATA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setupViews();

    }
    public void setupViews(){
        nameoftask=findViewById(R.id.txttx);
        datePicker=findViewById(R.id.datePicker);
        descr=findViewById(R.id.desc);
        spinner=findViewById(R.id.spn1);
        mainact=new Intent(AddTask.this,MainActivity.class);
        tasksact=new Intent(AddTask.this,TasksActivity.class);
        txtbtn=findViewById(R.id.txtbtn);
        setupSharedPrefs();
    }
    public void setupSharedPrefs(){
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor=prefs.edit();
    }

    public void backclk(View view) {
        startActivity(mainact);
    }
    public void returnvalue() {
        String name = nameoftask.getText().toString();
        String de = descr.getText().toString();
        if (name.equals("")) {
            nameoftask.setHint("Please Enter The Task's Name");
            nameoftask.setHintTextColor(Color.parseColor("#F00000"));
        }
        if (de.equals("")) {
            descr.setHint("Please Enter The Description");
            descr.setHintTextColor(Color.parseColor("#F00000"));
        }
        if (!name.equals("") && !de.equals("")) {
            if (spinner.getSelectedItem().toString().equals("Assignments Tasks")) {

                int d = datePicker.getMonth();
                d += 1;
                String val = datePicker.getDayOfMonth() + "-" + d + "-" + datePicker.getYear();
                Gson gson=new Gson();
                String str=prefs.getString(DATA,"");
                TheTask[] tasks=gson.fromJson(str,TheTask[].class);
                if (tasks == null) {
                    tasks = new TheTask[1];
                    tasks[0] = new TheTask(name, de, val,false);
                } else {
                    TheTask[] newTasks = new TheTask[tasks.length + 1];

                    for(int i=0;i<tasks.length;i++){
                        newTasks[i]=new TheTask(tasks[i].getName(),tasks[i].getDesc(),tasks[i].getDate(),tasks[i].isDone());
                    }
                    newTasks[tasks.length] = new TheTask(name, de, val,false);
                    tasks = newTasks;
                }


                String taskSttring =gson.toJson(tasks);
                editor.putString(DATA,taskSttring);
                editor.commit();
                nameoftask.setText("");
                descr.setText("");
                startActivity(tasksact);
            } else {
                Toast.makeText(AddTask.this, "This Library (''"+spinner.getSelectedItem().toString()+"'') Isn't Available RightNow", Toast.LENGTH_SHORT).show();
            }
        }

    }

//    public void returnvalue() {
//        String name = nameoftask.getText().toString();
//        String de = descr.getText().toString();
//        if (name.equals("")) {
//            nameoftask.setHint("Please Enter The Task's Name");
//            nameoftask.setHintTextColor(Color.parseColor("#F00000"));
//        }
//        if (de.equals("")) {
//            descr.setHint("Please Enter The Description");
//            descr.setHintTextColor(Color.parseColor("#F00000"));
//        }
//        if (!name.equals("") && !de.equals("")) {
//            if (spinner.getSelectedItem().toString().equals("Assignments Tasks")) {
//                String dat = datePicker.toString();
//                TheTask newTask = new TheTask(name, de, dat);
//                int d = datePicker.getMonth();
//                d += 1;
//                String val = datePicker.getDayOfMonth() + "-" + d + "-" + datePicker.getYear();
//
//                tasks.putExtra("newtask", name + "," + de + "," + val);
//                nameoftask.setText("");
//                descr.setText("");
//                startActivity(tasks);
////            Toast.makeText(AddTask.this,"New Task has been Added !",Toast.LENGTH_LONG).show();
//
//            } else {
//                Toast.makeText(AddTask.this, "This Library (''"+spinner.getSelectedItem().toString()+"'') Isn't Available RightNow", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    public void addimgclk(View view) {
        returnvalue();
    }

    public void addtxtclk(View view) {
        returnvalue();
    }
}