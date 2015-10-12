package mae.comp3717.bcit.ca.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TableLayout table;
    private Button signin;
    private Button create;
    private Button register;
    private Button cancel;
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

        Toast.makeText(this, "Login logged in: " + LoginActivity.loggedIn, Toast.LENGTH_LONG).show();

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
            Toast.makeText(this, "Locate Branch", Toast.LENGTH_LONG).show();
            intent = new Intent(this, LocatorActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_sign_in && !LoginActivity.loggedIn) {
            Toast.makeText(this, "Sign in", Toast.LENGTH_LONG).show();
            intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_sign_out && LoginActivity.loggedIn) {
            Toast.makeText(this, "Signing out", Toast.LENGTH_LONG).show();
            LoginActivity.loggedIn = false;
            intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_acct_summary && LoginActivity.loggedIn) {
            Toast.makeText(this, "Redirecting to Acct Summary", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, AcctSummaryActivity.class);
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

        return super.onOptionsItemSelected(item);
    }

    public void showRegistration(final View view) {

        signin.setVisibility(View.GONE);
        create.setVisibility(View.GONE);
        register.setVisibility(View.VISIBLE);
        table.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.VISIBLE);
    }

    public void hideRegistration(final View view) {

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

        intent = new Intent(this, AcctSummaryActivity.class);
        startActivity(intent);
        LoginActivity.loggedIn = true;
    }

    // TODO:  add ability to reset password
}
