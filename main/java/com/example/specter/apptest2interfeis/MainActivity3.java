package com.example.specter.apptest2interfeis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    /*ArrayList<Product> products = new ArrayList<Product>();
    ArrayList<Product> products2 = new ArrayList<Product>();
    BoxAdapter boxAdapter;*/

    ListView lvMain;
    ListView lvMain2;
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle("Новости");

        /*// создаем адаптер
        fillData();
        boxAdapter = new BoxAdapter(this, products);
        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(boxAdapter);*/
        /*fillData2();
        boxAdapter = new BoxAdapter(this, products2);
        ListView lvMain2 = (ListView) findViewById(R.id.lvMain2);
        lvMain2.setAdapter(boxAdapter);*/
        SetInfoToListView();

    }

    private  List<News20>  Zaglyshka(int nom)
    {
        List<News20> List = new ArrayList<News20>();

        if (nom == 1) {
            News20 item1 = new News20();
            item1.Name = "Русому — Орден Почета, Дворнику — Орден Отечества. Лукашенко вручил награды за плодотворную работу";
            item1.Comboprop = "TYT.by 27 июня 2016 19:23";
            item1.Patch = "http://img.tyt.by/80x48c/n/prezident/07/7/lukashenko_vstrecha_04082015_4.jpg";
            List.add(item1);

            News20 item2 = new News20();
            item2.Name = "Телевизоры и стиралки накопились, холодильники рассосались: что лежит на складах производителей";
            item2.Comboprop = "TYT.by 27 июня 2016 16:59";
            item2.Patch = "http://img.tyt.by/80x48c/n/0e/d/televizory_v_magazine.jpg";
            List.add(item2);

            News20 item3 = new News20();
            item3.Name = "EPAM Systems купил китайского разработчика программного обеспечения";
            item3.Comboprop = "TYT.by 27 июня 2016 15:48";
            item3.Patch = "http://img.tyt.by/80x48c/n/brushko/10/0/epam_02102015_tutby_brush_phsl_img_01.jpg";
            List.add(item3);

            News20 item4 = new News20();
            item4.Name = "\"Закон по полкам\": расчеты с нерезидентами, учет доходов и деноминации зарплаты";
            item4.Comboprop = "TYT.by 27 июня 2016 15:09";
            item4.Patch = "http://img.tyt.by/80x48c/n/09/d/nachislyat-nalogi.jpg";
            List.add(item4);

            News20 item5 = new News20();
            item5.Name = "БКК подписала анонсированный контракт с Индией, цена заметно ниже прошлогодней";
            item5.Comboprop = "TYT.by 27 июня 2016 14:17";
            item5.Patch = "http://img.tyt.by/80x48c/n/0b/7/bpc_kudryavets_2014_657.jpg";
            List.add(item5);
        }
        else if (nom == 2)
        {
            News20 item1 = new News20();
            item1.Name = "Шотландия: будет ли второй референдум о независимости?";
            item1.Comboprop = "TYT.by 27 июня 2016 23:26";
            item1.Patch = "http://img.tyt.by/80x48c/n/reuters/00/d/nikola_sterdzhen_23062016.jpg";
            List.add(item1);

            News20 item2 = new News20();
            item2.Name = "Уходя - уходи. ЕС ожидает Лондона формального запуска Brexit";
            item2.Comboprop = "TYT.by 27 июня 2016 23:08";
            item2.Patch = "http://img.tyt.by/80x48c/n/reuters/00/4/rtx2iiof_merkel_renci_olland.jpg";
            List.add(item2);

            News20 item3 = new News20();
            item3.Name = "Во Франции открыто дело о непредумышленном убийстве в связи с крушением самолета EgyptAir";
            item3.Comboprop = "TYT.by 27 июня 2016 22:11";
            item3.Patch = "http://img.tyt.by/80x48c/n/reuters/06/10/rtsezuc_egyptair.jpg";
            List.add(item3);

            News20 item4 = new News20();
            item4.Name = "Сенат Италии отклонил резолюцию об отмене антироссийских санкций";
            item4.Comboprop = "TYT.by 27 июня 2016 21:17";
            item4.Patch = "http://img.tyt.by/80x48c/n/fotofact/0d/6/14fotootdykh_italiya-3okt2014.jpg";
            List.add(item4);

            News20 item5 = new News20();
            item5.Name = "Кэмерон заявил, что не намерен подавать заявление о выходе из ЕС";
            item5.Comboprop = "TYT.by 27 июня 2016 19:47";
            item5.Patch = "http://img.tyt.by/80x48c/n/reuters/07/3/kemeron_24062016.jpg";
            List.add(item5);
        }

        return List;
    }


    private  void  SetInfoToListView()
    {
        List<News20> List = Zaglyshka(1);

        lvMain = (ListView) findViewById(R.id.lvMain);
        Add(lvMain,List,5,"Экономика и бизнес");


        List<News20> List2 = Zaglyshka(2);

        lvMain2 = (ListView) findViewById(R.id.lvMain2);
        Add(lvMain2,List2,5,"Мировые новости");
    }


    private  void  Add(ListView lvMain,List<News20> List,int CountNews,String header)
    {
        try {
            new MyAsyncTask(lvMain,this,CountNews,List,header).execute(new URL(List.get(0).Patch),new URL(List.get(1).Patch),new URL(List.get(2).Patch),new URL(List.get(3).Patch),new URL(List.get(4).Patch));
            //new MyAsyncTask(3, lvMain,this).execute(new URL(urlImage),new URL(urlImage),new URL(urlImage),new URL(urlImage),new URL(urlImage));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // Создаем свой класс от AsyncTask
    private class MyAsyncTask extends AsyncTask<URL, Void, ArrayList<Bitmap>> {

        ListView lvMainA;
        ArrayList<Product> products = new ArrayList<Product>();
        BoxAdapter boxAdapter;
        Context t;
        int count;
        List<News20> List;
        String Header;

        public MyAsyncTask(ListView lvMain, Context tt,int cnt,List<News20> ListP,String Head) {
            lvMainA = lvMain;
            t = tt;
            count = cnt;
            List = ListP;
            Header = Head;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected ArrayList<Bitmap> doInBackground(URL... urls) {

            ArrayList<Bitmap> products = new ArrayList<Bitmap>();

            for (int i = 0; i < count; i++) {
                URL networkUrl = urls[i];
                try {
                    Bitmap networkBitmap = null;
                    networkBitmap = BitmapFactory.decodeStream(networkUrl
                            .openConnection().getInputStream());
                    products.add(networkBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return products;
        }

        protected void onPostExecute(ArrayList<Bitmap> result) {
            for(int i = 0; i < count; i++)
            {
                products.add(new Product(List.get(i).Name, List.get(i).Comboprop,result.get(i)));
            }
            boxAdapter = new BoxAdapter(t, products);
            View header1 = createHeader(Header);
            lvMainA.addHeaderView(header1);
            lvMainA.setAdapter(boxAdapter);
        }
    }


    // создание Header
    View createHeader(String text) {
        View v = getLayoutInflater().inflate(R.layout.header, null);
        ((TextView)v.findViewById(R.id.tvText)).setText(text);
        return v;
    }

    /*class MyTask extends AsyncTask<Void, Void, Void> {

        //String title;//Тут храним значение заголовка сайта
        ParserTYT parser;
        protected Void doInBackground(Void... params) {

            try {
                parser = new ParserTYT("http://news.tut.by/economics/2");
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            /*UrlP = parser.getPageSourceLink();
            textView = (TextView)findViewById(R.id.Title1);
            textView.setText(parser.getPageTitle());
            Tags = (TextView)findViewById(R.id.textView);
            Tags.setText(parser.getPageTags().toString());*/
      /*  }
    }*/

}
