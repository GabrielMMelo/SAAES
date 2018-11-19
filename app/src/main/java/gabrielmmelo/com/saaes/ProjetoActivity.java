package gabrielmmelo.com.saaes;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;


public class ProjetoActivity extends DebugActivity {

    private ProjetoDB projetoDB;
    private final String TAG = "projeto";
    private Context context;
    /**
     * Called when the activity is starting.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        projetoDB = new ProjetoDB(context);
        Log.w("teste", "CRIOU");
        setContentView(R.layout.activity_projeto);
        Button btnNewProject = (Button) findViewById(R.id.btnNewProject);
        btnNewProject.setOnClickListener(onClickBtnNewProject());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void save(String nome){
        Projeto projeto = new Projeto(context, nome);
        projetoDB.save(projeto);
    }

    /**
     * OWN METHOD TO TREAT ON CLICK BUTTON EVENT
     * @return
     */
    private View.OnClickListener onClickBtnNewProject(){
        return new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                save(((TextView) findViewById(R.id.nome)).getText().toString());
                Log.i(TAG, "Gravou o projeto");
                finish();
            }
        };
    }
}
