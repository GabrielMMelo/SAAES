package gabrielmmelo.com.saaes;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class FotosFragment extends Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int THUMBNAIL_SIZE = 100;

    private ImageView foto_placa;
    private ImageView foto_conjunto;
    private ImageView foto_motor_bomba;
    private ImageView foto_banco_capacitores;
    private ImageView foto_painel;
    private int foto_selecionada;

    private String local;
    private String placa;
    private String sistema;

    private ActivityCommunicator activityCommunicator;
    private Context context;

    private String mCurrentPhotoPath;
    private Uri photoURI;
    private Bitmap bitmap;

    /**
     * Constructor needed according to Android documentation
     */
    public FotosFragment() {
        // Required empty public constructor
    }

    /**
     * Interface to send data to `FotosActivity`
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
        this.context = context;
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
        Bundle args = getArguments();
        local = args.getString("local");
        placa = args.getString("placa");
        sistema = args.getString("sistema");
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
                createIntent(1, "CONJUNTO");
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
                createIntent(2, "MOTOR_BOMBA");
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
                createIntent(3, "PLACA");
            }
        };
    }


    private View.OnClickListener OnClickBtnBancoCapacitoresListener(){
        return new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                createIntent(4, "BANCO_CAPACITORES");
            }
        };
    }


    private View.OnClickListener OnClickBtnPainelListener(){
        return new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                createIntent(5, "PAINEL");
            }
        };
    }

    /**
     * Mount the file name and get absolute path
     * @param filename
     * @return
     * @throws IOException
     */
    private File createImageFile(String filename) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = filename + "_" + timeStamp;
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    /**
     *  Method to make the picture accessible from gallery native application
     */
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

    /**
     * Prepare file to receive the image that comes of a camera native app intent
     * @param pictureNumber
     * @param pictureKind
     */
    private void createIntent(int pictureNumber, String pictureKind ){
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 0);
        }
        else{
            foto_selecionada = pictureNumber;
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                File photoFile = null;
                try {
                    JSONObject l = new JSONObject(local);
                    JSONObject p = new JSONObject(placa);
                    JSONObject s = new JSONObject(sistema);

                    String projeto = l.optString("projeto");
                    String local = l.optString("local");
                    String cidade = l.optString("cidade");

                    photoFile = createImageFile(projeto + "_" + local + "_" + cidade + "_" + pictureKind);
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }catch (JSONException ex) {
                    ex.printStackTrace();
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    photoURI = FileProvider.getUriForFile(getContext(),
                            "com.example.android.fileprovider",
                            photoFile);
                    takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        }
    }

    /**
     *  Receive activity result. As we are saving the intent OUTPUT inside `photoURI`, we check if
     *  the activity result is OK and then get the thumbnail from it
     *
     *  Send to interface method a picture id and bitmap
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        galleryAddPic();
        if (resultCode == getActivity().RESULT_OK){
            try {
                bitmap = getThumbnail(photoURI);
            } catch (IOException ex){

            }
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

    /**
     * Get thumbnail from URI
     * @param uri
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Bitmap getThumbnail(Uri uri) throws FileNotFoundException, IOException{
        InputStream input = context.getContentResolver().openInputStream(uri);

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither=true;//optional
        onlyBoundsOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();

        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1)) {
            return null;
        }

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

        double ratio = (originalSize > THUMBNAIL_SIZE) ? (originalSize / THUMBNAIL_SIZE) : 1.0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true; //optional
        bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//
        input = context.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }

    /**
     * Simple power of two
     * @param ratio
     * @return
     */
    private static int getPowerOfTwoForSampleRatio(double ratio){
        int k = Integer.highestOneBit((int)Math.floor(ratio));
        if(k==0) return 1;
        else return k;
    }
}

