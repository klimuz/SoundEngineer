package uz.klimuz.soundengineer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseStageBoxActivity extends AppCompatActivity {
    private Spinner stageBox1;
    private Spinner stageBox2;
    private Spinner stageBox3;
    private Spinner stageBox4;
    private Spinner stageBox5;
    private Spinner stageBox6;

    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private EditText editTextEnterName;

    public static int spinner1Position;
    public static int spinner2Position;
    public static int spinner3Position;
    public static int spinner4Position;
    public static int spinner5Position;
    public static int spinner6Position;

    public static boolean isLeftOdd1;
    public static boolean isLeftOdd2;
    public static boolean isLeftOdd3;
    public static boolean isLeftOdd4;
    public static boolean isLeftOdd5;
    public static boolean isLeftOdd6;
    public static String projectName = "";
    private boolean isNew = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        isNew = bundle.getBoolean("isNew", false);
        setContentView(R.layout.activity_choose_stagebox);
        stageBox1 = findViewById(R.id.spinnerFirstStageBox);
        stageBox2 = findViewById(R.id.spinnerSecondStageBox);
        stageBox3 = findViewById(R.id.spinnerThirdStageBox);
        stageBox4 = findViewById(R.id.spinnerFourthStageBox);
        stageBox5 = findViewById(R.id.spinnerFifthStageBox);
        stageBox6 = findViewById(R.id.spinnerSixthStageBox);

        checkBox1 = findViewById(R.id.checkBoxLeftIsOdd1);
        checkBox2 = findViewById(R.id.checkBoxLeftIsOdd2);
        checkBox3 = findViewById(R.id.checkBoxLeftIsOdd3);
        checkBox4 = findViewById(R.id.checkBoxLeftIsOdd4);
        checkBox5 = findViewById(R.id.checkBoxLeftIsOdd5);
        checkBox6 = findViewById(R.id.checkBoxLeftIsOdd6);
        editTextEnterName = findViewById(R.id.editTextEnterName);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        projectName = preferences.getString("projectName", projectName);
        spinner1Position = preferences.getInt("spinner1Position", 0);
        spinner2Position = preferences.getInt("spinner2Position", 0);
        spinner3Position = preferences.getInt("spinner3Position", 0);
        spinner4Position = preferences.getInt("spinner4Position", 0);
        spinner5Position = preferences.getInt("spinner5Position", 0);
        spinner6Position = preferences.getInt("spinner6Position", 0);

        if (isNew){
            projectName = "";
            editTextEnterName.setText(projectName);
        }else {
            editTextEnterName.setText(projectName);
        }
        stageBox1.setSelection(spinner1Position);
        stageBox2.setSelection(spinner2Position);
        stageBox3.setSelection(spinner3Position);
        stageBox4.setSelection(spinner4Position);
        stageBox5.setSelection(spinner5Position);
        stageBox6.setSelection(spinner6Position);

        stageBox1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner1Position = i;
                if (i == 0) {
                    stageBox2.setSelection(0);
                    stageBox2.setEnabled(false);
                    stageBox3.setSelection(0);
                    stageBox3.setEnabled(false);
                    stageBox4.setSelection(0);
                    stageBox4.setEnabled(false);
                    stageBox5.setSelection(0);
                    stageBox5.setEnabled(false);
                    stageBox6.setSelection(0);
                    stageBox6.setEnabled(false);
                } else {
                    stageBox2.setEnabled(true);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stageBox2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner2Position = i;
                if (i == 0) {
                    stageBox3.setSelection(0);
                    stageBox3.setEnabled(false);
                    stageBox4.setSelection(0);
                    stageBox4.setEnabled(false);
                    stageBox5.setSelection(0);
                    stageBox5.setEnabled(false);
                    stageBox6.setSelection(0);
                    stageBox6.setEnabled(false);
                } else {
                    stageBox3.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stageBox3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner3Position = i;
                if (i == 0) {
                    stageBox4.setSelection(0);
                    stageBox4.setEnabled(false);
                    stageBox5.setSelection(0);
                    stageBox5.setEnabled(false);
                    stageBox6.setSelection(0);
                    stageBox6.setEnabled(false);
                } else {
                    stageBox4.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stageBox4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner4Position = i;
                if (i == 0) {
                    stageBox5.setSelection(0);
                    stageBox5.setEnabled(false);
                    stageBox6.setSelection(0);
                    stageBox6.setEnabled(false);
                } else {
                    stageBox5.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stageBox5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner5Position = i;
                if (i == 0) {
                    stageBox6.setSelection(0);
                    stageBox6.setEnabled(false);
                } else {
                    stageBox6.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void onClickAcceptStageBoxes(View view) {
        spinner6Position = stageBox6.getSelectedItemPosition();
        projectName = editTextEnterName.getText().toString();

        isLeftOdd1 = checkBox1.isChecked();
        isLeftOdd2 = checkBox2.isChecked();
        isLeftOdd3 = checkBox3.isChecked();
        isLeftOdd4 = checkBox4.isChecked();
        isLeftOdd5 = checkBox5.isChecked();
        isLeftOdd6 = checkBox6.isChecked();


        Intent intent = new Intent(this, ChannelListActivity.class);
        intent.putExtra("isNew", isNew);
        startActivity(intent);
    }
}