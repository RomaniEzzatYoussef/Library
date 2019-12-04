package com.romani.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.romani.library.RDB.Book;
import com.romani.library.RDB.LibraryDB;

public class BookDetailsActivity extends AppCompatActivity
{
    TextView ID_B , bookID , bookName , bookAuthor , bookType , bookShelfNumber , bookNumber , numberOfCopies , bookInventory;
    Book book = new Book();
    LibraryDB libraryDB;
    String bookIDDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_details);

        bookIDDetail = getIntent().getStringExtra("ID");

        libraryDB = LibraryDB.getInstance(this);
        book = libraryDB.getBookDao().getBookByID(bookIDDetail);

        initViews();

        setBookViews();
    }

    public void initViews()
    {
        ID_B = findViewById(R.id.ID_TXTView);
        bookID = findViewById(R.id.bookID_TXTView);
        bookName = findViewById(R.id.bookName_TXTView);
        bookAuthor = findViewById(R.id.bookAuthor_TXTView);
        bookType = findViewById(R.id.bookType_TXTView);
        bookShelfNumber = findViewById(R.id.bookShelfNumber_TXTView);
        bookNumber = findViewById(R.id.bookNumber_TXTView);
        numberOfCopies = findViewById(R.id.bookNumberOfCopies_TXTView);
        bookInventory = findViewById(R.id.bookInventory_TXTView);

    }

    public void setBookViews()
    {
        ID_B.setText(book.getIDBook()+"");
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
        startActivity(new Intent(BookDetailsActivity.this, BooksActivity.class));
    }

    public void edit(View v)
    {
        Intent i = new Intent(BookDetailsActivity.this, AddBookActivity.class);
        i.putExtra("viewDetails" , "editBook");
        i.putExtra("ID" , bookIDDetail);
        startActivity(i);

    }

    public void perviousBook(View v)
    {
        long currentID = book.getIDBook();
        if (currentID > 1)
        {
            book = libraryDB.getBookDao().getBookByID((currentID - 1)+"");
            setBookViews();
            bookIDDetail = ID_B.getText()+"";

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
            bookIDDetail = ID_B.getText()+"";
        }

        else
        {
            Toast.makeText(this , "This is the Last Book" , Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onResume()
    {
        super.onResume();

        bookIDDetail = getIntent().getStringExtra("ID");
        book = libraryDB.getBookDao().getBookByID(bookIDDetail);
        setBookViews();
    }
}
