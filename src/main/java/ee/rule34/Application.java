package ee.rule34;

import ee.rule34.services.Search;

import java.util.List;

public class Application {
    static Properties properties = new Properties();

    public static void main(String[] args) {
        List<String> searchList = properties.getSearchList();
        while (true) {
            for (String searchThis : searchList) {
                while (Thread.activeCount() > properties.getThreads()) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Search search = new Search();
                search.setSearch(properties.getSearchUrl() + searchThis, searchThis);
                search.startSearch();
            }
        }
    }
}
