package specter.observer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RecyclerView_Adapter extends
        RecyclerView.Adapter<RecyclerViewHolder> {// Recyclerview will extend to
    // recyclerview adapter
    private ArrayList<Box> objects;
    private Context context;

    public RecyclerView_Adapter(Context context,
                                ArrayList<Box> arrayList) {
        this.context = context;
        this.objects = arrayList;

    }

    @Override
    public int getItemCount() {
        return (null != objects ? objects.size() : 0);

    }

    // элемент по позиции
    public Object getItem(int position) {
        return objects.get(position);
    }

    // товар по позиции
    public Box getBox(int position) {
        return ((Box) getItem(position));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        final Box model = objects.get(position);

        RecyclerViewHolder mainHolder = (RecyclerViewHolder) holder;// holder

        mainHolder.name.setText(model.getName());

        mainHolder.combo.setText(model.getCombo());

        mainHolder.imageview.setImageBitmap(model.getImage());


        /*holder.button.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parent_row = (View) v.getParent();
                RecyclerView lv = (RecyclerView) parent_row.getParent();


                /*Box p = getBox(position);
                Log.d("...", "position = " + position);
                Log.d("...", "name = " + p.name);
                Log.d("...", "price = " + p.combo);
                Log.d("...", "price = " + p.image);

                Log.d("...", "price = " + p.url);

                //lv.performClick();
                //lv.performItemClick(lv.getAdapter().getView(position, null, null), position, lv.getAdapter().getItemId(position));
            }
        });*/
    }

    // схема
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.item_row, viewGroup, false);
        RecyclerViewHolder listHolder = new RecyclerViewHolder(mainGroup);

        return listHolder;
    }

}