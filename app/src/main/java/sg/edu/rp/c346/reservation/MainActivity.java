package sg.edu.rp.c346.reservation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText textDate;
    EditText textTime;
    EditText textName;
    EditText textPhone;
    EditText textSize;

    CheckBox checkBoxSmoke;

    Button btnReserve;
    Button btnReset;
    String checked = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textDate = findViewById(R.id.editTextDate);
        textTime = findViewById(R.id.editTextTime);
        textName = findViewById(R.id.editTextName);
        textSize = findViewById(R.id.editTextSize);
        textPhone = findViewById(R.id.editTextPhone);
        checkBoxSmoke = findViewById(R.id.checkBox);
        btnReserve = findViewById(R.id.buttonReserve);
        btnReset = findViewById(R.id.buttonReset);


        textDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        textDate.setText(dayOfMonth + "/" + (monthOfYear) + "/" + year);
                    }
                };

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int dayofmonth = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, myDateListener, year, month, dayofmonth);
                myDateDialog.show();
            }
        });

        textTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        textTime.setText(hourOfDay + ":" + minute);
                    }
                };

                Calendar cal = Calendar.getInstance();
                int minute = cal.get(Calendar.MINUTE);
                int hour = cal.get(Calendar.HOUR);

                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this, myTimeListener, hour, minute ,true);
                myTimeDialog.show();
            }
        });

        checkBoxSmoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBoxSmoke.isChecked()){
                    checked = "Smoking: Yes";
                }
                else{
                    checked = "Smoking: No";
                }
            }
        });

        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.input, null);

                final TextView tvName = viewDialog.findViewById(R.id.textViewName);
                final TextView tvSmoking = viewDialog.findViewById(R.id.textViewSmoking);
                final TextView tvSize = viewDialog.findViewById(R.id.textViewSize);
                final TextView tvDate = viewDialog.findViewById(R.id.textViewDate);
                final TextView tvTime = viewDialog.findViewById(R.id.textViewTime);

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Confirm Your Order");

                tvName.setText("Name: " + textName.getText().toString());
                tvSmoking.setText(checked);
                tvSize.setText("Size: "+ textSize.getText().toString());
                tvDate.setText("Date: "+ textDate.getText().toString());
                tvTime.setText("Time: "+ textTime.getText().toString());

                myBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });
                myBuilder.setNeutralButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textName.setText("");
                textDate.setText("");
                textPhone.setText("");
                textTime.setText("");
                textSize.setText("");
                checkBoxSmoke.setChecked(false);

            }
        });

    }
}
