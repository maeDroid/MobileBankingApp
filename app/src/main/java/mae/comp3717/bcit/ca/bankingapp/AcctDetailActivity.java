package mae.comp3717.bcit.ca.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class AcctDetailActivity extends AppCompatActivity {

    @Override
    /**
     *  Hardcoded to show the same mock data (for the Chequing Account)
     *  regardless of what account is actually clicked on.  The title
     *  in the action bar and the account balance are dynamically loaded.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acct_detail);

        TextView tv_balance = (TextView) findViewById(R.id.tv_acct_balance);
        Intent intent       = getIntent();
        String acctname     = intent.getStringExtra("acctType");
        String balance      = intent.getStringExtra("acctTotal");

        setTitle(acctname + " Account Details");
        tv_balance.setText(balance);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem sign_in    = menu.findItem(R.id.action_sign_in);
        MenuItem sign_out   = menu.findItem(R.id.action_sign_out);
        MenuItem messages   = menu.findItem(R.id.action_messages);
        MenuItem summary    = menu.findItem(R.id.action_acct_summary);

        if (!LoginActivity.loggedIn) {
            sign_out.setVisible(false);
            sign_in.setVisible(true);
            messages.setVisible(false);
            summary.setVisible(false);
        } else {
            sign_out.setVisible(true);
            sign_in.setVisible(false);
            messages.setVisible(true);
            summary.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent;
        int id = item.getItemId();

        if (id == R.id.action_locate_branch) {
            intent = new Intent(this, LocatorActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_sign_in && !LoginActivity.loggedIn) {
            intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_sign_out && LoginActivity.loggedIn) {
            LoginActivity.loggedIn = false;
            Toast.makeText(this, "Signing out", Toast.LENGTH_SHORT).show();

            intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_acct_summary && LoginActivity.loggedIn) {
            intent = new Intent(this, TabbedActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_messages && LoginActivity.loggedIn) {
            intent = new Intent(this, MessageListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_settings) {
            intent = new Intent(this, SettingsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        /*
        if (id == R.id.action_about) {
            intent = new Intent(this, AboutActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_news) {
            intent = new Intent(this, NewsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_rates) {
            intent = new Intent(this, RatesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }

}
