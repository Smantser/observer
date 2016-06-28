package com.example.specter.apptest2interfeis;

public class News {
    public String Date;
    public String Text;
    public String Time;
    public String IzobrPatch;

    public String getDate() {
        return Date;
    }
    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getText() {
        return Text;
    }
    public void setText(String Text) {
        this.Text = Text;
    }

    public String getTime() {
        return Time;
    }
    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getIzobrPatch() {
        return IzobrPatch;
    }
    public void setIzobrPatch(String IzobrPatch) {
        this.IzobrPatch = IzobrPatch;
    }

    @Override
    public String toString()	{
        return "Date : " + Date + "; Text : " + Text + "; Time : " +  Time + "; IzobrPatch : " + IzobrPatch;
    }
}