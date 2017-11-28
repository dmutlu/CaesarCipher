package edu.umbc.dmutlu1.caesarcipher;

import android.content.Context;
import android.content.res.Resources;
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
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        Context context = getApplicationContext();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.img_rotate);
        ImageView imageView = findViewById(R.id.imageTeam);

        imageView.setOnClickListener(view -> {

            //Check to see if animation is already running.
            if (!animation.hasStarted() || animation.hasEnded())
            {
                imageView.startAnimation(animation);
                eggCounter++;

                //When to show easter egg hint toasts.
                if (eggCounter >= 4 && eggCounter < 6)
                {
                    Resources res = getResources();

                    //Grab our plurals for the countdown hints.
                    String text = res.getQuantityString(R.plurals.easter_toast, eggDisplay, eggDisplay);
                    int duration = Toast.LENGTH_SHORT;

                    toast = Toast.makeText(context, text, duration);
                    toast.show();

                    eggDisplay--;
                }
            }

            //When to change the original image to the easter egg image.
            if (eggCounter == 6)
            {
                //Set to easter egg image.
                imageView.setImageResource(R.drawable.caesar_salad);

                //Acknowledgement toast.
                String text = getString(R.string.toast_reveal);
                int duration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, text, duration);
                toast.show();
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