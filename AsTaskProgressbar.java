package specter.observer;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

public  class AsTaskProgressbar extends AsyncTask<Integer, Integer, String> {
    ProgressBar PB;
    Context t;
    int Width;
    int stepWidth;
    int progrW=0;

    public AsTaskProgressbar(Context tt, int width, ProgressBar _PB) {
        t = tt;
        Width = width;
        PB = _PB;
    }

    @Override
    protected String doInBackground(Integer... params) {
        for (int i = 0;i<4 ;i++)
        {
            try {
                Thread.sleep(1000);
                publishProgress();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return "Task Completed.";
    }
    @Override
    protected void onPostExecute(String result) {
        PB.setProgress(0);
    }
    @Override
    protected void onPreExecute() {
        PB.setProgress(0);
        stepWidth = Width/4;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        progrW = progrW + stepWidth;
        PB.setProgress(progrW);
    }
}