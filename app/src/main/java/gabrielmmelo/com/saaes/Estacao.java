package gabrielmmelo.com.saaes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Estacao extends SQLiteOpenHelper{

    public static final String NOME_BANCO = "conservacao_de_energia.sqlite";
    public static final int VERSAO_BANCO = 1;

    private static final String TAG = "sql";

    public Estacao(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Criando tabela 'Estacao'");
        db.execSQL("CREATE TABLE IF NOT EXISTS estacao(_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , cidade VARCHAR(100) NOT NULL, local VARCHAR(100) NOT NULL, tensao_placa FLOAT NOT NULL, tensao_medicao FLOAT NOT NULL, corrente_placa FLOAT NOT NULL, corrente_medicao FLOAT NOT NULL, potencia_ativa_placa FLOAT NOT NULL, potencia_ativa_medicao FLOAT NOT NULL, potencia_reativa_placa FLOAT NOT NULL, potencia_reativa_medicao FLOAT NOT NULL, fator_potencia_placa FLOAT NOT NULL, fator_potencia_medicao FLOAT NOT NULL, rotacao_placa FLOAT NOT NULL, rotacao_medicao FLOAT NOT NULL, fabricante_motor VARCHAR(100) NOT NULL, fabricante_bomba VARCHAR(100) NOT NULL, vazao_placa FLOAT NOT NULL, vazao_medicao FLOAT NOT NULL, altura_monometrica_placa FLOAT NOT NULL, altura_monometrica_medicao FLOAT NOT NULL)");
        Log.i(TAG, "Tabela 'Estacao criada com sucesso");

    }

    public long save(String _local, String _placa, String _medicao){
        SQLiteDatabase db = getWritableDatabase();

        long id;
        try{
            JSONObject local = new JSONObject(_local);
            JSONObject placa = new JSONObject(_placa);
            JSONObject medicao = new JSONObject(_medicao);
            ContentValues values = new ContentValues();

            // LOCAL
            values.put("cidade", local.optString("cidade"));
            values.put("local", local.optString("local"));

            // PLACA
            values.put("tensao_placa", placa.optString("tensao"));
            values.put("corrente_placa", placa.optString("corrente"));
            values.put("potencia_ativa_placa", placa.optString("potencia_ativa"));
            values.put("potencia_reativa_placa", placa.optString("potencia_reativa"));
            values.put("fator_potencia_placa", placa.optString("fator_potencia"));
            values.put("rotacao_placa", placa.optString("rotacao"));
            values.put("fabricante_motor", placa.optString("fabricante_motor"));
            values.put("altura_monometrica_placa", placa.optString("altura_monometrica"));
            values.put("vazao_placa", placa.optString("vazao"));
            values.put("fabricante_bomba", placa.optString("fabricante_bomba"));

            // MEDIÇÃO
            values.put("tensao_medicao", medicao.optString("tensao"));
            values.put("corrente_medicao", medicao.optString("corrente"));
            values.put("potencia_ativa_medicao", medicao.optString("potencia_ativa"));
            values.put("potencia_reativa_medicao", medicao.optString("potencia_reativa"));
            values.put("fator_potencia_medicao", medicao.optString("fator_potencia"));
            values.put("rotacao_medicao", medicao.optString("rotacao"));
            values.put("altura_monometrica_medicao", medicao.optString("altura_monometrica"));
            values.put("vazao_medicao", medicao.optString("vazao"));

            id = db.insert("estacao", "", values);

            return id;
        }catch (JSONException e) {
            e.printStackTrace();
        }

        finally {
            db.close();
        }

        return -1;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int findAll(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c;
        try{
            c = db.query("estacao",null, null, null, null, null, null, null);
            return c.getCount();

        }finally {
            db.close();
        }
    }

}
