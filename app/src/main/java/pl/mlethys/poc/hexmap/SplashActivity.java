package pl.mlethys.poc.hexmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by mlethys on 2015-03-01.
 */
public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread waitThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(5000);
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                } finally {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        waitThread.start();
    }

}
