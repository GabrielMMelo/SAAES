package gabrielmmelo.com.saaes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DadosLocalFragment extends Fragment {

    public Context context;
    private ActivityCommunicator activityCommunicator;
    private Spinner projetos;
    private ProjetoDB projetoDB;

    public DadosLocalFragment() {
        // Required empty public constructor
    }


    /**
     * Communication interface to send data do activity
     */
    public interface ActivityCommunicator{
        public void passDadosLocalToActivity(JSONObject json);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dados_local, container, false);

        context = getActivity();
        projetoDB = new ProjetoDB(context);
        projetos = view.findViewById(R.id.spinner_projetos);
        String[] items_projetos = getProjects();
        ArrayAdapter<String> adapter_projetos = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, items_projetos);
        projetos.setAdapter(adapter_projetos);

        getActivity().setTitle("LOCAL");

        if (items_projetos.length == 0){
            Toast.makeText(context, "Não existem projetos. Crie um projeto antes de continuar!",
                    Toast.LENGTH_LONG).show();
        }
        return view;
    }

    /**
     * Serialize and send data to Activity when the fragment is switched
     */
    @Override
    public void onPause() {
        super.onPause();

        try {
            JSONObject local = new JSONObject();
            local.put("projeto", (projetos.getSelectedItem() == null) ? "null" : projetos.getSelectedItem().toString());
            local.put("cidade", ((TextView) getView().findViewById(R.id.cidade) == null) ? "null" : ((TextView) getView().findViewById(R.id.cidade)).getText());
            local.put("local", ((TextView) getView().findViewById(R.id.local) == null) ? "null" : ((TextView) getView().findViewById(R.id.local)).getText());
            local.put("numero_instalacao", ((TextView) getView().findViewById(R.id.num_instalacao)).getText());
            local.put("endereco", ((TextView) getView().findViewById(R.id.endereco)).getText());
            this.activityCommunicator.passDadosLocalToActivity(local);
        } catch (JSONException json_exception){

        }
    }

    protected String[] getProjects(){
        List<Projeto> list = projetoDB.getAll();
        int size = list.size();
        String[] nomes = new String[size];
        for (int i = 0; i < size; i ++) {
            nomes[i] = list.get(i).getNome();
        }
        return nomes;
    }
}

