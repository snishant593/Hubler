package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.nishant.hubbler.R;

import java.util.ArrayList;

public class Showdata extends RecyclerView.Adapter<Showdata.ShowViewHolder> {

    ArrayList<String> namelist;

    public Showdata(ArrayList<String> namelist, Context context) {
        this.namelist = namelist;
    }

    @Override
    public Showdata.ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.name_row, parent, false);
        ShowViewHolder viewHolder = new ShowViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Showdata.ShowViewHolder holder, int position) {
       // holder.image.setImageResource(R.drawable.planetimage);
       holder.text.setText(namelist.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public static class ShowViewHolder extends RecyclerView.ViewHolder {

        protected TextView text;

        public ShowViewHolder(View itemView) {
            super(itemView);
            text= (TextView) itemView.findViewById(R.id.displayname);


        }
    }
}