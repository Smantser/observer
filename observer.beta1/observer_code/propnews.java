package specter.observer;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class propnews extends TabActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propnews);

        // получаем TabHost
        TabHost tabHost = getTabHost();

        // инициализация была выполнена в getTabHost
        // метод setup вызывать не нужно

        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("TYT.by");
        tabSpec.setContent(new Intent(this, tytbyTAB.class));
        tabHost.addTab(tabSpec);

        /*tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator("");
        tabSpec.setContent(new Intent(this, otherTab.class));
        tabHost.addTab(tabSpec);*/
    }


}
