package ee.rule34;

import ee.rule34.services.Search;

public class Application {
    static Properties properties = new Properties();

    public static void main(String[] args) {
        while (true) {
            for (String searchThis : properties.getSearchList()) {
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
            System.out.println("SearchList ends, going for next round");
        }
    }
}
