package es.ieslavereda.componentes_2425;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.ieslavereda.componentes_2425.model.Profesion;
import es.ieslavereda.componentes_2425.model.Usuario;

public class MainActivity extends AppCompatActivity {

    private CheckBox miCheckBox;
    private TextView salidaCheckBox, salidaTextView;
    private RadioGroup radioGroup;
    private Spinner spinner;
    private EditText nombre, apellidos;
    private Button anyadir, salirButton, acercaDeButton;
    private List<Usuario> usuarios;


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
        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellido);
        spinner = findViewById(R.id.spinner);
        anyadir = findViewById(R.id.anyadir);
        acercaDeButton = findViewById(R.id.acercaDeButton);
        salirButton = findViewById(R.id.salirButton);

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

        if(savedInstanceState==null){
            usuarios = new ArrayList<>();
            usuarios.add(new Usuario("Joaquin","Alonso Saiz", Profesion.FULL));
            usuarios.add(new Usuario("Xavier","Rosillo Guerrero", Profesion.BACK));
        } else {
            usuarios = (List<Usuario>) savedInstanceState.getSerializable("usuario");
        }

        ArrayAdapter<Usuario> miAdaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,usuarios);
        spinner.setAdapter(miAdaptador);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                salidaTextView.setText(usuarios.get(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ActivityResultLauncher<Intent> activityResult =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if(result.getResultCode()== Activity.RESULT_CANCELED){
                                Toast.makeText(this,"Alta cancelada",Toast.LENGTH_SHORT).show();
                            } else if (result.getResultCode()==Activity.RESULT_OK) {
                                Intent datos = result.getData();
                                Bundle extras = datos.getExtras();
                                Profesion profesion = (Profesion) extras.getSerializable("profesion");
                                usuarios.add(new Usuario(nombre.getText().toString(),
                                    apellidos.getText().toString(),profesion));
                                miAdaptador.notifyDataSetChanged();
                            } else {
                                //esto no puede pasar
                            }
                            nombre.setText("");
                            apellidos.setText("");
                        });

        anyadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(nombre.getText().toString().equals("") ||
                    apellidos.getText().toString().equals(""))
                Toast.makeText(context,"Algún campo vacío",
                        Toast.LENGTH_SHORT).show();
            else {
                Intent intent = new Intent(context, ProfesionActivity.class);
                intent.putExtra("nombre",nombre.getText().toString());
                intent.putExtra("apellido",apellidos.getText().toString());
                //lanzar la pantalla/actividad
                //necesito un objeto ActivityResult
                activityResult.launch(intent);
            }

            }
        });

        salirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        acercaDeButton.setOnClickListener(view -> {
            //SIN DATOS DE REGRESO
            Intent intent = new Intent(this, AcercaDeActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        //nuestros datos a guardar en un objecto de la clase Bundle
        outState.putSerializable("usuario", (Serializable) usuarios);
    }



}