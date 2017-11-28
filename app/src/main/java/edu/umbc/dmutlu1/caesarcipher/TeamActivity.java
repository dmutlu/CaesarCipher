package edu.umbc.dmutlu1.caesarcipher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class TeamActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.img_rotate);

        ImageView imageView = findViewById(R.id.imageTeam);
        imageView.setOnClickListener(view -> {

            if (!animation.hasStarted() || animation.hasEnded())
            {
                imageView.startAnimation(animation);
            }
        });
    }

    //Plays an exit animation when the user presses the back button.
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}