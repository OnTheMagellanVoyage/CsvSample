package com.example.csvsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.toString();
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.textView);
        loadAsset();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadAsset() {
        try {
            InputStream is = getAssets().open("Items.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            ArrayList<ArrayList<String>> list = new ArrayList();
            while ( (line = reader.readLine()) != null ) {
                Log.d(TAG, line);
                StringTokenizer st = new StringTokenizer(line, ",");
                String item = null;
                ArrayList<String> al = new ArrayList();
                while(st.hasMoreTokens()) {
                    item = st.nextToken();
                    al.add(item.trim());
                }
                list.add(al);
            }
            reader.close();
            for(int i=0;i<list.size();i++) {
                for(int j=0;j<list.get(i).size();j++) {
                    tv.append(list.get(i).get(j));
                    if(j==list.get(i).size()-1) {
                        tv.append("\n");
                    } else {
                        tv.append("|");
                    }
                }
            }
        } catch (IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }
}
