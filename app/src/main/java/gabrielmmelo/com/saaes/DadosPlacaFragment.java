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

    private String tensao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dados_placa, container, false);
        if(savedInstanceState != null){
            this.tensao = savedInstanceState.getString("tensao");
            TextView tensao = (TextView) view.findViewById(R.id.tensao);
            tensao.setText(this.tensao);
        }

        getActivity().setTitle("PLACA");


        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tensao", tensao);
    }
}
