package specter.observer;


import android.graphics.Bitmap;

public class Box {
    String name;
    String combo;
    Bitmap image;
    String Url;


    Box(String _describe, String _combo, Bitmap _image,String _Url) {
        name = _describe;
        combo = _combo;
        image = _image;
        Url = _Url;
    }
    public String getName() {
        return name;
    }

    public String getCombo() {
        return combo;
    }

    public String getUrl() {
        return Url;
    }

    public Bitmap getImage() {
        return image;
    }
}
