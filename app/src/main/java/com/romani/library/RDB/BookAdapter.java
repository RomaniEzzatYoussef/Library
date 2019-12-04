package com.romani.library.RDB;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.romani.library.AddBookActivity;
import com.romani.library.BookDetailsActivity;
import com.romani.library.R;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter
{

    ArrayList<Book> books;
    Context context;
    LibraryDB libraryDB;
    public BookAdapter(Context context, ArrayList<Book> books)
    {
        this.context = context;
        this.books = books;

        libraryDB = LibraryDB.getInstance(context);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View userView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_items, parent, false);
        BookViewHolder bookViewHolder = new BookViewHolder(userView);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position)
    {
        final BookViewHolder bookViewHolder = (BookViewHolder) holder;
        bookViewHolder.bookID.setText(books.get(position).getIDBook() + "");
        bookViewHolder.bookName.setText(books.get(position).getBookName());
        bookViewHolder.bookAuthor.setText(books.get(position).getBookAuthor());
        bookViewHolder.bookShelf.setText(books.get(position).getBookShelfNumber());
        bookViewHolder.bookNumber.setText(books.get(position).getBookNumber());

        bookViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context , BookDetailsActivity.class);
                intent.putExtra("ID" , books.get(position).getIDBook()+"");

                context.startActivity(intent);
            }
        });

        bookViewHolder.bookOptionsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                PopupMenu popup = new PopupMenu(context, v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_options, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        switch (item.getItemId()) {
                            case R.id.item_edit:
                                Intent i = new Intent(context , AddBookActivity.class);
                                i.putExtra("ID" , bookViewHolder.bookID.getText().toString());
                                i.putExtra("viewDetails" , "editBook");
                                context.startActivity(i);
                                return true;
                            case R.id.item_details:
                                Intent intent = new Intent(context , BookDetailsActivity.class);
                                intent.putExtra("ID" , bookViewHolder.bookID.getText().toString());
                                context.startActivity(intent);
                                return true;

                            case R.id.item_delete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                                builder.setTitle("Confirm");
                                builder.setMessage("Are you sure you want to delete book '" + bookViewHolder.bookName.getText().toString() +"' ?");

                                builder.setPositiveButton("YES", new DialogInterface.OnClickListener()
                                {

                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        // Do nothing but close the dialog
/*
                                        libraryDB.getBookDao().delete(borrowers.get(position));
                                        borrowers.remove(position);
                                        notifyDataSetChanged();

                                        Toast.makeText(context , "Book " + bookViewHolder.bookName.getText().toString() + " Deleted" , Toast.LENGTH_LONG).show();
*/
                                    }
                                });

                                builder.setNegativeButton("NO", new DialogInterface.OnClickListener()
                                {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {

                                        // Do nothing
                                        dialog.dismiss();
                                    }
                                });

                                AlertDialog alert = builder.create();
                                alert.show();

                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();




            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout relativeLayout;
        Button bookOptionsBTN;
        TextView bookID , bookName , bookAuthor ,bookShelf , bookNumber;

        public BookViewHolder(@NonNull View itemView)
        {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.item_layout);
            bookOptionsBTN = itemView.findViewById(R.id.book_options_BTN);
            bookID = itemView.findViewById(R.id.book_id_TXTView);
            bookName = itemView.findViewById(R.id.book_name_TXTView);
            bookAuthor = itemView.findViewById(R.id.author_text_view);
            bookShelf = itemView.findViewById(R.id.shelf_num_text_view);
            bookNumber = itemView.findViewById(R.id.book_num_text_view);
        }
    }
}
