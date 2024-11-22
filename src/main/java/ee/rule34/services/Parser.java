package ee.rule34.services;

import ee.rule34.Properties;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    Properties properties = new Properties();

    public List<String> fromSearchResponse(String response) {
        List<String> stringLinkList = new ArrayList<>();
        Document fullBody = Jsoup.parse(response);
        Element imageListDiv = fullBody.selectFirst("div.image-list");
        Elements links = imageListDiv.select("a");
        for (Element link : links) {
            stringLinkList.add(properties.getBaseUrl() + link.attr("href"));
        }
        return stringLinkList;
    }

    public String fromDirectResponse(String response) {
        if (response != null) {
            Document fullBody = Jsoup.parse(response);
            Elements metaTags = fullBody.select("meta[property=og:image][itemprop=image]");
            String url = metaTags.attr("content");
            Pattern pattern = Pattern.compile("\\?\\d+$");
            Matcher matcher = pattern.matcher(url);
            return matcher.replaceFirst("");
        } else {
            return null;
        }
    }
}