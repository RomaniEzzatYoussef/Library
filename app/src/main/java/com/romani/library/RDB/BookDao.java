package com.romani.library.RDB;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface BookDao
{
    @Insert
    public void insert(Book book);

    @Update
    public void update(Book book);

    @Insert
    public void insert(ArrayList<Book> books);

    @Query("SELECT * FROM books")
    public List<Book> getAllBooks();

    @Query("SELECT * FROM books where bookName like '%'||:BookName||'%'")
    public List<Book> getBooksByName(String BookName);

    @Query("SELECT * FROM books where bookAuthor like '%'||:author||'%'")
    public List<Book> getBooksByAuthor(String author);

    @Query("SELECT * FROM books where IDBook = :BID")
    public Book getBookByID(String BID);

    @Query("SELECT count(IDBook) FROM books")
    public int getSizeOFBook();

    @Delete
    public void delete(ArrayList<Book> books);

    @Delete
    public void delete(Book book);

}
