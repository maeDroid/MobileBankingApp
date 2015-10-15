package mae.comp3717.bcit.ca.bankingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class BillPaymentFragment extends Fragment{

    //Spinner     from;
    //Spinner     to;
    //EditText    et_amount;
    //EditText    et_payment_date;
    Button      btn_payment;

    public static BillPaymentFragment newInstance() {
        BillPaymentFragment payment = new BillPaymentFragment();
        return payment;
    }

    public BillPaymentFragment() {
        // empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView   = inflater.inflate(R.layout.fragment_payment, container, false);
        final int theme = android.R.style.Theme_Holo_Light_Dialog_NoActionBar;

        //from            = (Spinner) rootView.findViewById(R.id.from_spinner);
        //to              = (Spinner) rootView.findViewById(R.id.to_spinner);
        //et_amount       = (EditText) rootView.findViewById(R.id.et_payment_amt);
        //et_payment_date = (EditText) rootView.findViewById(R.id.et_payment_date);
        btn_payment     = (Button) rootView.findViewById(R.id.btn_pay);

        btn_payment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), theme);
                builder.setTitle("Confirm Payment");
                builder.setCancelable(false);
                builder.setPositiveButton("Pay Bill", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity().getBaseContext(), "Payment Completed", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity().getBaseContext(), TabbedActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        return rootView;
    }



}
