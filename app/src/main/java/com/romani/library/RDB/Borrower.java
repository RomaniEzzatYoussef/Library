package com.romani.library.RDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "borrowers")
public class Borrower
{
    @PrimaryKey(autoGenerate = true)
    private int ID_B;

    @ColumnInfo(name = "borrowerID")
    private String borrowerID;

    @ColumnInfo(name = "borrowerName")
    private String borrowerName;

    @ColumnInfo(name = "mobile")
    private String mobile;

    @ColumnInfo(name = "room")
    private String room;

    @ColumnInfo(name = "book1")
    private String book1;

    @ColumnInfo(name = "borrowDate1")
    private String borrowDate1;

    @ColumnInfo(name = "returnDate1")
    private String returnDate1;

    @ColumnInfo(name = "book2")
    private String book2;

    @ColumnInfo(name = "borrowDate2")
    private String borrowDate2;

    @ColumnInfo(name = "returnDate2")
    private String returnDate2;

    public int getID_B() {
        return ID_B;
    }

    public void setID_B(int ID_B) {
        this.ID_B = ID_B;
    }

    public String getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(String borrowerID) {
        this.borrowerID = borrowerID;
    }

    public String getName() {
        return borrowerName;
    }

    public void setName(String name) {
        this.borrowerName = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBook1() {
        return book1;
    }

    public void setBook1(String book1) {
        this.book1 = book1;
    }

    public String getBorrowDate1() {
        return borrowDate1;
    }

    public void setBorrowDate1(String borrowDate1) {
        this.borrowDate1 = borrowDate1;
    }

    public String getReturnDate1() {
        return returnDate1;
    }

    public void setReturnDate1(String returnDate1) {
        this.returnDate1 = returnDate1;
    }

    public String getBook2() {
        return book2;
    }

    public void setBook2(String book2) {
        this.book2 = book2;
    }

    public String getBorrowDate2() {
        return borrowDate2;
    }

    public void setBorrowDate2(String borrowDate2) {
        this.borrowDate2 = borrowDate2;
    }

    public String getReturnDate2() {
        return returnDate2;
    }

    public void setReturnDate2(String returnDate2) {
        this.returnDate2 = returnDate2;
    }
}
