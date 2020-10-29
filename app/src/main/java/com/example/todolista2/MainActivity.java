package com.example.todolista2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("tasks");

        final ArrayList<ToDo> arr = new ArrayList<ToDo>();
        ListView lv = findViewById(R.id.lv);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ToDo toDo = new ToDo();
                for(DataSnapshot messageSnapShot : dataSnapshot.getChildren()) {
                    for(DataSnapshot child : messageSnapShot.getChildren()) {
                        if(child.getKey().equals("date")) {
                            toDo.setDate((String) child.getValue());
                        } else if(child.getKey().equals("done")) {
                            toDo.setDone((Boolean) child.getValue());
                        } else if(child.getKey().equals("person")) {
                            toDo.setPerson((String) child.getValue());
                        } else if (child.getKey().equals("taskName")) {
                            toDo.setTaskName((String) child.getValue());
                        }
                    }
                    arr.add(toDo);
                }
                System.out.println(arr);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        ArrayAdapter<ToDo> arrayAdapter = new ArrayAdapter<ToDo>(this, android.R.layout.simple_list_item_1, arr);
        lv.setAdapter(arrayAdapter);
    }

    public void onAdd(View view) {
        ToDo toDo = createToDo();
        DatabaseReference myRef = database.getReference().child("tasks");
        myRef.child(toDo.getTaskName()).setValue(toDo);
    }

    public ToDo createToDo() {
        EditText taskName = (EditText) findViewById(R.id.txtTask);
        EditText personName = (EditText) findViewById(R.id.txtName);
        DatePicker datePicker = (DatePicker) findViewById(R.id.dpDateOfTask);
        CheckBox checkBox = (CheckBox) findViewById(R.id.chkDone);

        String nameOfTask = taskName.getText().toString();
        String nameOfPerson = personName.getText().toString();
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date date = calendar.getTime();

        boolean finished = checkBox.isChecked();

        ToDo toDo = new ToDo(nameOfTask, nameOfPerson, date.toString(), finished);
        return toDo;

    }

}