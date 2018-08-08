package gabrielmmelo.com.saaes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DadosLocalFragment extends Fragment {

    public Context context;
    private ActivityCommunicator activityCommunicator;

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

        getActivity().setTitle("LOCAL");
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
            local.put("cidade", ((TextView) getView().findViewById(R.id.cidade) == null) ? "null" : ((TextView) getView().findViewById(R.id.cidade)).getText() );
            local.put("corrente", ((TextView) getView().findViewById(R.id.local) == null) ? "null" : ((TextView) getView().findViewById(R.id.local)).getText() );
            this.activityCommunicator.passDadosLocalToActivity(local);
        } catch (JSONException json_exception){

        }
    }
}
