package specter.observer;

/**
 * Created by SPECTER on 24.08.2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder  {
    // View holder for gridview recycler view as we used in listview
    public TextView name;
    public TextView combo;
    public ImageView imageview;
    public Button button;



    public RecyclerViewHolder(View view) {
        super(view);
        // Find all views ids

        this.name = (TextView) view
                .findViewById(R.id.tvDescr);

        this.combo = (TextView) view
                .findViewById(R.id.tvPrice);

        this.imageview = (ImageView) view
                .findViewById(R.id.imageView2);

        this.button = (Button) view
                .findViewById(R.id.button5);



    }
}