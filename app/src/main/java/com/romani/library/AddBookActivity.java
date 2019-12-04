package com.romani.library;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.romani.library.RDB.Book;
import com.romani.library.RDB.LibraryDB;

import java.util.ArrayList;

public class AddBookActivity extends AppCompatActivity {

    RelativeLayout nevigationLayout;
    TextView addNewBook_txtView;
    EditText bookID, bookName, bookAuthor, bookType, bookShelfNumber, bookNumber, numberOfCopies, bookInventory;
    Book book = new Book();
    LibraryDB libraryDB;
    String bookIDDetail;
    String viewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        libraryDB = LibraryDB.getInstance(this);

        initViews();

        bookIDDetail = getIntent().getStringExtra("ID");

        viewDetails = getIntent().getStringExtra("viewDetails");


        if (viewDetails.equals("editBook"))
        {
            book = libraryDB.getBookDao().getBookByID(bookIDDetail);
            nevigationLayout.setVisibility(View.VISIBLE);
            addNewBook_txtView.setText("Edit Book");
            setBookViews();
        }
    }

    public void initViews() {

        nevigationLayout = findViewById(R.id.nevigation_relativeLayout);
        addNewBook_txtView = findViewById(R.id.addNewBook_txtView);
        bookID = findViewById(R.id.bookID_editText);
        bookName = findViewById(R.id.bookName_editText);
        bookAuthor = findViewById(R.id.bookAuthor_editText);
        bookType = findViewById(R.id.bookType_editText);
        bookShelfNumber = findViewById(R.id.bookShelfNumber_editText);
        bookNumber = findViewById(R.id.bookNumber_editText);
        numberOfCopies = findViewById(R.id.numberOfCopies_editText);
        bookInventory = findViewById(R.id.bookInventory_editText);

    }


    public void setBookObj() {

        book.setBookID(bookID.getText().toString());
        book.setBookName(bookName.getText().toString());
        book.setBookAuthor(bookAuthor.getText().toString());
        book.setBookType(bookType.getText().toString());
        book.setBookShelfNumber(bookShelfNumber.getText().toString());
        book.setBookNumber(bookNumber.getText().toString());
        book.setNumberOfCopies(numberOfCopies.getText().toString());
        book.setBookInventory(bookInventory.getText().toString());

    }


    public void setBookViews() {
        bookID.setText(book.getBookID());
        bookName.setText(book.getBookName());
        bookAuthor.setText(book.getBookAuthor());
        bookType.setText(book.getBookType());
        bookShelfNumber.setText(book.getBookShelfNumber());
        bookNumber.setText(book.getBookNumber());
        numberOfCopies.setText(book.getNumberOfCopies());
        bookInventory.setText(book.getBookInventory());
    }


    public void backToMainActivity(View v)
    {
        startActivity(new Intent(AddBookActivity.this, BooksActivity.class));
    }


    public void saveNewBook(View v) {

        if (addNewBook_txtView.getText().equals("Add New Book"))
        {
            if (bookName.getText().toString().length() > 1)
            {
                setBookObj();
                libraryDB.getBookDao().insert(book);
                startActivity(new Intent(AddBookActivity.this, BooksActivity.class));
                Toast.makeText(this, "Book '" + book.getBookName() + "' Added", Toast.LENGTH_LONG).show();
            }

            else
            {
                Toast.makeText(this, "You Should Enter Book Name", Toast.LENGTH_LONG).show();
            }

        } else if (addNewBook_txtView.getText().equals("Edit Book")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure you want to update borrower '" + book.getBookName() + "' ?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog

                    setBookObj();
                    libraryDB.getBookDao().update(book);
                    Toast.makeText(AddBookActivity.this, "Book '" + book.getBookName() + "' Updated", Toast.LENGTH_LONG).show();

                    long currentID = book.getIDBook();
                    int borrowerSize = libraryDB.getBookDao().getSizeOFBook();

                    if (currentID < borrowerSize)
                    {
                        book = libraryDB.getBookDao().getBookByID((currentID + 1)+"");

                        setBookViews();
                    }

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


    }

    public void perviousBook(View v)
    {
        long currentID = book.getIDBook();
        if (currentID > 1)
        {
            book = libraryDB.getBookDao().getBookByID((currentID - 1)+"");
            setBookViews();
        }

        else
        {
            Toast.makeText(this , "This is the First Book" , Toast.LENGTH_LONG).show();
        }

    }

    public void nextBook(View v)
    {
        long currentID = book.getIDBook();
        int borrowerSize = libraryDB.getBookDao().getSizeOFBook();

        if (currentID < borrowerSize)
        {
            book = libraryDB.getBookDao().getBookByID((currentID + 1)+"");

            setBookViews();
        }

        else
        {
            Toast.makeText(this , "This is the Last Book" , Toast.LENGTH_LONG).show();
        }

    }


}
