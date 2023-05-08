package sg.edu.rpc346.id22035553.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView tvAmount;
    TextView tvPax;
    ToggleButton tbSVS;
    ToggleButton tbGST;
    TextView tvDiscount;
    Button bSplit;
    Button bReset;
    TextView tvTTBill;
    TextView tvEachpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAmount = findViewById(R.id.textAmount);
        tvPax = findViewById(R.id.textPax);
        tbSVS = findViewById(R.id.toggleButtonSVS);
        tbGST = findViewById(R.id.toggleButtonGST);
        tvDiscount = findViewById(R.id.textDiscount);
        bSplit = findViewById(R.id.ButtonSplit);
        bReset = findViewById(R.id.ButtonReset);
        tvTTBill = findViewById(R.id.textViewTTBill);
        tvEachpay = findViewById(R.id.textViewEachpay);

        bSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvAmount.getText().toString().trim().length()!=0 &&
                        tvPax.getText().toString().trim().length()!=0) {
                    double newAmount = 0.0;
                    if (!tbSVS.isChecked() && !tbGST.isChecked()) {
                        newAmount = Double.parseDouble(tvAmount.getText().toString());
                    } else if (tbSVS.isChecked() && !tbGST.isChecked()) {
                        newAmount = Double.parseDouble(tvAmount.getText().toString()) * 1.1;
                    } else if (!tbSVS.isChecked() && tbGST.isChecked()) {
                        newAmount = Double.parseDouble(tvAmount.getText().toString()) * 1.07;
                    } else {
                        newAmount = Double.parseDouble(tvAmount.getText().toString()) * 1.17;
                    }

                    if (tvDiscount.getText().toString().trim().length()!= 0) {
                        newAmount*= 1-Double.parseDouble(tvDiscount.getText().toString())/100;
                    }
                    tvTTBill.setText("Total bill: $" + String.format("%.2f", newAmount));
                    int numberPerson = Integer.parseInt(tvPax.getText().toString());

                    //if (numberPerson!= 1)
                    tvEachpay.setText("Each Pays: $" + String.format("%.2f",newAmount/numberPerson));
                    // else
                    // Eachpay.setText("Each Pays: $" + newAmount);
                }
            }
        });

        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAmount.setText("");
                tvPax.setText("");
                tbSVS.setChecked(false);
                tbGST.setChecked(false);
                tvDiscount.setText("");
            }
        });
    }
}