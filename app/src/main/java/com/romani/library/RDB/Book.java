package com.romani.library.RDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "books")
public class Book
{
    @PrimaryKey (autoGenerate = true)
    private long IDBook;

    @ColumnInfo(name = "bookID")
    private String bookID;

    @ColumnInfo(name = "bookName")
    private String bookName;

    @ColumnInfo(name = "bookAuthor")
    private String bookAuthor;

    @ColumnInfo(name = "bookType")
    private String bookType;

    @ColumnInfo(name = "bookShelfNumber")
    private String bookShelfNumber;

    @ColumnInfo(name = "bookNumber")
    private String bookNumber;

    @ColumnInfo(name = "numberOfCopies")
    private String numberOfCopies;

    @ColumnInfo(name = "bookInventory")
    private String bookInventory;

    public String getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBookShelfNumber() {
        return bookShelfNumber;
    }

    public void setBookShelfNumber(String bookShelfNumber) {
        this.bookShelfNumber = bookShelfNumber;
    }

    public long getIDBook() {
        return IDBook;
    }

    public void setIDBook(long IDBook) {
        this.IDBook = IDBook;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(String numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public String getBookInventory() {
        return bookInventory;
    }

    public void setBookInventory(String bookInventory) {
        this.bookInventory = bookInventory;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
}
