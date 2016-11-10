package specter.observer;

public class News {
    public String Date;
    public String Text;
    public String Time;
    public String IMGPatch;
    public String Patch;

    News(String inDate, String inText, String inTime, String inIMGPatch,String inPatch) {
        Date = inDate;
        Text = inText;
        Time = inTime;
        IMGPatch = inIMGPatch;
        Patch = inPatch;
    }

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

    public String getIMGPatch() {
        return IMGPatch;
    }
    public void setIMGPatch(String IMGPatch) {
        this.IMGPatch = IMGPatch;
    }

    @Override
    public String toString()	{
        return "Date : " + Date + "; Text : " + Text + "; Time : " +  Time + "; IMGPatch : " + IMGPatch;
    }
}