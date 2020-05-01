package com.example.tallerfinalandroid_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText CodigoMatricula;
    private EditText Nombre;
    private EditText Nota1;
    private EditText Nota2;
    private EditText Nota3;
    private TextView NotaF;
    private Button Guardar;
    private Button Calcular;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CodigoMatricula = findViewById(R.id.txtCodigoMatricula);
        Nombre = (EditText) findViewById(R.id.txtNombresApellidos);
        Nota1 = (EditText) findViewById(R.id.txtNota1);
        Nota2 = (EditText) findViewById(R.id.txtNota2);
        Nota3 = (EditText) findViewById(R.id.txtNota3);
        NotaF = (TextView) findViewById(R.id.lblNotaF);
        Guardar = (Button) findViewById(R.id.btnGuardar);
        Calcular = (Button) findViewById(R.id.btnCalcular);

        databaseReference = FirebaseDatabase.getInstance().getReference("Alumnos");
        progressDialog = new ProgressDialog(this);

        Calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float n1 = 0, n2 = 0, n3 = 0;
                n1 = Float.parseFloat(Nota1.getText().toString());
                n2 = Float.parseFloat(Nota2.getText().toString());
                n3 = Float.parseFloat(Nota3.getText().toString());
                String CodM = CodigoMatricula.getText().toString().trim();
                String Nom = Nombre.getText().toString().trim();
                if ((n1 > 5) || (n2 > 5) || (n3 > 5)) {
                    String MSN_Error = NotaF.getText().toString();
                    Toast.makeText(MainActivity.this, "Verificar, las notas no pueden ser mayor a '5' " + MSN_Error, Toast.LENGTH_LONG).show();
                } else {
                    String MNS_n_f = NotaF.getText().toString();
                    NotaF.setText(((n1 + n2 + n3) / 3) + "");
                    Toast.makeText(MainActivity.this, "La nota final es: " + MNS_n_f, Toast.LENGTH_LONG).show();
                }
            }
        });

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String CodM = CodigoMatricula.getText().toString().trim();
                String Nom = Nombre.getText().toString().trim();
                String n_1 = Nota1.getText().toString().trim();
                String n_2 = Nota2.getText().toString().trim();
                String n_3 = Nota3.getText().toString().trim();
                String n_f = NotaF.getText().toString().trim();

                progressDialog.setMessage("Registrando notas");
                progressDialog.show();
                String id = databaseReference.push().getKey();
                DtoNotas dtoNotas = new DtoNotas(id, CodM, Nom, n_1, n_2, n_3, n_f);
                databaseReference.child(id).setValue(dtoNotas);
                Toast.makeText(MainActivity.this, "Guardado", Toast.LENGTH_LONG).show();


                CodigoMatricula.requestFocus();
                CodigoMatricula.setText("");
                Nombre.setText("");
                Nota1.setText("");
                Nota2.setText("");
                Nota3.setText("");
                NotaF.setText("");

                progressDialog.dismiss();
            }
        });

    }
}
