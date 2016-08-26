package specter.observer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;

public class tytbyTAB extends AppCompatActivity {

    public static final String APP_PREFERENCES = "settingsnewsspecter";

    final String[] category = new String[] { "TYTBY_politics", "TYTBY_economics", "TYTBY_society",
            "TYTBY_world", "TYTBY_culture", "TYTBY_accidents", "TYTBY_finance", "TYTBY_realty", "TYTBY_auto",
            "TYTBY_sport", "TYTBY_lady", "TYTBY_vashdom", "TYTBY_afisha", "TYTBY_press"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tytby_tab);
        setTitle("Новости с сайта news.tut.by");

        readPreferences();
    }

    private void readPreferences()
    {
        SharedPreferences pref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        for(int i = 1; i<=14;i++)
        {
            String text;
            if (i == 1) {
                text = pref.getString(category[i-1], "").toString();
                if (text.equals("+"))
                {
                    CheckBox myChb = (CheckBox) findViewById(R.id.checkBox);
                    myChb.setChecked(true);
                }
            }
            else
            {
                text = pref.getString(category[i-1], "").toString();
                if (text.equals("+"))
                {
                    CheckBox myChb;
                    myChb = (CheckBox) findViewById(getBaseContext().getResources().getIdentifier("checkBox" + i, "id", getBaseContext().getPackageName()));
                    myChb.setChecked(true);
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        int count = 0;
        SharedPreferences pref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editPref = pref.edit();
        for(int i = 1; i<=14;i++)
        {
            CheckBox myChb = null;
            String text = "-";
            if (i == 1)
            {
                myChb = (CheckBox) findViewById(R.id.checkBox);
                if (myChb.isChecked() == true) {
                    text = "+";
                    count++;
                }
                editPref.putString(category[i-1], text);
                editPref.commit();
            }
            else
            {
                myChb = (CheckBox) findViewById(getBaseContext().getResources().getIdentifier("checkBox" + i, "id", getBaseContext().getPackageName()));
                if (myChb.isChecked() == true) {
                    count++;
                    text = "+";
                }
                editPref.putString(category[i-1], text);
                editPref.commit();
            }
        }
        editPref.putString("count",String.valueOf(count));
        editPref.commit();
    }
}
