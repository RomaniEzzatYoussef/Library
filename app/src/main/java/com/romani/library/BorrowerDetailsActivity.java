package com.romani.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.romani.library.RDB.Borrower;
import com.romani.library.RDB.LibraryDB;

public class BorrowerDetailsActivity extends AppCompatActivity
{
    TextView ID_B, borrowerID , name , mobile , room , book1 , borrowDate1 , borrowReturn1 , book2 , borrowDate2 , borrowReturn2;
    Borrower borrower = new Borrower();
    LibraryDB libraryDB;
    String borrowerIDDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrower_details);

        initViews();

        borrowerIDDetail = getIntent().getStringExtra("ID");
        libraryDB = LibraryDB.getInstance(this);
        borrower = libraryDB.getBorrowerDao().getBorrowerByID(borrowerIDDetail);

        setBorrowerViews();


        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (mobile.getText() != null)
                {
                    Uri uri = Uri.parse("tel:" + mobile.getText());
                    Intent i = new Intent(Intent.ACTION_DIAL , uri);
                    startActivity(i);
                }

            }
        });

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (mobile.getText() != null)
                {
                    Uri uri = Uri.parse("https://www.google.com/search?q=" + name.getText());
                    Intent i = new Intent(Intent.ACTION_VIEW , uri);
                    startActivity(i);
                }

            }
        });
    }

    public void initViews()
    {
        ID_B = findViewById(R.id.IDB_T);
        borrowerID = findViewById(R.id.IDBorrower_T);
        name = findViewById(R.id.NameBorrower_T);
        mobile = findViewById(R.id.mobileBorrower_T);
        room = findViewById(R.id.roomBorrower_T);
        book1 = findViewById(R.id.book1Borrower_T);
        borrowDate1 = findViewById(R.id.borrowDate1Borrower_T);
        borrowReturn1 = findViewById(R.id.returnDate1Borrower_T);
        book2 = findViewById(R.id.book2Borrower_T);
        borrowDate2 = findViewById(R.id.borrowDate2Borrower_T);
        borrowReturn2 = findViewById(R.id.returnDate2Borrower_T);
    }

    public void setBorrowerViews()
    {
        ID_B.setText(borrower.getID_B() + "");
        borrowerID.setText(borrower.getBorrowerID());
        name.setText(borrower.getName());
        mobile.setText(borrower.getMobile());
        room.setText(borrower.getRoom());
        book1.setText(borrower.getBook1());
        borrowDate1.setText(borrower.getBorrowDate1());
        borrowReturn1.setText(borrower.getReturnDate1());
        book2.setText(borrower.getBook2());
        borrowDate2.setText(borrower.getBorrowDate2());
        borrowReturn2.setText(borrower.getReturnDate2());
    }

    public void backToBorrowerActivity(View v)
    {
        startActivity(new Intent(BorrowerDetailsActivity.this, BorrowerActivity.class));
    }

    public void edit(View v)
    {
        Intent i = new Intent(BorrowerDetailsActivity.this, AddBorrowerActivity.class);
        i.putExtra("viewDetails" , "editBorrower");
        i.putExtra("ID" , borrowerIDDetail);
        startActivity(i);

    }

    public void perviousBorrower(View v)
    {
        long currentID = borrower.getID_B();
        if (currentID > 1)
        {
            borrower = libraryDB.getBorrowerDao().getBorrowerByID((currentID - 1)+"");
            setBorrowerViews();
            borrowerIDDetail = ID_B.getText()+"";
        }

        else
        {
            Toast.makeText(this , "This is the First Borrower" , Toast.LENGTH_LONG).show();
        }

    }

    public void nextBorrower(View v)
    {
        long currentID = borrower.getID_B();
        int borrowerSize = libraryDB.getBorrowerDao().getSizeOFBorrower();


        if (currentID < borrowerSize)
        {
            borrower = libraryDB.getBorrowerDao().getBorrowerByID((currentID + 1)+"");
            setBorrowerViews();
            borrowerIDDetail = ID_B.getText()+"";

        }

        else
        {
            Toast.makeText(this , "This is the Last Borrower" , Toast.LENGTH_LONG).show();

        }

    }
}
