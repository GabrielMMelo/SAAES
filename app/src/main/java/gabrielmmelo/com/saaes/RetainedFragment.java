package gabrielmmelo.com.saaes;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.TextView;

public class RetainedFragment extends Fragment {

    // data object we want to retain
    private TextView textTest ;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(String data) {
        this.textTest.setText(data);
    }

    public TextView getData() {
        return this.textTest;
    }
}
