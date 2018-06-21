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

    public Context context;
    private DadosMotorBomba dadosPlaca = new DadosMotorBomba();
    private ActivityCommunicator activityCommunicator;

    /**
     * Constructor needed according to Android documentation
     */
    public DadosPlacaFragment(){}

    public static DadosPlacaFragment newInstance(){
        return new DadosPlacaFragment();
    }

    /**
     * Communication interface to send data do activity
     */
    public interface ActivityCommunicator{
        public void passDadosPlacaToActivity(JSONObject json);
    }

    /**
     * Get context to send data when fragment is attached
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = getActivity();
        activityCommunicator = (ActivityCommunicator)this.context;
    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dados_placa, container, false);

        getActivity().setTitle("PLACA");
        return view;
    }

    /**
     * Serialize and send data to Activity when the fragment is switched
     */
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

    /**
     * It <strong>wont</strong> be called because onSaveInstaceState is only called when ACTIVITY it calls onSaveInstanceState (when the OS needs to destroy activity to clean space)
     * @param outState
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
