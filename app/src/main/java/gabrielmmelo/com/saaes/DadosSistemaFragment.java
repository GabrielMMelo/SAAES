package gabrielmmelo.com.saaes;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class DadosSistemaFragment extends Fragment {

    private Spinner tipo_partida;
    private Spinner banco_capacitores;
    private Spinner sistema_supervisionado;

    /**
     * Constructor needed according to Android documentation
     */
    public DadosSistemaFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_dados_sistema, container, false);

        tipo_partida = view.findViewById(R.id.spinner_tipo_partida);
        String[] items_tipo_partida = new String[]{"Estrela-triângulo", "Inversor de Frequência", "Chave Compensadora", "Soft-start", "Partida Direta"};
        ArrayAdapter<String> adapter_tipo_partida = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items_tipo_partida);
        tipo_partida.setAdapter(adapter_tipo_partida);

        banco_capacitores = view.findViewById(R.id.spinner_banco_capacitores);
        String[] items_banco_capactiores = new String[]{"Instalado no painel", "Não instalado"};
        ArrayAdapter<String> adapter_banco_capacitores = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items_banco_capactiores);
        banco_capacitores.setAdapter(adapter_banco_capacitores);

        sistema_supervisionado = view.findViewById(R.id.spinner_sistema_supervisionado);
        String[] items_sistema_supervisionado = new String[]{"Controlado", "Não controlado"};
        ArrayAdapter<String> adapter_sistema_supervisionado = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items_sistema_supervisionado);
        sistema_supervisionado.setAdapter(adapter_sistema_supervisionado);

        getActivity().setTitle("SISTEMA");
        return view;
    }

    /*
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tipo_partida", tipo_partida.getSelectedItem().toString());
        outState.putString("banco_capacitores", tipo_partida.getSelectedItem().toString());
    }
    */
}