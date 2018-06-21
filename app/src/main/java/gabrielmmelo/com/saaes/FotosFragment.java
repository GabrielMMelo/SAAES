package gabrielmmelo.com.saaes;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FotosFragment extends Fragment {
    private ImageView foto_placa;
    private ImageView foto_conjunto;
    private ImageView foto_motor_bomba;
    private int foto_selecionada;

    /**
     * Constructor needed according to Android documentation
     */
    public FotosFragment() {
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

    /**
     *
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
                        foto_conjunto.setImageBitmap(bitmap);
                    break;

                    case 2:
                        foto_motor_bomba.setImageBitmap(bitmap);
                    break;

                    case 3:
                        foto_placa.setImageBitmap(bitmap);
                    break;
                }
            }
        }
    }
}
