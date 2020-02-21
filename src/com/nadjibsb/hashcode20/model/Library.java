package com.nadjibsb.hashcode20.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Library implements Comparable<Library>{
    private int ID;
    private ArrayList<Book> books;
    private int bookNbr;
    private int scoreTot;
    private int signup;
    private int bookPerDay;

    public Library(int id, int bookNbr, int signup, int bookPerDay) {
        this.ID = id;
        this.books = new ArrayList<Book>();
        this.bookNbr = bookNbr;
        this.signup = signup;
        this.bookPerDay = bookPerDay;
        this.scoreTot = 0;
    }

    public int getBookNbr() {
        return bookNbr;
    }

    public void setBookNbr(int bookNbr) {
        this.bookNbr = bookNbr;
    }

    public int getScoreTot() {
        return scoreTot;
    }

    public void setScoreTot(int scoreTot) {
        this.scoreTot = scoreTot;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        Collections.sort(books);
        for (Book book: books){
            scoreTot += book.getScore();
        }
        this.books = books;
    }

    public int getSignup() {
        return signup;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setSignup(int signup) {
        this.signup = signup;
    }

    public int getBookPerDay() {
        return bookPerDay;
    }

    public void setBookPerDay(int bookPerDay) {
        this.bookPerDay = bookPerDay;
    }

    @Override
    public int compareTo(Library library) {
        //if (this.signup > library.getSignup()) return 1;
        //if (this.signup < library.getSignup()) return -1;

        //double moy1 = this.scoreTot/(float) this.bookNbr;
        //double moy2 = library.scoreTot/(float) library.bookNbr;
        if (this.scoreTot > library.getScoreTot()) return -1;
        if (this.scoreTot < library.getScoreTot()) return 1;
        return 0;
    }
}
