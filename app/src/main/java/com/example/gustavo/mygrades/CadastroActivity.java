package com.example.gustavo.mygrades;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void save(View view){
        Materia m = new Materia();
        try{

            EditText campoNome = (EditText) findViewById(R.id.txtDisc);
            EditText campoP1 = (EditText) findViewById(R.id.valueP1);
            EditText campoP2 = (EditText) findViewById(R.id.valueP2);

            m.nome = String.valueOf(campoNome.getText());
            m.p1 = Float.parseFloat(String.valueOf(campoP1.getText()));
            m.p2 = Float.parseFloat(String.valueOf(campoP2.getText()));
        }catch(Exception e){
            Toast.makeText(this,R.string.warning,Toast.LENGTH_LONG).show();

        }

        //Grava arquivo
        String filename = m.nome;
        FileOutputStream output;

        try{
            output = openFileOutput(filename, Context.MODE_PRIVATE);
            output.write(("Nome da disciplina: " + m.nome + "\n").getBytes());
            output.write(("Nota P1: " + m.p1 + "\n").getBytes());
            output.write(("Nota P2: " + m.p2 + "\n").getBytes());
            output.close();

        }catch(Exception ex){
            Toast.makeText(this,"Erro ao gravar arquivo: " + ex.getLocalizedMessage(),Toast.LENGTH_LONG).show();
        }

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}
