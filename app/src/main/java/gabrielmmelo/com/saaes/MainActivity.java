package gabrielmmelo.com.saaes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// log
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static String stringTeste;

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
        setContentView(R.layout.activity_main);
        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(onClickBtnStart());

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
     * AUX METHOD TO GET CONTEXT OF ACTIVITY
     * @return actual context
     */
    private Context getContext(){
        return this;
    }
}