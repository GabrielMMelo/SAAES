/*
    TODO:
    Entender o funcionamento da FragmentTransations e FragmentManager

 */

package gabrielmmelo.com.saaes;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.design.widget.FloatingActionButton;

public class Form1Activity extends DebugActivity {

    private int fragment;

    private FragmentManager fm = getSupportFragmentManager();
    private DadosPlacaFragment dadosPlacaFragment = new DadosPlacaFragment();
    private DadosMedicaoFragment dadosMedicaoFragment = new DadosMedicaoFragment();
    private DadosSistemaFragment dadosSistemaFragment = new DadosSistemaFragment();
    private FloatingActionButton fabNext;
    private FloatingActionButton fabPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);
        if (savedInstanceState == null){

            FragmentTransaction ft = fm.beginTransaction();

            ft.add(R.id.layoutDadosPlacaFragment, dadosPlacaFragment, "DadosPlacaFragment");
            ft.add(R.id.layoutDadosMedicaoFragment, dadosMedicaoFragment, "DadosMedicaoFragment");
            ft.detach(dadosMedicaoFragment);
            ft.add(R.id.layoutDadosSistemaFragment, dadosSistemaFragment, "DadosSistemaFragment");
            ft.detach(dadosSistemaFragment);
            ft.commit();
            fragment = 1;

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fabNext = (FloatingActionButton) findViewById(R.id.fabNext);
        fabNext.setOnClickListener(onClickFabNext());
        fabPrevious = (FloatingActionButton) findViewById(R.id.fabPrevious);
        fabPrevious.setOnClickListener(onClickFabPrevious());
        fabPrevious.hide();
    }

    /*
            Criação de método próprio para tratar o evento de click no fab to next form view.
         */
    private View.OnClickListener onClickFabNext(){
        return new FloatingActionButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (fragment){
                    case 1:
                        FragmentTransaction ft1 = fm.beginTransaction();
                        ft1.detach(dadosPlacaFragment);
                        ft1.attach(dadosMedicaoFragment);
                        ft1.commit();
                        fabPrevious.show();
                        fragment += 1;
                    break;

                    case 2:
                        FragmentTransaction ft2 = fm.beginTransaction();
                        ft2.detach(dadosMedicaoFragment);
                        ft2.attach(dadosSistemaFragment);
                        ft2.commit();
                        fragment += 1;
                    break;

                    case 3:
                        FragmentTransaction ft3 = fm.beginTransaction();
                        ft3.detach(dadosMedicaoFragment);
                        ft3.attach(dadosPlacaFragment);
                        ft3.commit();
                        fragment += 1;
                    break;
                }
            }
        };
    }


    private View.OnClickListener onClickFabPrevious(){
        return new FloatingActionButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (fragment){
                    case 2:
                        FragmentTransaction ft2 = fm.beginTransaction();
                        ft2.detach(dadosMedicaoFragment);
                        ft2.attach(dadosPlacaFragment);
                        ft2.commit();
                        fabPrevious.hide();
                        fragment -= 1;
                        break;

                    case 3:
                        FragmentTransaction ft3 = fm.beginTransaction();
                        ft3.detach(dadosSistemaFragment);
                        ft3.attach(dadosMedicaoFragment);
                        ft3.commit();
                        fragment -= 1;
                        break;

                    case 4:
                        FragmentTransaction ft4 = fm.beginTransaction();
                        ft4.detach(dadosMedicaoFragment);
                        ft4.attach(dadosPlacaFragment);
                        ft4.commit();
                        fragment = 1;
                        break;
                }
            }
        };

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private Context getContext(){
        return this;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
