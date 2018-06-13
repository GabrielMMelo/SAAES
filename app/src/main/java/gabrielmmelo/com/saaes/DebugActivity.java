package gabrielmmelo.com.saaes;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;

public class DebugActivity extends AppCompatActivity {
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
