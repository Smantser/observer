package com.example.specter.apptest2interfeis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import android.widget.HeaderViewListAdapter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    final String[] catNamesArray = new String[] { "Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Томасина", "Бобик", "Кристина", "Пушок",
            "Дымка", "Кузя", "Китти", "Барбос", "Масяня", "Симба" };

    final String[] catNamesArray2 = new String[] { "Zaza", "vvvvv" };

    private ArrayAdapter<String> mAdapter;

    View header1;

    View footer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ////////////////////////////////////////////////////////////////////////////////////////////
        // находим список
        ListView List1 = (ListView) findViewById(R.id.listView);
        // создаем адаптер
        mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, catNamesArray);
        // присваиваем адаптер списку
        //List1.setAdapter(mAdapter);
        header1 = createHeader("Спорт");
        footer1 = createFooter();
        List1.addHeaderView(header1);
        List1.addFooterView(footer1);
        List1.setAdapter(mAdapter);
        ////////////////////////////////////////////////////////////////////////////////////////////

        // находим список
        ListView List2 = (ListView) findViewById(R.id.listView2);
        // создаем адаптер
        mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, catNamesArray);
        // присваиваем адаптер списку
        List2.setAdapter(mAdapter);


        // находим список
        ListView List3 = (ListView) findViewById(R.id.listView3);
        // создаем адаптер
        mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, catNamesArray);
        // присваиваем адаптер списку
        List3.setAdapter(mAdapter);


        // находим список
        ListView List4 = (ListView) findViewById(R.id.listView4);
        // создаем адаптер
        mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, catNamesArray);
        // присваиваем адаптер списку
        List4.setAdapter(mAdapter);


        // находим список
        ListView List5 = (ListView) findViewById(R.id.listView5);
        // создаем адаптер
        mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, catNamesArray);
        // присваиваем адаптер списку
        List5.setAdapter(mAdapter);
    }

    // создание Header
    View createHeader(String text) {
        View v = getLayoutInflater().inflate(R.layout.header, null);
        ((TextView)v.findViewById(R.id.tvText)).setText(text);
        return v;
    }


    // создание Footer
    View createFooter() {
        View v = getLayoutInflater().inflate(R.layout.footer, null);
        //((TextView)v.findViewById(R.id.tvText)).setText(text);
        return v;
    }


    public void onClickAddList(View v) {
        ListView List1 = (ListView) findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, catNamesArray2);
        List1.setAdapter(mAdapter);

    }





    ImageView logoImage;


    public void onClickZ(View v) {

        logoImage = (ImageView) findViewById(R.id.ivFromSite);

        // Загружаем картинку из интернета
        String urlImage = "http://developer.alexanderklimov.ru/android/images/android_cat.jpg";
        URL myURL;

        try {
            myURL = new URL(urlImage);
            new MyAsyncTask(logoImage).execute(myURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // Создаем свой класс от AsyncTask
    private class MyAsyncTask extends AsyncTask<URL, Void, Bitmap> {

        ImageView ivLogo;

        public MyAsyncTask(ImageView iv) {
            ivLogo = iv;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Bitmap doInBackground(URL... urls) {
            Bitmap networkBitmap = null;

            URL networkUrl = urls[0]; // загружаем первый элемент
            try {
                networkBitmap = BitmapFactory.decodeStream(networkUrl
                        .openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return networkBitmap;
        }

        protected void onPostExecute(Bitmap result) {
            ivLogo.setImageBitmap(result);
        }
    }
}
