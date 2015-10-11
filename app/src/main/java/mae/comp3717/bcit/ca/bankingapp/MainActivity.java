package mae.comp3717.bcit.ca.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(final View view) {
        final Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void launchNews(final View view) {
        //Intent intent = new Intent(this, NewsActivity.class);
        //startActivity(intent);
    }

    public void launchRates(final View view) {
        //Intent intent = new Intent(this, RatesActivity.class);
        //startActivity(intent);
    }

    public void launchAbout(final View view) {
        //Intent intent = new Intent(this, AboutActivity.class);
        //startActivity(intent);
    }

    public void locateBranch(final View view) {
        //Intent intent = new Intent(this, LocatorActivity.class);
        //startActivity(intent);
    }
}
