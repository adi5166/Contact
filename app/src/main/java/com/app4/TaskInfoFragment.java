package com.app4;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app4.tasks.TaskListContent;

public class TaskInfoFragment extends Fragment {

    public TaskInfoFragment() {
        // Required empty public constructor
    }

    public void displayTask(TaskListContent.Task task) {
        FragmentActivity activity = getActivity();

        TextView taskInfoTitle = activity.findViewById(R.id.taskInfoTitle);
        TextView taskInfoDescription = activity.findViewById(R.id.taskInfoDescription);
        ImageView taskInfoImage = activity.findViewById(R.id.taskInfoImage);

        taskInfoTitle.setText(task.title);
        taskInfoDescription.setText(task.details);

        if (task.picPath != null && !task.picPath.isEmpty()) {
            if (task.picPath.contains("avatar")) {
                Drawable taskDrawable;
                switch (task.picPath) {
                    case "avatar_1":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_1);
                        break;
                    case "avatar_2":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_2);
                        break;
                    case "avatar_3":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_3);
                        break;
                    case "avatar_4":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_4);
                        break;
                    case "avatar_5":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_5);
                        break;
                    case "avatar_6":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_6);
                        break;
                    case "avatar_7":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_7);
                        break;
                    case "avatar_8":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_8);
                        break;
                    case "avatar_9":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_9);
                        break;
                    case "avatar_10":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_10);
                        break;
                    case "avatar_11":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_11);
                        break;
                    case "avatar_12":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_12);
                        break;
                    case "avatar_13":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_13);
                        break;
                    case "avatar_14":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_14);
                        break;
                    case "avatar_15":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_15);
                        break;
                    default:
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_16);
                }
                taskInfoImage.setImageDrawable(taskDrawable);
            }

        } else {
            taskInfoImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.avatar_16));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceStates) {
        super.onActivityCreated(savedInstanceStates);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            TaskListContent.Task receivedTask = intent.getParcelableExtra(MainActivity.taskExtra);
            if (receivedTask != null) {
                displayTask(receivedTask);
            }
        }
    }
}
