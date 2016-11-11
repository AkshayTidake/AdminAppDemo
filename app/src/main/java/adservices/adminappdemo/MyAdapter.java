package adservices.adminappdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Akshay on 10/29/2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RViewHolder> {


   ArrayList<Data> arrayList=new ArrayList<>();

    public MyAdapter(ArrayList<Data> arrayList) {

        this.arrayList=arrayList;

    }

    @Override
    public MyAdapter.RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.textdata, parent, false);

        RViewHolder vh = new RViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RViewHolder holder, int position) {
        Data m=arrayList.get(position);
        holder.text1.setText(m.getName());
        holder.text2.setText(m.getDesc());
        // holder.text3.setText(mDataset.get(position).getDate());
        //  holder.text4.setText(mDataset.get(position).getFromt());




    }
    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    public static class RViewHolder extends RecyclerView.ViewHolder {

        public TextView text1,text2;

        public RViewHolder(View v) {
            super(v);
            text1 = (TextView) v.findViewById(R.id.t1);
            text2 = (TextView) v.findViewById(R.id.t2);
          //  text3 = (TextView) v.findViewById(R.id.t3);
          //  text4 = (TextView) v.findViewById(R.id.t4);
          //  text5 = (TextView) v.findViewById(R.id.t5);



        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
