package specter.observer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ParserTYT {
    ///////
    String pageSourceLink;
    String TimeToArray = "Сегодня";
    ArrayList<News> List = new ArrayList<News>();
    String Title;

    private String NewDataRazdel(Element li)
    {
        String Time;
        Time = li.select("li").first().text().toString();
        return Time;
    }

    public ParserTYT(String path) throws IOException{
        pageSourceLink = path;

        Connection.Response response= Jsoup.connect(pageSourceLink)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(12000)
                .followRedirects(true)
                .execute();

        Document document = response.parse();

        Elements allText = document.select("ul.b-lists");

        Elements aTitle = document.select("div.m_header");
        Title = aTitle.select("h1").first().text();

        Pars1(allText);
    }

    private Elements DecrementLI1(Elements allText)
    {
        allText.select("li").first().remove();
        return allText;
    }

    public ArrayList<News> getList() {
        return this.List;
    }

    private void Pars1(Elements allText)
    {
        int i = 1;
        for(int ii = 0;ii<11;ii++)
        {
            allText = DecrementLI1(allText);
        }

        Element li = allText.select("li").first();


//        int z =0;

        String liS;
        int counter = 0; //счетчик количества "lists__li_head" т.е. нужен для сегодняшней даты
        while(li != null)
        {
            liS = li.toString();
            if(liS.indexOf("lists__li_head") != -1)
            {
                TimeToArray = NewDataRazdel(li);
                allText = DecrementLI1(allText);
                li = allText.select("li").first();
                counter++;

                //System.out.println("------"+ TimeToArray + "------------------------------------"); /////////////////////////////////////////////////////
            }
            else
            {
//                if(List.size() == 10)
 //                   z = 1;


                if(counter == 0){ TimeToArray = "Сегодня";}
                add (li);
                allText = DecrementLI1(allText);
                li = allText.select("li").first();
            }
        }


    }



    private void add (Element li)
    {
        String Text = li.getElementsByTag("a").text();
        String Time = li.select("li").last().text();
        String IzobrPatch = li.getElementsByTag("img").first().attr("src");
        String  Patch = li.getElementsByTag("a").first().attr("href");

        // увеличенные картинки
        // проверенный результат - плохо
        /*String IzobrPatch = null;
        try {
            IzobrPatch = pageparsimg(Patch);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (IzobrPatch == null)
            IzobrPatch = li.getElementsByTag("img").first().attr("src");*/

        List.add(new News (TimeToArray,Text,Time,IzobrPatch,Patch));
    }

    /*private String pageparsimg(String  Patch) throws IOException
    {
            Connection.Response response = Jsoup.connect(Patch)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .timeout(12000)
                    .followRedirects(true)
                    .execute();

        Document document = response.parse();

        Elements allText = document.select(".b-article");

        Element li = allText.select("img").first();

        String IzobrPatch = null;
        if (li != null)
        IzobrPatch = li.getElementsByTag("img").first().attr("src");

        return IzobrPatch;
    }*/
}