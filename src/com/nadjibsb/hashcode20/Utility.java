package com.nadjibsb.hashcode20;

import com.nadjibsb.hashcode20.model.Book;
import com.nadjibsb.hashcode20.model.Library;

import java.io.File;
import java.util.ArrayList;

public class Utility {

    public static ArrayList<String> getFolderFilesPaths(final String name) {
        final File folder = new File(name);
        if (folder.exists()){
            ArrayList<String> paths = new ArrayList<String>();
            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isDirectory()) {
                    paths.addAll(getFolderFilesPaths(fileEntry.getPath()));
                } else {
                    paths.add(fileEntry.getPath());
                }
            }
            return paths;
        }
        return null;
    }

    public static Book getBook(ArrayList<Book> books, int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    public static int getLibrariesNbr(ArrayList<Library> libraries, int maxD) {
        int d = 0, nbr = 0;
        for (Library l : libraries) {
            nbr++;
            d += l.getSignup();
            if (d > maxD) {
                nbr--;
                d -= l.getSignup();
                break;
            }
        }
        return nbr;
    }

}
