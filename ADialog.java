package specter.observer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.IOException;

public class ADialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adialog);
        try {
                create();
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private void create()
    {
        String Patch;
        String Name;
        Patch = getIntent().getExtras().getString("Patch");
        Name = getIntent().getExtras().getString("Name");

        setTitle(Name);
        WebView WebView = (WebView) findViewById(R.id.webView2);
        WebView.getSettings().setJavaScriptEnabled(true);
        WebView.getSettings().setAllowFileAccess(true);
        WebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        WebView.setVerticalScrollBarEnabled(false);
        WebView.setHorizontalScrollBarEnabled(false);
        WebView.getSettings().setUseWideViewPort(true);

        new AsyncTaskPage(WebView,Patch).execute();
    }


    // AsyncTask Parser
    class AsyncTaskPage extends AsyncTask<Void, Void, Void> {

        WebView myWebView;
        String url;
        ParserTYTpage parser;

        public AsyncTaskPage(WebView myWebView1,String url1) {
            myWebView = myWebView1;
            url = url1;
        }

        protected Void doInBackground(Void... params) {

            try {
                parser = new ParserTYTpage(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            String htmlText = "<html><head> <style>" +
                    ".layer { overflow-x:hidden;}" +
                    "figure {display: inline; height: auto; max-width: 90%; padding: 0px; \n" +
                    "    float: left; /* Блоки выстраиваются по горизонтали */\n" +
                    "    text-align: center; /* Выравнивание по центру */\n" +
                    "   }" +
                    "img{display: inline; height: auto; max-width: 100%;}</style>" +
                    " </head><body>" + parser.text + " <script type=\"text/javascript\">/* <![CDATA[ */\n" +
                    "function uppodStartsReport(playerID){\n" +
                    "\tuppodSend(playerID,'get[m]','uppodManager.setType');\n" +
                    "\tuppodSend(playerID,'get[status]','uppodManager.setStatus');\n" +
                    "\tuppodManager.setPlayerId(playerID);\n" +
                    "\tuppodSend(playerID,'get[preroll]','uppodManager.setPrerol');\n" +
                    "\tif(uppodManager.getType() == 'video'){\n" +
                    "\t\tif(uppodManager.getPrerol() == null) sendTextAds(); else setTimeout(sendTextAds,10000);\n" +
                    "\t}\n" +
                    "\tuppodSend(playerID,'get[file]','uppodManager.setFile');\n" +
                    "}\n" +
                    "function uppodOnPlay(playerID){\n" +
                    "\tif(!uppodManager.getFile())\n" +
                    "\t\tuppodSend(playerID,'get[file]','uppodManager.setFile');\n" +
                    "}\n" +
                    "function sendTextAds(){\n" +
                    "\tvar playerID = uppodManager.getPlayerId();\n" +
                    "\t$.getJSON(\"http://www.tut.by/player.jsp?nid=\"+\"503765\"+\"&addon=textAdvert&jsoncallback=?\",function(json,sStatus){\n" +
                    "\t\tif((typeof json != \"undefined\")&&(typeof json.b != \"undefined\")&&(json.b!=\"\")) uppodSend(playerID,\"text:\"+json.b, \"\");\n" +
                    "\t});\n" +
                    "}\n" +
                    "</script> </body></html>";

            myWebView.getSettings().setUseWideViewPort(true);
            myWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            myWebView.loadDataWithBaseURL(null, htmlText, "text/html", "en_US", null);
        }
    }
}
