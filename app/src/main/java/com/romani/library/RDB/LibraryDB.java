package com.romani.library.RDB;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Executors;

@Database(entities = {Book.class , Borrower.class} , version =  1)
public abstract class LibraryDB extends RoomDatabase
{
    public abstract BookDao getBookDao();

    public abstract BorrowerDao getBorrowerDao();


    private final static String DB_NAME = "MyBooksDB.db";

    public static LibraryDB getInstance(Context context)
    {
        File dbFile = context.getDatabasePath(DB_NAME);

        if(!dbFile.exists())
        {
            copyDatabaseFile(context , "/data/data/com.romani.library/databases/MyBooksDB.db");
        }

        return Room.databaseBuilder(context.getApplicationContext(),LibraryDB.class, DB_NAME).allowMainThreadQueries().build();
    }

    private static void copyDatabaseFile(Context context, String destinationPath)
    {
        InputStream inputStream = null;
        OutputStream output = null;
        byte[] buffer = new byte[8192];
        int length = 0;



        try {
            inputStream = context.getAssets().open("databases/" + DB_NAME);
            output = new FileOutputStream(destinationPath);

            while (true)
            {

                if (!((length = inputStream.read(buffer, 0, 8192)) > 0))
                    break;

                output.write(buffer, 0, length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

