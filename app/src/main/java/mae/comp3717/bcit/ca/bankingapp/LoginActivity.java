package mae.comp3717.bcit.ca.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TableLayout table;
    private Button signin;
    private Button create;
    private Button register;
    private Button cancel;
    private Switch remember;
    private Intent intent;

    public static boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        table       = (TableLayout) findViewById (R.id.reg_table);
        signin      = (Button) findViewById(R.id.sign_in_button);
        create      = (Button) findViewById(R.id.create_registration);
        register    = (Button) findViewById(R.id.registration_button);
        cancel      = (Button) findViewById(R.id.cancel_registration);
        remember    = (Switch) findViewById(R.id.remember_me);
        //final Spinner spinner   = (Spinner) findViewById(R.id.spinner);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.questions, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);

        signin.setVisibility(View.VISIBLE);
        create.setVisibility(View.VISIBLE);
        register.setVisibility(View.GONE);
        table.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }

    public void showRegistration(final View view) {

        remember.setVisibility(View.GONE);
        signin.setVisibility(View.GONE);
        create.setVisibility(View.GONE);
        register.setVisibility(View.VISIBLE);
        table.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.VISIBLE);
    }

    public void hideRegistration(final View view) {

        remember.setVisibility(View.VISIBLE);
        signin.setVisibility(View.VISIBLE);
        create.setVisibility(View.VISIBLE);
        register.setVisibility(View.GONE);
        table.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);
    }

    public void register(final View view) {

        // TODO:  validate fields

        hideRegistration(view);
        create.setVisibility(View.GONE);
        //create.setText("Thank you for registering.  Please sign in.");

        Toast toast = Toast.makeText(this, R.string.thankyou, Toast.LENGTH_LONG);
        toast.show();
    }

    public void signin(final View view) {

        // TODO:  validate email and password

        LoginActivity.loggedIn = true;
        intent = new Intent(this, TabbedActivity.class);
        startActivity(intent);

    }

    // TODO:  add ability to reset password
}
