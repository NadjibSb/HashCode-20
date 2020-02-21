package com.nadjibsb.hashcode20.model;

public class Book implements Comparable<Book> {
    private int id;
    private int score;

    public Book(int id, int score) {
        this.id = id;
        this.score = score;
    }

    @Override
    public int compareTo(Book book) {
        if (this.score > book.getScore()) return -1;
        if (this.score < book.getScore()) return 1;
        else return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
