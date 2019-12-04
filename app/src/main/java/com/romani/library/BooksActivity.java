package com.romani.library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.romani.library.RDB.Book;
import com.romani.library.RDB.BookAdapter;
import com.romani.library.RDB.LibraryDB;

import java.util.ArrayList;

public class BooksActivity extends AppCompatActivity implements TextWatcher
{
    EditText searchByBookName , searchByBookAuthor;
    RecyclerView recyclerView;
    ArrayList<Book> books = new ArrayList<>();
    ProgressBar progressBar;
    LibraryDB libraryDB;

    public static final String CHANNEL_ID = "0";


    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private final int PERMISSION_REQ_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        addNotification();
        recyclerView = findViewById(R.id.books_recyclerView);
        searchByBookName = findViewById(R.id.searchByBookName);
        searchByBookAuthor = findViewById(R.id.searchByBookAuthor);
        progressBar = findViewById(R.id.myProgressMovie);


        libraryDB = LibraryDB.getInstance(this);
        bindRecycler();

        searchByBookName.addTextChangedListener( this);
        searchByBookAuthor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                books = (ArrayList<Book>) libraryDB.getBookDao().getBooksByAuthor(searchByBookAuthor.getText().toString());
                bindRecycler();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        if (ActivityCompat.checkSelfPermission(BooksActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQ_CODE);
        }


        Button b1 = findViewById(R.id.saveNoti);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification();
            }
        });

    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this , "10211")
                        .setSmallIcon(R.drawable.libr)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");
        builder.build();

        Intent notificationIntent = new Intent(this, BooksActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }

    public void showAddBookActivity(View v) {
        Intent i = new Intent(BooksActivity.this, AddBookActivity.class);
        i.putExtra("viewDetails", "newBook");
        startActivity(i);
    }


    @Override
    protected void onResume() {
        super.onResume();
        books = (ArrayList<Book>) libraryDB.getBookDao().getAllBooks();
        bindRecycler();

    }

    public void bindRecycler() {
        BookAdapter bookAdapter = new BookAdapter(this, books);
        recyclerView.setAdapter(bookAdapter);
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(BooksActivity.this, 1);
        recyclerView.setLayoutManager(layoutManager1);
    }

    public void deleteAll(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you want to delete all books?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                libraryDB.getBookDao().delete(books);
                books = (ArrayList<Book>) libraryDB.getBookDao().getAllBooks();
                bindRecycler();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void refresh(View v)
    {
        books = (ArrayList<Book>) libraryDB.getBookDao().getAllBooks();
        bindRecycler();
        addNotification();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {
        books = (ArrayList<Book>) libraryDB.getBookDao().getBooksByName(searchByBookName.getText().toString());
        bindRecycler();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


    public void saveDatabase(View v)
    {
/*
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            LibraryDB.getInstance(this).close();

            File db = getDatabasePath("my-db");
            File dbShm = new File(db.getParent(), "my-db-shm");
            File dbWal = new File(db.getParent(), "my-db-wal");

            File db2 = new File("/sdcard/", "my-db");
            File dbShm2 = new File(db2.getParent(), "my-db-shm");
            File dbWal2 = new File(db2.getParent(), "my-db-wal");

            try {
                FileUtils.copyFile(db, db2);
                FileUtils.copyFile(dbShm, dbShm2);
                FileUtils.copyFile(dbWal, dbWal2);
            } catch (Exception e) {
                Log.e("SAVEDB", e.toString());
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQ_CODE);

        }
*/
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PERMISSION_REQ_CODE) {
            if (ActivityCompat.checkSelfPermission(BooksActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQ_CODE);
            }
        }
    }

    public void goHome(View v)
    {
        startActivity(new Intent(BooksActivity.this , MainActivity.class));
    }


}
