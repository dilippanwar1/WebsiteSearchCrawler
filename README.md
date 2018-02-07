# WebsiteSearchCrawler
### Description
A class to search through webpages for a word.
User specifies starting webpage to begin search, word to search for and also limit the number of webpages to search through.

### Usage
``` java
public static void main(String[] args) {
        int totalLimit = 20;
        Spider spider = new Spider(totalLimit);
        String url = "http://www.bbc.com/";
        String wordToSearchFor = "queen";
        spider.search(url,wordToSearchFor);
}
