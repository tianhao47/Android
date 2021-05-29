package com.example.mytodolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.ClipData;
import android.content.DialogInterface;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.sql.ClientInfoStatus;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static Things things;
    private List<Thing> thingsList;
    private EditText TitleInput;
    private EditText ThingInput;
    private ThingAdapter thingAdapter;
    public static LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add = (Button) findViewById(R.id.add);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        TitleInput = (EditText) findViewById(R.id.myEditText_title);
        ThingInput = (EditText) findViewById(R.id.myEditText_thing);

        things = Room.databaseBuilder(getApplicationContext(), Things.class,
                "Things").addMigrations().allowMainThreadQueries().build();

        thingsList = new ArrayList<Thing>();
        thingsList = things.thingDao().getAll();
        //System.out.println(thingsList);


        thingAdapter = new ThingAdapter(thingsList, this);
        recyclerView.setAdapter(thingAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        layoutInflater = getLayoutInflater();

        thingAdapter.setButtonClick(new ThingAdapter.ButtonClick() {
            @Override
            public void onClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.update:
                        OnUpdate(position);
                        break;

                    case R.id.delete:
                        OnDelete(position);
                        break;
                    default:
                        break;
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog();
            }
        });
    }







        private void addDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = View.inflate(MainActivity.this, R.layout.thing, null);
            TitleInput = view.findViewById(R.id.myEditText_title);
            ThingInput = view.findViewById(R.id.myEditText_thing);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Thing item = getThing();
                    thingsList.add(item);
                    thingAdapter.notifyItemInserted(thingAdapter.getItemCount());
                    things.thingDao().insert(item);
                    dialog.dismiss();
                }
            });
            builder.setTitle("item").setView(view).create().show();
        }


    private void OnUpdate(int position) {
       // Thing thing = thingsList.get(position);
        Thing thing = thingsList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialog = View.inflate(MainActivity.this,R.layout.thing,null);
        EditText newTitle = dialog.findViewById(R.id.myEditText_title);
        EditText newContent = dialog.findViewById(R.id.myEditText_thing);
        builder.setPositiveButton("update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                thing.setTitle(newTitle.getText().toString());
                thing.setContent(newContent.getText().toString());
                thing.setDate(getTime());
                thingsList.set(position, thing);
                thingAdapter.notifyDataSetChanged();
                things.thingDao().update(thing);
                dialog.dismiss();
            }
        });
        builder.setView(dialog).create().show();
    }

    private void OnDelete(int position) {
        things.thingDao().delete(thingsList.get(position));
        thingsList.remove(position);
        thingAdapter.notifyItemRemoved(position);

    }

    private Thing getThing(){
        Thing thing=new Thing();
        String time =getTime();
        thing.setTitle(TitleInput.getText().toString());
        thing.setContent(ThingInput.getText().toString());
        thing.setDate(time);
        return thing;
    }
    private String getTime(){
        long newTime =System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String time=simpleDateFormat.format(newTime);
        return time;
    }


}