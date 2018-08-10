package gabrielmmelo.com.saaes;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DadosMedicaoFragment extends Fragment {

    public Context context;
    private DadosMotorBomba dadosMedicao;
    private ActivityCommunicator activityCommunicator;

    /**
     * Constructor needed according to Android documentation
     */
    public DadosMedicaoFragment(){
    }

    public static DadosMedicaoFragment newInstance(){
        return new DadosMedicaoFragment();
    }

    /**
     * Communication interface to send data do activity
     */
    public interface ActivityCommunicator{
        public void passDadosMedicaoToActivity(JSONObject json);
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
        View view = inflater.inflate(R.layout.fragment_dados_medicao, container, false);
        getActivity().setTitle("MEDIÇÕES");
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

          this.activityCommunicator.passDadosMedicaoToActivity(placa);
        } catch (JSONException json_exception){
        }
    }
}
