package specter.observer;

import android.graphics.Bitmap;

public class BoxText {
    String name1;
    String combo1;
    String Url1;

    String name2;
    String combo2;
    String Url2;

    String name3;
    String combo3;
    String Url3;

    BoxText(String _describe1, String _combo1, String _Url1,String _describe2, String _combo2,String _Url2,String _describe3, String _combo3, String _Url3) {
        name1 = _describe1;
        combo1 = _combo1;
        Url1 = _Url1;

        name2 = _describe2;
        combo2 = _combo2;
        Url2 = _Url2;

        name3 = _describe3;
        combo3 = _combo3;
        Url3 = _Url3;
    }


    public String getName1() { return name1;   }

    public String getCombo1() {
        return combo1;
    }

    public String getUrl1() {
        return Url1;
    }



    public String getName2() { return name2;   }

    public String getCombo2() {
        return combo2;
    }

    public String getUrl2() {
        return Url2;
    }



    public String getName3() { return name3;   }

    public String getCombo3() {
        return combo3;
    }

    public String getUrl3() {
        return Url3;
    }

}
