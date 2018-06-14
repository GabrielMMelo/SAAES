package gabrielmmelo.com.saaes;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DadosMedicaoFragment extends Fragment {

    private Dados dadosMedicao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dados_medicao, container, false);

        if(savedInstanceState != null){
            this.dadosMedicao.setTensao(savedInstanceState.getFloat("tensao"));
            TextView tensao = (TextView) view.findViewById(R.id.tensao);
            tensao.setText(Float.toString(this.dadosMedicao.getTensao()));

            this.dadosMedicao.setCorrente(savedInstanceState.getFloat("corrente"));
            TextView corrente = (TextView) view.findViewById(R.id.corrente);
            corrente.setText(Float.toString(this.dadosMedicao.getCorrente()));

            this.dadosMedicao.setPotencia_ativa(savedInstanceState.getFloat("potencia_ativa"));
            TextView potencia_ativa = (TextView) view.findViewById(R.id.potencia_ativa);
            potencia_ativa.setText(Float.toString(this.dadosMedicao.getPotencia_ativa()));

            this.dadosMedicao.setPotencia_reativa(savedInstanceState.getFloat("potencia_reativa"));
            TextView potencia_reativa = (TextView) view.findViewById(R.id.potencia_reativa);
            potencia_reativa.setText(Float.toString(this.dadosMedicao.getPotencia_reativa()));

            this.dadosMedicao.setFator_potencia(savedInstanceState.getFloat("fator_potencia"));
            TextView fator_potencia = (TextView) view.findViewById(R.id.fator_potencia);
            fator_potencia.setText(Float.toString(this.dadosMedicao.getFator_potencia()));

            this.dadosMedicao.setRotacao(savedInstanceState.getFloat("rotacao"));
            TextView rotacao = (TextView) view.findViewById(R.id.rotacao);
            rotacao.setText(Float.toString(this.dadosMedicao.getRotacao()));

            this.dadosMedicao.setFabricante_motor(savedInstanceState.getString("fabricante_motor"));
            TextView fabricante_motor = (TextView) view.findViewById(R.id.fabricante_motor);
            fabricante_motor.setText(this.dadosMedicao.getFabricante_motor());

            this.dadosMedicao.setVazao(savedInstanceState.getFloat("vazao"));
            TextView vazao = (TextView) view.findViewById(R.id.vazao);
            vazao.setText(Float.toString(this.dadosMedicao.getVazao()));

            this.dadosMedicao.setAltura_monometrica(savedInstanceState.getFloat("altura_monometrica"));
            TextView altura_monometrica = (TextView) view.findViewById(R.id.altura_monometrica);
            altura_monometrica.setText(Float.toString(this.dadosMedicao.getAltura_monometrica()));

            this.dadosMedicao.setFabricante_bomba(savedInstanceState.getString("fabricante_bomba"));
            TextView fabricante_bomba = (TextView) view.findViewById(R.id.fabricante_bomba);
            fabricante_bomba.setText(this.dadosMedicao.getFabricante_bomba());

        }

        getActivity().setTitle("MEDIÇÕES");
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("dadosMedicao", this.dadosMedicao.getTensao());
        outState.putFloat("corrente", this.dadosMedicao.getCorrente());
        outState.putFloat("potencia_ativa", this.dadosMedicao.getPotencia_ativa());
        outState.putFloat("potencia_reativa", this.dadosMedicao.getPotencia_ativa());
        outState.putFloat("fator_potencia", this.dadosMedicao.getFator_potencia());
        outState.putFloat("rotacao", this.dadosMedicao.getRotacao());
        outState.putString("fabricante_motor", this.dadosMedicao.getFabricante_motor());
        outState.putFloat("vazao", this.dadosMedicao.getVazao());
        outState.putFloat("altura_monometrica", this.dadosMedicao.getAltura_monometrica());
        outState.putString("fabricante_bomba", this.dadosMedicao.getFabricante_bomba());
    }

}
