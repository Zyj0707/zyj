package com.zyj;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 张垚杰 on 2018/1/31.
 */

public class Book implements Parcelable{
    private String name;

    private int price;

    public Book(){}

    protected Book(Parcel in) {
        name = in.readString();
        price = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
    }

    public void readFromParcel(Parcel dest){
        name = dest.readString();
        price = dest.readInt();
    }

    @Override
    public String toString() {
        return "name:"+name+", price:"+price;
    }
}
