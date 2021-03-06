package edu.carthage.zorpix.phonechooser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class AddWaiter extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_waiter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_waiter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_GetTip) {
            Log.d("AddWaiter", "Calculating tips");
            Intent intent = new Intent(this, MainActivity.class);
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

    public void addtime(View view)
    {
        final EditText editText = (EditText) findViewById(R.id.name);
        String name = editText.getText().toString();
        final EditText editText2 = (EditText) findViewById(R.id.tipercent);
        String tipercent = editText2.getText().toString();
        final EditText editText3 = (EditText) findViewById(R.id.restaurant);
        String restaurant = editText3.getText().toString();
        final EditText editText4 = (EditText) findViewById(R.id.notes);
        String notes = editText4.getText().toString();

        if (DBHelper.getInstance(this).checkWaiter(name, tipercent, restaurant, notes))
        {
            DBHelper.getInstance(this).addWaiter(name, tipercent, restaurant, notes);
            DBHelper.getInstance(this).addTip(name, tipercent, restaurant);
            editText.setText("");
            editText2.setText("");
            editText3.setText("");
            editText4.setText("");
            Log.d("AddWaiter", "Well that's a relief." + name);
        }
        else
        {
            Log.d("AddWaiter", "Well that's not a relief." + name);
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Uh oh");
            alertDialog.setMessage("A waiter with that name at that restaurant already exists!");
            alertDialog.setButton("Try again", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    editText.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
                }
            });
            alertDialog.show();
        }
    }
}
