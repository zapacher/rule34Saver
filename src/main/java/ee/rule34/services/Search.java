package ee.rule34.services;

import java.util.ArrayList;
import java.util.List;

public class Search extends Thread {
    Thread thread = new Thread(this);
    Connection connection = new Connection();
    String searchUrl;
    String ownDirectory;

    public void startSearch() {
        thread.start();
    }

    public void run() {
        connection.setUrl(searchUrl);
        String response = connection.getResponse();
        List<String> urlList = new Parser().fromSearchResponse(response);
        List<String> directLinks = new ArrayList<>();
        for(String url : urlList) {
            Connection directImg = new Connection();
            directImg.setUrl(url);
            directLinks.add(new Parser().fromDirectResponse(directImg.getResponse()));
        }
        for(String url : directLinks) {
            Saver saver = new Saver();
            saver.saveFromLink(url, ownDirectory);
        }
    }

    public void setSearch(String searchUrl, String category) {
        this.searchUrl = searchUrl;
        this.ownDirectory = category;
    }
}
