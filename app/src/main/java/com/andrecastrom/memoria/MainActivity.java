package com.andrecastrom.memoria;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generarArchivo(View view) {

        try {
            EditText edtNombre = (EditText) findViewById(R.id.edtNombre);
            String nombre = edtNombre.getText().toString();

            FileOutputStream outputStream = null;
            outputStream = openFileOutput("MiArchivo.txt", Context.MODE_PRIVATE);
            outputStream.write(nombre.getBytes());
            outputStream.close();

            Toast.makeText(this, "El archivo se ha creado", Toast.LENGTH_SHORT).show();
            edtNombre.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.error_guardar, Toast.LENGTH_SHORT).show();
        }

    }


    public void guardarPreferencia(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        EditText edtNombre = (EditText) findViewById(R.id.edtNombre);
        EditText edtEmail = (EditText) findViewById(R.id.edtEmail);
        String nombre = edtNombre.getText().toString();
        String email = edtEmail.getText().toString();
        editor.putString(getResources().getString(R.string.pnombre), nombre);
        editor.putString(getResources().getString(R.string.pemail), email);
        editor.commit();

        Toast.makeText(this, "Se ha creado la Prefrencia Compartida", Toast.LENGTH_SHORT).show();
        edtNombre.setText("");
        edtEmail.setText("");

    }

    public void mostrarPreferencia(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);
        String nombre = sharedPreferences.getString(getResources().getString(R.string.pnombre),
                getResources().getString(R.string.perror));
        String email = sharedPreferences.getString(getResources().getString(R.string.pemail),
                getResources().getString(R.string.perror));

        TextView tvPreferenciaCompartida = (TextView) findViewById(R.id.tvPreferenciaCompartida);
        String preferencia = "\nNobre: " + nombre + "\nEmail: " + email;
        tvPreferenciaCompartida.setText(preferencia);

    }

}
