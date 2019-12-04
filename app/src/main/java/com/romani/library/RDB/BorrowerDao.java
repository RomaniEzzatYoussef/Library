package com.romani.library.RDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface BorrowerDao
{
    @Insert
    public void insert(Borrower borrower);

    @Update
    public void update(Borrower borrower);

    @Insert
    public void insert(ArrayList<Borrower> borrowers);

    @Query("SELECT * FROM borrowers")
    public List<Borrower> getAllBorrowers();

    @Query("SELECT * FROM borrowers where borrowerName like '%'||:bName||'%'")
    public List<Borrower> getBorrowerByName(String bName);

    @Query("SELECT * FROM borrowers where ID_B = :BID")
    public Borrower getBorrowerByID(String BID);

    @Query("SELECT count(ID_B) FROM borrowers")
    public int getSizeOFBorrower();

    @Delete
    public void delete(ArrayList<Borrower> borrowers);

    @Delete
    public void delete(Borrower borrower);

}
