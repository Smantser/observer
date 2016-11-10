package specter.observer;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;


public class AdapterProgress extends
        RecyclerView.Adapter<RecyclerViewHolderProgress> {

    private ArrayList<ProgrID> objects;
    private Context context;
    int style;

    public AdapterProgress(Context context,
                           ArrayList<ProgrID> arrayList,int _style) {
        this.context = context;
        this.objects = arrayList;
        this.style = _style;
    }

    @Override
    public int getItemCount() {
        return (null != objects ? objects.size() : 0);

    }

    // элемент по позиции
    public Object getItem(int position) {
        return objects.get(position);
    }
    public ProgrID getID(int position) {
        return ((ProgrID) getItem(position));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderProgress holder, final int position) {
        /*final ProgrID model = objects.get(position);
        RecyclerViewHolderProgress mainHolder = (RecyclerViewHolderProgress) holder;
        Settings s = new Settings();
        s.SetIDprogress(context,mainHolder.PB.getId());*/
    }

    // схема
    @Override
    public RecyclerViewHolderProgress onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.item_progress, viewGroup, false);

        RecyclerViewHolderProgress listHolder = new RecyclerViewHolderProgress(mainGroup,style);

        return listHolder;
    }
}
