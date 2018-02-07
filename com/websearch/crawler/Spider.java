package com.websearch.crawler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Spider {
    private int maxPagesToSearch;
    private List<String> pagesToVisit = new LinkedList<>();
    private Set<String> pagesVisited = new HashSet<>();


    /**
     * A class to crawl through webpages to search for a word
     *
     * @param maxPagesToSearch
     *                          - User specifies limit of pages to search through
     */
    public Spider(int maxPagesToSearch) {
        this.maxPagesToSearch = maxPagesToSearch;
    }


    /**
     * Main method to start searching. Feed it a location (url) to start the search for a word.
     * The heavy lifting (http requests and responses/word searchs and link parses) is done in class SpidersFood
     * @param url
     *              - Starting url address for crawler
     * @param word
     *              - Word that you wish to search for
     */

    public void search(String url, String word) {
        boolean success = false;
        String currentUrl = url;
        while (this.pagesVisited.size() < this.maxPagesToSearch && !success) {
            SpidersFood spidersFood = new SpidersFood();
            if (this.pagesToVisit.isEmpty()) {
                this.pagesToVisit.add(url);
            } else {
                currentUrl = this.nextURL();
            }
            spidersFood.fetchPage(currentUrl);
            success = spidersFood.searchForWord(word);
            this.pagesToVisit.addAll(spidersFood.getLinks());
        }
        if (success) {
            System.out.println("Success at finding " + word + " at Url " + currentUrl);
        } else {
            System.out.println("Failed to find \"" + word +"\"");
        }
    }

    /**
     * Acquires the next URL
     * @return
     *          - Returns a url string
     */
    private String nextURL() {
        String nextUrl;
        do {
            nextUrl = this.pagesToVisit.remove(0);
        } while (this.pagesVisited.contains(nextUrl));      //Check to see if we have visited the URL
        this.pagesVisited.add(nextUrl);
        return nextUrl;
    }


}
