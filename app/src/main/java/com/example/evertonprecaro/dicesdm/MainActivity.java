package com.example.evertonprecaro.dicesdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //Variavel que controla o começo de um novo jogo
        private Boolean novoJogo = true;

    //Instanciando ArrayList que será o recipiente das bolas do bingo
        ArrayList<Integer> sorteador = new ArrayList();

    //Instanciando quantidade de bolas do bingo
        int numeroBolas = 75;


    //Referencia para os componentes visuais no arquivo de Layout
    private TextView resultadoTextView;
    private ImageView resultadoImageView;
    private ImageView resultado2ImageView;
    private Spinner numDadosSpinner;
    private ImageView getResultadoImageView;

    private String temp = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout_activity_main);


        //Recuperando referencia para os componesntes de Layout
            resultadoTextView = findViewById(R.id.resultadoTextView);

        //Preenchendo o recipiente com as bolas do bingo e realizando o shuffle das bolas
            for(int i=1; i<=numeroBolas; i++) { sorteador.add(i);}
            Collections.shuffle(sorteador);

    }

    public void sortearBingo(View view) {
    if (view.getId() == R.id.SortearBingoButton) {

            String strResultado = "";

            if(sorteador.isEmpty()){
                resultadoTextView.setText(getString(R.string.acabaram_as_bolas));
            }
            else{
                strResultado = String.valueOf(sorteador.get(0));
                resultadoTextView.setText("Bola sorteada: " + strResultado);
                sorteador.remove(0);
            }
        }

    }

    public void reiniciarBingo(View view) {
        if (view.getId() == R.id.ReiniciarBingoButton) {

            resultadoTextView.setText(getString(R.string.Pressione_o_botao_sortear_para_iniciar_novo_jogo));

            // Inicialmente verifica se o recipiente de sorteio contém elementos do jogo anterior e remove eles
            if (!sorteador.isEmpty()) {
                sorteador.clear();
            }

            //Preenchendo novamente o recipiente com as bolas do bingo e realizando o shuffle das bolas
            for (int i = 1; i <= numeroBolas; i++) { sorteador.add(i); }
            Collections.shuffle(sorteador);

        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);

        // Save the user's current state
        savedInstanceState.putIntegerArrayList("restanteDasBolas",sorteador);
        savedInstanceState.putString("resultadoTextView", (String) resultadoTextView.getText());


    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        sorteador = savedInstanceState.getIntegerArrayList("restanteDasBolas");
        temp = savedInstanceState.getString("resultadoTextView");
        resultadoTextView.setText(temp);
    }


}


