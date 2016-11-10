package specter.observer;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

//созданный адаптер, который будем использовать
public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<MenuV1Box> objects;

    int heighttxt = 0;

    BoxAdapter(Context context, ArrayList<MenuV1Box> Boxs) {
        ctx = context;
        objects = Boxs;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // TODO кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // TODO элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // TODO id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // TODO пункт списка
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

    // TODO перенос строки для Textbox меню
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
            if (cnt + arrWords[index].length() <= 14) { // Если текущая фраза + текущее слово в массиве arrWords не превышает 14
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

    // TODO коробка с элеменами по позиции
    public MenuV1Box getBox(int position) {
        return ((MenuV1Box) getItem(position));
    }
}