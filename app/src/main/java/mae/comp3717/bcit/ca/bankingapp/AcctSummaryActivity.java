package mae.comp3717.bcit.ca.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AcctSummaryActivity extends AppCompatActivity {

    private boolean expandBanking = true;
    private boolean expandBorrowing = false;
    private boolean expandInvesting = false;
    private ListView banking_listview;
    private ListView borrowing_listview;
    private ListView investing_listview;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acct_summary);

        Toast.makeText(this, "Acct Summary logged in: " + LoginActivity.loggedIn, Toast.LENGTH_LONG).show();

        //String[] categories = {"Banking", "Borrowing", "Investing"};
        String[] bankingList = {"Savings", "Chequing", "USD Savings"};
        String[] borrowingList = {"VISA", "Mortgage"};
        String[] investingList = {"Bonds", "GIC", "TFSA", "RRSP", "Mutual Funds", "Stocks"};

        //ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, categories);
        ListAdapter bankingListAdapter = new CustomAdapter(this, bankingList);
        banking_listview = (ListView) findViewById(R.id.banking_listview);
        banking_listview.setAdapter(bankingListAdapter);

        ListAdapter borrowingListAdapter = new CustomAdapter(this, borrowingList);
        borrowing_listview = (ListView) findViewById(R.id.borrowing_listview);
        borrowing_listview.setAdapter(borrowingListAdapter);
        borrowing_listview.setVisibility(View.GONE);

        ListAdapter investingListAdapter = new CustomAdapter(this, investingList);
        investing_listview = (ListView) findViewById(R.id.investing_listview);
        investing_listview.setAdapter(investingListAdapter);
        investing_listview.setVisibility(View.GONE);

        banking_listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                        String cat_name = String.valueOf(parent.getItemAtPosition(index));
                        Toast.makeText(AcctSummaryActivity.this, cat_name, Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem sign_in    = menu.findItem(R.id.action_sign_in);
        MenuItem sign_out   = menu.findItem(R.id.action_sign_out);
        MenuItem messages   = menu.findItem(R.id.action_messages);

        if (!LoginActivity.loggedIn) {
            sign_out.setVisible(false);
            sign_in.setVisible(true);
            messages.setVisible(false);
        } else {
            sign_out.setVisible(true);
            sign_in.setVisible(false);
            messages.setVisible(true);
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

    public void toggleBankingListview(final View view) {

        //Toast.makeText(AcctSummaryActivity.this, "toggle banking " + expandBanking , Toast.LENGTH_LONG).show();
        ImageButton arrow = (ImageButton) findViewById(R.id.ib_banking);

        if (expandBanking) {
            banking_listview.setVisibility(View.GONE);
            expandBanking = false;
            arrow.setImageResource(R.drawable.ic_keyboard_arrow_down_white_36dp);
        } else {
            banking_listview.setVisibility(View.VISIBLE);
            expandBanking = true;
            arrow.setImageResource(R.drawable.ic_keyboard_arrow_up_white_36dp);
        }
    }

    public void toggleBorrowingListview(final View view) {

        ImageButton arrow = (ImageButton) findViewById(R.id.ib_borrowing);
        //Toast.makeText(AcctSummaryActivity.this, "toggle borrowing " + expandBorrowing , Toast.LENGTH_LONG).show();

        if (expandBorrowing) {
            borrowing_listview.setVisibility(View.GONE);
            expandBorrowing = false;
            arrow.setImageResource(R.drawable.ic_keyboard_arrow_down_white_36dp);
        } else {
            borrowing_listview.setVisibility(View.VISIBLE);
            expandBorrowing = true;
            arrow.setImageResource(R.drawable.ic_keyboard_arrow_up_white_36dp);
        }
    }

    public void toggleInvestingListview(final View view) {

        //Toast.makeText(AcctSummaryActivity.this, "toggle investing " + expandInvesting , Toast.LENGTH_LONG).show();
        ImageButton arrow = (ImageButton) findViewById(R.id.ib_investing);

        if (expandInvesting) {
            investing_listview.setVisibility(View.GONE);
            expandInvesting = false;
            arrow.setImageResource(R.drawable.ic_keyboard_arrow_down_white_36dp);
        } else {
            investing_listview.setVisibility(View.VISIBLE);
            expandInvesting = true;
            arrow.setImageResource(R.drawable.ic_keyboard_arrow_up_white_36dp);
        }
    }


}
