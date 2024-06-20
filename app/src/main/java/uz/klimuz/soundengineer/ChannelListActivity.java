package uz.klimuz.soundengineer;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ChannelListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewChannels;
    private TextView textViewTotal;
    private FloatingActionButton buttonUndo;
    public static int totalChannelsQuantity = 0;
    public static final ArrayList<Channel> channels = new ArrayList<>();
    public static final ArrayList<Channel> backup = new ArrayList<>();
    private ChannelsAdapter adapter;
    private ChannelsDBHelper dbHelper;
    private SQLiteDatabase database;
    private boolean isNew = false;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_list);
        bundle = getIntent().getExtras();
        isNew = bundle.getBoolean("isNew", false);
        recyclerViewChannels = findViewById(R.id.recyclerViewChannels);
        textViewTotal = findViewById(R.id.textViewTotal);
        buttonUndo = findViewById(R.id.buttonUndo);
        if (backup.isEmpty()){
            buttonUndo.setEnabled(false);
        }
        dbHelper = new ChannelsDBHelper(this);
        database = dbHelper.getWritableDatabase();
        if (channels.isEmpty()) {
            if (isNew) {
                database.delete(ChannelsContract.ChannelsEntry.TABLE_NAME, null, null);
            } else {
                Cursor cursor = database.query(ChannelsContract.ChannelsEntry.TABLE_NAME, null, null, null, null, null, null);
                boolean isDBEmpty = cursor.getCount() > 0;
                while (cursor.moveToNext()) {
                    int number = cursor.getInt(cursor.getColumnIndex(ChannelsContract.ChannelsEntry.COLUMN_NUMBER));
                    String rioName = cursor.getString(cursor.getColumnIndex(ChannelsContract.ChannelsEntry.COLUMN_RIO_NAME));
                    String rioNumber = cursor.getString(cursor.getColumnIndex(ChannelsContract.ChannelsEntry.COLUMN_RIO_NUMBER));
                    String name = cursor.getString(cursor.getColumnIndex(ChannelsContract.ChannelsEntry.COLUMN_NAME));
                    String pickup = cursor.getString(cursor.getColumnIndex(ChannelsContract.ChannelsEntry.COLUMN_PICKUP));
                    String note = cursor.getString(cursor.getColumnIndex(ChannelsContract.ChannelsEntry.COLUMN_NOTE));
                    Channel channel = new Channel();
                    channel.setNumber(number);
                    channel.setRioName(rioName);
                    channel.setRioNumber(rioNumber);
                    channel.setName(name);
                    channel.setPickup(pickup);
                    channel.setNote(note);
                    channels.add(channel);
                }
                cursor.close();
                correctChannelNumbers();
            }
        }
        database.delete(ChannelsContract.ChannelsEntry.TABLE_NAME, null, null);
        textViewTotal.setText(String.valueOf(totalChannelsQuantity));
        adapter = new ChannelsAdapter(channels);
        recyclerViewChannels.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewChannels.setAdapter(adapter);
        adapter.setOnChannelClickListener(new ChannelsAdapter.OnChannelClickListener() {
            @Override
            public void onChannelClick(int position) {
            }

            @Override
            public void onLongClick(int position) {
            openChannelEditor(position);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                backup.clear();
                backup.addAll(channels);
                buttonUndo.setEnabled(true);
                switch (i){
                    case 4: remove(viewHolder.getAdapterPosition());
                    break;
                    case 8: removeAndTrim(viewHolder.getAdapterPosition());
                    break;
                }
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewChannels);
    }
    @Override
    protected void onStop(){
        super.onStop();
        saveAppState();
        arrayListToDatabase();
    }
    private void openChannelEditor(int position){
        Channel channel = channels.get(position);
        Intent intent = new Intent(this, EditChannelActivity.class);
        intent.putExtra(Channel.class.getSimpleName(), channel);
        startActivity(intent);
    }
    private void remove(int position){
        channels.remove(position);
        Channel channel = new Channel();
        channels.add(position, channel);
        correctChannelNumbers();
        adapter.notifyDataSetChanged();
    }
    private void removeAndTrim(int position){
        channels.remove(position);
        correctChannelNumbers();
        textViewTotal.setText(String.valueOf(totalChannelsQuantity));
        adapter.notifyDataSetChanged();
    }

    public void onClickAddChannel(View view) {
        Intent intent = new Intent(this, AddChannelActivity.class);
        startActivity(intent);
    }

    public void onClickShare(View view) {
        arrayListToDatabase();
        saveAppState();
        Intent intent = new Intent(this, TableOrSummary.class);
        startActivity(intent);
    }

    public void arrayListToDatabase (){
        database.delete(ChannelsContract.ChannelsEntry.TABLE_NAME, null, null);
        for (Channel channel : channels){
            ContentValues contentValues = new ContentValues();
            contentValues.put(ChannelsContract.ChannelsEntry.COLUMN_NUMBER, channel.getNumber());
            contentValues.put(ChannelsContract.ChannelsEntry.COLUMN_RIO_NAME, channel.getRioName());
            contentValues.put(ChannelsContract.ChannelsEntry.COLUMN_RIO_NUMBER,channel.getRioNumber());
            contentValues.put(ChannelsContract.ChannelsEntry.COLUMN_NAME, channel.getName());
            contentValues.put(ChannelsContract.ChannelsEntry.COLUMN_PICKUP, channel.getPickup());
            contentValues.put(ChannelsContract.ChannelsEntry.COLUMN_NOTE, channel.getNote());
            database.insert(ChannelsContract.ChannelsEntry.TABLE_NAME, null, contentValues);
        }
    }

    public static void correctChannelNumbers(){
        RioDistributor rioDistributor = new RioDistributor();
        for (Channel channel: channels){
            int channelNumber = channels.indexOf(channel) + 1;
            channel.setNumber(channelNumber);
            channel.setRioName(rioDistributor.distributeStageBoxes(channelNumber).get(0));
            channel.setRioNumber(rioDistributor.distributeStageBoxes(channelNumber).get(1));
            totalChannelsQuantity = channels.size();
        }
    }

    public void onClickUndo(View view) {
        channels.clear();
        channels.addAll(backup);
        backup.clear();
        adapter.notifyDataSetChanged();
        totalChannelsQuantity = channels.size();
        textViewTotal.setText(String.valueOf(totalChannelsQuantity));
        buttonUndo.setEnabled(false);
    }
    private void  saveAppState(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putString("projectName", ChooseStageBoxActivity.projectName).apply();
        preferences.edit().putInt("spinner1Position", ChooseStageBoxActivity.spinner1Position).apply();
        preferences.edit().putInt("spinner2Position", ChooseStageBoxActivity.spinner2Position).apply();
        preferences.edit().putInt("spinner3Position", ChooseStageBoxActivity.spinner3Position).apply();
        preferences.edit().putInt("spinner4Position", ChooseStageBoxActivity.spinner4Position).apply();
        preferences.edit().putInt("spinner5Position", ChooseStageBoxActivity.spinner5Position).apply();
        preferences.edit().putInt("spinner6Position", ChooseStageBoxActivity.spinner6Position).apply();
    }
}