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

import com.romani.library.AddBorrowerActivity;
import com.romani.library.BorrowerDetailsActivity;
import com.romani.library.R;

import java.util.ArrayList;

public class BorrowerAdapter extends RecyclerView.Adapter
{

    ArrayList<Borrower> borrowers;
    Context context;
    LibraryDB libraryDB;
    public BorrowerAdapter(Context context, ArrayList<Borrower> borrowers)
    {
        this.context = context;
        this.borrowers = borrowers;

        libraryDB = LibraryDB.getInstance(context);
    }

    @NonNull
    @Override
    public BorrowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View userView = LayoutInflater.from(parent.getContext()).inflate(R.layout.borrower_items, parent, false);
        BorrowerViewHolder borrowerViewHolder = new BorrowerViewHolder(userView);
        return borrowerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position)
    {
        final BorrowerViewHolder borrowerViewHolder = (BorrowerViewHolder) holder;
        borrowerViewHolder.ID_TXT.setText(borrowers.get(position).getID_B() + "");
        borrowerViewHolder.BID.setText(borrowers.get(position).getBorrowerID());
        borrowerViewHolder.name.setText(borrowers.get(position).getName());
        borrowerViewHolder.bookName.setText(borrowers.get(position).getBook1());
        borrowerViewHolder.borroDate.setText(borrowers.get(position).getBorrowDate1());

        borrowerViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context , BorrowerDetailsActivity.class);
                intent.putExtra("ID" , borrowers.get(position).getID_B()+"");

                context.startActivity(intent);
            }
        });

        borrowerViewHolder.bOptionsBTN.setOnClickListener(new View.OnClickListener() {
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
                                Intent i = new Intent(context , AddBorrowerActivity.class);
                                i.putExtra("ID" , borrowerViewHolder.ID_TXT.getText().toString());
                                i.putExtra("viewDetails" , "editBorrower");
                                context.startActivity(i);
                                return true;
                            case R.id.item_details:
                                Intent intent = new Intent(context , BorrowerDetailsActivity.class);
                                intent.putExtra("ID" , borrowerViewHolder.ID_TXT.getText().toString());
                                context.startActivity(intent);
                                return true;

                            case R.id.item_delete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                                builder.setTitle("Confirm");
                                builder.setMessage("Are you sure you want to delete Borrwer '" + borrowerViewHolder.name.getText().toString() +"' ?");

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
        return borrowers.size();
    }

    class BorrowerViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout relativeLayout;
        Button bOptionsBTN;
        TextView ID_TXT , BID , name ,bookName , borroDate;

        public BorrowerViewHolder(@NonNull View itemView)
        {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.bor_layout);
            bOptionsBTN = itemView.findViewById(R.id.brow_options_BTN);
            ID_TXT = itemView.findViewById(R.id.id_TXTView);
            BID = itemView.findViewById(R.id.b_id_TXTView);
            name = itemView.findViewById(R.id.b_name_text_view);
            bookName = itemView.findViewById(R.id.book_name_text_view);
            borroDate = itemView.findViewById(R.id.book_date_text_view);
        }
    }
}
