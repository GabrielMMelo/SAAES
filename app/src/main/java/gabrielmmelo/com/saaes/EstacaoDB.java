package gabrielmmelo.com.saaes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class EstacaoDB extends SQLiteOpenHelper{

    public static final String NOME_BANCO = "conservacao_de_energia.sqlite";
    public static final int VERSAO_BANCO = 2;

    private static final String TAG = "sql";

    public EstacaoDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Criando tabela 'Estacao'");
        db.execSQL("CREATE TABLE IF NOT EXISTS estacao(_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , cidade VARCHAR(100) NOT NULL, local VARCHAR(100) NOT NULL, tensao_placa FLOAT NOT NULL, tensao_medicao FLOAT NOT NULL, corrente_placa FLOAT NOT NULL, corrente_medicao FLOAT NOT NULL, potencia_ativa_placa FLOAT NOT NULL, potencia_ativa_medicao FLOAT NOT NULL, potencia_reativa_placa FLOAT NOT NULL, potencia_reativa_medicao FLOAT NOT NULL, fator_potencia_placa FLOAT NOT NULL, fator_potencia_medicao FLOAT NOT NULL, rotacao_placa FLOAT NOT NULL, rotacao_medicao FLOAT NOT NULL, fabricante_motor VARCHAR(100) NOT NULL, fabricante_bomba VARCHAR(100) NOT NULL, vazao_placa FLOAT NOT NULL, altura_monometrica_placa FLOAT NOT NULL, tipo_partida VARCHAR(100) NOT NULL, sistema_supervisionado VARCHAR(100) NOT NULL, banco_capacitores VARCHAR(100) NOT NULL, endereco VARCHAR(300) NOT NULL, numero_instalacao VARCHAR(100) NOT NULL, observacoes VARCHAR(300) NOT NULL, Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)");
        Log.i(TAG, "Tabela 'Estacao criada com sucesso");

        Log.i(TAG, "Criando tabela Projeto");
        db.execSQL("CREATE TABLE IF NOT EXISTS projeto(_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT)");
        Log.i(TAG, "Tabela projeto criada com sucesso");
    }

    public long save(Estacao estacao){
        SQLiteDatabase db = getWritableDatabase();

        long id;
        try{
            ContentValues values = new ContentValues();

            // LOCAL
            values.put("cidade", estacao.getCidade());
            values.put("local", estacao.getLocal());

            // PLACA
            values.put("tensao_placa", estacao.getTensao_placa());
            values.put("corrente_placa", estacao.getCorrente_placa());
            values.put("potencia_ativa_placa", estacao.getPotencia_ativa_placa());
            values.put("potencia_reativa_placa", estacao.getPotencia_reativa_placa());
            values.put("fator_potencia_placa", estacao.getFator_potencia_placa());
            values.put("rotacao_placa", estacao.getRotacao_placa());
            values.put("fabricante_motor", estacao.getFabricante_motor());
            values.put("altura_monometrica_placa", estacao.getAltura_monometrica_placa());
            values.put("vazao_placa", estacao.getVazao_placa());
            values.put("fabricante_bomba", estacao.getFabricante_bomba());

            // MEDIÇÃO
            values.put("tensao_medicao", estacao.getTensao_medicao());
            values.put("corrente_medicao", estacao.getCorrente_medicao());
            values.put("potencia_ativa_medicao", estacao.getPotencia_ativa_medicao());
            values.put("potencia_reativa_medicao", estacao.getPotencia_reativa_medicao());
            values.put("fator_potencia_medicao", estacao.getFator_potencia_medicao());
            values.put("rotacao_medicao", estacao.getRotacao_medicao());

            // SISTEMA
            values.put("tipo_partida", estacao.getTipo_partida());
            values.put("banco_capacitores", estacao.getBanco_capacitores());
            values.put("sistema_supervisionado", estacao.getSistema_supervisionado());
            values.put("observacoes", estacao.getObservacoes());
            values.put("numero_instalacao", estacao.getNumero_instalacao());
            values.put("endereco", estacao.getEndereco());

            id = db.insert("estacao", "", values);

            return id;
        }
        finally {
            db.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If change database, sql here!
    }

    public int getCount(String table){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c;
        try{
            c = db.query(table,null, null, null, null, null, null, null);
            return c.getCount();

        }finally {
            db.close();
        }
    }

    public void execSQL(String sql){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL(sql);
        }finally {
            db.close();
        }
    }

    public void deleteTable(String table){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.delete(table, null, null);
        }finally {
            db.close();
        }
    }

    public List<Estacao> getAll(){
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor c = db.query("estacao", null, null, null, null, null, null, null);
            return toList(c);
        }finally {
            db.close();
        }
    }

    private List<Estacao> toList(Cursor c){
        List<Estacao> estacoes = new ArrayList<Estacao>();
        if(c.moveToFirst()){
            do {
                Estacao estacao = new Estacao(
                    c.getFloat(c.getColumnIndex("tensao_placa")),
                    c.getFloat(c.getColumnIndex("tensao_medicao")),
                    c.getFloat(c.getColumnIndex("corrente_placa")),
                    c.getFloat(c.getColumnIndex("corrente_medicao")),
                    c.getFloat(c.getColumnIndex("potencia_ativa_placa")),
                    c.getFloat(c.getColumnIndex("potencia_ativa_medicao")),
                    c.getFloat(c.getColumnIndex("potencia_reativa_placa")),
                    c.getFloat(c.getColumnIndex("potencia_reativa_medicao")),
                    c.getFloat(c.getColumnIndex("fator_potencia_placa")),
                    c.getFloat(c.getColumnIndex("fator_potencia_medicao")),
                    c.getFloat(c.getColumnIndex("rotacao_placa")),
                    c.getFloat(c.getColumnIndex("rotacao_medicao")),
                    c.getFloat(c.getColumnIndex("vazao_placa")),
                    c.getFloat(c.getColumnIndex("altura_monometrica_placa")),
                    c.getString(c.getColumnIndex("fabricante_bomba")),
                    c.getString(c.getColumnIndex("fabricante_motor")),
                    c.getString(c.getColumnIndex("cidade")),
                    c.getString(c.getColumnIndex("local")),
                    c.getString(c.getColumnIndex("tipo_partida")),
                    c.getString(c.getColumnIndex("banco_capacitores")),
                    c.getString(c.getColumnIndex("sistema_supervisionado")),
                    c.getString(c.getColumnIndex("numero_instalacao")),
                    c.getString(c.getColumnIndex("endereco")),
                    c.getString(c.getColumnIndex("observacoes"))
                );
                estacoes.add(estacao);
            }while(c.moveToNext());
        }
        return estacoes;
    }


    /**
     * Get an estacao by table id
     * @param id
     * @return
     */
    public Estacao getEstacao(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c;
        try{
            //c = db.query("estacao",null, "_id=?",new String[]{"2"}, null, null, null);
            c = db.query("estacao",null, "_id=?",new String[]{id+""} , null, null, null);
            c.moveToPosition(c.getCount() - 1);
            if (c.getCount() > 0) {
                Log.i("sql", c.getCount() + " CONTADOR DA QUERY");
                Log.i("sql", c.getString(c.getColumnIndex("cidade")) + " valor de cidade");
                Log.i("sql", c.getString(c.getColumnIndex("tensao_placa")) + " valor de tensao_placa");
                Estacao estacao = new Estacao(
                        c.getFloat(c.getColumnIndex("tensao_placa")),
                        c.getFloat(c.getColumnIndex("tensao_medicao")),
                        c.getFloat(c.getColumnIndex("corrente_placa")),
                        c.getFloat(c.getColumnIndex("corrente_medicao")),
                        c.getFloat(c.getColumnIndex("potencia_ativa_placa")),
                        c.getFloat(c.getColumnIndex("potencia_ativa_medicao")),
                        c.getFloat(c.getColumnIndex("potencia_reativa_placa")),
                        c.getFloat(c.getColumnIndex("potencia_reativa_medicao")),
                        c.getFloat(c.getColumnIndex("fator_potencia_placa")),
                        c.getFloat(c.getColumnIndex("fator_potencia_medicao")),
                        c.getFloat(c.getColumnIndex("rotacao_placa")),
                        c.getFloat(c.getColumnIndex("rotacao_medicao")),
                        c.getFloat(c.getColumnIndex("vazao_placa")),
                        c.getFloat(c.getColumnIndex("altura_monometrica_placa")),
                        c.getString(c.getColumnIndex("fabricante_bomba")),
                        c.getString(c.getColumnIndex("fabricante_motor")),
                        c.getString(c.getColumnIndex("cidade")),
                        c.getString(c.getColumnIndex("local")),
                        c.getString(c.getColumnIndex("tipo_partida")),
                        c.getString(c.getColumnIndex("banco_capacitores")),
                        c.getString(c.getColumnIndex("sistema_supervisionado")),
                        c.getString(c.getColumnIndex("numero_instalacao")),
                        c.getString(c.getColumnIndex("endereco")),
                        c.getString(c.getColumnIndex("observacoes"))
                );
                Log.i("sql", estacao.getCidade() + " valor de cidade pela classe");
                return estacao;
            }
        }finally {
            db.close();
        }
        return null;
    }

    public Estacao getEstacao(String cidade){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c;
        try{
            //c = db.query("estacao",null, "_id=?",new String[]{"2"}, null, null, null);
            c = db.query("estacao",null, "cidade=?",new String[]{cidade} , null, null, null);
            c.moveToPosition(c.getCount() - 1);
            if (c.getCount() > 0) {
                Log.i("sql", c.getCount() + " CONTADOR DA QUERY");
                Log.i("sql", c.getString(c.getColumnIndex("cidade")) + " valor de cidade");
                Log.i("sql", c.getString(c.getColumnIndex("tensao_placa")) + " valor de tensao_placa");
                Estacao estacao = new Estacao(
                        c.getFloat(c.getColumnIndex("tensao_placa")),
                        c.getFloat(c.getColumnIndex("tensao_medicao")),
                        c.getFloat(c.getColumnIndex("corrente_placa")),
                        c.getFloat(c.getColumnIndex("corrente_medicao")),
                        c.getFloat(c.getColumnIndex("potencia_ativa_placa")),
                        c.getFloat(c.getColumnIndex("potencia_ativa_medicao")),
                        c.getFloat(c.getColumnIndex("potencia_reativa_placa")),
                        c.getFloat(c.getColumnIndex("potencia_reativa_medicao")),
                        c.getFloat(c.getColumnIndex("fator_potencia_placa")),
                        c.getFloat(c.getColumnIndex("fator_potencia_medicao")),
                        c.getFloat(c.getColumnIndex("rotacao_placa")),
                        c.getFloat(c.getColumnIndex("rotacao_medicao")),
                        c.getFloat(c.getColumnIndex("vazao_placa")),
                        c.getFloat(c.getColumnIndex("altura_monometrica_placa")),
                        c.getString(c.getColumnIndex("fabricante_bomba")),
                        c.getString(c.getColumnIndex("fabricante_motor")),
                        c.getString(c.getColumnIndex("cidade")),
                        c.getString(c.getColumnIndex("local")),
                        c.getString(c.getColumnIndex("tipo_partida")),
                        c.getString(c.getColumnIndex("banco_capacitores")),
                        c.getString(c.getColumnIndex("sistema_supervisionado")),
                        c.getString(c.getColumnIndex("numero_instalacao")),
                        c.getString(c.getColumnIndex("endereco")),
                        c.getString(c.getColumnIndex("observacoes"))
                );
                Log.i("sql", estacao.getCidade() + " valor de cidade pela classe");
                return estacao;
            }
        }finally {
            db.close();
        }
        return null;
    }
}
