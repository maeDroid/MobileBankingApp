package mae.comp3717.bcit.ca.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


/**
 * An activity representing a list of Messages. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MessageDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link MessageListFragment} and the item details
 * (if present) is a {@link MessageDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link MessageListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class MessageListActivity extends AppCompatActivity
        implements MessageListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        if (findViewById(R.id.message_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((MessageListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.message_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem sign_in    = menu.findItem(R.id.action_sign_in);
        MenuItem sign_out   = menu.findItem(R.id.action_sign_out);
        MenuItem messages   = menu.findItem(R.id.action_messages);
        MenuItem summary    = menu.findItem(R.id.action_acct_summary);

        messages.setVisible(false);

        if (!LoginActivity.loggedIn) {
            sign_out.setVisible(false);
            sign_in.setVisible(true);
            summary.setVisible(false);
        } else {
            sign_out.setVisible(true);
            sign_in.setVisible(false);
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
            intent = new Intent(this, AcctSummaryActivity.class);
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

    /**
     * Callback method from {@link MessageListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(MessageDetailFragment.ARG_ITEM_ID, id);
            MessageDetailFragment fragment = new MessageDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.message_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, MessageDetailActivity.class);
            detailIntent.putExtra(MessageDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
