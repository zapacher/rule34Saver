package ee.rule34;

import ee.rule34.services.Search;
import ee.rule34.services.SearchList;

import java.util.List;

public class Main {
    static List<String> searchList = new SearchList().getSeatchList();

    public static void main(String[] args) {
        while(true) {
            for (String searchThis : searchList) {
                while (Thread.activeCount() > 6) {
                    try {
                        System.out.println("sleeping for " + searchThis);
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Started thread for : " + searchThis);
                Search search = new Search();
                search.setSearch("https://rule34.xxx/index.php?page=post&s=list&tags=" + searchThis, searchThis);
                search.startSearch();
            }
        }
    }
}
