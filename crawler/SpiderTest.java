package com.websearch.crawler;

public class SpiderTest {
    public static void main(String[] args) {
        int totalPages = 20;
        Spider spider = new Spider(totalPages);
        String url = "http://193.1.101.7/4082/";
        spider.search(url,"pony");
    }
}
