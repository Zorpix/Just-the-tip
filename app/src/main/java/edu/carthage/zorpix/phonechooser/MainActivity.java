package edu.carthage.zorpix.phonechooser;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        if (id == R.id.action_AddWaiter) {
            Log.d("MainActivity", "Adding a waiter");
            Intent intent = new Intent(this, AddWaiter.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_DisplayTips) {
            Log.d("MainActivity", "Displaying Tips");
            Intent intent = new Intent(this, DisplayTips.class);
            startActivity(intent);
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_TipWaiter) {
            Log.d("MainActivity", "Tipping a waiter");
            Intent intent = new Intent(this, TipOldWaiter.class);
            startActivity(intent);
            return true;
        }

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_ViewWaiter) {
            Log.d("MainActivity", "Viewing a waiter");
            Intent intent = new Intent(this, ViewWaiters.class);
            startActivity(intent);
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    public void calculate(View view) {
        Intent intent = new Intent(this, TipActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        Log.d("MainActivity", message);
        intent.putExtra("tippy", message);
        startActivity(intent);
    }
}
