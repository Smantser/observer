package specter.observer;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;

// TODO Класс настроек
public class Settings {

    public static final String APP_PREFERENCES = "observer";

    public boolean SettingsisNetworkConnected(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    // TODO Название:"hasConnection" | Назначение: проверка есть ли доступ к интернету | Тип: boolean
    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo;
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }

    // TODO Название:"MenuElemAdd" | Назначение: перезапись настроек при добавлении | Тип: void
    public void MenuElemAdd(Context ctx)
    {
        SharedPreferences pref = ctx.getSharedPreferences(APP_PREFERENCES, ctx.MODE_PRIVATE);
        iffirst(pref);

        int count = Integer.parseInt(pref.getString("count", "").toString());
        count = count + 1;
        SharedPreferences.Editor editPref = pref.edit();

        editPref.putString("count", String.valueOf(count));
        editPref.commit();
    }

    // TODO Название:"MenuElemDel" | Назначение: перезапись настроек при удалении | Тип: void
    public void MenuElemDel(Context ctx)
    {
        SharedPreferences pref = ctx.getSharedPreferences(APP_PREFERENCES, ctx.MODE_PRIVATE);
        int count = Integer.parseInt(pref.getString("count", "").toString());
        if(count >=1)
        {
            count = count - 1;

            SharedPreferences.Editor editPref = pref.edit();

            editPref.putString("count", String.valueOf(count));
            editPref.commit();
        }

    }

    // TODO Название:"iffirst" | Назначение: проверка в первый ли раз запускают программу | Тип: void
    public void iffirst(SharedPreferences pref)
    {
        // проверяем, первый ли раз открывается программа
        boolean hasVisited = pref.getBoolean("hasVisited", false);

        if (!hasVisited) {
            // первый раз
            SharedPreferences.Editor e = pref.edit();
            e.putBoolean("hasVisited", true);
            e.commit(); // подтвердить изменения

            SharedPreferences.Editor editPref = pref.edit();

            editPref.putString("count",String.valueOf(0));
            editPref.commit();
        }
    }


    // TODO Название:"SetSelectCount" | Назначение: устанавливает позицию выделенного элемента | Тип: void
    public void SetSelectCount(Context ctx,int position)
    {
        SharedPreferences pref = ctx.getSharedPreferences(APP_PREFERENCES, ctx.MODE_PRIVATE);
        SharedPreferences.Editor editPref = pref.edit();
        editPref.putString("Select",String.valueOf(position));
        editPref.commit();

        //Log.d("my", String.valueOf(position));
    }

    // TODO Название:"GetSelectCount" | Назначение: получает позицию выделенного элемента | Тип: int
    public int GetSelectCount(Context ctx)
    {
        SharedPreferences pref = ctx.getSharedPreferences(APP_PREFERENCES, ctx.MODE_PRIVATE);
        int position = Integer.parseInt(pref.getString("Select", "").toString());
        return position;
    }

    // TODO Название:"SetSelectCountToNull" | Назначение: обнуляет позицию выделенного элемента | Тип: void
    public void SetSelectCountToNull(Context ctx)
    {
        SharedPreferences pref = ctx.getSharedPreferences(APP_PREFERENCES, ctx.MODE_PRIVATE);
        SharedPreferences.Editor editPref = pref.edit();
        editPref.putString("Select",String.valueOf(-1));
        editPref.commit();
    }

    // TODO Название:"?" | Назначение:  | Тип:
    public void SettingsNewsRadio1(Context ctx,int pos,int Count)
    {
        if (pos > 0 && pos <= Count)
        {

        }
        else
        {
            Toast.makeText(ctx.getApplicationContext(), "Не выбран элемент меню", Toast.LENGTH_SHORT).show();
        }


    }
    // TODO Название:"?" | Назначение:  | Тип:
    public void SettingsNewsRadio2(Context ctx)
    {

    }
    // TODO Название:"?" | Назначение:  | Тип:
    public void SettingsNewsRadio3(Context ctx)
    {

    }

    /*public void SettingsRe(Context ctx,String ns,String tema)
    {
        SharedPreferences pref = ctx.getSharedPreferences(APP_PREFERENCES, ctx.MODE_PRIVATE);

        int countsel = Integer.parseInt(pref.getString("Select", "").toString());

        SharedPreferences.Editor editPref = pref.edit();
        editPref.putString(countsel+"|site",ns);
        editPref.commit();
        editPref.putString(countsel+"|genre",tema);
        editPref.commit();
    }*/

    // TODO Название:"SettingsWrite" | Назначение: Записывает коллекцию настроек в память | Тип: void
    public void SettingsWrite(Context ctx,ArrayList<MenuV1Box> List)
    {
        SharedPreferences pref = ctx.getSharedPreferences(APP_PREFERENCES, ctx.MODE_PRIVATE);

        int count = Integer.parseInt(pref.getString("count", "").toString());

        Iterator<MenuV1Box> iter = List.iterator();
        int countlist = 0;
        while (iter.hasNext()) {
            MenuV1Box elem = iter.next();
            countlist++;

            String site = elem.getcat1();
            String teme = elem.getcat2();

            if (!site.equals("- сайт -") && !teme.equals("- тема -")) {
                SharedPreferences.Editor editPref = pref.edit();
                editPref.putString(countlist + "|site", site);
                editPref.commit();
                editPref.putString(countlist + "|teme", teme);
                editPref.commit();
            }
            else
            {
                SharedPreferences.Editor editPref = pref.edit();
                editPref.putString("count", String.valueOf(count-1));
                editPref.commit();
            }
        }
    }

    // TODO Название:"SettingsRead" | Назначение: читает настройки из памяти в массив настроек | Тип: ArrayList<MenuV1Box>
    public ArrayList<MenuV1Box> SettingsRead(Context ctx)
    {
        ArrayList<MenuV1Box> List = new ArrayList<MenuV1Box>();
        SharedPreferences pref = ctx.getSharedPreferences(APP_PREFERENCES, ctx.MODE_PRIVATE);

        iffirst(pref);
        int count = Integer.parseInt(pref.getString("count", "").toString());

        if (count == 0)
        {
            List.add(new MenuV1Box("- сайт -", "- тема -"));
            SharedPreferences.Editor editPref = pref.edit();
            editPref.putString("count", String.valueOf(1));
            editPref.commit();
        }
        else {
            for (int i = 1; i <= count; i++) {
                String site = pref.getString(i + "|site", "").toString();
                String teme = pref.getString(i + "|teme", "").toString();
                List.add(new MenuV1Box(site, teme));
            }
        }

        return List;
    }

    // TODO Название:"SettingsOverflowButton" | Назначение: проверяет не выходит ли за пределы выделенный пункт меню и устанавливает новое значение пунктаменю | Тип: void
    public void SettingsOverflowButton(Context ctx,int pos,int Count,ArrayList<MenuV1Box> finalList2,AdapterMenu Adapter,String Site,String Teme)
    {
        if (pos > 0 && pos <= Count)
        {
            MenuV1Box element = new MenuV1Box(Site, Teme);
            finalList2.set(pos - 1, element);
            Adapter.notifyDataSetChanged();
        }
        else
        {
            Toast.makeText(ctx.getApplicationContext(), "Не выбран элемент меню", Toast.LENGTH_SHORT).show();
        }
    }

    /*public int GetCount(Context ctx)
    {
        SharedPreferences pref = ctx.getSharedPreferences(APP_PREFERENCES, ctx.MODE_PRIVATE);
        int position = Integer.parseInt(pref.getString("count", "").toString());
        return position;
    }*/

    // TODO Название:"CountDecr" | Назначение: уменьшает count (число пунктов) настроек на 1 | Тип: void
    public void CountDecr(Context ctx)
    {
        SharedPreferences pref = ctx.getSharedPreferences(APP_PREFERENCES, ctx.MODE_PRIVATE);
        int count = Integer.parseInt(pref.getString("count", "").toString());
        SharedPreferences.Editor editPref = pref.edit();
        editPref.putString("count", String.valueOf(count-1));
        editPref.commit();
    }


    // TODO Название:"CheckForAMatch" | Назначение: проверка на совпадение тем в коллекции настроек | Тип: int
    public int CheckForAMatch(Context ctx,ArrayList<MenuV1Box> finalList2,String Site,String Teme)
    {
        int booleanList = 0;
        for (MenuV1Box elem : finalList2) {
            String category1 = elem.getcat1();
            String category2 = elem.getcat2();
            if (Site.equals(category1) && Teme.equals(category2)) {
                booleanList = 1;
                Toast.makeText(ctx.getApplicationContext(), "Эта тема уже есть в вашем списке", Toast.LENGTH_SHORT).show();
            }
        }
        return  booleanList;
    }


    // TODO
    ArrayList<String> ListUrl;
    ArrayList<String> CoutnPageList;
    int count;

    public ArrayList<String> getListUrl() {
        return ListUrl;
    }

    public ArrayList<String> getCoutnPageList() {
        return CoutnPageList;
    }

    public int getcount() {
        return count;
    }

    public void SettingsReadList(Context ctx) {

        ArrayList<MenuV1Box> ListM = SettingsRead(ctx);
        ListUrl = new ArrayList<String>();
        CoutnPageList = new ArrayList<String>();

        String[] Site = new String[] { "TYT.by" };
        /*String[] TYTteme = new String[] { "Политика", "Экономика и бизнес", "Общество",
                "Мировые новости", "Кругозор", "Происшествия", "Финансы", "Недвижимость", "Авто",
                "Спорт", "Леди", "Ваш дом", "Афиша", "Новости компаний"};
        String[] TYTurl = new String[] { "politics", "economics", "society",
                "world", "culture", "accidents", "finance", "realty", "auto",
                "sport", "lady", "vashdom", "afisha", "press"};*/

        String[] TYTteme = new String[] { "Экономика",
                "Общество",
                "В мире",
                "Кругозор",
                "Происшествия",
                "Финансы",
                "Недвижимость",
                "Авто",
                "Спорт",
                "42",
                "Леди",
                "Ваш дом",
                "Афиша",
                "Новости компаний"};

        String[] TYTurl = new String[] { "economics",
                "society",
                "world",
                "culture",
                "accidents",
                "finance",
                "realty",
                "auto",
                "sport",
                "it",
                "lady",
                "vashdom",
                "afisha",
                "press"};


        for (MenuV1Box elem : ListM) {
            String category1 = elem.getcat1();
            String category2 = elem.getcat2();

            CoutnPageList.add(new String("1"));
            ListUrl.add(new String(getUrl(category1,category2,Site, TYTteme, TYTurl)));

        }
        count = ListM.size();
    }

    public String getUrl(String category1,String category2,String[] Site, String[] TYTteme,String[] TYTurl)
    {
        String Url = null;

        if (category1.equals(Site[0])) {
            int booleanCounter = 0;
            for (String elem : TYTteme) {
                String site = elem;
                if (site.equals(category2)) {
                    Url = "http://news.tut.by/"+TYTurl[booleanCounter]+"/";
                    break;
                }
                booleanCounter ++;
            }
        }

        return Url;
    }

    // TODO старое = новое
    public int OldrNew(ArrayList<MenuV1Box> listMOld, ArrayList<MenuV1Box> listM)
    {
        int _boolean = 0;
        if(listMOld.size() != listM.size())
            _boolean = 1;
        else {
            if (listMOld != listM) {
                _boolean = 1;
            }
        }
        return  _boolean;
    }


    // TODO
    public void SetIDprogress(Context ctx,int id)
    {
        SharedPreferences pref = ctx.getSharedPreferences(APP_PREFERENCES, ctx.MODE_PRIVATE);
        SharedPreferences.Editor editPref = pref.edit();
        editPref.putString("PrID",String.valueOf(id));
        editPref.commit();
    }

    // TODO
    public int GetIDprogress(Context ctx)
    {
        SharedPreferences pref = ctx.getSharedPreferences(APP_PREFERENCES, ctx.MODE_PRIVATE);
        int id = Integer.parseInt(pref.getString("PrID", "").toString());
        return id;
    }

}
