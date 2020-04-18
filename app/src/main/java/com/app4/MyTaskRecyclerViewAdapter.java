package com.app4;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app4.TaskFragment.OnListFragmentInteractionListener;
import com.app4.tasks.TaskListContent.Task;

import java.util.List;

public class MyTaskRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder> {

    private final List<Task> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyTaskRecyclerViewAdapter(List<Task> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Task task = mValues.get(position);
        holder.mItem = task;
        holder.mContentView.setText(task.name);
        final String picPath = task.picPath;
        Context context = holder.mView.getContext();
        if (picPath != null && !picPath.isEmpty()) {
            if (picPath.contains("avatar")) {
                Drawable taskDrawable;
                switch (task.picPath) {
                    case "avatar_1":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_1);
                        break;
                    case "avatar_2":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_2);
                        break;
                    case "avatar_3":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_3);
                        break;
                    case "avatar_4":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_4);
                        break;
                    case "avatar_5":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_5);
                        break;
                    case "avatar_6":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_6);
                        break;
                    case "avatar_7":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_7);
                        break;
                    case "avatar_8":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_8);
                        break;
                    case "avatar_9":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_9);
                        break;
                    case "avatar_10":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_10);
                        break;
                    case "avatar_11":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_11);
                        break;
                    case "avatar_12":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_12);
                        break;
                    case "avatar_13":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_13);
                        break;
                    case "avatar_14":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_14);
                        break;
                    case "avatar_15":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_15);
                        break;
                    default:
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_16);
                }
                holder.mItemImageView.setImageDrawable(taskDrawable);
            }
        } else {
            holder.mItemImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.avatar_16));
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.OnListFragmentClickInteraction(holder.mItem, position);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mListener.OnListFragmentLongClickInteraction(position);
                return false;
            }
        });

        holder.mDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.OnListDeleteInteraction(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final ImageView mItemImageView;
        public Task mItem;
        public ImageView mDeleteImage;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = view.findViewById(R.id.content);
            mItemImageView = view.findViewById(R.id.item_image);
            mDeleteImage = view.findViewById(R.id.deleteButton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
