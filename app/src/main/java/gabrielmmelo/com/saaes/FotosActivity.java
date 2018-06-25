package gabrielmmelo.com.saaes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

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
import java.util.Iterator;
import java.util.Random;

public class FotosActivity extends AppCompatActivity implements FotosFragment.ActivityCommunicator{

    private FragmentManager fm = getSupportFragmentManager();
    private FotosFragment fotosFragment = new FotosFragment();
    private FloatingActionButton fabSubmit;
    private String dadosPlaca;
    private String dadosMedicao;
    private Bitmap conjuntoPicture;
    private Bitmap motorBombaPicture;
    private Bitmap placaPicture;

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

        // Receive data by bundle from previous activity
        this.dadosPlaca = args.getString("placa");
        this.dadosMedicao = args.getString("medicao");

        // CREATE FAB TO SWITCH TO NEXT FORM FRAGMENT & SET EXCLUSIVE TREATMENT EVENT METHOD
        fabSubmit = (FloatingActionButton) findViewById(R.id.fabSubmit);
        fabSubmit.setOnClickListener(onClickFabSubmit());
    }


    private View.OnClickListener onClickFabSubmit(){
        return new FloatingActionButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                String leituraPlaca = readFromFile("placa.csv");
                String leituraMedicao = readFromFile("medicao.csv");
                if(leituraPlaca.isEmpty()){
                    writeToFile("placa.csv","sep=,\nTensao, Corrente, Potencia ativa, Potencia reativa, Fator de potencia, Rotacao, Fabricante do motor, Altura monometrica, Vazao, Fabricante da bomba");
                    Log.i("TESTE", "Empty placa file");
                }
                if(leituraMedicao.isEmpty()){
                    writeToFile("medicao.csv","sep=,\nTensao, Corrente, Potencia ativa, Potencia reativa, Fator de potencia, Rotacao, Fabricante do motor, Altura monometrica, Vazao, Fabricante da bomba");
                    Log.i("TESTE", "Empty medicao file");
                }
                prepararEscrita("placa.csv", dadosPlaca);
                prepararEscrita("medicao.csv", dadosMedicao);
                Log.i("TESTE", readFromFile("placa.csv"));
                Log.i("TESTE", readFromFile("medicao.csv"));


                saveImage(conjuntoPicture);
        /*      saveImage(motorBombaPicture);
                saveImage(placaPicture);

       */
            }
        };
    }

    /**
     * Prepare .csv file parsing JSON and calling the writing method
     * @param fileName
     * @param dados
     */
    public void prepararEscrita(String fileName, String dados){
        try {
            JSONObject json = new JSONObject(dados);
            writeToFile(fileName, "\n");
            writeToFile(fileName, json.optString("tensao") + ",");
            writeToFile(fileName, json.optString("corrente") + ",");
            writeToFile(fileName, json.optString("potencia_ativa") + ",");
            writeToFile(fileName, json.optString("potencia_reativa") + ",");
            writeToFile(fileName, json.optString("fator_potencia") + ",");
            writeToFile(fileName, json.optString("rotacao") + ",");
            writeToFile(fileName, json.optString("fabricante_motor") + ",");
            writeToFile(fileName, json.optString("altura_monometrica") + ",");
            writeToFile(fileName, json.optString("vazao") + ",");
            writeToFile(fileName, json.optString("fabricante_bomba"));
            Log.i("TESTE", "Successfully created " + fileName);

        } catch (JSONException e){
            e.printStackTrace();
        }
    }
    /**
     *
     * @param fileName
     * @param data
     */
    private void writeToFile(String fileName, String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getContext().openFileOutput(fileName, Context.MODE_APPEND));
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

        String root = Environment.getExternalStorageDirectory().toString();
        Log.i("TESTE", root);
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
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
