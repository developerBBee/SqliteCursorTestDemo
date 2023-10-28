package jp.developer.bbee.sqlitecursortestdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Sample implements Parcelable {
    private final int columnInt;
    public int getColumnInt() {
        return columnInt;
    }

    private final String columnString;
    public String getColumnString() {
        return columnString;
    }

    public Sample(int columnInt, String columnString) {
        this.columnInt = columnInt;
        this.columnString = columnString;
    }

    protected Sample(Parcel in) {
        columnInt = in.readInt();
        columnString = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(columnInt);
        dest.writeString(columnString);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Sample> CREATOR = new Creator<Sample>() {
        @Override
        public Sample createFromParcel(Parcel in) {
            return new Sample(in);
        }

        @Override
        public Sample[] newArray(int size) {
            return new Sample[size];
        }
    };
}
