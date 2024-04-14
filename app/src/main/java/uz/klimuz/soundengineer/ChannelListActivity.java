package uz.klimuz.soundengineer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChannelListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewChannels;
    private TextView textViewTotalChannelsDigits;
    public static int totalCannelsQuantity = 0;
    public static final ArrayList<Channel> channels = new ArrayList<>();
    ChannelsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_list);
        recyclerViewChannels = findViewById(R.id.recyclerViewChannels);
        textViewTotalChannelsDigits = findViewById(R.id.textViewTotalChannelsDigits);
        if (channels.isEmpty()) {
            channels.add(new Channel(1, "name1", "condenser"));
            channels.add(new Channel(2, "name2", "D.I.box"));
            channels.add(new Channel(3, "name3", "wireless"));
            channels.add(new Channel(4, "name4", "DPA"));
            channels.add(new Channel(5, "name5", "HeadSet"));
            channels.add(new Channel(6, "name6", "BodyPack"));
            channels.add(new Channel(7, "name7", "dynamic"));
            channels.add(new Channel(8, "name8", "dynamic"));
            channels.add(new Channel(9, "name9", "dynamic"));
            channels.add(new Channel(10, "name10", "dynamic"));
            channels.add(new Channel(11, "name11", "dynamic"));
            channels.add(new Channel(12, "name12", "dynamic"));
            channels.add(new Channel(13, "name13", "dynamic"));
            channels.add(new Channel(14, "name14", "dynamic"));
            channels.add(new Channel(15, "name15", "dynamic"));
            channels.add(new Channel(16, "name16", "dynamic"));
            channels.add(new Channel(17, "name17", "dynamic"));
            channels.add(new Channel(18, "name18", "dynamic"));
            channels.add(new Channel(19, "name19", "dynamic"));
            channels.add(new Channel(20, "name20", "dynamic"));
            channels.add(new Channel(21, "name21", "dynamic"));
            channels.add(new Channel(22, "name22", "dynamic"));
            channels.add(new Channel(23, "name23", "dynamic"));
            channels.add(new Channel(24, "name24", "dynamic"));
            channels.add(new Channel(25, "name25", "dynamic"));
            channels.add(new Channel(26, "name26", "dynamic"));
            channels.add(new Channel(27, "name27", "dynamic"));
            channels.add(new Channel(28, "name28", "dynamic"));
            channels.add(new Channel(29, "name29", "dynamic"));
            channels.add(new Channel(30, "name30", "dynamic"));
            channels.add(new Channel(31, "name31", "dynamic"));
            channels.add(new Channel(32, "name32", "dynamic"));
            channels.add(new Channel(33, "name33", "dynamic"));
            channels.add(new Channel(34, "name34", "dynamic"));
            channels.add(new Channel(35, "name35", "dynamic"));
        }
        correctChannelNumbers();
        textViewTotalChannelsDigits.setText(String.valueOf(totalCannelsQuantity));
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
        textViewTotalChannelsDigits.setText(String.valueOf(totalCannelsQuantity));
        adapter.notifyDataSetChanged();
    }

    public void onClickAddChannel(View view) {
        Intent intent = new Intent(this, AddChannelActivity.class);
        startActivity(intent);
    }

    public void onClickShare(View view) {
        Intent intent = new Intent(this, TableOrSummary.class);
        startActivity(intent);
    }

    public static void correctChannelNumbers(){
        RioDistributor rioDistributor = new RioDistributor();
        for (Channel channel: channels){
            int channelNumber = channels.indexOf(channel) + 1;
            channel.setNumber(channelNumber);
            channel.setRioName(rioDistributor.distributeStageBoxes(channelNumber).get(0));
            channel.setRioNumber(rioDistributor.distributeStageBoxes(channelNumber).get(1));
            totalCannelsQuantity = channels.size();

        }
    }
}