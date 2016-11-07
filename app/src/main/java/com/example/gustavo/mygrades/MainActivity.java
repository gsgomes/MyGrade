package com.example.gustavo.mygrades;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    File[] Files;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> files = new ArrayList<>();
        File dir = getFilesDir();
        Files = dir.listFiles();
        for (int i = 0; i < Files.length; i++) {
            if(!Files[i].isDirectory()) {
                files.add(Files[i].getName());
            }
        }
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files);
        ListView list = (ListView)findViewById(R.id.listDisc);
        list.setAdapter(aa);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String filename = MainActivity.this.Files[i].getName();
                AlertDialog.Builder fileDialog = new AlertDialog.Builder(MainActivity.this);
                fileDialog.setTitle(filename);
                try{
                    BufferedReader in = new BufferedReader(new FileReader(MainActivity.this.Files[i]));
                    StringBuilder text = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        text.append(line);
                        text.append('\n');
                    }
                    in.close();
                    fileDialog.setMessage(text);
                }catch(Exception ex){
                    fileDialog.setMessage("Erro ao carregar arquivo: "+ex.getLocalizedMessage());
                }
                fileDialog.setNeutralButton("Fechar", null);
                fileDialog.show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<String> files = new ArrayList<>();
        File dir = getFilesDir();
        Files = dir.listFiles();
        for (int i = 0; i < Files.length; i++) {
            if(!Files[i].isDirectory()) {
                files.add(Files[i].getName());
            }
        }
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files);
        ListView list = (ListView)findViewById(R.id.listDisc);
        list.setAdapter(aa);
    }

    public void initCadastro(View view){
        Intent i = new Intent(getApplicationContext(),CadastroActivity.class);
        startActivity(i);
    }
}

