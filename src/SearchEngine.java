/*
 * Name: TODO
 * PID:  TODO
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Search Engine implementation.
 * 
 * @author Arnav Khanna
 * @since  5/8/22
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                /* TODO */
                populateHelper(movieTree, cast, movie);
                populateHelper(ratingTree, cast, rating);
                populateHelper(studioTree, studios, movie);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    public static void populateHelper(BSTree<String> tree, String[] keys, String data) {
        for (String key : keys) {
            key = key.toLowerCase();
            if(!tree.findKey(key)) {
                tree.insert(key);
            }
            if(!tree.findDataList(key).contains(data)) {
                tree.insertData(key, data);
            }


        }
    }

    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {

        /* TODO */
        // process query
        String[] keys = query.toLowerCase().split(" ");
        LinkedList<String> values = new LinkedList<>();

        try {
            String key1 = keys[0];
            values.addAll(searchTree.findDataList(key1));
        } catch (IllegalArgumentException e) {

        }

        for (int i = 1; i < keys.length; i++) {
            try {
                values.retainAll(searchTree.findDataList(keys[i]));
            } catch (IllegalArgumentException e){
                values.clear();
            }
        }

        print(query, values);

        if (keys.length == 1) {
            return;
        }

        for (String key: keys) {
            LinkedList<String> values2 = new LinkedList<>();
            try {
                values2.addAll(searchTree.findDataList(key));
                values2.removeAll(values);
                values.addAll(values2);
                if (!values2.isEmpty()) {
                    print(key, values2);
                }
            } catch (IllegalArgumentException e){
                print(key, values2);
            }
        }

    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {


        BSTree movies = new BSTree<>();
        BSTree ratings = new BSTree<>();
        BSTree studios = new BSTree<>();

        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);
        String query = String.join(" ", Arrays.copyOfRange(args, 2, args.length));

        // populate search trees
        populateSearchTrees(movies, studios, ratings, fileName);
        // choose the right tree to query

        if (searchKind == 0) {
            searchMyQuery(movies, query);
        } else if (searchKind == 1) {
            searchMyQuery(studios, query);
        } else if (searchKind == 2) {
            searchMyQuery(ratings, query);
        }

    }
}
