package gabrielmmelo.com.saaes;

import android.content.Intent;
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

import java.util.Iterator;

public class FormActivity extends DebugActivity {

    private int fragment;
    private FragmentManager fm = getSupportFragmentManager();
    private DadosPlacaFragment dadosPlacaFragment = new DadosPlacaFragment();
    private DadosMedicaoFragment dadosMedicaoFragment = new DadosMedicaoFragment();
    private DadosSistemaFragment dadosSistemaFragment = new DadosSistemaFragment();
    private FotosFragment fotosFragment = new FotosFragment();
    private FloatingActionButton fabNext;
    private FloatingActionButton fabPrevious;
    private FloatingActionButton fabPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fabNext = (FloatingActionButton) findViewById(R.id.fabNext);
        fabNext.setOnClickListener(onClickFabNext());
        fabPrevious = (FloatingActionButton) findViewById(R.id.fabPrevious);
        fabPrevious.setOnClickListener(onClickFabPrevious());
        fabPrevious.hide();
        fabPicture = (FloatingActionButton) findViewById(R.id.fabPicture);
        try {
            fabPicture.setOnClickListener(onClickFabPicture());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        fabPicture.hide();
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

    private View.OnClickListener onClickFabPicture() throws org.json.JSONException {
        return new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), FotosActivity.class);
                Bundle params = new Bundle();
                params.putFloat("placa_tensao",123);
                params.putFloat("placa_corrente", 123);
                params.putFloat("placa_potencia_ativa", 123);
                params.putFloat("placa_potencia_reativa", 123);
                params.putFloat("placa_fator_potencia", 123);
                params.putFloat("placa_rotacao", 123);
                params.putString("placa_fabricante", "OI");

                params.putFloat("medicao_tensao", 123);
                params.putFloat("medicao_corrente", 123);
                params.putFloat("medicao_potencia_ativa", 123);
                params.putFloat("medicao_potencia_reativa", 123);
                params.putFloat("medicao_fator_potencia", 123);
                params.putFloat("medicao_rotacao", 123);
                params.putString("medicao_fabricante", "OI");
                /*
                    trying to convert json object to bundle

                try {
                    JSONObject json = new JSONObject();
                    JSONObject placa = new JSONObject();
                    JSONObject medicao = new JSONObject();
                    //JSONObject sistema = new JSONObject();
                    placa.put("tensao", "emil");
                    placa.put("corrente", "emil111");
                    placa.put("potencia_ativa", "111");
                    placa.put("potencia_ativa", "111");
                    placa.put("potencia_reativa", "111");
                    placa.put("fator_potencia", "111");
                    placa.put("rotacao", "111");
                    placa.put("fabricante", "111");
                    medicao.put("tensao", "emil");
                    medicao.put("corrente", "emil111");
                    medicao.put("potencia_ativa", "111");
                    medicao.put("potencia_ativa", "111");
                    medicao.put("potencia_reativa", "111");
                    medicao.put("fator_potencia", "111");
                    medicao.put("rotacao", "111");
                    medicao.put("fabricante", "111");
                    json.put("placa", placa);
                    json.put("placa", medicao);
                    // json.put("placa",sistema);
                    params.putBundle("data", jsonToBundle(json));
                } catch (JSONException ignored){}
                */
                intent.putExtras(params);
                startActivity(intent);
                finish();
            }
        };
    }

    private static Bundle jsonToBundle(JSONObject jsonObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator iter = jsonObject.keys();
        while(iter.hasNext()){
            String key = (String)iter.next();
            String value = jsonObject.getString(key);
            bundle.putString(key,value);
        }
        return bundle;
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