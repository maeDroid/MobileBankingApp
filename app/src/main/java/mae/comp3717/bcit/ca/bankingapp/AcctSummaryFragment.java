package mae.comp3717.bcit.ca.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.HashMap;


public class AcctSummaryFragment extends Fragment{

    private boolean expandBanking = true;
    private boolean expandBorrowing = true;
    private boolean expandInvesting = false;

    private View rootView;
    private ListView banking_listview;
    private ListView borrowing_listview;
    private ListView investing_listview;
    private Button btn_banking;
    private Button btn_borrowing;
    private Button btn_investing;

    public static HashMap<String, Double> acctTotals = new HashMap<String, Double>();

    public static AcctSummaryFragment newInstance() {
        AcctSummaryFragment summary = new AcctSummaryFragment();
        return summary;
    }

    public AcctSummaryFragment() {
        // empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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

        rootView = inflater.inflate(R.layout.activity_acct_summary, container, false);

        // set listviews,adapters, and listeners
        ListAdapter bankingListAdapter = new CustomAdapter(getActivity().getBaseContext(), bankingList);
        banking_listview = (ListView) rootView.findViewById(R.id.banking_listview);
        banking_listview.setAdapter(bankingListAdapter);
        banking_listview.setOnItemClickListener(new loadDetails());

        ListAdapter borrowingListAdapter = new CustomAdapter(getActivity().getBaseContext(), borrowingList);
        borrowing_listview = (ListView) rootView.findViewById(R.id.borrowing_listview);
        borrowing_listview.setAdapter(borrowingListAdapter);
        borrowing_listview.setOnItemClickListener(new loadDetails());

        ListAdapter investingListAdapter = new CustomAdapter(getActivity().getBaseContext(), investingList);
        investing_listview = (ListView) rootView.findViewById(R.id.investing_listview);
        investing_listview.setAdapter(investingListAdapter);
        investing_listview.setOnItemClickListener(new loadDetails());
        investing_listview.setVisibility(View.GONE);

        // sets listeners for header sections to toggle open/close
        btn_banking = (Button) rootView.findViewById(R.id.btn_banking);
        btn_borrowing = (Button) rootView.findViewById(R.id.btn_borrowing);
        btn_investing = (Button) rootView.findViewById(R.id.btn_investing);
        btn_banking.setOnClickListener(new toggleBankingList());
        btn_borrowing.setOnClickListener(new toggleBorrowingList());
        btn_investing.setOnClickListener(new toggleInvestingList());

        return rootView;
    }

    /**
     *  Listener for OnItemClick events on the listview items.
     *  Hardcoded to load the same 'Acct Detail Activity' regardless of what item is clicked.
     */
    public class loadDetails implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

            DecimalFormat fmt = new DecimalFormat("$ #,###.00");
            String cat_name = String.valueOf(adapter.getItemAtPosition(position));
            Toast.makeText(getActivity().getBaseContext(), "Mock data for Chequing Account", Toast.LENGTH_SHORT).show();

            TextView listText = (TextView) view.findViewById(R.id.bankingType);
            String acctType = listText.getText().toString();
            String acctTotal = fmt.format(AcctSummaryActivity.acctTotals.get(cat_name));
            Intent intent = new Intent(getActivity().getBaseContext(), AcctDetailActivity.class);
            intent.putExtra("acctType", acctType);
            intent.putExtra("acctTotal", acctTotal);
            startActivity(intent);
        }
    }

    /**
     *  Handles toggling open and close the Banking ListView
     */
    public class toggleBankingList implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            ImageButton arrow = (ImageButton) rootView.findViewById(R.id.ib_banking);

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
    }

    /**
     *  Handles toggling open and close the Banking ListView
     */
    public class toggleBorrowingList implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            ImageButton arrow = (ImageButton) rootView.findViewById(R.id.ib_borrowing);

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
    }

    /**
     *  Handles toggling open and close the Banking ListView
     */
    public class toggleInvestingList implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            ImageButton arrow = (ImageButton) rootView.findViewById(R.id.ib_investing);

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


}
