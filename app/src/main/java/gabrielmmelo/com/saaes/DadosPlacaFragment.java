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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DadosPlacaFragment extends Fragment {

    public Context context;
    private DadosMotorBomba dadosPlaca = new DadosMotorBomba();
    private ActivityCommunicator activityCommunicator;
    private boolean tensaoFlag; // Says if tensaoEditText is visible
    private Spinner tensaoSpinner;
    private boolean rotacaoFlag; // Says if rotacaoEditText is visible
    private Spinner rotacaoSpinner;
    private boolean fabricanteMotorFlag; // Says if rotacaoEditText is visible
    private Spinner fabricanteMotorSpinner;
    private boolean fatorPotenciaFlag; // Says if rotacaoEditText is visible
    private Spinner fatorPotenciaSpinner;


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


        // TENSÃO
        final EditText tensaoEditText = view.findViewById(R.id.tensao);
        tensaoEditText.setVisibility(View.INVISIBLE);
        tensaoFlag = false;

        tensaoSpinner = view.findViewById(R.id.spinner_tensao);
        String[] items_tensao = new String[]{"220", "380", "440", "760", "Outro"};
        ArrayAdapter<String> adapter_tensao = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items_tensao);
        tensaoSpinner.setAdapter(adapter_tensao);
        tensaoSpinner.setOnItemSelectedListener(onSelectTensao(tensaoEditText));


        // ROTAÇÃO
        final EditText rotacaoEditText = view.findViewById(R.id.rotacao);
        rotacaoEditText.setVisibility(View.INVISIBLE);
        rotacaoFlag = false;

        rotacaoSpinner = view.findViewById(R.id.spinner_rotacao);
        String[] items_rotacao = new String[]{"1185", "1190", "1780", "1785", "1790", "3570", "Outro"};
        ArrayAdapter<String> adapter_rotacao = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items_rotacao);
        rotacaoSpinner.setAdapter(adapter_rotacao);
        rotacaoSpinner.setOnItemSelectedListener(onSelectRotacao(rotacaoEditText));


        // FABRICANTE MOTOR
        final EditText fabricanteMotorEditText = view.findViewById(R.id.fabricante_motor);
        fabricanteMotorEditText.setVisibility(View.INVISIBLE);
        fabricanteMotorFlag = false;

        fabricanteMotorSpinner = view.findViewById(R.id.spinner_fabricante_motor);
        String[] items_fabricanteMotor = new String[]{"VOGE", "EBERLE", "WEG - Alto rendimento", "WEG - Baixo rendimento", "Outro"};
        ArrayAdapter<String> adapter_fabricanteMotor = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items_fabricanteMotor);
        fabricanteMotorSpinner.setAdapter(adapter_fabricanteMotor);
        fabricanteMotorSpinner.setOnItemSelectedListener(onSelectFabricanteMotor(fabricanteMotorEditText));


        // FATOR POTÊNCIA
        final EditText fatorPotenciaEditText = view.findViewById(R.id.fator_potencia);
        fatorPotenciaEditText.setVisibility(View.INVISIBLE);
        fatorPotenciaFlag = false;

        fatorPotenciaSpinner = view.findViewById(R.id.spinner_fator_potencia);
        String[] items_fatorPotencia = new String[]{"0.81", "0.82", "0.83", "0.84", "0.85", "0.86", "0.87", "0.88", "0.89", "Outro"};
        ArrayAdapter<String> adapter_fatorPotencia = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items_fatorPotencia);
        fatorPotenciaSpinner.setAdapter(adapter_fatorPotencia);
        fatorPotenciaSpinner.setOnItemSelectedListener(onSelectFatorPotencia(fatorPotenciaEditText));


        getActivity().setTitle("PLACA");
        return view;
    }

    private AdapterView.OnItemSelectedListener onSelectTensao(final EditText tensaoEditText){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(parentView.getSelectedItem().toString().equals("Outro")){
                    tensaoEditText.setVisibility(View.VISIBLE);
                    tensaoFlag = true;
                    parentView.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    private AdapterView.OnItemSelectedListener onSelectRotacao(final EditText rotacaoEditText){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(parentView.getSelectedItem().toString().equals("Outro")){
                    rotacaoEditText.setVisibility(View.VISIBLE);
                    rotacaoFlag = true;
                    parentView.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    private AdapterView.OnItemSelectedListener onSelectFabricanteMotor(final EditText fabricanteMotorEditText){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(parentView.getSelectedItem().toString().equals("Outro")){
                    fabricanteMotorEditText.setVisibility(View.VISIBLE);
                    fabricanteMotorFlag = true;
                    parentView.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    private AdapterView.OnItemSelectedListener onSelectFatorPotencia(final EditText fatorPotenciaEditText){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(parentView.getSelectedItem().toString().equals("Outro")){
                    fatorPotenciaEditText.setVisibility(View.VISIBLE);
                    fatorPotenciaFlag = true;
                    parentView.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    /**
     * Serialize and send data to Activity when the fragment is switched
     */
    @Override
    public void onPause() {
        super.onPause();

        try {
            JSONObject placa = new JSONObject();

            if (tensaoFlag)
                placa.put("tensao", ((TextView) getView().findViewById(R.id.tensao) == null) ? "null" : ((TextView) getView().findViewById(R.id.tensao)).getText());

            else
                placa.put("tensao", (tensaoSpinner.getSelectedItem().toString()));

            placa.put("corrente", ((TextView) getView().findViewById(R.id.corrente) == null) ? "null" : ((TextView) getView().findViewById(R.id.corrente)).getText() );
            placa.put("potencia_ativa", ((TextView) getView().findViewById(R.id.potencia_ativa) == null) ? "null" : ((TextView) getView().findViewById(R.id.potencia_ativa)).getText() );
            placa.put("potencia_reativa", ((TextView) getView().findViewById(R.id.potencia_reativa) == null) ? "null" : ((TextView) getView().findViewById(R.id.potencia_reativa)).getText() );

            if (fatorPotenciaFlag)
                placa.put("fator_potencia", ((TextView) getView().findViewById(R.id.fator_potencia) == null) ? "null" : ((TextView) getView().findViewById(R.id.fator_potencia)).getText() );

            else
                placa.put("fator_potencia", (fatorPotenciaSpinner.getSelectedItem().toString()));


            if (rotacaoFlag)
                placa.put("rotacao", ((TextView) getView().findViewById(R.id.rotacao) == null) ? "null" : ((TextView) getView().findViewById(R.id.rotacao)).getText());

            else
                placa.put("rotacao", (rotacaoSpinner.getSelectedItem().toString()));

            if (fabricanteMotorFlag)
                placa.put("fabricante_motor", ((TextView) getView().findViewById(R.id.fabricante_motor) == null) ? "null" : ((TextView) getView().findViewById(R.id.fabricante_motor)).getText());

            else
                placa.put("fabricante_motor", (fabricanteMotorSpinner.getSelectedItem().toString()));

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
