package com.nadjibsb.hashcode20.model;

import java.util.ArrayList;
import java.util.Collections;

public class Library implements Comparable<Library> {
    private int ID;
    private ArrayList<Book> books;
    private int bookNbr;
    private int scoreTot;
    private int signup;
    private int bookPerDay;

    public Library(int id, int bookNbr, int signup, int bookPerDay) {
        this.ID = id;
        this.bookNbr = bookNbr;
        this.signup = signup;
        this.bookPerDay = bookPerDay;
        this.books = new ArrayList<Book>();
        this.scoreTot = 0;
    }

    @Override
    public int compareTo(Library library) {
        //if (this.signup > library.getSignup()) return 1;
        //if (this.signup < library.getSignup()) return -1;

        //if (this.scoreTot > library.getScoreTot()) return -1;
        //if (this.scoreTot < library.getScoreTot()) return 1;

        double moy1 = (this.scoreTot/(float) this.bookNbr) * this.bookPerDay;
        double moy2 = (library.scoreTot/(float) library.bookNbr) * library.getBookPerDay();
        double ratio = this.signup/(float) library.getSignup();
        if (ratio>=1.2) return 1;
        if (ratio<1.2 && ratio>0.8){
            if (moy1 > moy2) return -1;
            if (moy1 < moy2) return 1;
            else {
                if (ratio>1) return 1;
                if (ratio<1) return -1;
                else return 0;
            }
        }
        if (ratio<=0.8) return -1;
        //if (moy1 > moy2) return -1;
        //if (moy1 < moy2) return 1;
        return 0;
    }

    public void setBooks(ArrayList<Book> books) {
        Collections.sort(books);
        for (Book book : books) {
            scoreTot += book.getScore();
        }
        this.books = books;
    }

    public ArrayList<Book> getBooks() {
        return books;
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


}
