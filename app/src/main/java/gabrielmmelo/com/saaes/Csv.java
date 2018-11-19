package gabrielmmelo.com.saaes;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Csv {
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private Activity context;

    /**
     *
     * @param context
     */
    public Csv(Activity context) {
        this.context = context;
    }

    /**
     *
     * @param estacoes
     */
    public void exportCSV(List<Estacao> estacoes){
        Date now = new Date();
        Timestamp time = new Timestamp(now.getTime());

        int i = 0;
        for (Estacao estacao : estacoes) {

            if(i++ == estacoes.size() - 1) {
                writeToFile("estacoes.csv", "\nData:" + time);

                // MOTOR


                writeToFile("estacoes.csv", "\nLocal, " + estacao.getProjeto() + " " + estacao.getCidade() + "/" + estacao.getLocal() + ", " + estacao.getEndereco() + ", Número de instalação: " + estacao.getNumero_instalacao());
                writeToFile("estacoes.csv", "\nMotor, , Dados de Placa, Dados medidos");
                writeToFile("estacoes.csv", "\n , Tensão (V), " + estacao.getTensao_placa() + ", " + estacao.getTensao_medicao());
                writeToFile("estacoes.csv", "\n , Corrente (A), " + estacao.getCorrente_placa() + ", " + estacao.getCorrente_medicao());
                writeToFile("estacoes.csv", "\n , Potência Ativa (kW/cv), " + estacao.getPotencia_ativa_placa() + ", " + estacao.getPotencia_ativa_medicao());
                writeToFile("estacoes.csv", "\n , Potência Reativa (kVAr), " + estacao.getPotencia_reativa_placa() + ", " + estacao.getPotencia_reativa_medicao());
                writeToFile("estacoes.csv", "\n , Fator de Potência, " + estacao.getFator_potencia_placa() + ", " + estacao.getFator_potencia_medicao());
                writeToFile("estacoes.csv", "\n , Rotação (rpm), " + estacao.getRotacao_placa() + ", " + estacao.getRotacao_medicao());
                writeToFile("estacoes.csv", "\n , Fabricante, " + estacao.getFabricante_bomba() + ", , ");
                writeToFile("estacoes.csv", "\n\n");

                // BOMBA
                writeToFile("estacoes.csv", "\nBomba, Fabricante, " + estacao.getFabricante_motor() + ", ");
                writeToFile("estacoes.csv", "\n , Vazão (m³/h), " + estacao.getVazao_placa() + ", ");
                writeToFile("estacoes.csv", "\n , Altura monométrica (mca), " + estacao.getAltura_monometrica_placa() + ", ");
                writeToFile("estacoes.csv", "\n\n");

                // SISTEMA
                writeToFile("estacoes.csv", "\n , Tipo de partida, " + estacao.getTipo_partida() + ", ");
                writeToFile("estacoes.csv", "\n , Banco de capacitores, " + estacao.getBanco_capacitores() + ", ");
                writeToFile("estacoes.csv", "\n , Sistema supervisionado, " + estacao.getSistema_supervisionado() + ", ");
                writeToFile("estacoes.csv", "\n\n");
                writeToFile("estacoes.csv", "\nObservações, , , \n");
                writeToFile("estacoes.csv", estacao.getObservacoes());
                writeToFile("estacoes.csv", "\n\n\n\n");
            }
        }
    }

    /**
     *
     * @return
     */
    private Activity getContext(){
        return this.context;
    }


    /**
     *
     * @param fileName
     * @param data
     */
    private void writeToFile(String fileName, String data) {
        try {
            /*
             Try this to solve permission problems
             in API 23
             https://stackoverflow.com/questions/33162152/storage-permission-error-in-marshmallow/41221852#41221852
             */
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(getContext(),
                        Manifest.permission.READ_CONTACTS)) {

                    Log.i("TESTE", "EXPLANATION");

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(getContext(),
                            new String[]{Manifest.permission.READ_CONTACTS},
                            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                    Log.i("TESTE", "TESTE");
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
            Log.i("LOGZAO",dir.getAbsolutePath());
            if (!dir.isDirectory()) {
                Log.e("LOGZAO", String.valueOf(dir.mkdirs()));
                Log.i("LOGZAO", "OI");
            }
            else
                Log.i("TESTE", "Directory already exists");


            File file = new File(dir, fileName);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, true));
            if (!file.exists()) {
                outputStreamWriter.write("sep=,");
            }
            //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getContext().openFileOutput(fileName, Context.MODE_APPEND));

            outputStreamWriter.write(data);
            Log.i("LOGZAO", "Successfully created " + fileName);
            outputStreamWriter.close();

        } catch (IOException e) {
            Log.e("LOGZAO", "File write failed: " + e.toString());
        }
    }
}
