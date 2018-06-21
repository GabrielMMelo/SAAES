package gabrielmmelo.com.saaes;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;

/**
 * Class that override all activities lifecycle methods to print some debug on <strong>Logcat</strong>
 */
public class DebugActivity extends AppCompatActivity {

    /**
     * Called when the activity is starting.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.i("DEBUG", "CRIADO");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("DEBUG", "COMEÇADO");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("DEBUG", "PAUSADO");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("DEBUG", "PARADO");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("DEBUG", "DESTRUÍDO");
    }
}
