package specter.observer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<News> List;
    ArrayList<String> String;
    ArrayList<String> Int;

    int count;
    public static final String APP_PREFERENCES = "settingsnewsspecter";

    final String[] category = new String[] { "TYTBY_politics", "TYTBY_economics", "TYTBY_society",
            "TYTBY_world", "TYTBY_culture", "TYTBY_accidents", "TYTBY_finance", "TYTBY_realty", "TYTBY_auto",
            "TYTBY_sport", "TYTBY_lady", "TYTBY_vashdom", "TYTBY_afisha", "TYTBY_press"};

    int hs = 0; // просмотр
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Observer");
        create();
    }


    private void create() {
        int pros = 0;
        try {
            readPreferences();

        } catch (Exception ex) {
            pros = 1;
        }
        if (pros == 0){
            try {
                for (int i = 1; i <= count; i++) {
                    activelist(i);
                }
            } catch (Exception ex) {
                Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
            }


            DeleteLishniListView(count + 1);


    }
    }

    private void readPreferences()
    {
        String = new ArrayList<String>();
        Int = new ArrayList<String>();

        SharedPreferences pref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        for(int i = 1; i<=14;i++)
        {
            String text = pref.getString(category[i-1], "").toString();
            if (text.equals("+"))
            {
                String arrStr[] = category[i-1].split("_");
                String.add(new String("http://news.tut.by/"+arrStr[1]+"/"));

            }
            Int.add(new String("1"));
        }
        String text = pref.getString("count", "").toString();
        count = Integer.parseInt(text);
    }

    private void activelist(int i) {
        hs = 0;
        RecyclerView lvMain = (RecyclerView) findViewById(getBaseContext().getResources().getIdentifier("lvMain" + i, "id", getBaseContext().getPackageName()));
        new MyTask(lvMain,this,List,"",String.get(i-1)+"1").execute();
    }


    private void DeleteLishniListView(int n)
    {
        for(int i = n;i<=14;i++) {
            RecyclerView lvMain = (RecyclerView) findViewById(getBaseContext().getResources().getIdentifier("lvMain" + i, "id", getBaseContext().getPackageName()));
            lvMain.setVisibility(View.GONE);
        }
    }

    // AsyncTask Parser
    class MyTask extends AsyncTask<Void, Void, Void> {

        RecyclerView lvMainA;
        Context t;
        ArrayList<News> List;
        String Header;
        String url;

        public MyTask(RecyclerView lvMain, Context tt,ArrayList<News> Listt,String Head,String url1) {
            lvMainA = lvMain;
            t = tt;
            List = Listt;
            Header = Head;
            url = url1;
        }

        ParserTYT parser;
        protected Void doInBackground(Void... params) {

            try {
                parser = new ParserTYT(url);
                Header = parser.Title;
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            List = parser.getList();

            String [] url = new String[List.size()];

            for (int i = 0; i < List.size(); i++) {
                url[i] = List.get(i).getIMGPatch();
            }

            //try {
            new MyAsyncTask(lvMainA,t,List.size(),List,Header).execute(url);
            //new MyAsyncTask(3, lvMain,this).execute(new URL(urlImage),new URL(urlImage),new URL(urlImage),new URL(urlImage),new URL(urlImage));
            /*} catch (MalformedURLException e) {
                e.printStackTrace();
            }*/
        }
    }


    // AsyncTask GET image + create listview
    private class MyAsyncTask extends AsyncTask<String, Void, ArrayList<Bitmap>> {

        RecyclerView lvMainA;
        ArrayList<Box> Boxs = new ArrayList<Box>();
        Context t;
        int count;
        List<News> List;
        String Header;

        public MyAsyncTask(RecyclerView lvMain, Context tt,int cnt,List<News> ListP,String Head) {
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
        protected ArrayList<Bitmap> doInBackground(String... urls) {

            ArrayList<Bitmap> bmps = new ArrayList<Bitmap>();

            for (int i = 0; i < count; i++) {
                URL networkUrl = null;
                try
                {
                    networkUrl = new URL(urls[i]);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                //URL networkUrl = urls[i];
                try {
                    Bitmap networkBitmap = null;
                    networkBitmap = BitmapFactory.decodeStream(networkUrl.openConnection().getInputStream());
                    bmps.add(networkBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return bmps;
        }

        protected void onPostExecute(ArrayList<Bitmap> result) {
            for(int i = 0; i < count; i++)
            {
                Boxs.add(new Box(List.get(i).Text, List.get(i).Date + " " + List.get(i).getTime(),result.get(i),List.get(i).Patch));
            }

            lvMainA.setLayoutManager(new LinearLayoutManager(t, LinearLayoutManager.HORIZONTAL, false)); // горизонтальная формация

            RecyclerView lvMain = (RecyclerView) findViewById(getBaseContext().getResources().getIdentifier("lvMain" + 1, "id", getBaseContext().getPackageName()));
            if (lvMain.getId() == lvMainA.getId())
            {
                final RecyclerView_AdapterLarge  adapterLarge = new RecyclerView_AdapterLarge(t, Boxs);
                lvMainA.setAdapter(adapterLarge);
                adapterLarge.notifyDataSetChanged();

                lvMainA.addOnItemTouchListener(new RecyclerClickListener(t) {
                    @Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
                    @Override
                    public void onItemClick(RecyclerView recyclerView, View itemView,int position) {
                        Intent intent = new Intent(t, ADialog.class);
                        intent.putExtra("Patch", adapterLarge.getBox(position).Url.toString());
                        intent.putExtra("Name", adapterLarge.getBox(position).name.toString());
                        startActivity(intent);
                    }
                });
            }
            else
            {
                final RecyclerView_Adapter  adapter = new RecyclerView_Adapter(t, Boxs);
                lvMainA.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                lvMainA.addOnItemTouchListener(new RecyclerClickListener(t) {
                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                    @Override
                    public void onItemClick(RecyclerView recyclerView, View itemView,
                                            int position) {
                    /*
                    Log.d("...", "name = " + position);
                    Log.d("...", "name = " + adapter.getBox(position).name);
                    Log.d("...", "name = " + adapter.getBox(position).Url);
                    */
                        Intent intent = new Intent(t, ADialog.class);
                        intent.putExtra("Patch", adapter.getBox(position).Url.toString());
                        intent.putExtra("Name", adapter.getBox(position).name.toString());
                        startActivity(intent);
                    }
                });
            }
        }
    }

    /*
    // создание Header
    View createHeader(String text) {
        View v = getLayoutInflater().inflate(R.layout.header, null);
        ((TextView)v.findViewById(R.id.tvText)).setText(text);
        return v;
    }

    // создание Footer
    View createFooter() {
        View v = getLayoutInflater().inflate(R.layout.footer, null);
        return v;
    }


    public void onclick(View v) {

        View parent_row = (View) v.getParent();
        ListView lv = (ListView) parent_row.getParent();
        for(int i = 1;i<=14;i++) {
            RecyclerView lvMain = (RecyclerView) findViewById(getBaseContext().getResources().getIdentifier("lvMain" + i, "id", getBaseContext().getPackageName()));
            if (lvMain.getId() == lv.getId())
            {
                hs = 1;
                int nom = Integer.parseInt(Int.get(i-1)) + 1;
                Int.set(i-1,Integer.toString(nom));
                new MyTask(lvMain,this,List,"",String.get(i-1)+nom).execute();
                break;
            }
        }
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(this, propnews.class);
                startActivity(intent);
                return true;
            case R.id.item3:
                // TODO:
                return true;
            case R.id.item4:
                // TODO:
                return true;
            case R.id.item5:
                this.recreate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
