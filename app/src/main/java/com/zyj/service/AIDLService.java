package com.zyj.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.zyj.Book;
import com.zyj.BookManager;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service {

    public final String TAG = this.getClass().getSimpleName();
    
    private List<Book> mBooks = new ArrayList<>();
    
    //由AIDL生成的BookManager
    private final BookManager.Stub mBookManager = new BookManager.Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            synchronized (this){
                Log.e(TAG, "invoking getBooks() method , now the list is : " + mBooks.toString());
                if(mBooks != null){
                    return mBooks;
                }
                return new ArrayList<>();
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (this){
                if(mBooks == null){
                    mBooks = new ArrayList<>();
                }
                if(book == null){
                    Log.e(TAG, "book is null" );
                    book = new Book();
                }
                book.setPrice(2333);
                if(!mBooks.contains(book)){
                    mBooks.add(book);
                }
                Log.e(TAG, "invoking addBooks() method , now the list is : " + mBooks.toString());
            }

        }
    };
    
    public AIDLService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Book book = new Book();
        book.setName("Android");
        book.setPrice(25);
        mBooks.add(book);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(getClass().getSimpleName(), String.format("on bind,intent = %s", intent.toString()));
        return mBookManager;
    }
}
