package edu.umbc.dmutlu1.caesarcipher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private int userKey;
    private String userMsg;
    private String userMsgEncrypt;
    private String userMsgDecrypt;
    private String[] ciphers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declare and instantiate Charlie's ShiftCipher code.
        ShiftCipher shiftCipher = new ShiftCipher();

        Spinner actionSpinner = findViewById(R.id.cipherSpinner);
        EditText inputText = findViewById(R.id.inputText);
        EditText inputKey = findViewById(R.id.inputKey);
        Button btnCipher = findViewById(R.id.button);

        //Put the XML string array and place it into a String Array object.
        ciphers = getResources().getStringArray(R.array.ciphers);

        /*Main Activity Toolbar*/
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        /*CardView Setup*/
        RecyclerView mRecyclerView = findViewById(R.id.recycleView);
        RecyclerView.LayoutManager mLayoutManager;
        MyAdapter mAdapter = new MyAdapter(this);

        mAdapter.setHasStableIds(true);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mRecyclerView.setAdapter(mAdapter);

        //Run button code.
        btnCipher.setOnClickListener(view ->
        {
            //Hold users message.
            userMsg = inputText.getText().toString();

            if (inputText.getText().toString().isEmpty() || inputKey.getText().toString().isEmpty())
            {
                //If any inputs are empty, prompt user.
                makeToast(R.string.toast_empty);
            } else
            {
                //Grab and store the inputted cipher key.
                userKey = Integer.parseInt(inputKey.getText().toString());

                //If cipher is selected, encrypt message.
                if (actionSpinner.getSelectedItem().equals(ciphers[0]))
                {
                    userMsgEncrypt = shiftCipher.cipher(userMsg, userKey);

                    //Send a new message to RecycleView MyAdapter.
                    mAdapter.addMessage(new Message(userMsg, userMsgEncrypt, userKey));
                }
                //If Decipher is selected, decrypt message.
                else if (actionSpinner.getSelectedItem().equals(ciphers[1]))
                {
                    userMsgDecrypt = shiftCipher.decipher(userMsg, userKey);

                    //Send a new message to RecycleView MyAdapter.
                    mAdapter.addMessage(new Message(userMsg, userMsgDecrypt, userKey));
                }
            }
        });
    }

    /*Toolbar Overflow Menu*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    /*Popup Item Selection*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //Get the Id of the popup menu item.
        switch (item.getItemId())
        {
            //Start the AboutActivity when 'About' is selected.
            case R.id.action_about:
            {
                startActivity(new Intent(this, AboutActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            }
        }
        return false;
    }

    /*Toaster*/
    private void makeToast(int res)
    {
        Context context = getApplicationContext();
        CharSequence text = getString(res);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}