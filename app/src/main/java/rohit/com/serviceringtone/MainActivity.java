package rohit.com.serviceringtone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton mImageButtonPlay, mImageButtonMute;
    Button mButtonNext;
    boolean state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageButtonMute = findViewById(R.id.imageButtonMute);
        mImageButtonPlay = findViewById(R.id.imageButtonPlay);
        mButtonNext = findViewById(R.id.buttonNext);

        mImageButtonPlay.setOnClickListener(this);
        mImageButtonMute.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId()== R.id.imageButtonPlay) {
            if (state){
                stopService(new Intent(this, MyService.class));
                state = false;

            }
            Toast.makeText(this, "Played is tapped ", Toast.LENGTH_LONG).show();
            mImageButtonPlay.setVisibility(View.INVISIBLE);
            mImageButtonMute.setVisibility(View.VISIBLE);
            startService(new Intent(this, MyService.class));

        } else if (v.getId()==R.id.imageButtonMute ) {
            state = true;
            Toast.makeText(this, "Mute is tapped ", Toast.LENGTH_LONG).show();
            mImageButtonPlay.setVisibility(View.VISIBLE);
            mImageButtonMute.setVisibility(View.INVISIBLE);
            stopService(new Intent(this, MyService.class));

        } else if (v.getId()== R.id.buttonNext){
            Intent mNextActivityIntent = new Intent(this,NextActivity.class);
            startActivity(mNextActivityIntent);


        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(this, MyService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(this, MyService.class));
        state = true;

    }
}
