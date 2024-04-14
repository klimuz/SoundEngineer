package uz.klimuz.soundengineer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditChannelActivity extends AppCompatActivity {

    private EditText editTextEnterChannelNumber;
    private EditText editTextChannelName;
    private Spinner spinnerPickup;
    private int channelNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_channel);
        editTextEnterChannelNumber = findViewById(R.id.editTextEnterChannelNumber);
        editTextChannelName = findViewById(R.id.editTextChannelName);
        spinnerPickup = findViewById(R.id.spinnerPickup);

        Bundle arguments = getIntent().getExtras();
        Channel channel;
        if (arguments != null){
            channel = (Channel) arguments.getSerializable(Channel.class.getSimpleName());
            channelNumber = channel.getNumber();
            editTextEnterChannelNumber.setText(String.valueOf(channelNumber));
            editTextChannelName.setText(channel.getName());
            String pickup = channel.getPickup();
            int pickupIndex;
            switch (pickup){
                case "condenser": pickupIndex = 1;
                break;
                case "D.I.box": pickupIndex = 2;
                break;
                case "wireless": pickupIndex = 3;
                break;
                case "DPA": pickupIndex = 4;
                break;
                case "HeadSet": pickupIndex = 5;
                break;
                case "BodyPack": pickupIndex = 6;
                break;
                default: pickupIndex = 0;
                break;
            }
            spinnerPickup.setSelection(pickupIndex);
        }
    }
    public void onClickCancel(View view) {
        Intent intent = new Intent(this, ChannelListActivity.class);
        startActivity(intent);
    }
    public void onClickOk(View view) {
        String numberString = editTextEnterChannelNumber.getText().toString().trim();
        String name = editTextChannelName.getText().toString().trim();
        String pickup = spinnerPickup.getSelectedItem().toString();
        if (isFilled(numberString, name)) {
            int number = Integer.parseInt(numberString);
            Channel channel = new Channel(number, name, pickup);
            ChannelListActivity.channels.remove(channelNumber - 1);
            if (number > ChannelListActivity.channels.size()) {
                fillEmptyChannels(ChannelListActivity.channels.size() - 1, number - 2);
            }
            ChannelListActivity.channels.add(number - 1, channel);
            ChannelListActivity.correctChannelNumbers();

            Intent intent = new Intent(this, ChannelListActivity.class);
            startActivity(intent);
        } else  {
            String toastFillAllFields = getString(R.string.warning_fill_all_fields);
            Toast.makeText(this, toastFillAllFields, Toast.LENGTH_SHORT).show();
        }
    }
    private void fillEmptyChannels(int from, int until){
        for (int i = from; i < until; i++){
            Channel channel = new Channel();
            channel.setNumber(ChannelListActivity.channels.size() + 1);
            ChannelListActivity.channels.add(channel);
        }
    }
    private boolean isFilled (String number, String name){
        return !number.isEmpty() && !name.isEmpty();
    }

}