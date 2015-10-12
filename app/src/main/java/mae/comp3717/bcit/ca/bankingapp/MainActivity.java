package mae.comp3717.bcit.ca.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    public static boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem sign_in = menu.findItem(R.id.action_sign_in);
        MenuItem sign_out = menu.findItem(R.id.action_sign_out);
        if (!loggedIn) {
            sign_out.setVisible(false);
            sign_in.setVisible(true);
        } else {
            sign_out.setVisible(false);
            sign_in.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_locate_branch) {
            Toast.makeText(this, "Locate Branch", Toast.LENGTH_LONG).show();
            intent = new Intent(this, LocatorActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_sign_in) {
            Toast.makeText(this, "Sign in", Toast.LENGTH_LONG).show();
            intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_settings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show();
            intent = new Intent(this, SettingsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_about) {
            Toast.makeText(this, "About", Toast.LENGTH_LONG).show();
            intent = new Intent(this, AboutActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_news) {
            Toast.makeText(this, "News", Toast.LENGTH_LONG).show();
            intent = new Intent(this, NewsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_rates) {
            Toast.makeText(this, "Rates", Toast.LENGTH_LONG).show();
            intent = new Intent(this, RatesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    public void sign_in(final View view) {
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void launchNews(final View view) {
        intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }

    public void launchRates(final View view) {
        intent = new Intent(this, RatesActivity.class);
        startActivity(intent);
    }

    public void launchAbout(final View view) {
        intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void locateBranch(final View view) {
        intent = new Intent(this, LocatorActivity.class);
        startActivity(intent);
    }
}
