package gabrielmmelo.com.saaes;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

public class Form2Activity extends DebugActivity {

    private static String param_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fabPrevious = (FloatingActionButton) findViewById(R.id.fabPrevious);
        FloatingActionButton fabNext = (FloatingActionButton) findViewById(R.id.fabNext);
        fabPrevious.setOnClickListener(onClickFabPrevious());
        fabNext.setOnClickListener(onClickFabNext());

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(param_1, ((TextView) findViewById(R.id.param_1)).getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView aux = (TextView) findViewById(R.id.param_1);
        aux.setText(savedInstanceState.getString(param_1));
    }

    /*
                Criação de método próprio para tratar o evento. O método serve apenas para o btnStart.
             */
    private View.OnClickListener onClickFabPrevious(){
        return new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Form1Activity.class);
                startActivity(intent);
            }
        };

    }

    private View.OnClickListener onClickFabNext(){
        return new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Form2Activity.class);
                startActivity(intent);
            }
        };
    }

    private Context getContext(){
        return this;
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