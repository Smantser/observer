package specter.observer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import specter.observer.Adapters.RecyclerClickListener;
import specter.observer.Adapters.RecyclerView_Adapter;
import specter.observer.Adapters.RecyclerView_AdapterLarge;
import specter.observer.Adapters.RecyclerView_AdapterText;

import specter.observer.SimpleGestureFilter.SimpleGestureListener;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener/*, SimpleGestureListener*/ {

    ArrayList<News> List;
    ArrayList<String> String;
    ArrayList<String> Int;
    Settings settings = new Settings();
    SettingsAdapters settingsads = new SettingsAdapters();
    ArrayList<MenuV1Box> ListM;
    AdapterMenu Adapter;

    private SimpleGestureFilter detector;

    int count;

    int hs = 0; // просмотр
    /** Called when the activity is first created. */
    Context ctx = this;

    int isOpen = 0;
    private Toolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        settingsads.SetWindParam(ctx); // размеры экрана в память
        boolean isConnect = settings.hasConnection(this); // проверка есть ли подключение к интернету
        // скрытие меню
        RelativeLayout element = (RelativeLayout) findViewById(R.id.relativeLayout);
        element.setVisibility(View.GONE);
        //предварительные настройки
        presetting();

        if(isConnect == true) {
            GetInfoToLists(); // загрузка
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Нет подключения к интернету", Toast.LENGTH_SHORT).show();
        }

        /*final String TAG = "myLogs";
        SwipeRefreshLayout TouchLayout = (SwipeRefreshLayout) findViewById(R.id.relativeLayout2);
        assert TouchLayout != null;
        TouchLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, event.getX() + "\n" + event.getY());
                //Toast.makeText(getApplicationContext(), event.getX() + "\n" + event.getY(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/
        /*TextView tv;
        tv = new TextView(this);
        tv.setOnTouchListener(this);
        setContentView(tv);*/

        /*yourRelativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //gesture detector to detect swipe.
                gestureDetector.onTouchEvent(arg1);
                return true;//always return true to consume event
            }
        });*/


        // Detect touched area
        //detector = new SimpleGestureFilter(this,this);



        /*Intent intent = new Intent(this, ATest.class);
        startActivity(intent);*/

        /*ProgressBar PB = (ProgressBar) findViewById(R.id.progressBar2);
        PB.setProgressDrawable(getResources().getDrawable(R.drawable.progresstheme1));
        new AsTaskProgressbar(ctx,80,PB).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);*/
    }


/*
    @Override
    public boolean dispatchTouchEvent(MotionEvent me){
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }
    @Override
    public void onSwipe(int direction) {
        String str = "";

        switch (direction) {
            // TODO Swipe
            case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
                if (isOpen == 0)
                    MenuOpen();
                break;
            case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
                if (isOpen == 1)
                    MenuClose();
                break;
            case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
                break;
            case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
                break;

        }
    }

    @Override
    public void onDoubleTap() {
        //Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
    }
*/

/*
    float x;
    float y;
    String sDown;
    String sMove;
    String sUp;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                sDown = "Down: " + x + "," + y;
                sMove = ""; sUp = "";
                break;
            case MotionEvent.ACTION_MOVE: // движение
                sMove = "Move: " + x + "," + y;
                break;
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                sMove = "";
                sUp = "Up: " + x + "," + y;
                break;
        }
        Toast.makeText(getApplicationContext(), sDown + "\n" + sMove + "\n" + sUp, Toast.LENGTH_SHORT).show();
        return true;
    }*/



    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        AddLishniListView(1);
        GetInfoToLists();

        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1200);
    }


    // TODO Предустановка (меню,настройки и т.д.)
    private void presetting()
    {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.relativeLayout2);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        ListM = settings.SettingsRead(ctx);

        Adapter = new AdapterMenu(this, ListM);
        final ListView LvMenu = (ListView) findViewById(R.id.lvMainMenu);
        LvMenu.setAdapter(Adapter);

        // TODO Уровень 1   Создание события добавления
        FloatingActionButton fabadd = (FloatingActionButton) findViewById(R.id.flipadd);
        final ArrayList<MenuV1Box> finalList1 = ListM;
        assert fabadd != null;
        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int isAdd = 0;
                Iterator<MenuV1Box> iter = ListM.iterator();
                while (iter.hasNext()) {
                    MenuV1Box elem = iter.next();
                    String site = elem.getcat1();
                    if (site.equals("- сайт -")) isAdd = 1;
                }
                if (isAdd == 0) {
                    finalList1.add(new MenuV1Box("- сайт -", "- тема -"));//(0, editText.getText().toString());
                    settings.SetSelectCountToNull(ctx);
                    Adapter.notifyDataSetChanged();
                    LvMenu.smoothScrollToPosition(LvMenu.getCount());
                    settings.MenuElemAdd(ctx);
                }
            }
        });

        // TODO Уровень 1   Создание события удаления
        FloatingActionButton fabdel = (FloatingActionButton) findViewById(R.id.flipdel);
        final ArrayList<MenuV1Box> finalList = ListM;
        assert fabdel != null;
        fabdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = settings.GetSelectCount(ctx);
                if (pos > 0 && pos <= LvMenu.getCount())
                {
                    finalList.remove(pos - 1);
                    // уведомляем, что данные изменились
                    settings.SetSelectCountToNull(ctx);
                    Adapter.notifyDataSetChanged();
                    settings.MenuElemDel(ctx);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Не выбран элемент для удаления", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // TODO Уровень 1   Создание события нажатия на меню (fab)
        final ImageButton BTp = (ImageButton) findViewById(R.id.imageButton2);
        assert BTp != null;
        BTp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Scroll();
            }
        });

        final ImageButton BTm = (ImageButton) findViewById(R.id.imageButton);
        assert BTm != null;
        BTm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Scroll();
            }
        });


        // TODO Уровень 1   Создание обработчика нажатия на элементы тем в меню
        final ArrayList<MenuV1Box> finalList2 = ListM;
        View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton)v;
                int pos;
                String Site;
                String Teme;
                int booleanList;
                switch (rb.getId()) {
                    case R.id.tyt11:
                        pos = settings.GetSelectCount(ctx);
                        Site = "TYT.by";
                        Teme = "Экономика";
                        booleanList = settings.CheckForAMatch(ctx,finalList2,Site,Teme);
                        if (booleanList == 0) {
                            settings.SettingsOverflowButton(ctx, pos, LvMenu.getCount(), finalList2, Adapter, Site, Teme);
                            settings.SetSelectCountToNull(ctx);
                            Clear_TYTradio(1);
                        }
                        else Clear_TYTradio(0);
                        break;
                    case R.id.tyt12:
                        pos = settings.GetSelectCount(ctx);
                        Site = "TYT.by";
                        Teme = "Общество";
                        booleanList = settings.CheckForAMatch(ctx,finalList2,Site,Teme);
                        if (booleanList == 0) {
                            settings.SettingsOverflowButton(ctx, pos, LvMenu.getCount(), finalList2, Adapter, Site, Teme);
                            settings.SetSelectCountToNull(ctx);
                            Clear_TYTradio(1);
                        }
                        else Clear_TYTradio(0);
                        break;

                    case R.id.tyt21:
                        pos = settings.GetSelectCount(ctx);
                        Site = "TYT.by";
                        Teme = "В мире";
                        booleanList = settings.CheckForAMatch(ctx,finalList2,Site,Teme);
                        if (booleanList == 0) {
                            settings.SettingsOverflowButton(ctx, pos, LvMenu.getCount(), finalList2, Adapter, Site, Teme);
                            settings.SetSelectCountToNull(ctx);
                            Clear_TYTradio(2);
                        }
                        else Clear_TYTradio(0);
                        break;

                    case R.id.tyt22:
                        pos = settings.GetSelectCount(ctx);
                        Site = "TYT.by";
                        Teme = "Кругозор";
                        booleanList = settings.CheckForAMatch(ctx,finalList2,Site,Teme);
                        if (booleanList == 0) {
                            settings.SettingsOverflowButton(ctx, pos, LvMenu.getCount(), finalList2, Adapter, Site, Teme);
                            settings.SetSelectCountToNull(ctx);
                            Clear_TYTradio(2);
                        }
                        else Clear_TYTradio(0);
                        break;

                    case R.id.tyt31:
                        pos = settings.GetSelectCount(ctx);
                        Site = "TYT.by";
                        Teme = "Происшествия";
                        booleanList = settings.CheckForAMatch(ctx,finalList2,Site,Teme);
                        if (booleanList == 0) {
                            settings.SettingsOverflowButton(ctx, pos, LvMenu.getCount(), finalList2, Adapter, Site, Teme);
                            settings.SetSelectCountToNull(ctx);
                            Clear_TYTradio(3);
                        }
                        else Clear_TYTradio(0);
                        break;

                    case R.id.tyt32:
                        pos = settings.GetSelectCount(ctx);
                        Site = "TYT.by";
                        Teme = "Финансы";
                        booleanList = settings.CheckForAMatch(ctx,finalList2,Site,Teme);
                        if (booleanList == 0) {
                            settings.SettingsOverflowButton(ctx, pos, LvMenu.getCount(), finalList2, Adapter, Site, Teme);
                            settings.SetSelectCountToNull(ctx);
                            Clear_TYTradio(3);
                        }
                        else Clear_TYTradio(0);
                        break;

                    case R.id.tyt41:
                        pos = settings.GetSelectCount(ctx);
                        Site = "TYT.by";
                        Teme = "Недвижимость";
                        booleanList = settings.CheckForAMatch(ctx,finalList2,Site,Teme);
                        if (booleanList == 0) {
                            settings.SettingsOverflowButton(ctx, pos, LvMenu.getCount(), finalList2, Adapter, Site, Teme);
                            settings.SetSelectCountToNull(ctx);
                            Clear_TYTradio(4);
                        }
                        else Clear_TYTradio(0);
                        break;

                    case R.id.tyt42:
                        pos = settings.GetSelectCount(ctx);
                        Site = "TYT.by";
                        Teme = "Авто";
                        booleanList = settings.CheckForAMatch(ctx,finalList2,Site,Teme);
                        if (booleanList == 0) {
                            settings.SettingsOverflowButton(ctx, pos, LvMenu.getCount(), finalList2, Adapter, Site, Teme);
                            settings.SetSelectCountToNull(ctx);
                            Clear_TYTradio(4);
                        }
                        else Clear_TYTradio(0);
                        break;

                    case R.id.tyt51:
                        pos = settings.GetSelectCount(ctx);
                        Site = "TYT.by";
                        Teme = "Спорт";
                        booleanList = settings.CheckForAMatch(ctx,finalList2,Site,Teme);
                        if (booleanList == 0) {
                            settings.SettingsOverflowButton(ctx, pos, LvMenu.getCount(), finalList2, Adapter, Site, Teme);
                            settings.SetSelectCountToNull(ctx);
                            Clear_TYTradio(5);
                        }
                        else Clear_TYTradio(0);
                        break;

                    case R.id.tyt52:
                        pos = settings.GetSelectCount(ctx);
                        Site = "TYT.by";
                        Teme = "42";
                        booleanList = settings.CheckForAMatch(ctx,finalList2,Site,Teme);
                        if (booleanList == 0) {
                            settings.SettingsOverflowButton(ctx, pos, LvMenu.getCount(), finalList2, Adapter, Site, Teme);
                            settings.SetSelectCountToNull(ctx);
                            Clear_TYTradio(5);
                        }
                        else Clear_TYTradio(0);
                        break;

                    case R.id.tyt61:
                        pos = settings.GetSelectCount(ctx);
                        Site = "TYT.by";
                        Teme = "Леди";
                        booleanList = settings.CheckForAMatch(ctx,finalList2,Site,Teme);
                        if (booleanList == 0) {
                            settings.SettingsOverflowButton(ctx, pos, LvMenu.getCount(), finalList2, Adapter, Site, Teme);
                            settings.SetSelectCountToNull(ctx);
                            Clear_TYTradio(6);
                        }
                        else Clear_TYTradio(0);
                        break;

                    case R.id.tyt62:
                        pos = settings.GetSelectCount(ctx);
                        Site = "TYT.by";
                        Teme = "Ваш дом";
                        booleanList = settings.CheckForAMatch(ctx,finalList2,Site,Teme);
                        if (booleanList == 0) {
                            settings.SettingsOverflowButton(ctx, pos, LvMenu.getCount(), finalList2, Adapter, Site, Teme);
                            settings.SetSelectCountToNull(ctx);
                            Clear_TYTradio(6);
                        }
                        else Clear_TYTradio(0);
                        break;

                    case R.id.tyt71:
                        pos = settings.GetSelectCount(ctx);
                        Site = "TYT.by";
                        Teme = "Афиша";
                        booleanList = settings.CheckForAMatch(ctx,finalList2,Site,Teme);
                        if (booleanList == 0) {
                            settings.SettingsOverflowButton(ctx, pos, LvMenu.getCount(), finalList2, Adapter, Site, Teme);
                            settings.SetSelectCountToNull(ctx);
                            Clear_TYTradio(7);
                        }
                        else Clear_TYTradio(0);
                        break;

                    case R.id.tyt72:
                        pos = settings.GetSelectCount(ctx);
                        Site = "TYT.by";
                        Teme = "Новости компаний";
                        booleanList = settings.CheckForAMatch(ctx,finalList2,Site,Teme);
                        if (booleanList == 0) {
                            settings.SettingsOverflowButton(ctx, pos, LvMenu.getCount(), finalList2, Adapter, Site, Teme);
                            settings.SetSelectCountToNull(ctx);
                            Clear_TYTradio(7);
                        }
                        else Clear_TYTradio(0);
                        break;

                    default:
                        break;
                }
            }
        };
        RadioButton RadioButtonTYT1 = (RadioButton)findViewById(R.id.tyt11);
        RadioButtonTYT1.setOnClickListener(radioButtonClickListener);
        RadioButton RadioButtonTYT2 = (RadioButton)findViewById(R.id.tyt12);
        RadioButtonTYT2.setOnClickListener(radioButtonClickListener);
        RadioButton RadioButtonTYT3 = (RadioButton)findViewById(R.id.tyt21);
        RadioButtonTYT3.setOnClickListener(radioButtonClickListener);
        RadioButton RadioButtonTYT4 = (RadioButton)findViewById(R.id.tyt22);
        RadioButtonTYT4.setOnClickListener(radioButtonClickListener);
        RadioButton RadioButtonTYT5 = (RadioButton)findViewById(R.id.tyt31);
        RadioButtonTYT5.setOnClickListener(radioButtonClickListener);
        RadioButton RadioButtonTYT6 = (RadioButton)findViewById(R.id.tyt32);
        RadioButtonTYT6.setOnClickListener(radioButtonClickListener);
        RadioButton RadioButtonTYT7 = (RadioButton)findViewById(R.id.tyt41);
        RadioButtonTYT7.setOnClickListener(radioButtonClickListener);
        RadioButton RadioButtonTYT8 = (RadioButton)findViewById(R.id.tyt42);
        RadioButtonTYT8.setOnClickListener(radioButtonClickListener);
        RadioButton RadioButtonTYT9 = (RadioButton)findViewById(R.id.tyt51);
        RadioButtonTYT9.setOnClickListener(radioButtonClickListener);
        RadioButton RadioButtonTYT10 = (RadioButton)findViewById(R.id.tyt52);
        RadioButtonTYT10.setOnClickListener(radioButtonClickListener);
        RadioButton RadioButtonTYT11 = (RadioButton)findViewById(R.id.tyt61);
        RadioButtonTYT11.setOnClickListener(radioButtonClickListener);
        RadioButton RadioButtonTYT12 = (RadioButton)findViewById(R.id.tyt62);
        RadioButtonTYT12.setOnClickListener(radioButtonClickListener);
        RadioButton RadioButtonTYT13 = (RadioButton)findViewById(R.id.tyt71);
        RadioButtonTYT13.setOnClickListener(radioButtonClickListener);
        RadioButton RadioButtonTYT14 = (RadioButton)findViewById(R.id.tyt72);
        RadioButtonTYT14.setOnClickListener(radioButtonClickListener);
    }

    // TODO очистить старые отмеченые группы кроме number
    public void Clear_TYTradio(int number)
    {
        RadioGroup radioGroupTYT1 = (RadioGroup) findViewById(R.id.radioTYT1);
        RadioGroup radioGroupTYT2 = (RadioGroup) findViewById(R.id.radioTYT2);
        RadioGroup radioGroupTYT3 = (RadioGroup) findViewById(R.id.radioTYT3);
        RadioGroup radioGroupTYT4 = (RadioGroup) findViewById(R.id.radioTYT4);
        RadioGroup radioGroupTYT5 = (RadioGroup) findViewById(R.id.radioTYT5);
        RadioGroup radioGroupTYT6 = (RadioGroup) findViewById(R.id.radioTYT6);
        RadioGroup radioGroupTYT7 = (RadioGroup) findViewById(R.id.radioTYT7);

        if(number != 1)
            radioGroupTYT1.clearCheck();
        if(number != 2)
            radioGroupTYT2.clearCheck();
        if(number != 3)
            radioGroupTYT3.clearCheck();
        if(number != 4)
            radioGroupTYT4.clearCheck();
        if(number != 5)
            radioGroupTYT5.clearCheck();
        if(number != 6)
            radioGroupTYT6.clearCheck();
        if(number != 7)
            radioGroupTYT7.clearCheck();
    }


    // TODO События при паузе приложения
    protected void onPause() {
        super.onPause();
        Iterator<MenuV1Box> iter = ListM.iterator();
        int countlist = 0;
        while (iter.hasNext()) {
            MenuV1Box elem = iter.next();
            countlist++;
            String site = elem.getcat1();
            if (site.equals("- сайт -"))
            {
                ListM.remove(countlist - 1);
                settings.CountDecr(ctx);
            }

        }

        settings.SettingsWrite(ctx,ListM);
    }

    public void onclick(View v)
    {
        Scroll();
    }

    // TODO Открытие меню
    private void MenuOpen()
    {
        RelativeLayout element = (RelativeLayout) findViewById(R.id.relativeLayout);
        element.setVisibility(View.VISIBLE);
        isOpen = 1;
        settings.SetSelectCountToNull(ctx);
        Adapter.notifyDataSetChanged();
        //ListMOld = ListM;
    }

    // TODO Закрытие меню
    private void MenuClose()
    {
        RelativeLayout element = (RelativeLayout) findViewById(R.id.relativeLayout);
        element.setVisibility(View.GONE);
        isOpen = 0;
        Iterator<MenuV1Box> iter = ListM.iterator();
        int countlist = 0;
        while (iter.hasNext()) {
            MenuV1Box elem = iter.next();
            countlist++;
            String site = elem.getcat1();
            if (site.equals("- сайт -")) {
                ListM.remove(countlist - 1);
                settings.CountDecr(ctx);
            }
        }
        settings.SettingsWrite(ctx,ListM);

        //if(settings.OldrNew(ListMOld,ListM) == 1) {
        AddLishniListView(1);
        GetInfoToLists();
        //}
    }

    // TODO События при открытии,закрытии меню
    private void Scroll()
    {
        if (isOpen == 0) {
            // TODO Уровень 1 открытие меню
            MenuOpen();
        }
        else
        {
            // TODO Уровень 1 закрытие меню
            MenuClose();
        }
    }

    // TODO чтение настроек
    private void readPreferencesList() {
        settings.SettingsReadList(ctx);
        String = settings.getListUrl();
        Int = settings.getCoutnPageList();
        count = settings.getcount();
    }

    // TODO Функция устанавливающая ленты новостей
    private void GetInfoToLists() {
        int pros = 0;
        try {
            readPreferencesList();

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

    // TODO Функция запускающая асинхронную загрузку одной ленты
    private void activelist(int i) {
        hs = 0;
        RecyclerView lvMain = (RecyclerView) findViewById(getBaseContext().getResources().getIdentifier("lvMain" + i, "id", getBaseContext().getPackageName()));
        new AsyncTaskSystemNumberOne(lvMain,this,List,"",String.get(i-1)+"1").execute();
    }

    // TODO Функция удаляющая ненужные шаблоны лент
    private void DeleteLishniListView(int n)
    {
        for(int i = n;i<=14;i++) {
            RecyclerView lvMain = (RecyclerView) findViewById(getBaseContext().getResources().getIdentifier("lvMain" + i, "id", getBaseContext().getPackageName()));
            lvMain.setVisibility(View.GONE);
        }
    }

    // TODO Функция создающая шаблоны лент
    private void AddLishniListView(int n)
    {
        for(int i = n;i<=14;i++) {
            RecyclerView lvMain = (RecyclerView) findViewById(getBaseContext().getResources().getIdentifier("lvMain" + i, "id", getBaseContext().getPackageName()));
            lvMain.setVisibility(View.VISIBLE);
            RecyclerView_Adapter adapter = new RecyclerView_Adapter(ctx, null);
            lvMain.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


    // TODO первая асинхронная система (парсер)
    class AsyncTaskSystemNumberOne extends AsyncTask<Void, Integer, Void> {

        RecyclerView lvMainA;
        Context t;
        ArrayList<News> List;
        String Header;
        String url;


        /*ProgressBar PB;
        int Width=1080;
        int stepWidth;
        int progrW=0;*/

        @Override
        protected void onPreExecute() {
            /*RecyclerView lvMaint = (RecyclerView) findViewById(getBaseContext().getResources().getIdentifier("lvMain" + 1, "id", getBaseContext().getPackageName()));
            if (lvMaint.getId() == lvMainA.getId()) {
                ArrayList<ProgrID> Boxs = new ArrayList<ProgrID>();
                Boxs.add(new ProgrID(0));
                lvMainA.setLayoutManager(new LinearLayoutManager(t, LinearLayoutManager.HORIZONTAL, false)); // горизонтальная формация
                AdapterProgress adapterPROGR = new AdapterProgress(t,Boxs);
                lvMainA.setAdapter(adapterPROGR);
                adapterPROGR.notifyDataSetChanged();

                //PB = adapterText.getItemId(1);
                Settings sz = new Settings();
                int id = sz.GetIDprogress(ctx);

                PB = (ProgressBar) findViewById(id);

                //PB.setProgress(0);
                stepWidth = Width/3;
            }*/

            RecyclerView lvMaint = (RecyclerView) findViewById(getBaseContext().getResources().getIdentifier("lvMain" + 1, "id", getBaseContext().getPackageName()));
            RecyclerView lvMaint2 = (RecyclerView) findViewById(getBaseContext().getResources().getIdentifier("lvMain" + 3, "id", getBaseContext().getPackageName()));
            if (lvMaint.getId() == lvMainA.getId() || lvMaint2.getId() == lvMainA.getId()) {
                ArrayList<ProgrID> Boxs = new ArrayList<ProgrID>();
                Boxs.add(new ProgrID(0));
                lvMainA.setLayoutManager(new LinearLayoutManager(t, LinearLayoutManager.HORIZONTAL, false)); // горизонтальная формация
                AdapterProgress adapterPROGR = new AdapterProgress(t,Boxs,2);
                lvMainA.setAdapter(adapterPROGR);
                adapterPROGR.notifyDataSetChanged();
            }
            else
            {
                ArrayList<ProgrID> Boxs = new ArrayList<ProgrID>();
                Boxs.add(new ProgrID(0));
                lvMainA.setLayoutManager(new LinearLayoutManager(t, LinearLayoutManager.HORIZONTAL, false)); // горизонтальная формация
                AdapterProgress adapterPROGR = new AdapterProgress(t,Boxs,1);
                lvMainA.setAdapter(adapterPROGR);
                adapterPROGR.notifyDataSetChanged();
            }


            //PB.setProgress(0);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //progrW = progrW + stepWidth;
            /*RecyclerView lvMaint = (RecyclerView) findViewById(getBaseContext().getResources().getIdentifier("lvMain" + 1, "id", getBaseContext().getPackageName()));
            if (lvMaint.getId() == lvMainA.getId()) {
                PB.setProgress(100);
            }*/
            /*PB.setProgress(pr);
            pr = pr + 1;*/
        }


        public AsyncTaskSystemNumberOne(RecyclerView lvMain, Context tt,ArrayList<News> Listt,String Head,String url1) {
            lvMainA = lvMain;
            t = tt;
            List = Listt;
            Header = Head;
            url = url1;

        }

        ParserTYT parser;
        protected Void doInBackground(Void... params) {
            publishProgress();
            try {
                parser = new ParserTYT(url);
                Header = parser.Title;
            } catch (IOException e) {
                //TODO
                Toast.makeText(getApplicationContext(), "Потеряно соединение к интернету", Toast.LENGTH_SHORT).show();
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
            new AsyncTaskSystemNumberTwo(lvMainA,t,List.size(),List,Header).execute(url);
            //new MyAsyncTask(3, lvMain,this).execute(new URL(urlImage),new URL(urlImage),new URL(urlImage),new URL(urlImage),new URL(urlImage));
            /*} catch (MalformedURLException e) {
                e.printStackTrace();
            }*/
        }
    }

    // TODO вторая асинхронная система (получение изображений  + создание лент)
    private class AsyncTaskSystemNumberTwo extends AsyncTask<String, Void, ArrayList<Bitmap>> {

        RecyclerView lvMainA;
        ArrayList<Box> Boxs = new ArrayList<Box>();
        Context t;
        int count;
        List<News> List;
        String Header;

        public AsyncTaskSystemNumberTwo(RecyclerView lvMain, Context tt,int cnt,List<News> ListP,String Head) {
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
                    Toast.makeText(getApplicationContext(), "Потеряно соединение к интернету", Toast.LENGTH_SHORT).show();
                }
                //URL networkUrl = urls[i];
                try {
                    Bitmap networkBitmap = null;
                    networkBitmap = BitmapFactory.decodeStream(networkUrl.openConnection().getInputStream());
                    bmps.add(networkBitmap);
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Потеряно соединение к интернету", Toast.LENGTH_SHORT).show();
                }
            }
            return bmps;
        }

        protected void onPostExecute(ArrayList<Bitmap> result) {
            SettingsAsTask SAT = new SettingsAsTask();

            /////////////// TODO: 19.10.2016
            RecyclerView lvMaint = (RecyclerView) findViewById(getBaseContext().getResources().getIdentifier("lvMain" + 3, "id", getBaseContext().getPackageName()));
            if (lvMaint.getId() == lvMainA.getId())
            {
                ArrayList<BoxText> Boxtext = new ArrayList<BoxText>();
                Boxtext = SAT.SetListTiAdapterText(Boxtext,count,List);
                lvMainA.setLayoutManager(new LinearLayoutManager(t, LinearLayoutManager.HORIZONTAL, false)); // горизонтальная формация
                RecyclerView_AdapterText adapterText = new RecyclerView_AdapterText(t, Boxtext);
                lvMainA.setAdapter(adapterText);
                adapterText.notifyDataSetChanged();
            }
            else {

                for (int i = 0; i < count; i++) {
                    Boxs.add(new Box(List.get(i).Text, List.get(i).Date + " " + List.get(i).getTime(), null, List.get(i).Patch));

                    //TODO старое с изображениями
                    //Boxs.add(new Box(List.get(i).Text, List.get(i).Date + " " + List.get(i).getTime(), result.get(i), List.get(i).Patch));
                }

                lvMainA.setLayoutManager(new LinearLayoutManager(t, LinearLayoutManager.HORIZONTAL, false)); // горизонтальная формация

                RecyclerView lvMain = (RecyclerView) findViewById(getBaseContext().getResources().getIdentifier("lvMain" + 1, "id", getBaseContext().getPackageName()));
                if (lvMain.getId() == lvMainA.getId()) {
                    final RecyclerView_AdapterLarge adapterLarge = new RecyclerView_AdapterLarge(t, Boxs);
                    lvMainA.setAdapter(adapterLarge);
                    adapterLarge.notifyDataSetChanged();

                    lvMainA.addOnItemTouchListener(new RecyclerClickListener(t) {
                        @Override
                        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                        }

                        @Override
                        public void onItemClick(RecyclerView recyclerView, View itemView, int position) {
                            Intent intent = new Intent(t, ADialog.class);
                            intent.putExtra("Patch", adapterLarge.getBox(position).Url.toString());
                            intent.putExtra("Name", adapterLarge.getBox(position).name.toString());
                            startActivity(intent);
                        }
                    });
                } else {
                    final RecyclerView_Adapter adapter = new RecyclerView_Adapter(t, Boxs);
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


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                //Intent intent = new Intent(this, s.class);
                startActivity(intent);
                return true;
            case R.id.item3:

                return true;
            case R.id.item4:

                return true;
            case R.id.item5:
                this.recreate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    /*@Override
    public boolean onTouch(View v, MotionEvent event) {
        // получаем координаты касания
        mX = event.getX();
        mY = event.getY();

        // переключатель в зависимости от типа события
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                mXDOWN = mX;
                mYDOWN = mY;
                break;

            case MotionEvent.ACTION_MOVE: // движение
                //mCoords = "Coords: x = " + mX + ", y = " + mY;
                break;

            case MotionEvent.ACTION_UP: // отпускание
                mXUP = mX;
                mYUP = mY;
                ListnerMenu();
                break;

            case MotionEvent.ACTION_CANCEL:
                // ничего не делаем
                break;
        }
        //tv.setText(mCoords);
        return true;
    }

    private void ListnerMenu()
    {
        if (mXDOWN < 210 && mXUP > 800 && isOpen == 0)
            Scroll();
        if (mXUP < 210 &&  mXDOWN> 800 && isOpen == 1)
            Scroll();
    }
    */

}
