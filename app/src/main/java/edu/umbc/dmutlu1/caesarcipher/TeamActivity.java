package edu.umbc.dmutlu1.caesarcipher;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class TeamActivity extends AppCompatActivity
{
    private int eggCounter = 0;
    private int eggDisplay = 2;

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
                eggCounter++;

                if (eggCounter >= 4 && eggCounter < 6)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "You are now " + eggDisplay + " presses from revealing the easter egg.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    eggDisplay--;
                }
            }

            if (eggCounter == 6)
            {
                imageView.setImageResource(R.drawable.caesar_salad);
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