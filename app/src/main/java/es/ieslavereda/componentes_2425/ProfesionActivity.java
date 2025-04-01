package es.ieslavereda.componentes_2425;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ieslavereda.componentes_2425.model.Profesion;

public class ProfesionActivity extends AppCompatActivity {

    private TextView mensajeTextView;
    private Button aceptar, cancelar;
    private Spinner profesionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profesion_layout);

        Bundle extras = getIntent().getExtras();
        String nombre = extras.getString("nombre");
        String apellido = extras.getString("apellido");

        mensajeTextView = findViewById(R.id.mensajeTextView);
        aceptar = findViewById(R.id.okButton);
        cancelar = findViewById(R.id.cancelarButton);
        profesionSpinner = findViewById(R.id.spinnerProfesion);

        mensajeTextView.setText("Hola " + nombre + " " + apellido + " elige una profesión");

        ArrayAdapter<Profesion> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,Profesion.values());
        profesionSpinner.setAdapter(adapter);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("profesion",
                        (Profesion) profesionSpinner.getSelectedItem());
                setResult(RESULT_OK,intent);
                finish();
            }
        });





    }

}
