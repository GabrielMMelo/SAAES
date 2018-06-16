package gabrielmmelo.com.saaes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DadosPlacaFragment extends Fragment {

    public DadosMotorBomba dadosPlaca = new DadosMotorBomba();
    public Context context;
    private ActivityCommunicator activityCommunicator;

    /*
        CONSTRUCTOR
     */
    public DadosPlacaFragment(){}

    public static DadosPlacaFragment newInstance(){
        return new DadosPlacaFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = getActivity();
        activityCommunicator = (ActivityCommunicator)this.context;
    }

    /*
        Communication interface to send data do activity
    */
    public interface ActivityCommunicator{
        public void passDadosPlacaToActivity(JSONObject json);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dados_placa, container, false);
        /*if(savedInstanceState != null){
            Log.i("TESTE", "savedInstanceState is NOT null");
            this.dadosPlaca.setTensao(savedInstanceState.getFloat("tensao"));
            TextView tensao = (TextView) view.findViewById(R.id.tensao);
            tensao.setText(Float.toString(this.dadosPlaca.getTensao()));

            this.dadosPlaca.setCorrente(savedInstanceState.getFloat("corrente"));
            TextView corrente = (TextView) view.findViewById(R.id.corrente);
            corrente.setText(Float.toString(this.dadosPlaca.getCorrente()));

            this.dadosPlaca.setPotencia_ativa(savedInstanceState.getFloat("potencia_ativa"));
            TextView potencia_ativa = (TextView) view.findViewById(R.id.potencia_ativa);
            potencia_ativa.setText(Float.toString(this.dadosPlaca.getPotencia_ativa()));

            this.dadosPlaca.setPotencia_reativa(savedInstanceState.getFloat("potencia_reativa"));
            TextView potencia_reativa = (TextView) view.findViewById(R.id.potencia_reativa);
            potencia_reativa.setText(Float.toString(this.dadosPlaca.getPotencia_reativa()));

            this.dadosPlaca.setFator_potencia(savedInstanceState.getFloat("fator_potencia"));
            TextView fator_potencia = (TextView) view.findViewById(R.id.fator_potencia);
            fator_potencia.setText(Float.toString(this.dadosPlaca.getFator_potencia()));

            this.dadosPlaca.setRotacao(savedInstanceState.getFloat("rotacao"));
            TextView rotacao = (TextView) view.findViewById(R.id.rotacao);
            rotacao.setText(Float.toString(this.dadosPlaca.getRotacao()));

            this.dadosPlaca.setFabricante_motor(savedInstanceState.getString("fabricante_motor"));
            TextView fabricante_motor = (TextView) view.findViewById(R.id.fabricante_motor);
            fabricante_motor.setText(this.dadosPlaca.getFabricante_motor());

            this.dadosPlaca.setVazao(savedInstanceState.getFloat("vazao"));
            TextView vazao = (TextView) view.findViewById(R.id.vazao);
            vazao.setText(Float.toString(this.dadosPlaca.getVazao()));

            this.dadosPlaca.setAltura_monometrica(savedInstanceState.getFloat("altura_monometrica"));
            TextView altura_monometrica = (TextView) view.findViewById(R.id.altura_monometrica);
            altura_monometrica.setText(Float.toString(this.dadosPlaca.getAltura_monometrica()));

            this.dadosPlaca.setFabricante_bomba(savedInstanceState.getString("fabricante_bomba"));
            TextView fabricante_bomba = (TextView) view.findViewById(R.id.fabricante_bomba);
            fabricante_bomba.setText(this.dadosPlaca.getFabricante_bomba());
        }

        else{
            Log.i("TESTE", "savedInstanceState is null");
        }
        */

        getActivity().setTitle("PLACA");
        //setRetainInstance(true);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        try {
            JSONObject placa = new JSONObject();
            placa.put("tensao", ((TextView) getView().findViewById(R.id.tensao) == null) ? "null" : ((TextView) getView().findViewById(R.id.tensao)).getText() );
            placa.put("corrente", ((TextView) getView().findViewById(R.id.corrente) == null) ? "null" : ((TextView) getView().findViewById(R.id.corrente)).getText() );
            placa.put("potencia_ativa", ((TextView) getView().findViewById(R.id.potencia_ativa) == null) ? "null" : ((TextView) getView().findViewById(R.id.potencia_ativa)).getText() );
            placa.put("potencia_reativa", ((TextView) getView().findViewById(R.id.potencia_reativa) == null) ? "null" : ((TextView) getView().findViewById(R.id.potencia_reativa)).getText() );
            placa.put("fator_potencia", ((TextView) getView().findViewById(R.id.fator_potencia) == null) ? "null" : ((TextView) getView().findViewById(R.id.fator_potencia)).getText() );
            placa.put("rotacao", ((TextView) getView().findViewById(R.id.rotacao) == null) ? "null" : ((TextView) getView().findViewById(R.id.rotacao)).getText() );
            placa.put("fabricante_motor", ((TextView) getView().findViewById(R.id.fabricante_motor) == null) ? "null" : ((TextView) getView().findViewById(R.id.fabricante_motor)).getText() );
            placa.put("altura_monometrica", ((TextView) getView().findViewById(R.id.altura_monometrica) == null) ? "null" : ((TextView) getView().findViewById(R.id.altura_monometrica)).getText() );
            placa.put("vazao", ((TextView) getView().findViewById(R.id.vazao) == null) ? "null" : ((TextView) getView().findViewById(R.id.vazao)).getText() );
            placa.put("fabricante_bomba", ((TextView) getView().findViewById(R.id.fabricante_bomba) == null) ? "null" : ((TextView) getView().findViewById(R.id.fabricante_bomba)).getText() );
            this.activityCommunicator.passDadosPlacaToActivity(placa);
        } catch (JSONException json_exception){

        }

    }

    /*
        IT WONT BE CALLED BECAUSE onSaveInstaceState is only called when ACTIVITY it calls onSaveInstanceState (when the OS needs to destroy activity to clean space)
    */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("dadosPlaca", this.dadosPlaca.getTensao());
        outState.putFloat("corrente", this.dadosPlaca.getCorrente());
        outState.putFloat("potencia_ativa", this.dadosPlaca.getPotencia_ativa());
        outState.putFloat("potencia_reativa", this.dadosPlaca.getPotencia_ativa());
        outState.putFloat("fator_potencia", this.dadosPlaca.getFator_potencia());
        outState.putFloat("rotacao", this.dadosPlaca.getRotacao());
        outState.putString("fabricante_motor", this.dadosPlaca.getFabricante_motor());
        outState.putFloat("vazao", this.dadosPlaca.getVazao());
        outState.putFloat("altura_monometrica", this.dadosPlaca.getAltura_monometrica());
        outState.putString("fabricante_bomba", this.dadosPlaca.getFabricante_bomba());
    }
}
