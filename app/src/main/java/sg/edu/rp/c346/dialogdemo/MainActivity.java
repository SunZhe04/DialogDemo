package sg.edu.rp.c346.dialogdemo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnSimpleDialog;
    TextView tvButton;
    Button btnDialog;
    TextView tvInput;
    Button btnInput;
    TextView tvCalender;
    Button btnCalender;
    TextView tvTime;
    Button btnTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //simple dialog step 2b binding and implementing listener
        btnSimpleDialog=(Button)findViewById(R.id.buttonSimpleDialog);
        btnSimpleDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setPositiveButton("Close",null);

                myBuilder.setTitle("Simple Dialog");
                myBuilder.setMessage("I can develop Android App.");
                myBuilder.setCancelable(false);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }

        });
        btnDialog=(Button)findViewById(R.id.buttonDialog);
        tvButton=(TextView)findViewById(R.id.textViewDemo2);
        btnDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                myBuilder.setTitle("Demo 2 Buttons Dialog");
                myBuilder.setMessage("Select one of the Button below.");
                myBuilder.setCancelable(false);
                // Configure the 'positive' button
                myBuilder.setPositiveButton("Positive", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // IMPORTANT: Update TextView as a form of data passing between Dialog and MainActivity
                        tvButton.setText("You have selected Positive.");
                    }
                });
                myBuilder.setNegativeButton("Negative", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // IMPORTANT: Update TextView as a form of data passing between Dialog and MainActivity
                        tvButton.setText("You have selected Negative.");
                    }
                });
                // Configure the 'neutral' button
                myBuilder.setNeutralButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
        tvInput=(TextView)findViewById(R.id.textViewDemo3);
        btnInput=(Button)findViewById(R.id.buttonDemo3);
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inflate the input.xml layout file

                LayoutInflater inflater =
                        (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.input2, null);


                // Obtain the UI component in the input.xml layout
                final EditText etInput = (EditText)viewDialog.findViewById(R.id.editTextNum1);
                final EditText etInput2 = (EditText)viewDialog.findViewById(R.id.editTextNum2);

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                // Set the view of the dialog
                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Demo 3-Text Input Dialog");

                myBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Extract the Text entered by the user
                       float sum = Integer.parseInt(etInput.getText().toString())+ Integer.parseInt(etInput2.getText().toString());

                        // Update the Text to TextView
                        tvInput.setText("The sum is "+sum);
                    }
                });

                myBuilder.setNegativeButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
        tvCalender=(TextView)findViewById(R.id.textViewCalendar);
        btnCalender=(Button)findViewById(R.id.buttonCalendar);
        btnCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creating the Listener to set the date
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        Calendar now = Calendar.getInstance();
                        String datetime = now.get(Calendar.DAY_OF_MONTH)+ "/"+
                                (now.get(Calendar.MONTH)+1) + "/" +
                                now.get(Calendar.YEAR) + " " +
                                now.get(Calendar.HOUR_OF_DAY)+":"+
                                now.get(Calendar.MINUTE);

                        tvCalender.setText("Date: " + dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                    }
                };
                Calendar now = Calendar.getInstance();
                // Create the Date Picker Dialog
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this,myDateListener,
                        now.get(Calendar.YEAR),(now.get(Calendar.MONTH)),now.get(Calendar.DAY_OF_MONTH));


                myDateDialog.show();
            }
        });
        tvTime=(TextView)findViewById(R.id.textViewTime);
        btnTime=(Button)findViewById(R.id.buttonTime);
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creating the Listener to set the time
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        tvTime.setText("Time: " + hourOfDay + ":" + minute);
                    }
                };
                /*

                // Create the Time Picker Dialog
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,
                        myTimeListener, 20, 00, true);
                */
                Calendar now = Calendar.getInstance();
                int hour = now.get(Calendar.HOUR_OF_DAY);
                int minute = now.get(Calendar.MINUTE);
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,myTimeListener,hour,minute,true);

                myTimeDialog.show();
            }
        });






    }
}
