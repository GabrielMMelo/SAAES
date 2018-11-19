package gabrielmmelo.com.saaes;

// TODO: Verify if fields are empty on click button
// TODO: Images handler



import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// log
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Estacao estacao = new Estacao(getContext());
    private Csv csv = new Csv(this);

    /**
     * Called when the activity is starting.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("teste", "CRIOU");
        setContentView(R.layout.activity_main_tablet);
        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(onClickBtnStart());
        Button btnExportCsv = (Button) findViewById(R.id.btnExportCsv);
        btnExportCsv.setOnClickListener(onClickBtnExportCsv());
        Button btnCreateProject = (Button) findViewById(R.id.btnCreateProject);
        btnCreateProject.setOnClickListener(onClickBtnCreateProject());
        TextView author = (TextView) findViewById(R.id.author);
        author.setText(Html.fromHtml("Por <a href=\"https://github.com/GabrielMMelo\"> Gabriel M. Melo </a>"));
        author.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
    }

    /**
     * OWN METHOD TO TREAT ON CLICK BUTTON EVENT
     * @return
     */
    private View.OnClickListener onClickBtnStart(){
        return new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FormActivity.class);
                startActivity(intent);
            }
        };
    }

    /**
     * OWN METHOD TO TREAT ON CLICK BUTTON EVENT
     * @return
     */
    private View.OnClickListener onClickBtnExportCsv(){
        return new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                csv.exportCSV(estacao.getAll());
                Toast.makeText(getContext(), ".csv exportado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    /**
     * OWN METHOD TO TREAT ON CLICK BUTTON EVENT
     * @return
     */
    private View.OnClickListener onClickBtnCreateProject(){
        return new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProjetoActivity.class);
                startActivity(intent);
            }
        };
    }
    /**
     * AUX METHOD TO GET CONTEXT OF ACTIVITY
     * @return actual context
     */
    private Context getContext(){
        return this;
    }
}