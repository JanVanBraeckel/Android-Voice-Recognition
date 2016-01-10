package com.example.gebruiker.speechtotext;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_SPEECH = 1;

    @Bind(R.id.txtSpeechInput)
    TextView txtSpeech;
    @Bind(R.id.txtSpeechResponse)
    TextView txtSpeechResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnStartSpeech)
    public void btnStartSpeechOnClick() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SPEECH);
        } else {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.notSupported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    String res = result.get(0);

                    txtSpeech.setText(res);

                    if(res.equals("Android is cool")){
                        txtSpeechResponse.setText(R.string.knowit);
                    }
                }
                break;
            }
        }
    }
}
