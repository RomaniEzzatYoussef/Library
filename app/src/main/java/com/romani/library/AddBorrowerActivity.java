package com.romani.library;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.romani.library.RDB.Borrower;
import com.romani.library.RDB.LibraryDB;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddBorrowerActivity extends AppCompatActivity {

    RelativeLayout nevigationLayout;
    TextView addNewBorrower_txtView;
    EditText borrowerID, borrowerName, room, mobile, book1, book2 , bookDate1 , bookDate2 , returnDate1 , returnDate2;
    Borrower borrower = new Borrower();
    LibraryDB libraryDB;
    String borrowerIDDetail;
    String viewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_borrower);

        libraryDB = LibraryDB.getInstance(this);

        initViews();

        borrowerIDDetail = getIntent().getStringExtra("ID");

        viewDetails = getIntent().getStringExtra("viewDetails");


        if (viewDetails.equals("editBorrower"))
        {
            borrower = libraryDB.getBorrowerDao().getBorrowerByID(borrowerIDDetail);
            nevigationLayout.setVisibility(View.VISIBLE);
            addNewBorrower_txtView.setText("Edit Borrower");
            setBorrowerViews();
        }
    }

    public void initViews() {

        nevigationLayout = findViewById(R.id.nevigation_relativeLayoutBorrower);
        addNewBorrower_txtView = findViewById(R.id.addNewBorrower_txtView);
        borrowerID = findViewById(R.id.borrowerID_editText);
        borrowerName = findViewById(R.id.borrowerName_editText);
        room = findViewById(R.id.room_editText);
        mobile = findViewById(R.id.mobile_editText);
        book1 = findViewById(R.id.bookName1_editText);
        book2 = findViewById(R.id.bookName2_editText);
        bookDate1 = findViewById(R.id.bookDate1_EditTXT);
        bookDate2 = findViewById(R.id.bookDate2_EditTXT);
        returnDate1 = findViewById(R.id.returnDate1_EditTXT);
        returnDate2 = findViewById(R.id.returnDate2_EditTXT);

    }


    public void setBorrowerObj()
    {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        borrower.setBorrowerID(borrowerID.getText().toString());
        borrower.setBorrowerName(borrowerName.getText().toString());
        borrower.setRoom(room.getText().toString());
        borrower.setMobile(mobile.getText().toString());
        borrower.setBook1(book1.getText().toString());
        borrower.setBook2(book2.getText().toString());
        borrower.setBorrowDate1(bookDate1.getText().toString());
        borrower.setBorrowDate2(bookDate2.getText().toString());
        borrower.setReturnDate1(returnDate1.getText().toString());
        borrower.setReturnDate2(returnDate2.getText().toString());
    }


    public void setBorrowerViews()
    {
        borrowerID.setText(borrower.getBorrowerID());
        borrowerName.setText(borrower.getName());
        room.setText(borrower.getRoom());
        mobile.setText(borrower.getMobile());
        book1.setText(borrower.getBook1());
        book2.setText(borrower.getBook2());
        bookDate1.setText(borrower.getBorrowDate1());
        bookDate2.setText(borrower.getBorrowDate2());
        returnDate1.setText(borrower.getReturnDate1());
        returnDate2.setText(borrower.getReturnDate2());
    }


    public void backToBorrowerActivity(View v)
    {
        startActivity(new Intent(AddBorrowerActivity.this, BorrowerActivity.class));
    }


    public void saveNewBorrower(View v) {

        if (addNewBorrower_txtView.getText().equals("Add New Borrower"))
        {
            if (borrowerName.getText().toString().length() > 1)
            {
                setBorrowerObj();
                libraryDB.getBorrowerDao().insert(borrower);
                startActivity(new Intent(AddBorrowerActivity.this, BorrowerActivity.class));
                Toast.makeText(this, "Borrower '" + borrower.getName() + "' Added", Toast.LENGTH_LONG).show();
            }

            else
            {
                Toast.makeText(this, "You Should Enter Borrower Name", Toast.LENGTH_LONG).show();
            }

        } else if (addNewBorrower_txtView.getText().equals("Edit Borrower")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure you want to update Borrower '" + borrower.getName() + "' ?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog

                    setBorrowerObj();
                    libraryDB.getBorrowerDao().update(borrower);
                    Toast.makeText(AddBorrowerActivity.this, "Borrower '" + borrower.getName() + "' Updated", Toast.LENGTH_LONG).show();

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

    public void perviousBorrower(View v)
    {
        long currentID = borrower.getID_B();
        if (currentID > 1)
        {
            borrower = libraryDB.getBorrowerDao().getBorrowerByID((currentID - 1)+"");
            setBorrowerViews();
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


        }

        else
        {
            Toast.makeText(this , "This is the Last Borrower" , Toast.LENGTH_LONG).show();

        }

    }

}
