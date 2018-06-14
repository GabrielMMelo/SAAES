package gabrielmmelo.com.saaes;


import android.content.res.Resources;
import android.os.Bundle;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dados_sistema, container, false);
        Spinner dropdown = view.findViewById(R.id.spinner_tipo_partida);
        String[] items = new String[]{"Partida Compensadora", "Estrela-triângulo", "Inversor de Frequência", "Chave Compensadora", "Soft-start", "Partida Direta"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        return view;
    }

}
