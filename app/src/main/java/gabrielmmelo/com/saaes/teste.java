package gabrielmmelo.com.saaes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// log

public class teste extends AppCompatActivity {

    /*
    TAG para debug no LogCat usando os métodos de Log -> (v,d,i,w,e)
    private static final String TAG = "livro";
     */

    private static String stringTeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            Log.w("teste", "CRIOU");
            setContentView(R.layout.activity_main);
            Button btnStart = (Button) findViewById(R.id.btnStart);
            btnStart.setOnClickListener(onClickBtnStart());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("TESTE2",savedInstanceState.getString(stringTeste));
        TextView aux = (TextView) findViewById(R.id.textTest);
        aux.setText(savedInstanceState.getString(stringTeste));

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(stringTeste, ((TextView) findViewById(R.id.textTest)).getText().toString());
        Log.i("TESTE",outState.getString(stringTeste));
        super.onSaveInstanceState(outState);

    }

    /*
            Criação de método próprio para tratar o evento. O método serve apenas para o btnStart.
         */
    private View.OnClickListener onClickBtnStart(){
        return new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(teste.this, "Teste2", Toast.LENGTH_SHORT).show();
            }
        };
    }
}