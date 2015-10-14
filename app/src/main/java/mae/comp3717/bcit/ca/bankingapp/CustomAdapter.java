package mae.comp3717.bcit.ca.bankingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;

public class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, String[] resource) {
        super(context, R.layout.custom_row, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView         = inflater.inflate(R.layout.custom_row, parent, false);
        TextView acctName       = (TextView) customView.findViewById(R.id.bankingType);
        TextView currency       = (TextView) customView.findViewById(R.id.bankingCurrency);
        TextView amount         = (TextView) customView.findViewById(R.id.bankingAmt);
        String category         = getItem(position);

        // set values
        DecimalFormat fmt = new DecimalFormat("$ #,###.00");
        acctName.setText(category);
        currency.setText("");
        amount.setText(fmt.format(AcctSummaryFragment.acctTotals.get(category)));

        return customView;
    }
}
