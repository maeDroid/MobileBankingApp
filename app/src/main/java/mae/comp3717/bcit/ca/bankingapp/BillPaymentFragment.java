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


public class BillPaymentFragment extends Fragment{

    Spinner from;
    Spinner to;
    EditText et_amount;
    EditText et_payment_date;
    Button btn_payment;

    public static BillPaymentFragment newInstance() {
        BillPaymentFragment payment = new BillPaymentFragment();
        return payment;
    }

    public BillPaymentFragment() {
        // empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_payment, container, false);

        from = (Spinner) rootView.findViewById(R.id.from_spinner);
        to = (Spinner) rootView.findViewById(R.id.to_spinner);
        et_amount = (EditText) rootView.findViewById(R.id.et_payment_amt);
        et_payment_date = (EditText) rootView.findViewById(R.id.et_payment_date);
        btn_payment = (Button) rootView.findViewById(R.id.btn_pay);

        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getBaseContext(), "Amount Paid", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }


}
