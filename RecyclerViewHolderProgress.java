package specter.observer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


public class RecyclerViewHolderProgress extends RecyclerView.ViewHolder {
    public ProgressBar PB;

    public RelativeLayout RL;

    public RecyclerViewHolderProgress(View view, int type) {
        super(view);
        this.RL = (RelativeLayout) view;
        if (type == 2) {
            SettingsAdapters s = new SettingsAdapters();
            int widthLarge = s.GetWidthLarge(RL.getContext());
            if(widthLarge > 1080)
            {
                this.RL.getLayoutParams().width = 1080;
                this.RL.getLayoutParams().height = 1080;
            }
            else
            {
                this.RL.getLayoutParams().width = widthLarge - 5;
                this.RL.getLayoutParams().height = widthLarge - 5;
            }
        }
        else if(type == 1)
        {
            SettingsAdapters s = new SettingsAdapters();
            int width = s.GetWidthLarge(RL.getContext());
            if(width > 1080)
            {
                this.RL.getLayoutParams().width = 1080;
                this.RL.getLayoutParams().height = 540;
            }
            else
            {
                this.RL.getLayoutParams().width = width - 5;
                this.RL.getLayoutParams().height = width/2 - 3;
            }
        }


        this.PB = (ProgressBar) view
                .findViewById(R.id.progressBaritem);
    }
}
