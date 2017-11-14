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
        ShiftCipher shiftCipher = new ShiftCipher();
        Spinner actionSpinner = findViewById(R.id.cipherSpinner);
        EditText inputText = findViewById(R.id.inputText);
        EditText inputKey = findViewById(R.id.inputKey);
        Button btnCipher = findViewById(R.id.button);
        ciphers = getResources().getStringArray(R.array.ciphers);

        /*Main Activity Toolbar*/
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        /*CardView Setup*/
        RecyclerView mRecyclerView = findViewById(R.id.recycleView);
        RecyclerView.LayoutManager mLayoutManager;
        MyAdapter mAdapter = new MyAdapter(this);

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
            userMsg = inputText.getText().toString();

            if (inputText.getText().toString().isEmpty() || inputKey.getText().toString().isEmpty())
            {
                makeToast("Empty.");
            } else
            {
                userKey = Integer.parseInt(inputKey.getText().toString());

                if (actionSpinner.getSelectedItem().equals(ciphers[0]))
                {
                    userMsgEncrypt = shiftCipher.cipher(userMsg, userKey);

                    mAdapter.addMessage(new Message(userMsg,userMsgEncrypt, userKey));
                } else if (actionSpinner.getSelectedItem().equals(ciphers[1]))
                {
                    userMsgDecrypt = shiftCipher.decipher(userMsg, userKey);

                    mAdapter.addMessage(new Message(userMsg,userMsgDecrypt, userKey));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_about:
            {
                startActivity(new Intent(this, AboutActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            }
        }
        return false;
    }

    public void makeToast(String str)
    {
        Context context = getApplicationContext();
        //CharSequence text = getString(res);
        CharSequence text = str;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}