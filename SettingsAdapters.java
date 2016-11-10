package specter.observer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;


public class SettingsAdapters {

    public static final String APP_PREFERENCES = "observer";

    public void SetWindParam(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        //int height = size.y;
        SharedPreferences pref = ctx.getSharedPreferences(APP_PREFERENCES, ctx.MODE_PRIVATE);
        SharedPreferences.Editor editPref = pref.edit();
        editPref.putString("DisplayWidth",String.valueOf(width));
        editPref.commit();
    }


    public int GetWidthLarge(Context ctx)
    {
        SharedPreferences pref = ctx.getSharedPreferences(APP_PREFERENCES, ctx.MODE_PRIVATE);
        int width = Integer.parseInt(pref.getString("DisplayWidth", "").toString());
        return width;
    }
}
