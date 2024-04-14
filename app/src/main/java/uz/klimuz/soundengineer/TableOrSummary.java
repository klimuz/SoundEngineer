package uz.klimuz.soundengineer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TableOrSummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_or_summary);
    }

    public void sendSheet(View view) {
        StringBuilder builder = new StringBuilder();
        for (Channel channel: ChannelListActivity.channels){
            builder.append(channel.getNumber()).append(" ").append(channel.getRioNumber())
                    .append(" ").append(channel.getName()).append(" ").append(channel.getPickup()).append("\n");
        }
        String sheet = builder.toString();
        Intent sheetIntent = new Intent();
        sheetIntent.setAction(Intent.ACTION_SEND);
        sheetIntent.setType("text/plain");
        sheetIntent.putExtra(Intent.EXTRA_TEXT, sheet);
        Intent action_share = Intent.createChooser(sheetIntent, null);
        startActivity(action_share);
    }

    public void sendSummary(View view) {
    }
}