package com.ifmg.carteiramensal;
//Guilherme Monteiro de Souza
//Info 3
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;
import java.lang.String;
import java.util.*;
import org.w3c.dom.Text;

import ferramentas.EventosDB;

public class MainActivity extends AppCompatActivity {

    private TextView titulo;
    private TextView entrada;
    private TextView saida;
    private TextView saldo;
    private ImageButton entradaBtn;
    private ImageButton saidaBtn;
    private Button anteriorBtn;
    private Button proximoBtn;
    private Button novoBtn;
    private Calendar hoje;
    private Calendar dataAPP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //criando o link entre componentes JAVA X XML
        titulo = (TextView) findViewById(R.id.tituloMain);
        entrada = (TextView) findViewById(R.id.entradaTxt);
        saida = (TextView) findViewById(R.id.saidaTxt);
        saldo = (TextView) findViewById(R.id.saldoTxt);

        entradaBtn = (ImageButton) findViewById(R.id.entradaBtn);
        saidaBtn = (ImageButton) findViewById(R.id.saidaBtn);

        anteriorBtn = (Button) findViewById(R.id.anteriorBtn);
        proximoBtn = (Button) findViewById(R.id.proximoBtn);
        novoBtn = (Button) findViewById(R.id.novoBtn);

        //responsavel por implementar todos os eventos de botoes
        cadastroEventos();

        //recupero a data e hora atual
        dataAPP = Calendar.getInstance();
        hoje = Calendar.getInstance();


        mostraDataApp();

    }
    private void cadastroEventos(){
        anteriorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarMes(-1);
            }
        });

        proximoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarMes(1);
            }
        });

        novoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventosDB db =  new EventosDB(MainActivity.this);
                db.insereEvento();

                Toast.makeText(MainActivity.this, db.getDatabaseName(), Toast.LENGTH_LONG).show();

                /* Outra forma de fazer, esse parte acima do codigo: (de maneira mais sucinta)
                ContentValues valores = new ContentValues();
                valores.put("nome", "padaria");
                valores.put("valor", -70);
                db.insert("evento",null,valores); */

            }
        });
    }

    private void mostraDataApp(){
        //0 - janeiro, 1 - fevereiro...
        String nomeMes[] = {"Janeiro", "Fevereiro", "Março", "Abri", "Maio", "Junho", "Julho", "Agosto",
                "Setembro", "Outubro", "Novembro", "Dezembro"};

        int mes = dataAPP.get(Calendar.MONTH);
        int ano = dataAPP.get(Calendar.YEAR);
        titulo.setText(nomeMes[mes] + "/"+ano);
    }

    private void atualizarMes(int ajuste){
        dataAPP.add(Calendar.MONTH, ajuste);

        //proximo mes nao pode passar do mes atual
        if (ajuste > 0){
            if (dataAPP.after(hoje)){
                dataAPP.add(Calendar.MONTH, -1);
            }
        }else{
            //aqui temos que realizar uma busca no banco de dados(avaliar se existem meses anteriores cadastrados)
        }

        mostraDataApp();



    }

}