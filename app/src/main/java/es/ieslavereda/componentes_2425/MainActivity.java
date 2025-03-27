package es.ieslavereda.componentes_2425;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private CheckBox miCheckBox;
    private TextView salidaCheckBox, salidaTextView;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this;
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        miCheckBox = findViewById(R.id.miCheckBox);
        salidaCheckBox = findViewById(R.id.salidaCheckBox);
        salidaTextView = findViewById(R.id.salida);
        radioGroup = findViewById(R.id.radioGroup);

        miCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(miCheckBox.isChecked()){
                    salidaCheckBox.setTextColor(ContextCompat.getColor(context,R.color.rojo));
                    salidaCheckBox.setText("CheckBox activado");
//                    salidaCheckBox.setVisibility(View.VISIBLE);
//                    salidaSpinner.setVisibility(View.VISIBLE);
                }
                else {
                    salidaCheckBox.setTextColor(ContextCompat.getColor(context,R.color.black));
                    salidaCheckBox.setText("CheckBox desactivado");
//                    salidaCheckBox.setVisibility(View.INVISIBLE);
//                    salidaSpinner.setVisibility(View.GONE);
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.radioButtonPerro)
                    salidaTextView.setText(R.string.perro);
                else if(checkedId==R.id.radioButtonGata)
                    salidaTextView.setText(R.string.gata);
                else
                    salidaTextView.setText(R.string.pollo);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        //nuestros datos a guardar en un objecto de la clase Bundle
    }



}