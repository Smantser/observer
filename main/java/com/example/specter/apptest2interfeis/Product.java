package com.example.specter.apptest2interfeis;

import android.graphics.Bitmap;

// класс, описывающий товар
// Тут все просто – только конструктор и элементы класса. Не заморачиваюсь с доступом и методами Set/Get, чтобы не усложнять код.
public class Product {

    String name;
    String price;
    //int image;
    Bitmap image;
    //boolean box;


    Product(String _describe, String _price, Bitmap _image) {
        name = _describe;
        price = _price;
        image = _image;
        //box = _box;
    }
}