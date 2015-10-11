package mae.comp3717.bcit.ca.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TableLayout table = (TableLayout) findViewById (R.id.reg_table);
        final Button signin     = (Button) findViewById(R.id.sign_in_button);
        final Button create     = (Button) findViewById(R.id.create_registration);
        final Button register   = (Button) findViewById(R.id.registration_button);
        final Button cancel     = (Button) findViewById(R.id.cancel_registration);

        signin.setVisibility(View.VISIBLE);
        create.setVisibility(View.VISIBLE);
        register.setVisibility(View.GONE);
        table.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void showRegistration(final View view) {

        final TableLayout table = (TableLayout) findViewById (R.id.reg_table);
        final Button signin     = (Button) findViewById(R.id.sign_in_button);
        final Button create     = (Button) findViewById(R.id.create_registration);
        final Button register   = (Button) findViewById(R.id.registration_button);
        final Button cancel     = (Button) findViewById(R.id.cancel_registration);

        signin.setVisibility(View.GONE);
        create.setVisibility(View.GONE);
        register.setVisibility(View.VISIBLE);
        table.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.VISIBLE);
    }

    public void hideRegistration(final View view) {

        final TableLayout table = (TableLayout) findViewById (R.id.reg_table);
        final Button signin     = (Button) findViewById(R.id.sign_in_button);
        final Button create     = (Button) findViewById(R.id.create_registration);
        final Button register   = (Button) findViewById(R.id.registration_button);
        final Button cancel     = (Button) findViewById(R.id.cancel_registration);

        signin.setVisibility(View.VISIBLE);
        create.setVisibility(View.VISIBLE);
        register.setVisibility(View.GONE);
        table.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);
    }

    public void register(final View view) {

        Intent intent;

        //intent      = new Intent(this, AcctSummaryActivity.class);
        //startActivity(intent);
    }

    public void signin(final View view) {

        Intent intent;

        //intent      = new Intent(this, AcctSummaryActivity.class);
        //startActivity(intent);
    }
}
