package com.app4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

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

        EditText taskNameEditText = findViewById(R.id.taskTitle);
        EditText taskPhoneEditText = findViewById(R.id.taskPhone);
        EditText taskSurnameEditText = findViewById(R.id.taskSurname);
        EditText taskBirthdayEditText = findViewById(R.id.taskBirthday);

        String taskName = taskNameEditText.getText().toString();
        String taskPhone = taskPhoneEditText.getText().toString();
        String taskSurname = taskSurnameEditText.getText().toString();
        String taskBirthday = taskBirthdayEditText.getText().toString();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        Intent data = new Intent();
        data.putExtra(MainActivity.addName, taskName);
        data.putExtra(MainActivity.addPhone, taskPhone);
        data.putExtra(MainActivity.addSurname, taskSurname);
        data.putExtra(MainActivity.addBirthday, taskBirthday);
        setResult(RESULT_OK, data);
        finish();
    }


}
