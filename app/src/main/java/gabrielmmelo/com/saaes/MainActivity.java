package gabrielmmelo.com.saaes;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// log
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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

    /*
            Criação de método próprio para tratar o evento. O método serve apenas para o btnStart.
         */
    private View.OnClickListener onClickBtnStart(){
        return new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Form1Activity.class);
                startActivity(intent);
            }
        };
    }

    private Context getContext(){
        return this;
    }
}