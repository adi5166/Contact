package com.app4.tasks;

import android.os.Parcel;
import android.os.Parcelable;

import com.app4.MainActivity;
import com.app4.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TaskListContent {

    public static final List<Task> ITEMS = new ArrayList<Task>();

    public static final Map<String, Task> ITEM_MAP = new HashMap<String, Task>();

    private static final int COUNT = 2;

    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(Task item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static void removeItem(int position){
        String itemId = ITEMS.get(position).id;
        ITEMS.remove(position);
        ITEM_MAP.remove(itemId);
    }

    private static Task createDummyItem(int position) {
        return new Task(String.valueOf(position), "Name " + position, makePhoneNumber(position), "Kowalski", "0"+((position-1)%9+1)+"/0"+((position+2)%9+1)+"/2020");
    }

    private static String makePhoneNumber(int position) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        int rnd = random.nextInt(9);
        int rnd2 = random.nextInt(9);
        builder.append(rnd2).append(position).append(58).append(rnd).append(64).append(position).append(rnd2);
        return builder.toString();
    }

    public static class Task implements Parcelable {
        public final String id;
        public final String name;
        public final String phone;
        public final String picPath;
        public final String surname;
        public final String birthday;

        public Task(String id, String name, String phone, String surname, String birthday) {
            this.id = id;
            this.name = name;
            this.phone = phone;
            int rnd = new Random().nextInt(15)+1;
            this.picPath = "avatar_"+rnd;
            this.surname = surname;
            this.birthday = birthday;
        }


        protected Task(Parcel in) {
            id = in.readString();
            name = in.readString();
            phone = in.readString();
            picPath = in.readString();
            surname = in.readString();
            birthday = in.readString();
        }

        public static final Creator<Task> CREATOR = new Creator<Task>() {
            @Override
            public Task createFromParcel(Parcel in) {
                return new Task(in);
            }

            @Override
            public Task[] newArray(int size) {
                return new Task[size];
            }
        };

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
            dest.writeString(phone);
            dest.writeString(picPath);
            dest.writeString(surname);
            dest.writeString(birthday);
        }
    }
}
