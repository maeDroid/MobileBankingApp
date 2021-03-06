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


public class TransferFragment extends Fragment{

    //Spinner from;
    //Spinner to;
    //EditText et_amount;
    //EditText et_transfer_date;
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

        View rootView   = inflater.inflate(R.layout.fragment_transfer, container, false);
        final int theme = android.R.style.Theme_Holo_Light_Dialog_NoActionBar;

        //from             = (Spinner) rootView.findViewById(R.id.from_spinner);
        //to               = (Spinner) rootView.findViewById(R.id.to_spinner);
        //et_amount        = (EditText) rootView.findViewById(R.id.et_transfer_amt);
        //et_transfer_date = (EditText) rootView.findViewById(R.id.et_transfer_date);
        btn_transfer    = (Button) rootView.findViewById(R.id.btn_transfer);

        btn_transfer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), theme);
                builder.setTitle("Confirm Transfer?");
                builder.setCancelable(false);
                builder.setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity().getBaseContext(), "Transfer Completed", Toast.LENGTH_LONG).show();
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
