package gabrielmmelo.com.saaes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ProjetoDB extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "conservacao_de_energia.sqlite";
    public static final int VERSAO_BANCO = 2;

    private static final String TAG = "sql";

    public ProjetoDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Criando tabela Projeto");
        db.execSQL("CREATE TABLE IF NOT EXISTS projeto(_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nome VARCHAR(100) UNIQUE NOT NULL)");
        Log.i(TAG, "Tabela projeto criada com sucesso");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If change database, sql here!
    }

    public long save(Projeto projeto){
        SQLiteDatabase db = getWritableDatabase();

        long id;
        try{
            ContentValues values = new ContentValues();

            // LOCAL
            values.put("nome", projeto.getNome());
            id = db.insert("projeto", "", values);

            return id;
        }
        finally {
            db.close();
        }
    }

    public List<Projeto> getAll(){
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor c = db.query("projeto", null, null, null, null, null, null, null);
            return toList(c);
        }finally {
            db.close();
        }
    }

    private List<Projeto> toList(Cursor c){
        List<Projeto> projetos = new ArrayList<Projeto>();
        if(c.moveToFirst()){
            do {
                Projeto projeto = new Projeto(
                        c.getString(c.getColumnIndex("nome"))
                );
                projetos.add(projeto);
            }while(c.moveToNext());
        }
        return projetos;
    }
}

