package gabrielmmelo.com.saaes;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class FormActivity extends DebugActivity implements DadosPlacaFragment.ActivityCommunicator, DadosMedicaoFragment.ActivityCommunicator {

    private int fragment;
    private JSONObject dadosPlaca;
    private JSONObject dadosMedicao;
    private FragmentManager fm = getSupportFragmentManager();
    private DadosPlacaFragment dadosPlacaFragment = new DadosPlacaFragment();
    private DadosMedicaoFragment dadosMedicaoFragment = new DadosMedicaoFragment();
    private DadosSistemaFragment dadosSistemaFragment = new DadosSistemaFragment();
    private FotosFragment fotosFragment = new FotosFragment();
    private FloatingActionButton fabNext;
    private FloatingActionButton fabPrevious;
    private FloatingActionButton fabPicture;

    /**
     * ActivityCommunicator interface method implementation that "catch" sent data from DadosMedicao fragment
     * @param json
     */
    @Override
    public void passDadosMedicaoToActivity(JSONObject json) {
        this.dadosMedicao = json;
    }

    /**
     * ActivityCommunicator interface method implementation that "catch" sent data from DadosPlaca fragment
     * @param json
     */
    @Override
    public void passDadosPlacaToActivity(JSONObject json) {
        this.dadosPlaca = json;
    }

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        /*
         * FIRST CREATE ACTIVITY TIME
         */
        if (savedInstanceState == null){
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.layoutDadosPlacaFragment, dadosPlacaFragment, "DadosPlacaFragment");
            ft.add(R.id.layoutDadosMedicaoFragment, dadosMedicaoFragment, "DadosMedicaoFragment");
            ft.detach(dadosMedicaoFragment);
            ft.add(R.id.layoutDadosSistemaFragment, dadosSistemaFragment, "DadosSistemaFragment");
            ft.detach(dadosSistemaFragment);
            ft.add(R.id.layoutFotosFragment, fotosFragment, "FotosFragment");
            ft.detach(fotosFragment);
            ft.commit();
            fragment = 1;
        }

        // ENABLE TOP LEFT RETURN HOME ACTION BUTTON
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // CREATE FAB TO SWITCH TO NEXT FORM FRAGMENT & SET EXCLUSIVE TREATMENT EVENT METHOD
        fabNext = (FloatingActionButton) findViewById(R.id.fabNext);
        fabNext.setOnClickListener(onClickFabNext());

        // CREATE FAB TO SWITCH TO PREVIOUS FORM FRAGMENT & SET EXCLUSIVE TREATMENT EVENT METHOD
        fabPrevious = (FloatingActionButton) findViewById(R.id.fabPrevious);
        fabPrevious.setOnClickListener(onClickFabPrevious());
        fabPrevious.hide();

        // CREATE FAB TO SUBMIT ALL FORM DATA TO NEXT ACTIVITY & SET EXCLUSIVE TREATMENT EVENT METHOD
        fabPicture = (FloatingActionButton) findViewById(R.id.fabPicture);
        fabPicture.setOnClickListener(onClickFabPicture());
        fabPicture.hide();
    }

    /*
            Criação de método próprio para tratar o evento de click no fab to next form view.
         */

    /**
     * TREATMENT METHOD THAT PROPERLY ATTACH/DETACH FRAGMENT AND ALSO HIDE/SHOW FABs ON CLICK NEXT
     * @return
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
                        fabNext.hide();
                        fabPicture.show();
                        fragment += 1;
                    break;

                    /*case 3:
                        FragmentTransaction ft3 = fm.beginTransaction();
                        ft3.detach(dadosSistemaFragment);
                        ft3.attach(fotosFragment);
                        ft3.commit();
                        fabNext.hide();
                        fragment += 1;
                    break;
                    */
                }
            }
        };
    }

    /**
     * TREATMENT METHOD THAT PROPERLY ATTACH/DETACH FRAGMENT AND ALSO HIDE/SHOW FABs ON CLICK PREVIOUS
     * @return
     */
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
                        fabPicture.hide();
                        fabNext.show();
                        break;

                    /*case 4:
                        FragmentTransaction ft4 = fm.beginTransaction();
                        ft4.detach(fotosFragment);
                        ft4.attach(dadosSistemaFragment);
                        ft4.commit();
                        fabNext.show();
                        fragment -= 1;
                    break;
                    */
                }
            }
        };
    }

    /**
     * TREATMENT METHOD THAT PROPERLY SEND ALL FRAGMENT DATA TO ACTIVITY ON CLICK PICTURE
     * @return
     */
    private View.OnClickListener onClickFabPicture(){
        return new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), FotosActivity.class);
                Bundle params = new Bundle();
                params.putString("placa",dadosPlaca.toString());
                params.putString("medicao",dadosMedicao.toString());
                intent.putExtras(params);
                startActivity(intent);
                finish();
            }
        };
    }

    /**
     * TREAT ON CLICK HOME RETURN ACTION BUTTON EVENT
     * @param item
     * @return
     */
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