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

    public Csv(Activity context) {
        this.context = context;
    }

    public void exportCSV(List<Estacao> estacoes){
        Date now = new Date();
        Timestamp time = new Timestamp(now.getTime());

        writeToFile(time+".csv", "sep=,");


        for (Estacao estacao : estacoes) {
            Log.i("DEBUG", estacao.getCidade());
            // MOTOR
            writeToFile(time+".csv", "\nLocal, "+ estacao.getCidade() + "/" + estacao.getLocal() + ", , ");
            writeToFile(time+".csv", "\nMotor, , Dados de Placa, Dados medidos");
            writeToFile(time+".csv", "\n , Tensão (V), " + estacao.getTensao_placa() + ", " + estacao.getTensao_medicao());
            writeToFile(time+".csv", "\n , Corrente (A), " + estacao.getCorrente_placa() + ", " + estacao.getCorrente_medicao());
            writeToFile(time+".csv", "\n , Potência Ativa (kW/cv), " + estacao.getPotencia_ativa_placa() + ", " + estacao.getPotencia_ativa_medicao());
            writeToFile(time+".csv", "\n , Potência Reativa (kVAr), " + estacao.getPotencia_reativa_placa() + ", " + estacao.getPotencia_reativa_medicao());
            writeToFile(time+".csv", "\n , Fator de Potência, " + estacao.getFator_potencia_placa() + ", " + estacao.getFator_potencia_medicao());
            writeToFile(time+".csv", "\n , Rotação (rpm), " + estacao.getRotacao_placa() + ", " + estacao.getRotacao_medicao());
            writeToFile(time+".csv", "\n , Fabricante, " + estacao.getFabricante_motor() + ", , ");
            writeToFile(time+".csv", "\n\n");

            // BOMBA
            writeToFile(time+".csv", "\nBomba, Fabricante, " + estacao.getFabricante_bomba() + ", ");
            writeToFile(time+".csv", "\n , Vazão (m³/h), " + estacao.getVazao_placa() + ", ");
            writeToFile(time+".csv", "\n , Altura monométrica (mca), " + estacao.getAltura_monometrica_placa() + ", ");
            writeToFile(time+".csv", "\n\n");

            // SISTEMA
            writeToFile(time+".csv", "\n , Tipo de partida, " + estacao.getTipo_partida() + ", ");
            writeToFile(time+".csv", "\n , Banco de capacitores, " + estacao.getBanco_capacitores() + ", ");
            writeToFile(time+".csv", "\n , Sistema supervisionado, " + estacao.getSistema_supervisionado() + ", ");
            writeToFile(time+".csv", "\n\n\n");

        }
    }

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
}
