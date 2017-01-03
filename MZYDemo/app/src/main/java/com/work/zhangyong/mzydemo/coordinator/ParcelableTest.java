package com.work.zhangyong.mzydemo.coordinator;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangyong on 2016/9/30.
 */

public class ParcelableTest implements Parcelable {
    private int id;
    private String name ;
    protected ParcelableTest(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<ParcelableTest> CREATOR = new Creator<ParcelableTest>() {
        @Override
        public ParcelableTest createFromParcel(Parcel in) {
            return new ParcelableTest(in);
        }

        @Override
        public ParcelableTest[] newArray(int size) {
            return new ParcelableTest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
