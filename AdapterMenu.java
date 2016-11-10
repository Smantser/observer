package specter.observer;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class AdapterMenu extends BaseAdapter{
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<MenuV1Box> objects;

    int heighttxt = 0;

    AdapterMenu(Context context, ArrayList<MenuV1Box> obj) {
        ctx = context;
        objects = obj;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view

        View view = convertView;
        if (position == 0)
        {
            view = lInflater.inflate(R.layout.item_menu_t2large, parent, false);
        }
        else
            //if (view == null) {
            view = lInflater.inflate(R.layout.item_menu_t2, parent, false);
        //}

        MenuV1Box p = getBox(position);

        String text = Split(p.getcat2());
        ((TextView) view.findViewById(R.id.tvVData)).setHeight(100);
        ((TextView) view.findViewById(R.id.tvVData)).setText(text);

        //((TextView) view.findViewById(R.id.tvResData)).setText(p.getcat1());
        ((TextView) view.findViewById(R.id.tvResData)).setText(p.getcat1());

        /*Settings set = new Settings();
        int cnt = set.GetSelectCount(ctx);
        if(cnt-1 == position)
        {
            view.setBackgroundColor(Color.parseColor("#FFFFFF"));
            Settings sett = new Settings();
            sett.SetSelectCount(ctx, position);
        }*/



        Button btn = (Button) view.findViewById(R.id.buttonselect);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View parent_row = (View) v.getParent();
                ListView lv = (ListView) parent_row.getParent();
                final int position = lv.getPositionForView(parent_row);

                    Settings set = new Settings();
                    int cnt = set.GetSelectCount(ctx);


                    try {
                        if (cnt >= 0) {
                            lv.getChildAt(cnt - 1).setBackgroundColor(Color.parseColor("#D3D3D3"));
                        }
                    }
                    catch (Exception ex)
                    {
                        //Toast.makeText(ctx, "osherr", Toast.LENGTH_SHORT).show();
                    }


                    set.SetSelectCount(ctx, position + 1);
                    parent_row.setBackgroundColor(Color.parseColor("#FFFFFF"));





                //((TextView) parent_row.findViewById(R.id.color)).setWidth(100);

                //((RelativeLayout) parent_row.findViewById(R.id.color)).setBackgroundColor(Color.parseColor("#B8860B"));
                /*
                Box p = getBox(position-1);
                Log.d("...", "name = " + p.name);
                Log.d("...", "price = " + p.price);
                Log.d("...", "price = " + p.image);*/
                // lv.performItemClick(lv.getAdapter().getView(position, null, null), position, lv.getAdapter().getItemId(position));
            }
        });

        /*Button btn = (Button) view.findViewById(R.id.button5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parent_row = (View) v.getParent();
                ListView lv = (ListView) parent_row.getParent();
                final int position = lv.getPositionForView(parent_row);
                /*
                Box p = getBox(position-1);
                Log.d("...", "name = " + p.name);
                Log.d("...", "price = " + p.price);
                Log.d("...", "price = " + p.image);*/
        // lv.performItemClick(lv.getAdapter().getView(position, null, null), position, lv.getAdapter().getItemId(position));
          /*  }
        });*/

        return view;
    }

    private String Split(String testString)
    {
        String text = "";

        String arrWords[] = testString.split(" ");  // Массив слов
        ArrayList<String> arrPhrases = new ArrayList<String>(); // Коллекция подстрок(фраз)

        StringBuilder stringBuffer = new StringBuilder(); // Буфер для накопления фразы
        int cnt = 0;   // Счётчик, чтобы не выйти за пределы 30 символов
        int index = 0; // Индекс элемента в массиве arrWords. Сразу указывает на первый элемент
        int length = arrWords.length; // Общее количество слов (длина массива)

        while (index != length) {  // Пока не дойдём до последнего элемента
            if (cnt + arrWords[index].length() <= 14) { // Если текущая фраза + текущее слово в массиве arrWords не превышает 30
                cnt += arrWords[index].length() + 1;  // То увеличиваем счётчик
                stringBuffer.append(arrWords[index]).append(" ");  // и накапливаем фразу
                index++;   // Переходим на следующее слово
            } else {   // Фраза превысит лимит в 30 символов
                arrPhrases.add(stringBuffer.toString());   // Добавляем фразу в коллекцию
                stringBuffer = new StringBuilder();
                cnt = 0;                                   // Обнуляем счётчик
            }

        }

        if (stringBuffer.length() > 0) {
            arrPhrases.add(stringBuffer.toString());       // Забираем "остатки"
        }

        for (String elem : arrPhrases) {
            text = text + elem + "\n";
            heighttxt ++;
        }

        return text;
    }

    // box по позиции
    public MenuV1Box getBox(int position) {
        return ((MenuV1Box) getItem(position));
    }
}