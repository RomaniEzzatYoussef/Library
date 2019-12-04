package com.romani.library;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void showMenu(View v)
    {
        PopupMenu popup = new PopupMenu(this , v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.books_item:
                        Intent i = new Intent(MainActivity.this, BooksActivity.class);
                        startActivity(i);
                        return true;

                    case R.id.borrower_item:
                        startActivity(new Intent(MainActivity.this, BorrowerActivity.class));
                        return true;

                    default:
                        return false;
                }
            }
        });

        popup.show();
    }

    public void books(View v)
    {
        Intent i = new Intent(MainActivity.this, BooksActivity.class);
        startActivity(i);
    }

    public void borrowers(View v)
    {
        startActivity(new Intent(MainActivity.this, BorrowerActivity.class));
    }
}