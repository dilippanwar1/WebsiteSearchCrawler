package com.websearch.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.LinkedList;
import java.util.List;
import java.io.IOException;


public class SpidersFood {
    private Document html = null;
    private List<String> links = new LinkedList<>();
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0";

    /**
     * Method performs http request that is specified by argument url. All hypertext markup language links on the page
     * specified by argument url are then saved to list links.
     * @param url
     *           - URL that will be fetched
     * @return
     *          - Returns whether of not attempted to fetch page was successful
     */
    public boolean fetchPage(String url) {
        boolean connOk = false;
        try {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            this.html = connection.get();
            if (connection.response().statusCode() == 200) {                //Ensure correct response code
                Elements urlLinks = this.html.select("a[href]");    //Css Query to select html links
                for (Element link : urlLinks) {
                    this.links.add(link.attr("abs:href"));        //Add absolute link to list
                }
                connOk = true;                                              //Successfully fetched html
            } else {
                System.out.println("Something went wrong fetching " + url);
            }

        } catch (IOException iox) {
            System.out.println("Error with HTTP call: " + iox);
        }

        return connOk;
    }

    /**
     * Searchs through the html page for the argument word
     * @param word
     *            - Word to search the html page for
     * @return
     *            - Return true if the word was found, else false
     */

    public boolean searchForWord(String word) {
        if (this.html == null) {
            System.out.println("Incorrect usage of searchForWord method. Please call method fetchPage first.");
            return false;
        }
        String bodyText = this.html.body().text();
        return bodyText.toLowerCase().contains(word.toLowerCase());
    }

    /**
     * Returns all the absolute html links found
     * @return
     *        - Return a list of html links found
     */

    public List<String> getLinks() {
        return this.links;
    }



}
