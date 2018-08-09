package gabrielmmelo.com.saaes;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.Random;

public class FotosActivity extends AppCompatActivity implements FotosFragment.ActivityCommunicator{

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private FragmentManager fm = getSupportFragmentManager();
    private FotosFragment fotosFragment = new FotosFragment();
    private FloatingActionButton fabSubmit;
    private String dadosPlaca;
    private String dadosMedicao;
    private String dadosLocal;
    private Bitmap conjuntoPicture;
    private Bitmap motorBombaPicture;
    private Bitmap placaPicture;
    private Bitmap bancoCapacitoresPicture;
    private Bitmap painelPicture;
    private EstacaoDB estacaoDB = new EstacaoDB(getContext());
    private Estacao estacao = new Estacao();;

    @Override
    public void passPicturesToActivity(int id, Bitmap bitmap) {
        switch (id){
            case 1:
                this.conjuntoPicture = bitmap;
            break;

            case 2:
                this.motorBombaPicture = bitmap;
            break;

            case 3:
                this.placaPicture = bitmap;
            break;

            case 4:
                this.bancoCapacitoresPicture = bitmap;
            break;

            case 5:
                this.painelPicture = bitmap;
            break;
        }
    }

    /**
     * Called when the activity is starting.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos);
        if (savedInstanceState == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.layoutFotosFragment, fotosFragment, "FotosFragment");
            ft.commit();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle args = intent.getExtras();

        Log.i("DEBUG","DADOS DO SISTEMA " + args.getString("sistema"));

        // Receive data by bundle from previous activity
        this.estacao.estacaoFromJson(args.getString("local"), args.getString("placa"), args.getString("medicao"), args.getString("sistema"));

        // CREATE FAB TO SWITCH TO NEXT FORM FRAGMENT & SET EXCLUSIVE TREATMENT EVENT METHOD
        fabSubmit = (FloatingActionButton) findViewById(R.id.fabSubmit);
        fabSubmit.setOnClickListener(onClickFabSubmit());
    }


    private View.OnClickListener onClickFabSubmit(){
        return new FloatingActionButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                prepararEscrita();
                totalEstacao();
                getEstacao("Gabriel");


                saveImage(conjuntoPicture);
                Toast.makeText(FotosActivity.this, "Dados salvos no banco de dados!", Toast.LENGTH_SHORT).show();
        /*      saveImage(motorBombaPicture);
                saveImage(placaPicture);
       */
                finish();
            }
        };
    }

    public void getEstacao(int id){
        Estacao estacao;
        estacao = estacaoDB.getEstacao(id);

        Log.i("sql", estacao.getCidade() + " é a cidade.");
    }

    public void getEstacao(String cidade){
        Estacao estacao;
        estacao = estacaoDB.getEstacao(cidade);

        Log.i("sql", estacao.getCidade() + " é a cidade.");
    }

    public void totalEstacao(){
        Log.i("sql",estacaoDB.getCount("estacao")+" REGISTROS NO BANCO");
    }

    /**
     * TODO: Fix method comment
     * Prepare .csv file parsing JSON and calling the writing method
     */
    public void prepararEscrita(){
        estacaoDB.save(estacao);
    }

    /**
     *
     * @param fileName
     * @param data
     */
    private void writeToFile(String fileName, String data) {
        try {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CONTACTS)) {

                    Log.i("TESTE", "EXPLANATION");

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }

            //Check SD card state
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state) || !Environment.MEDIA_MOUNTED.equals(state)) {
                Log.e("TESTE", "Error: external storage is read only or unavailable");
            } else {
                Log.d("TESTE", "External storage is not read only or unavailable");
            }

            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File (sdCard.getAbsolutePath() + "/SAAE/");
            //dir.createNewFile(); // *
            //Files.createDirectory(dir.toPath());
            Log.i("TESTE",dir.getAbsolutePath());
            if (!dir.isDirectory()) {
                Log.i("TESTE", String.valueOf(dir.mkdirs()));
                Log.i("TESTE", "OI");
            }
            else
                Log.i("TESTE", "Directory already exists");


            File file = new File(dir, fileName);
            //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getContext().openFileOutput(fileName, Context.MODE_APPEND));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, true));
            outputStreamWriter.write(data);
            Log.i("TESTE", "Successfully created " + fileName);
            outputStreamWriter.close();

        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     *
     * @param fileName
     * @return
     */
    private String readFromFile(String fileName) {

        String ret = "";

        try {
            InputStream inputStream = getContext().openFileInput(fileName);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
            else{
                Log.e("TESTE", "ARQUIVO NAO EXISTE");
                return "-1";
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    /**
     *
     * @param finalBitmap
     */
    private void saveImage(Bitmap finalBitmap) {

        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File (sdCard.getAbsolutePath() + "/SAAE/img");
        if (!dir.isDirectory()) {
            Log.i("TESTE", String.valueOf(dir.mkdirs()));
            Log.i("TESTE", "OI");
        }
        else
            Log.i("TESTE", "Directory already exists");
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (dir, fname);
        if (file.exists ())
            file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Context getContext(){
        return this;
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
