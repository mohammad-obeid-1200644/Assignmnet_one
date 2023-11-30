package com.example.assignmnet_one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    private ImageButton imgbtn;
    private TextView txt;
     Intent addtaskPage;
     Intent Task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        loadtasks();


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
//                    Toast.makeText(MainActivity.this,"Assignments",Toast.LENGTH_LONG).show();
                    startActivity(Task);
                }
                else if(position==1){
                    Toast.makeText(MainActivity.this,"Not Done Yet",Toast.LENGTH_SHORT).show();
                }
                else if(position==2){
                    Toast.makeText(MainActivity.this,"Not Done Yet",Toast.LENGTH_SHORT).show();
                }



//              setContentView(R.layout.activity_add_task);
//            Toast.makeText(MainActivity.this,"ffffgkslaa;lds",Toast.LENGTH_LONG).show();
            }

        });
    }


    public void setUpViews(){
        addtaskPage=new Intent(this,AddTask.class);
        Task=new Intent(this,TasksActivity.class);
        listview=findViewById(R.id.listview1);
//        imgbtn=findViewById(R.id.addbtn);
//        txt=findViewById(R.id.imgplustxt);


    }
    public void loadtasks(){
        ArrayList<String> arr=new ArrayList<String>();
        arr.add("Assignments Tasks");
        arr.add("Projects Tasks");
        arr.add("Quiz's Tasks");
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arr);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listview.setAdapter(adapter);
    }

    public void addimgclk(View view) {
        startActivity(addtaskPage);
    }

    public void addtxtclk(View view) {
        startActivity(addtaskPage);
    }


}