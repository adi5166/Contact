package com.app4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;

import com.app4.tasks.TaskListContent;

public class AddContactActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }
/*
    public void setAddClick(){
        EditText nameEditText = findViewById(R.id.addName);
        EditText surnameEditText = findViewById(R.id.addSurname);
        EditText birthdayEditText= findViewById(R.id.addBirthday);
        EditText phoneEditText = findViewById(R.id.addPhone);

        String Name = nameEditText.getText().toString();
        String Surname =surnameEditText.getText().toString();
        String Birthday = birthdayEditText.getText().toString();
        String Phone = phoneEditText.getText().toString();

        Intent data = new Intent();

        data.putExtra(MainActivity.addName, Name);
        data.putExtra(MainActivity.addSurname, Surname);
        data.putExtra(MainActivity.addBirthday, Birthday);
        data.putExtra(MainActivity.addPhone, Phone);

        setResult(RESULT_OK, data);
        finish();
    }
    */

    public void addClick2(View view) {

        EditText taskTitleEditText = findViewById(R.id.taskTitle2);
        EditText taskDescriptionEditText = findViewById(R.id.taskDescription2);
        String taskTitle = taskTitleEditText.getText().toString();
        String taskDescription = taskDescriptionEditText.getText().toString();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        Intent data = new Intent();
        data.putExtra(MainActivity.addTitle, taskTitle);
        data.putExtra(MainActivity.addDesc, taskDescription);
        setResult(RESULT_OK, data);
        finish();
    }


}
