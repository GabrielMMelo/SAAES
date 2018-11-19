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
import java.util.List;
import java.util.Random;

import livroandroid.lib.utils.SDCardUtils;
import livroandroid.lib.utils.ImageResizeUtils;


public class FotosActivity extends AppCompatActivity implements FotosFragment.ActivityCommunicator{


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
    private Estacao estacao = new Estacao(getContext());
    private Csv csv = new Csv(this);
    private File file;

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

        Log.i("DEBUG","DADOS DO SISTEMA " + args.getString("local"));

        // Receive data by bundle from previous activity
        this.estacao.estacaoFromJson(args.getString("local"), args.getString("placa"), args.getString("medicao"), args.getString("sistema"));

        // CREATE FAB TO SWITCH TO NEXT FORM FRAGMENT & SET EXCLUSIVE TREATMENT EVENT METHOD
        fabSubmit = (FloatingActionButton) findViewById(R.id.fabSubmit);
        fabSubmit.setOnClickListener(onClickFabSubmit());
    }

    /**
     *
     * @return
     */
    private View.OnClickListener onClickFabSubmit(){
        return new FloatingActionButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                estacao.prepararEscrita();
                //estacao.totalEstacao();

                //file = SDCardUtils.getPrivateFile(getBaseContext(), "teste.jpg", Environment.DIRECTORY_PICTURES);
                //getEstacao("Gabriel");

                saveImage(estacao.getProjeto() + "_" + estacao.getCidade() + "_" + estacao.getLocal() + "_" + "Motor" + estacao.getTensao_placa() + "conjunto", conjuntoPicture);
                Toast.makeText(FotosActivity.this, "Dados salvos no banco de dados!", Toast.LENGTH_SHORT).show();
        /*      saveImage(motorBombaPicture);
                saveImage(placaPicture);
       */
                finish();
            }
        };
    }


    /**
     *
     * @param finalBitmap
     */
    private void saveImage(String filename, Bitmap finalBitmap) {

        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File (sdCard.getAbsolutePath() + "/SAAE/img");
        if (!dir.isDirectory()) {
            Log.i("TESTE", String.valueOf(dir.mkdirs()));
            Log.i("TESTE", "OI");
        }
        else
            Log.i("TESTE", "Directory already exists");

        File file = new File (dir, filename + ".jpg");
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

    /**
     *
     * @return
     */
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
