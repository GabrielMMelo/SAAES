package gabrielmmelo.com.saaes;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.net.URI;

import livroandroid.lib.utils.SDCardUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class FotosFragment extends Fragment {
    private ImageView foto_placa;
    private ImageView foto_conjunto;
    private ImageView foto_motor_bomba;
    private ImageView foto_banco_capacitores;
    private ImageView foto_painel;
    private int foto_selecionada;
    private ActivityCommunicator activityCommunicator;

    /**
     * Constructor needed according to Android documentation
     */
    public FotosFragment() {
        // Required empty public constructor
    }

    /**
     *
     */
    public interface ActivityCommunicator{
        void passPicturesToActivity(int id, Bitmap bitmap);
    }


    /**
     * Get context to send data when fragment is attached
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityCommunicator = (ActivityCommunicator) getContext();
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
        View view = inflater.inflate(R.layout.fragment_fotos, container, false);

        foto_conjunto = (ImageView) view.findViewById(R.id.foto_conjunto);
        ImageButton btn_conjunto = (ImageButton) view.findViewById(R.id.btn_foto_conjunto);
        btn_conjunto.setOnClickListener(OnClickBtnConjuntoListener());

        foto_motor_bomba = (ImageView) view.findViewById(R.id.foto_motor_bomba);
        ImageButton btn_motor_bomba = (ImageButton) view.findViewById(R.id.btn_foto_motor_bomba);
        btn_motor_bomba.setOnClickListener(OnClickBtnMotorBombaListener());

        foto_placa = (ImageView) view.findViewById(R.id.foto_placa);
        ImageButton btn_placa = (ImageButton) view.findViewById(R.id.btn_foto_placa);
        btn_placa.setOnClickListener(OnClickBtnPlacaListener());

        foto_banco_capacitores = (ImageView) view.findViewById(R.id.foto_banco_capacitores);
        ImageButton btn_banco_capacitores = (ImageButton) view.findViewById(R.id.btn_foto_banco_capacitores);
        btn_banco_capacitores.setOnClickListener(OnClickBtnBancoCapacitoresListener());

        foto_painel = (ImageView) view.findViewById(R.id.foto_painel);
        ImageButton btn_painel = (ImageButton) view.findViewById(R.id.btn_foto_painel);
        btn_painel.setOnClickListener(OnClickBtnPainelListener());

        getActivity().setTitle("FOTOS");
        return view;
    }

    /**
     *
     * @return
     */
    private View.OnClickListener OnClickBtnConjuntoListener(){
        return new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 0);
                }
                else{
                    foto_selecionada = 1;
          //          File file;
            //        file = SDCardUtils.getPrivateFile(getContext(), "teste.jpg", Environment.DIRECTORY_PICTURES);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              //      Uri uri = FileProvider.getUriForFile(getContext(),getContext().getApplicationContext().getPackageName() + ".provider", file);
                //    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, 0);
                }

            }
        };
    }

    /**
     *
     * @return
     */
    private View.OnClickListener OnClickBtnMotorBombaListener(){
        return new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 0);
                }
                else{
                    foto_selecionada = 2;
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                }

            }
        };
    }

    /**
     *
     * @return
     */
    private View.OnClickListener OnClickBtnPlacaListener(){
        return new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 0);
                }
                else{
                    foto_selecionada = 3;
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                }

            }
        };
    }


    private View.OnClickListener OnClickBtnBancoCapacitoresListener(){
        return new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 0);
                }
                else{
                    foto_selecionada = 4;
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                }

            }
        };
    }


    private View.OnClickListener OnClickBtnPainelListener(){
        return new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 0);
                }
                else{
                    foto_selecionada = 5;
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                }

            }
        };
    }

    /**
     *  Receive activity result
     *
     *  Send to interface method a picture id and bitmap
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            Bundle bundle = data.getExtras();
            if (bundle != null){
                Bitmap bitmap = (Bitmap) bundle.get("data");
                switch (foto_selecionada){
                    case 1:
                        this.activityCommunicator.passPicturesToActivity(1, bitmap);
                        foto_conjunto.setImageBitmap(bitmap);
                    break;

                    case 2:
                        this.activityCommunicator.passPicturesToActivity(2, bitmap);
                        foto_motor_bomba.setImageBitmap(bitmap);
                    break;

                    case 3:
                        this.activityCommunicator.passPicturesToActivity(3, bitmap);
                        foto_placa.setImageBitmap(bitmap);
                    break;

                    case 4:
                        this.activityCommunicator.passPicturesToActivity(4, bitmap);
                        foto_banco_capacitores.setImageBitmap(bitmap);
                    break;

                    case 5:
                        this.activityCommunicator.passPicturesToActivity(5, bitmap);
                        foto_painel.setImageBitmap(bitmap);
                    break;
                }
            }
        }
    }
}
