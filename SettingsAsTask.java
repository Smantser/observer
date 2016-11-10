package specter.observer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Date;

public class SettingsAsTask {

    private String GetData(String Date)
    {
        if(Date == "Сегодня") Date = set_today(Date);
            else Date = set_allday(Date);
        return Date;
    }

    private String set_today(String Date)
    {
        long date = System.currentTimeMillis();
        SimpleDateFormat sdfDay = new SimpleDateFormat("d MMM", new Locale("ru"));
        Date = sdfDay.format(date);
        return Date;
    }
    private String set_allday(String Date) {
        DateFormat format = new SimpleDateFormat("d MMM", new Locale("ru"));
        Date date = null;
        try {
            date = format.parse(Date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdfDay = new SimpleDateFormat("d MMM", new Locale("ru"));
        Date = sdfDay.format(date);
        return Date;
    }

    public ArrayList<BoxText> SetListTiAdapterText(ArrayList<BoxText> Boxtext, int count, List<News> List)
    {
        int zero = count / 3;
        int tozero = 1;
        for (int i = 0; i < count; i++) {

            String Text1 = null;
            String Combo1 = null;
            String Patch1= null;

            String Text2= null;
            String Combo2= null;
            String Patch2= null;

            String Text3= null;
            String Combo3= null;
            String Patch3= null;

            if(tozero <= zero) {

                Text1= List.get(i).Text;
                Combo1 = GetData(List.get(i).Date) + " " + List.get(i).getTime();
                Patch1 = List.get(i).Patch;

                Text2= List.get(i+1).Text;
                Combo2 = GetData(List.get(i+1).Date) + " " + List.get(i+1).getTime();
                Patch2 = List.get(i+1).Patch;

                Text3= List.get(i+2).Text;
                Combo3 = GetData(List.get(i+2).Date) + " " + List.get(i+2).getTime();
                Patch3 = List.get(i+2).Patch;

                tozero++;
            }
            else
            {
                int ost = count - zero*3;

                if (ost == 1)
                {
                    Text1= List.get(i).Text;
                    Combo1 = GetData(List.get(i).Date) + " " + List.get(i).getTime();
                    Patch1 = List.get(i).Patch;

                    Text2= "0";
                    Combo2 = "0";
                    Patch2 = "0";

                    Text3= "0";
                    Combo3 = "0";
                    Patch3 = "0";
                }
                else if (ost == 2)
                {
                    Text1= List.get(i).Text;
                    Combo1 = GetData(List.get(i).Date) + " " + List.get(i).getTime();
                    Patch1 = List.get(i).Patch;

                    Text2= List.get(i+1).Text;
                    Combo2 = GetData(List.get(i+1).Date) + " " + List.get(i+1).getTime();
                    Patch2 = List.get(i+1).Patch;

                    Text3= "0";
                    Combo3 = "0";
                    Patch3 = "0";
                }
            }

            Boxtext.add(new BoxText(Text1,Combo1,Patch1,Text2,Combo2,Patch2,Text3,Combo3,Patch3));

            i++;
            i++;
        }
        return Boxtext;
    }
}
