package mae.comp3717.bcit.ca.bankingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, String[] resource) {
        super(context, R.layout.custom_row, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        String category     = getItem(position);
        TextView acctName   = (TextView) customView.findViewById(R.id.bankingType);
        TextView currency   = (TextView) customView.findViewById(R.id.bankingCurrency);
        TextView amount     = (TextView) customView.findViewById(R.id.bankingAmt);

        acctName.setText(category);
        currency.setText("");
        amount.setText("$ 9.99");

        return customView;
    }
}
