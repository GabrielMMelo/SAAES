package gabrielmmelo.com.saaes;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.lang.reflect.Field;
import java.util.Iterator;

public class FotosActivity extends AppCompatActivity {

    private FragmentManager fm = getSupportFragmentManager();
    private FotosFragment fotosFragment = new FotosFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos);
        if (savedInstanceState == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.layoutFotosFragment, fotosFragment, "FotosFragment");
            ft.commit();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle args = intent.getExtras();

        Log.i("TESTE", Float.toString(args.getFloat("placa_tensao")));

        /*
            trying to convert JSON object to bundle

        for (String key : args.keySet()) {
            Object value = args.get(key);
            Log.d("TESTE", String.format("%s %s (%s)", key,
                    value.toString(), value.getClass().getName()));
            for (Field field : value.getClass().getDeclaredFields()) {
                field.setAccessible(true); // You might want to set modifier to public first.
                try {
                    Object value2 = field.get(value);

                if (value2 != null) {
                    Log.d("TESTE", String.format("%s = %s", field.getName(),
                            value2));
                }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        */

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
