package gabrielmmelo.com.saaes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DadosPlacaFragment extends Fragment {

    private Dados dadosPlaca;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dados_placa, container, false);
        if(savedInstanceState != null){
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

        getActivity().setTitle("PLACA");
        return view;
    }

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
