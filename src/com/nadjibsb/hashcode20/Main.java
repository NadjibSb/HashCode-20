package com.nadjibsb.hashcode20;

import com.nadjibsb.hashcode20.model.Book;
import com.nadjibsb.hashcode20.model.Library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    ArrayList<Book> books;

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("f.txt");
        Scanner scanner = new Scanner(path);

        String[] line1 = scanner.nextLine().split(" ");
        int nbrB = Integer.parseInt(line1[0]);
        int nbrL = Integer.parseInt(line1[1]);
        int nbrD = Integer.parseInt(line1[2]);

        String[] line2 = scanner.nextLine().split(" ");

        ArrayList<Book> books = new ArrayList<Book>();
        for (int i=0;i<line2.length;i++){
            books.add(new Book(i,Integer.parseInt(line2[i])));
        }
        System.out.println("books finished");

        ArrayList<Library> libraries = new ArrayList<Library>();
        int j=0;
        while(scanner.hasNextLine()){
            //process each line
            String[] line = scanner.nextLine().split(" ");
            Library l = new Library(j,Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]));
            //System.out.println(j);
            ArrayList<Book> bookL = new ArrayList<Book>();
            String[] bookLine = scanner.nextLine().split(" ");
            for (int i=0;i<bookLine.length;i++){
                bookL.add(getBook(books, Integer.parseInt(bookLine[i])));
            }
            l.setBooks(bookL);
            libraries.add(l);
            j++;
            //System.out.println(bookL.get(0).getScore()+ " => " + bookL.get(1).getScore());
            //System.out.println("=>"+j);
        }
        scanner.close();

        System.out.println("read finished");
        Collections.sort(libraries);
        System.out.println(libraries.get(0).getScoreTot()+" =>"+libraries.get(1).getScoreTot());

        //*********************************************************************
//        for (Book b: books){
//            System.out.println(" "+b.getScore());
//        }

        //*********************************************************************

        BufferedWriter writer = new BufferedWriter(new FileWriter("outF.txt"));
        int lNbr= getLibrariesNbr(libraries,nbrD);
        writer.write(lNbr+"\n");
        for (int i = 0; i<lNbr;i++) {
            Library lib = libraries.get(i);
            writer.append(lib.getID() + " " + lib.getBookNbr()+"\n");

            String s = "";
            for (int x=0; x<lib.getBooks().size(); x++){
                s+= lib.getBooks().get(x).getId() + " ";
            }

            writer.append(s+"\n");
        }

        writer.close();


    }

    public static Book getBook(ArrayList<Book> books , int id){
        for (Book b: books){
            if (b.getId()==id) return b;
        }
        return null;
    }

    public static int getLibrariesNbr(ArrayList<Library> libraries , int max){
        int d = 0;
        int i=0;
        for (Library l: libraries){
            i++;
            d += l.getSignup();
            if (d>max){
                d -= l.getSignup();
                i--;
                break;
            }
        }
        return i;
    }

}
