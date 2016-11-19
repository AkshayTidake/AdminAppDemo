package adservices.adminappdemo;

import android.content.Context;
import android.content.Intent;
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

    Context ctx;
   ArrayList<Data> arrayList=new ArrayList<>();

    public MyAdapter(ArrayList<Data> arrayList,Context ctx) {

        this.arrayList=arrayList;
        this.ctx=ctx;
    }

    @Override
    public MyAdapter.RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.textdata, parent, false);

        RViewHolder vh = new RViewHolder(v,ctx,arrayList);
        return vh;
    }

    @Override
    public void onBindViewHolder(RViewHolder holder, int position) {
        Data m=arrayList.get(position);
        holder.text1.setText(m.getName());
        holder.text2.setText(m.getDesc());
         holder.text3.setText(m.getDat());





    }
    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    public static class RViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView text1,text2,text3;
        ArrayList<Data> alist=new ArrayList<Data>();
        Context ctx;
        public RViewHolder(View v,Context ctx,ArrayList<Data> alist) {
            super(v);
            this.alist=alist;
            this.ctx=ctx;
            v.setOnClickListener(this);
            text1 = (TextView) v.findViewById(R.id.t1);
            text2 = (TextView) v.findViewById(R.id.t2);
            text3 = (TextView) v.findViewById(R.id.t3);




        }

        @Override
        public void onClick(View view) {

        int position= getAdapterPosition();
            Data alist=this.alist.get(position);
            Intent i=new Intent(this.ctx,FullEvent.class);
            i.putExtra("name",alist.getName());
            i.putExtra("desc",alist.getDesc());
            i.putExtra("dt",alist.getDat());
            this.ctx.startActivity(i);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
