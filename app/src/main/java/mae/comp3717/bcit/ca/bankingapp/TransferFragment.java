package mae.comp3717.bcit.ca.bankingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class TransferFragment extends Fragment{

    Spinner from;
    Spinner to;
    EditText et_amount;
    EditText et_transfer_date;
    Button btn_transfer;

    public static TransferFragment newInstance() {
        TransferFragment transfer = new TransferFragment();
        return transfer;
    }

    public TransferFragment() {
        // empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_transfer, container, false);

        from = (Spinner) rootView.findViewById(R.id.from_spinner);
        to = (Spinner) rootView.findViewById(R.id.to_spinner);
        et_amount = (EditText) rootView.findViewById(R.id.et_transfer_amt);
        et_transfer_date = (EditText) rootView.findViewById(R.id.et_transfer_date);
        btn_transfer = (Button) rootView.findViewById(R.id.btn_transfer);

        btn_transfer.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               Toast.makeText(getActivity().getBaseContext(), "Amount transfered", Toast.LENGTH_LONG).show();
           }
        });

        return rootView;
    }


}
