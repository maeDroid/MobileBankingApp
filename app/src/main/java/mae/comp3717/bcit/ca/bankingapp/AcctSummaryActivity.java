package mae.comp3717.bcit.ca.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.HashMap;

public class AcctSummaryActivity extends AppCompatActivity {

    private boolean expandBanking = true;
    private boolean expandBorrowing = true;
    private boolean expandInvesting = false;
    private ListView banking_listview;
    private ListView borrowing_listview;
    private ListView investing_listview;
    public static HashMap<String, Double> acctTotals = new HashMap<String, Double>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acct_summary);

        // hard coded data to display
        String[] bankingList    = {"Savings", "Chequing"};
        String[] borrowingList  = {"VISA"};
        String[] investingList  = {"Bonds", "GIC", "TFSA", "RRSP"};
        acctTotals.put("Savings", 1021.23);
        acctTotals.put("Chequing", 237.98);
        acctTotals.put("VISA", 65.73);
        acctTotals.put("Bonds", 5000.00);
        acctTotals.put("GIC", 1500.00);
        acctTotals.put("TFSA", 12576.88);
        acctTotals.put("RRSP", 25641.30);

        // set listviews,adapters, and listeners
        ListAdapter bankingListAdapter = new CustomAdapter(this, bankingList);
        banking_listview = (ListView) findViewById(R.id.banking_listview);
        banking_listview.setAdapter(bankingListAdapter);
        banking_listview.setOnItemClickListener(new loadDetails());

        ListAdapter borrowingListAdapter = new CustomAdapter(this, borrowingList);
        borrowing_listview = (ListView) findViewById(R.id.borrowing_listview);
        borrowing_listview.setAdapter(borrowingListAdapter);
        borrowing_listview.setOnItemClickListener(new loadDetails());

        ListAdapter investingListAdapter = new CustomAdapter(this, investingList);
        investing_listview = (ListView) findViewById(R.id.investing_listview);
        investing_listview.setAdapter(investingListAdapter);
        investing_listview.setOnItemClickListener(new loadDetails());
        investing_listview.setVisibility(View.GONE);

    }

    /**
     *  Listener for OnItemClick events on the listview items.
     *  Hardcoded to load the same 'Acct Detail Activity' regardless of what item is clicked.
     */
    public class loadDetails implements OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

            DecimalFormat fmt = new DecimalFormat("$ #,###.00");
            String cat_name = String.valueOf(adapter.getItemAtPosition(position));
            Toast.makeText(AcctSummaryActivity.this, "Mock data for Chequing Account", Toast.LENGTH_SHORT).show();

            TextView listText = (TextView) view.findViewById(R.id.bankingType);
            String acctType = listText.getText().toString();
            String acctTotal = fmt.format(AcctSummaryActivity.acctTotals.get(cat_name));
            Intent intent = new Intent(AcctSummaryActivity.this, AcctDetailActivity.class);
            intent.putExtra("acctType", acctType);
            intent.putExtra("acctTotal", acctTotal);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem sign_in    = menu.findItem(R.id.action_sign_in);
        MenuItem sign_out   = menu.findItem(R.id.action_sign_out);
        MenuItem messages   = menu.findItem(R.id.action_messages);
        MenuItem summary    = menu.findItem(R.id.action_acct_summary);

        summary.setVisible(false);

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
        Intent intent;
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_locate_branch) {
            intent = new Intent(this, LocatorActivity.class);
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

        if (id == R.id.action_tabs && LoginActivity.loggedIn){
            intent = new Intent(this, TabbedActivity.class);
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

    /**
     *  Handles toggling open and close the Banking ListView
     */
    public void toggleBankingList(final View view) {

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

    /**
     *  Handles toggling open and close the Banking ListView
     */
    public void toggleBorrowingList(final View view) {

        ImageButton arrow = (ImageButton) findViewById(R.id.ib_borrowing);

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

    /**
     *  Handles toggling open and close the Banking ListView
     */
    public void toggleInvestingList(final View view) {

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
