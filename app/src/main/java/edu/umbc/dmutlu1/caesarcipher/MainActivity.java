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
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private String userMsg;
    private String userMsgEncrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShiftCipher shiftCipher = new ShiftCipher();
        EditText inputText = findViewById(R.id.inputText);
        Button btnCipher = findViewById(R.id.button);

        /*Main Activity Toolbar*/
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        /*CardView Setup*/
        RecyclerView mRecyclerView = findViewById(R.id.recycleView);
        RecyclerView.LayoutManager mLayoutManager;
        RecyclerView.Adapter mAdapter;

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Insert data

        // specify an adapter
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);

        btnCipher.setOnClickListener(view ->
        {
            userMsg = inputText.getText().toString();
            userMsgEncrypt = shiftCipher.cipher(userMsg, 4);

            System.out.println(userMsg);
            System.out.println(userMsgEncrypt);
            System.out.println(shiftCipher.decipher(userMsgEncrypt, 4));
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