package com.nadjibsb.hashcode20;

import com.nadjibsb.hashcode20.model.Book;
import com.nadjibsb.hashcode20.model.Library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static com.nadjibsb.hashcode20.Utility.*;

public class Main {
    ArrayList<Book> books;

    public static void main(String[] args) throws IOException {

        ArrayList<String> files = getFolderFilesPaths("input");

        for (String filePath : files) {
            Path path = Paths.get(filePath);
            System.out.println("******************\n- Start processing => input file : " + path.getFileName().toString());
            Scanner scanner = new Scanner(path);

            // Read the first line (nbr of books, nbr of libraries, nbr of days)
            String[] line1 = scanner.nextLine().split(" ");
            int nbrB = Integer.parseInt(line1[0]);
            int nbrL = Integer.parseInt(line1[1]);
            int nbrD = Integer.parseInt(line1[2]);

            // Read the second line (books scores ordered by BookID)
            String[] line2 = scanner.nextLine().split(" ");
            ArrayList<Book> books = new ArrayList<Book>();
            for (int i = 0; i < line2.length; i++) {
                books.add(new Book(i, Integer.parseInt(line2[i])));
            }
            System.out.println("books initializing finished");

            // Read the rest (each library is represented in two lines)
            ArrayList<Library> libraries = new ArrayList<Library>();
            int j = 0;
            while (scanner.hasNextLine()) {
                //process each line
                String[] line = scanner.nextLine().split(" ");
                Library l = new Library(j, Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
                //System.out.println(j);
                ArrayList<Book> bookL = new ArrayList<Book>();
                String[] bookLine = scanner.nextLine().split(" ");
                for (int i = 0; i < bookLine.length; i++) {
                    bookL.add(getBook(books, Integer.parseInt(bookLine[i])));
                }
                l.setBooks(bookL);
                libraries.add(l);
                j++;
            }
            scanner.close();

            System.out.println("Libraries initializing finished");
            Collections.sort(libraries);
            System.out.println(libraries.get(0).getScoreTot() + " =>" + libraries.get(1).getScoreTot());

            BufferedWriter writer = new BufferedWriter(new FileWriter("output/out_" + path.getFileName().toString()));
            int lNbr = getLibrariesNbr(libraries, nbrD);
            writer.write(lNbr + "\n");
            for (int i = 0; i < lNbr; i++) {
                Library lib = libraries.get(i);
                writer.append(lib.getID() + " " + lib.getBookNbr() + "\n");

                String s = "";
                for (int x = 0; x < lib.getBooks().size(); x++) {
                    s += lib.getBooks().get(x).getId() + " ";
                }

                writer.append(s + "\n");
            }

            System.out.println("\nend processing => output file : " + path.getFileName().toString() + "\n__________________");

            writer.close();
        }
    }
}
