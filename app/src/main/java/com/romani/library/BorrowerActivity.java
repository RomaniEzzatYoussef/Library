package com.romani.library;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.romani.library.RDB.BorrowerAdapter;
import com.romani.library.RDB.LibraryDB;
import com.romani.library.RDB.Borrower;

import java.util.ArrayList;

public class BorrowerActivity extends AppCompatActivity
{
    EditText searchByBorrowerName;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ArrayList<Borrower> borrowers = new ArrayList<>();
    LibraryDB libraryDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrower);

        recyclerView = findViewById(R.id.borrowers_recyclerView);
        searchByBorrowerName = findViewById(R.id.searchByBorrowerName);
        progressBar = findViewById(R.id.myProgressMovie);


        libraryDB = LibraryDB.getInstance(this);
        bindRecycler();

        searchByBorrowerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                borrowers = (ArrayList<Borrower>) libraryDB.getBorrowerDao().getBorrowerByName(searchByBorrowerName.getText().toString());
                bindRecycler();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void showAddBorrowerActivity(View v) {
        Intent i = new Intent(BorrowerActivity.this, AddBorrowerActivity.class);
        i.putExtra("viewDetails", "newBorrower");
        startActivity(i);
    }


    @Override
    protected void onResume() {
        super.onResume();

        borrowers = (ArrayList<Borrower>) libraryDB.getBorrowerDao().getAllBorrowers();
        bindRecycler();

    }

    public void bindRecycler() {
        BorrowerAdapter borrowerAdapter = new BorrowerAdapter(BorrowerActivity.this, borrowers);
        recyclerView.setAdapter(borrowerAdapter);
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(BorrowerActivity.this, 1);
        recyclerView.setLayoutManager(layoutManager1);
    }

    public void deleteAll(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you want to delete all borrpwers?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                libraryDB.getBorrowerDao().delete(borrowers);
                borrowers = (ArrayList<Borrower>) libraryDB.getBorrowerDao().getAllBorrowers();
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
        borrowers = (ArrayList<Borrower>) libraryDB.getBorrowerDao().getAllBorrowers();
        bindRecycler();
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

    public void goHome(View v)
    {
        startActivity(new Intent(BorrowerActivity.this , MainActivity.class));
    }



}
