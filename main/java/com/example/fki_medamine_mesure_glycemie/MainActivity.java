package com.example.fki_medamine_mesure_glycemie;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public void init() {
        etValeur = findViewById(R.id.etValeur);
        rbtOui = findViewById(R.id.rbtOui);
        rbtNon = findViewById(R.id.rbtNon);
        sbage = findViewById(R.id.sbAge);
        tvage = findViewById(R.id.tvage);
        tvres = findViewById(R.id.result);
        consulterBtn = findViewById(R.id.btnConsulter);
    }

    private EditText etValeur;
    private RadioButton rbtOui, rbtNon;
    private SeekBar sbage;
    private TextView tvage,tvres;
    private Button consulterBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        sbage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Information", "onProgressChanged " + progress);
                //affichage dans le log de android studio pour avoir les changements détectés sur le seekbar
                tvage.setText("Votre age : " + progress);
                //mise a jour du textview par la valeur : progress a chaque changement
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        consulterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculer(view);
            }
        });
    }

    public void calculer(View view) {
        Log.i("Inforamtion", "Button cliqué: ");
        double val = Double.parseDouble(etValeur.getText().toString());
        // valeur mésurée
        int age = sbage.getProgress();
        // age
        if (etValeur.getText().toString().isEmpty()) {
            String message = "Valeur de glycémie donnée est invalide";
            int duration = Toast.LENGTH_SHORT;
            Context context = getApplicationContext();
            Toast.makeText(context, message, duration).show();
        } else if (age == 0) {
            String message = "Age donné est invalid";
            int duration = Toast.LENGTH_SHORT;
            Context context = getApplicationContext();
            Toast.makeText(context, message, duration).show();
        } else {
            if (rbtOui.isChecked()) {
                // avant repas
                if (age > 13) {
                    // age > 13
                    if (val > 7.2) {
                        tvres.setText("Niveau de Glycémie est top élevé");
                    } else if (val < 5.0) {
                        tvres.setText("Niveau de Glycémie est trop bas");
                    } else {
                        tvres.setText("Niveau de Glycémie est normal");
                    }
                } else if (age < 6) {
                    // age < 6
                    if (val > 10.0) {
                        tvres.setText("Niveau de Glycémie est top élevé");
                    } else if (val < 5.5) {
                        tvres.setText("Niveau de Glycémie est trop bas");
                    } else {
                        tvres.setText("Niveau de Glycémie est normal");
                    }
                } else {
                    // 6 < age < 13
                    if (val > 10.0) {
                        tvres.setText("Niveau de Glycémie est top élevé");
                    } else if (val < 5.0) {
                        tvres.setText("Niveau de Glycémie est trop bas");
                    } else {
                        tvres.setText("Niveau de Glycémie est normal");
                    }
                }
            } else {
                // apres repas
                if (val < 10.5) {
                    tvres.setText("Niveau de Glycémie est trop bas");
                } else {
                    tvres.setText("Niveau de Glycémie est normal");
                }
            }
        }
    }
}