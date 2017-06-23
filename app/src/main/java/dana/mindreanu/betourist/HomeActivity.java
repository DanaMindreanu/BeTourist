package dana.mindreanu.betourist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

public class HomeActivity extends AppCompatActivity {

    // for a cover activity
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
