package com.example.datospersonales;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {

    Button btnReiniciar, btnMostrar;
    EditText editNombre, editApellidos, editEdad;
    Spinner spinnerIdioma, spinnerEstadoCivil;
    RadioGroup radioGroupGenero;
    RadioButton rbHombre, rbMujer;
    SwitchCompat switchHijos;
    TextView textError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReiniciar = findViewById(R.id.btnReiniciar);
        btnMostrar = findViewById(R.id.btnMostrar);
        editNombre = findViewById(R.id.editNombre);
        editApellidos = findViewById(R.id.editApellidos);
        editEdad = findViewById(R.id.editEdad);
        spinnerIdioma = findViewById(R.id.spinnerIdioma);
        spinnerEstadoCivil = findViewById(R.id.spinnerEstadoCivil);
        radioGroupGenero = findViewById(R.id.radioGroupGenero);
        rbHombre = findViewById(R.id.rbHombre);
        rbMujer = findViewById(R.id.rbMujer);
        switchHijos = findViewById(R.id.switchHijos);
        textError = findViewById(R.id.textError);

        spinnerIdioma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<CharSequence> adapter;

                switch (position) {
                    case 0:
                        editNombre.setHint(getString(R.string.hint_nombre_es));
                        editApellidos.setHint(getString(R.string.hint_apellidos_es));
                        editEdad.setHint(getString(R.string.hint_edad_es));
                        switchHijos.setText(getString(R.string.txt_hijos_es));
                        btnMostrar.setText(getString(R.string.btn_mostrar_es));
                        btnReiniciar.setText(getString(R.string.btn_reiniciar_es));
                        rbHombre.setText(getString(R.string.txt_hombre_es));
                        rbMujer.setText(getString(R.string.txt_mujer_es));
                        adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.estado_es, android.R.layout.simple_spinner_item);
                        spinnerEstadoCivil.setAdapter(adapter);
                        break;
                    case 1:
                        editNombre.setHint(getString(R.string.hint_nombre_en));
                        editApellidos.setHint(getString(R.string.hint_apellidos_en));
                        editEdad.setHint(getString(R.string.hint_edad_en));
                        switchHijos.setText(getString(R.string.txt_hijos_en));
                        btnMostrar.setText(getString(R.string.btn_mostrar_en));
                        btnReiniciar.setText(getString(R.string.btn_reiniciar_en));
                        rbHombre.setText(getString(R.string.txt_hombre_en));
                        rbMujer.setText(getString(R.string.txt_mujer_en));
                        adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.estado_en, android.R.layout.simple_spinner_item);
                        spinnerEstadoCivil.setAdapter(adapter);
                        break;
                    case 2:
                        editNombre.setHint(getString(R.string.hint_nombre_fr));
                        editApellidos.setHint(getString(R.string.hint_apellidos_fr));
                        editEdad.setHint(getString(R.string.hint_edad_fr));
                        switchHijos.setText(getString(R.string.txt_hijos_fr));
                        btnMostrar.setText(getString(R.string.btn_mostrar_fr));
                        btnReiniciar.setText(getString(R.string.btn_reiniciar_fr));
                        rbHombre.setText(getString(R.string.txt_hombre_fr));
                        rbMujer.setText(getString(R.string.txt_mujer_fr));
                        adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.estado_fr, android.R.layout.simple_spinner_item);
                        spinnerEstadoCivil.setAdapter(adapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnReiniciar.setOnClickListener(v -> {
            editNombre.setText("");
            editApellidos.setText("");
            editEdad.setText("");
            radioGroupGenero.clearCheck();
            switchHijos.setChecked(false);
            spinnerIdioma.setSelection(0);
            spinnerEstadoCivil.setSelection(0);
            textError.setText("");
        });

        btnMostrar.setOnClickListener(v -> {
            textError.setText("");

            String nombre = editNombre.getText().toString().trim();
            String apellidos = editApellidos.getText().toString().trim();
            String edadString = editEdad.getText().toString().trim();

            String mensajeFaltan = "Te falta escribir: ";
            boolean hayError = false;

            if (nombre.isEmpty()) {
                mensajeFaltan = mensajeFaltan + "nombre ";
                hayError = true;
            }
            if (apellidos.isEmpty()) {
                mensajeFaltan = mensajeFaltan + "apellidos ";
                hayError = true;
            }
            if (edadString.isEmpty()) {
                mensajeFaltan = mensajeFaltan + "edad";
                hayError = true;
            }

            if (hayError) {
                textError.setText(mensajeFaltan);
                return;
            }

            int edadNum = Integer.parseInt(edadString);
            String mayoriaEdad = (edadNum >= 18) ? "mayor de edad" : "menor de edad";

            int idGenero = radioGroupGenero.getCheckedRadioButtonId();
            String genero = "persona";
            if (idGenero != -1) {
                RadioButton rb = findViewById(idGenero);
                genero = rb.getText().toString().toLowerCase();
            }

            String estadoCivil = spinnerEstadoCivil.getSelectedItem().toString().toLowerCase();
            String tieneHijos = switchHijos.isChecked() ? "con hijos" : "sin hijos";

            String frase = apellidos + ", " + nombre + ", " + mayoriaEdad + ", " +
                    genero + " " + estadoCivil + " y " + tieneHijos + ".";

            new android.app.AlertDialog.Builder(MainActivity.this)
                    .setTitle("Información")
                    .setMessage(frase)
                    .setPositiveButton("Aceptar", null)
                    .show();
        });
    }
}