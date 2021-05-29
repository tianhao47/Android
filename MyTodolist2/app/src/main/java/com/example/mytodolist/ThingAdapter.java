package com.example.mytodolist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class ThingViewHolder extends RecyclerView.ViewHolder{
    TextView Title;
    TextView Thing;
    TextView Date;
    Button Delete,Update;

    public ThingViewHolder(View view){
        super(view);
        Title=view.findViewById(R.id.thing_title);
        Thing=view.findViewById(R.id.ting_content);
        Date=view.findViewById(R.id.date);
        Delete=view.findViewById(R.id.delete);
        Update=view.findViewById(R.id.update);
    }
}


public class ThingAdapter extends RecyclerView.Adapter<ThingViewHolder> {
    private List<Thing> thingList;
    private Context context;
    private ButtonClick buttonClick;
    private ThingViewHolder holder;
    private int position;


    public ThingAdapter(List<Thing> things,Context context){
        this.context=context;
        this.thingList=things;
    }
    public interface ButtonClick{
        void onClick(View view,int position);
    }
    public void setButtonClick(ButtonClick buttonClick){
        this.buttonClick=buttonClick;
    }


    @NonNull
    @Override
    public ThingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  MainActivity.layoutInflater.inflate(R.layout.list,parent,false);
        return new ThingViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ThingViewHolder holder, int position) {

        this.holder = holder;
        this.position = position;

        Thing thing = thingList.get(position);
        holder.Thing.setText(thing.getContent().toString());
        holder.Title.setText(thing.getTitle());
        holder.Date.setText(thing.getDate());

        holder.Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonClick != null) {
                    buttonClick.onClick(v, position);
                }
            }
        });

        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonClick != null) {
                    buttonClick.onClick(v, position);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return thingList.size();
    }


}
