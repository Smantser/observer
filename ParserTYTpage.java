package specter.observer;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParserTYTpage {

    String pageSourceLink;
    String text;

    public ParserTYTpage(String path) throws IOException {
        pageSourceLink = path;

        Connection.Response response= Jsoup.connect(pageSourceLink)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(12000)
                .followRedirects(true)
                .execute();

        Document document = response.parse();

        Elements allText = document.select(".b-article");

        text = allText.toString();
    }
}
