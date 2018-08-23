package infinity.com.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import infinity.com.sampleutils.Check;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Check check = new Check(this);
        check.check();
    }
}
