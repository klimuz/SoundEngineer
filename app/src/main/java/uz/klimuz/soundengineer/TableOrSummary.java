package uz.klimuz.soundengineer;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.aspose.cells.Cell;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

import java.io.File;

public class TableOrSummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_or_summary);
        //needed to send exel file
//        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//        StrictMode.setVmPolicy(builder.build());
    }

    public void sendTextSheet(View view) {
        StringBuilder builder = new StringBuilder();
        builder.append(ChooseStageBoxActivity.projectName).append(" input list").append("\n");
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

    public void sendExcelSheet(View view) {
        Workbook workbook = SheetCreator.createExcelWorkbook();
        Intent action_share = Intent.createChooser(ShareExcel.saveExcel(workbook, this), null);
        action_share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(action_share);
        }catch (Exception e){
            Log.i("myLog", e.toString());
        }
    }
    public void sendSummary(View view) {
        int dynamic = 0;
        int condenser = 0;
        int di = 0;
        int wireless = 0;
        int dpa = 0;
        int headSet = 0;
        int bodyPack = 0;
        for (Channel channel: ChannelListActivity.channels){
            if (!channel.getName().equals("---")) {
                switch (channel.getPickup()) {
                    case "BodyPack":
                        bodyPack++;
                        break;
                    case "condenser":
                        condenser++;
                        break;
                    case "D.I.box":
                        di++;
                        break;
                    case "wireless":
                        wireless++;
                        break;
                    case "DPA":
                        dpa++;
                        break;
                    case "HeadSet":
                        headSet++;
                        break;
                    default:
                        dynamic++;
                        break;
                }
            }
        }
        @SuppressLint("DefaultLocale") String summary = String.format("dynamic = %d;\ncondenser = %d;\nD.I.boxes = %d;\nwireless = %d;\nDPA = %d;\nHeadSets = %d;\nBodyPacks = %d;\ncommon = %d",
                dynamic, condenser, di, wireless, dpa, headSet, bodyPack, ChannelListActivity.totalChannelsQuantity);
        Intent summaryIntent = new Intent();
        summaryIntent.setAction(Intent.ACTION_SEND);
        summaryIntent.setType("text/plain");
        summaryIntent.putExtra(Intent.EXTRA_TEXT, summary);
        Intent action_share = Intent.createChooser(summaryIntent, null);
        startActivity(action_share);
    }
}