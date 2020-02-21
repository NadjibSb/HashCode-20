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

    public static void main(String[] args) throws IOException {

        ArrayList<String> files = getFolderFilesPaths("input");

        for (String filePath : files) {
            Path path = Paths.get(filePath);
            System.out.println("******************\n- Start processing => input file : " + path.getFileName().toString());
            Scanner scanner = new Scanner(path);

            /** Read & Initialize ********************************************************************************** **/
            // Read the first line (nbr of books, nbr of libraries, nbr of days)
            String[] line1 = scanner.nextLine().split(" ");
            int nbrB = Integer.parseInt(line1[0]);
            int nbrL = Integer.parseInt(line1[1]);
            int nbrD = Integer.parseInt(line1[2]);

            // Read the second line (books scores ordered by BookID)
            ArrayList<Book> books = initializeBooks(scanner);

            // Read the rest (each library is represented in two lines)
            ArrayList<Library> libraries = initializeLibraries(scanner, books);

            /** Process ******************************************************************************************** **/
            Collections.sort(libraries);

            /** Generate output ************************************************************************************ **/
            generateOutput(path, nbrD, libraries);
            System.out.println("\nend processing => output file : output/out_" + path.getFileName().toString() + "\n__________________");


        }
    }

    private static ArrayList<Book> initializeBooks(Scanner scanner) {
        String[] line2 = scanner.nextLine().split(" ");
        ArrayList<Book> books = new ArrayList<>();
        for (int i = 0; i < line2.length; i++) {
            books.add(new Book(i, Integer.parseInt(line2[i])));
        }
        System.out.println("books initializing finished");
        return books;
    }

    private static ArrayList<Library> initializeLibraries(Scanner scanner, ArrayList<Book> books) {
        ArrayList<Library> libraries = new ArrayList<>();
        int libraryID = 0;
        while (scanner.hasNextLine()) {

            //First line contains Library informations (nbr of books, signup duration, books/day)
            String[] line = scanner.nextLine().split(" ");
            Library lib = new Library(libraryID, Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));

            //second line contains books IDs
            ArrayList<Book> libBooks = new ArrayList<>();
            String[] bookLine = scanner.nextLine().split(" ");
            for (int i = 0; i < bookLine.length; i++) {
                libBooks.add(getBook(books, Integer.parseInt(bookLine[i])));
            }
            lib.setBooks(libBooks);
            libraries.add(lib);
            libraryID++;
        }
        scanner.close();
        System.out.println("Libraries initializing finished");
        return libraries;
    }

    private static void generateOutput(Path path, int nbrD, ArrayList<Library> libraries) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/out_" + path.getFileName().toString()));
        int libsToInsert = getLibrariesNbr(libraries, nbrD);

        // the first line contains the total number of libraries to insert
        int libNbr =0;
        String toWrite="";

        //each library is described in 2 lines
        for (int i = 0; i < libraries.size(); i++) {
            //first line contains (lib ID, nbr of books to scan )
            Library lib = libraries.get(i);

            //the second line containes the books to insert
            String bookL = "";
            int booksNbr=0;
            for (int x = 0; x < lib.getBooks().size(); x++) {
                Book book = lib.getBooks().get(x);
                if (!book.isScanned()){
                    book.setScanned();
                    bookL += book.getId() + " ";
                    booksNbr++;
                }
            }
            if (booksNbr>0){
                toWrite += lib.getID() + " " + booksNbr + "\n";
                toWrite += bookL+ "\n";
                libNbr++;
            }
        }
        writer.write(libNbr + "\n");
        writer.append(toWrite);
        writer.close();
    }
}
