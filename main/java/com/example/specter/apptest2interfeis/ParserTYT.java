package com.example.specter.apptest2interfeis;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author SPECTER
 */
public class ParserTYT {

    String pageData;
    String pageTitle;
    String pageSourceLink;
    String pageImagePath;
    ArrayList<String> pageTags;
    Element pageContent;
    ///////
    String TimeToArray;
    ArrayList<News> NEWS;

    public ParserTYT(String path) throws IOException{
        pageSourceLink = path;
        Document document = Jsoup.connect(path).get();
        Element allText = document.select("div.col-c").first();
        allText.select("div.b-pagination").first().remove();
        Pars(allText);
    }

    private void Pars(Element allText) throws IOException
    {
        int i = 1;
        Element li = allText.select("li").first();
        String liS;
        int counter = 0; //счетчик количества "lists__li_head" т.е. нужен для сегодняшней даты
        while(li != null)
        {
            liS = li.toString();
            if(liS.indexOf("lists__li_head") != -1)
            {
                TimeToArray = NewDataRazdel(li);
                allText = DecrementLI(allText);
                li = allText.select("li").first();
                counter++;

                System.out.println("------"+ TimeToArray + "------------------------------------"); /////////////////////////////////////////////////////
            }
            else
            {
                if(counter == 0){ TimeToArray = "Сегодня";}
                add (li);
                allText = DecrementLI(allText);
                li = allText.select("li").first();
            }
        }
        img();
        /*do {
            i++;
        } while (li.toString().equals(allText.select("li").last().toString()));
        System.out.println("--------------------------------------------"+i);*/
   /*
    allText.select("li").
    allText.getElementsByTag("li").first().remove();
    System.out.println("--------------------------------------------");
            System.out.println(allText.toString());*/

    }

    private void img()
    {
        //http://javatalks.ru/topics/37027////////////////////////////////////////////////////////////////////
        URL url;
        URLConnection conn;
        try
        {

            url = new URL("http://img.tyt.by/80x48c/n/0f/2/dollars_euro.jpg"); //Формирование url-адреса файла
            conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);

            File file = File.createTempFile("file",".jpg");
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            FileOutputStream fos = new FileOutputStream(file);

            int ch;
            while ((ch = bis.read())!=-1)
            {
                fos.write(ch);
            }
            bis.close();
            fos.flush();
            fos.close();

        }
        catch (MalformedURLException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

    private Element DecrementLI(Element allText)
    {
        allText.getElementsByTag("li").first().remove();
        return allText;
    }

    private String NewDataRazdel(Element li)
    {
        String Time;
        Time = li.select("li").first().text().toString();
        return Time;
    }

    private void add (Element li)
    {
        String Text = li.getElementsByTag("a").text();
        String Time = li.select("li").last().text();
        String IzobrPatch = li.getElementsByTag("img").first().attr("src");
        List<News> List = new ArrayList<News>();
        News item0 = new News();
        item0.Date= TimeToArray;
        item0.Text = Text;
        item0.Time = Time;
        item0.IzobrPatch = IzobrPatch;
        List.add(item0);

        System.out.println(Text); ///////////////////////////////////////////////////////////////////////
        System.out.println(Time); ///////////////////////////////////////////////////////////////////////
        System.out.println(IzobrPatch); /////////////////////////////////////////////////////////////////
    }



}
