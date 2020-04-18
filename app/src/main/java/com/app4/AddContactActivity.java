package com.app4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class AddContactActivity extends AppCompatActivity {

    public static final Pattern PATTERN_PHONE = Pattern.compile("[\\d]{9}");
    public static final Pattern PATTERN_BIRTHDAY = Pattern.compile("^(((0[1-9]|[1-2][0-9]|3[0-1]))/(0[13578]|(10|12))|((0[1-9]|[1-2][0-9])/02)|((0[1-9]|[1-2][0-9]|30))/(0[469]|11))/[0-9]{4}$");
    public static final Pattern PATTERN_NAME = Pattern.compile("[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]+" + "\\s?" + "[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]*");
    public static final Pattern PATTERN_SURNAME = Pattern.compile("[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]+" + "-?" + "[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]*");
    private TextInputLayout textLayoutName;
    private TextInputLayout textLayoutSurame;
    private TextInputLayout textLayoutPhone;
    private TextInputLayout textLayoutBirthday;

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

        textLayoutName = findViewById(R.id.text_input_name);
        textLayoutPhone = findViewById(R.id.text_input_phone);
        textLayoutSurame = findViewById(R.id.text_input_surname);
        textLayoutBirthday = findViewById(R.id.text_input_birthday);

        if(confirmInput(view)) {
            String taskName = textLayoutName.getEditText().getText().toString();
            String taskPhone = textLayoutPhone.getEditText().getText().toString();
            String taskSurname = textLayoutSurame.getEditText().getText().toString();
            String taskBirthday = textLayoutBirthday.getEditText().getText().toString();

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
        }else {
            return;
        }
    }

    public boolean confirmInput(View view) {
        if (!validateName() | !validatePhone() | !validateSurname() | !validateBirthday()) {
            return false;
        }
        return true;
    }

    private boolean validateName() {
        String inputName = textLayoutName.getEditText().getText().toString().trim();
        if (inputName.isEmpty()) {
            textLayoutName.setError("Field can't be empty");
            return false;
        } else if (!PATTERN_NAME.matcher(inputName).matches()) {
            textLayoutName.setError("Enter a valid surname: only 1 space");
            return false;
        } else {
            textLayoutName.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String inputPhone = textLayoutPhone.getEditText().getText().toString().trim();
        if (inputPhone.isEmpty()) {
            textLayoutPhone.setError("Field can't be empty");
            return false;
        } else if (!PATTERN_PHONE.matcher(inputPhone).matches()) {
            textLayoutPhone.setError("Enter a valid phone number");
            return false;
        } else {
            textLayoutPhone.setError(null);
            return true;
        }
    }

    private boolean validateSurname() {
        String inputSurname = textLayoutSurame.getEditText().getText().toString().trim();
        if (inputSurname.isEmpty()) {
            textLayoutSurame.setError("Field can't be empty");
            return false;
        } else if (!PATTERN_SURNAME.matcher(inputSurname).matches()) {
            textLayoutSurame.setError("Enter a valid surname: only 1 \" - \"");
            return false;
        } else {
            textLayoutSurame.setError(null);
            return true;
        }
    }

    private boolean validateBirthday() {
        String inputBirthday = textLayoutBirthday.getEditText().getText().toString();
        if (inputBirthday.isEmpty()) {
            textLayoutBirthday.setError("Field can't be empty");
            return false;
        } else if (!PATTERN_BIRTHDAY.matcher(inputBirthday).matches()) {
            textLayoutBirthday.setError("Enter a valid birthday: only dd/mm/yyyy");
            return false;
        } else {
            textLayoutBirthday.setError(null);
            return true;
        }
    }


}
