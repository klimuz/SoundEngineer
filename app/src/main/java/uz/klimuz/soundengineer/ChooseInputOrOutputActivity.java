package uz.klimuz.soundengineer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseInputOrOutputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_input_or_output);
    }
    public void onClickInputList(View view) {
        Intent intent = new Intent(this, ChannelListActivity.class);
        startActivity(intent);
    }
    public void onClickOutputList(View view) {
        Toast.makeText(this, "For future use", Toast.LENGTH_SHORT).show();
    }
}