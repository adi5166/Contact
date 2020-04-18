package com.app4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.app4.tasks.TaskListContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements
        TaskFragment.OnListFragmentInteractionListener,
        DeleteDialog.OnDeleteDialogInteractionListener, CallDialog.OnCallDialogInteractionListener{

    public static final String taskExtra = "taskExtra";
    private int currentItemPosition = -1;

    public static final String addName = "addName";
    public static final String addPhone = "addPhone";
    public static final String addSurname = "addSurname";
    public static final String addBirthday = "addBirthday";
    private String Name;
    private String Phone;
    private String Surname;
    private String Birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO dodać opcje landscape
                Intent intent = new Intent(v.getContext(), AddContactActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Name = data.getStringExtra(addName);
                Phone = data.getStringExtra(addPhone);
                Surname = data.getStringExtra(addSurname);
                Birthday = data.getStringExtra(addBirthday);
                addNewClick();
            }
        }
    }

    public void addNewClick() {
        if (Name.isEmpty() && Phone.isEmpty() && Surname.isEmpty() && Birthday.isEmpty()) {
            TaskListContent.addItem(new TaskListContent.Task(
                    "Task" + TaskListContent.ITEMS.size() + 1,
                    getString(R.string.default_name),
                    getString(R.string.default_phone),
                    getString(R.string.default_surname),
                    getString(R.string.default_birthday)));
        } else {
            if (Name.isEmpty()) {
                Name = getString(R.string.default_name);
            }
            if (Phone.isEmpty()) {
                Phone = getString(R.string.default_phone);
            }
            if(Surname.isEmpty()) {
                Surname = getString(R.string.default_surname);
            }
            if(Birthday.isEmpty()) {
                Birthday = getString(R.string.default_birthday);
            }
            TaskListContent.addItem(new TaskListContent.Task("Task" + TaskListContent.ITEMS.size() + 1,
                    Name, Phone, Surname, Birthday));
        }
        ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
    }

    private void showDeleteDialog() {
        DeleteDialog.newInstance().show(getSupportFragmentManager(), getString(R.string.delete_dialog_tag));
    }

    private void showCallDialog() {
        CallDialog.newInstance().show(getSupportFragmentManager(), getString(R.string.call_dialog_tag));
    }

    private void startSecondActivity(TaskListContent.Task task, int position) {
        Intent intent = new Intent(this, TaskInfoActivity.class);
        intent.putExtra(taskExtra, task);
        startActivity(intent);
    }
/*
    public void addClick(View view) {
        EditText taskTitleEditText = findViewById(R.id.taskTitle);
        EditText taskDescriptionEditText = findViewById(R.id.taskDescription);
        Spinner drawableSpinner = findViewById(R.id.drawableSpinner);
        String taskTitle = taskTitleEditText.getText().toString();
        String taskDescription = taskDescriptionEditText.getText().toString();
        String selectedImage = drawableSpinner.getSelectedItem().toString();

        if (taskTitle.isEmpty() && taskDescription.isEmpty()) {
            TaskListContent.addItem(new TaskListContent.Task(
                    "Task" + TaskListContent.ITEMS.size() + 1,
                    getString(R.string.default_title),
                    getString(R.string.default_description)));
        } else {
            if (taskTitle.isEmpty()) {
                taskTitle = getString(R.string.default_title);
            }
            if (taskDescription.isEmpty()) {
                taskDescription = getString(R.string.default_description);
            }
            TaskListContent.addItem(new TaskListContent.Task("Task" + TaskListContent.ITEMS.size() + 1,
                    taskTitle,
                    taskDescription));
        }
        ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();
        taskTitleEditText.setText("");
        taskDescriptionEditText.setText("");

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
*/
    @Override
    public void OnListFragmentClickInteraction(TaskListContent.Task task, int position) {
        //Toast.makeText(this, getString(R.string.item_selected_msg), Toast.LENGTH_SHORT).show();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            displayTaskInFragment(task);
        } else {
            startSecondActivity(task, position);
        }
    }

    private void displayTaskInFragment(TaskListContent.Task task) {
        TaskInfoFragment taskInfoFragment = ((TaskInfoFragment) getSupportFragmentManager().findFragmentById(R.id.displayFragment));
        if (taskInfoFragment != null) {
            taskInfoFragment.displayTask(task);
        }
    }
/*
    private void displayAddFragment(TaskListContent.Task task) {
        TaskInfoFragment addContactFragment = ((TaskInfoFragment) getSupportFragmentManager().findFragmentById(R.id.addFragment));
        if (addContactFragment != null) {
            addContactFragment.displayAdd(task);
        }
    }
*/
    @Override
    public void OnListFragmentLongClickInteraction(int position) {
        //Toast.makeText(this, getString(R.string.long_call_msg) + position, Toast.LENGTH_SHORT).show();
        showCallDialog();
        currentItemPosition = position;
    }

    @Override
    public void OnListDeleteInteraction(int position) {
        //Toast.makeText(this, getString(R.string.long_delete_msg) + position, Toast.LENGTH_SHORT).show();
        showDeleteDialog();
        currentItemPosition = position;
    }

    @Override
    public void OnDialogPositiveClick(DialogFragment dialog) {
        if (currentItemPosition != -1 && currentItemPosition < TaskListContent.ITEMS.size()) {
            TaskListContent.removeItem(currentItemPosition);
            ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();
        }
    }

    @Override
    public void OnDialogNegativeClick(DialogFragment dialog) {
        View v = findViewById(R.id.fab);
        if (v != null) {
            Snackbar.make(v, getString(R.string.delete_cancel_msg), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.retry_delete_msg), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDeleteDialog();
                        }
                    }).show();
        }
    }

    @Override
    public void OnCallPositiveClick(DialogFragment dialog) {
        if (currentItemPosition != -1 && currentItemPosition < TaskListContent.ITEMS.size()) {
            //TaskListContent.callItem(currentItemPosition);
        }
    }

    @Override
    public void OnCallNegativeClick(DialogFragment dialog) {

    }
}
