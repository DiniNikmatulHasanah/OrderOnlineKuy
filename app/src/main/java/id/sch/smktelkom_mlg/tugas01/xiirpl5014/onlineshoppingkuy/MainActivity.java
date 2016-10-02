package id.sch.smktelkom_mlg.tugas01.xiirpl5014.onlineshoppingkuy;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    EditText etName;
    EditText etEmail;
    EditText etAddress;
    EditText etDate;
    Button bOk;
    RadioGroup rgGender;

    int nBeli;

    CheckBox cbW, cbS, cbD, cbTS, cbP;

    TextView tvBeli;
    EditText date;
    DatePickerDialog datePickerDialog;

    Spinner spProvinsi;
    TextView tvHasil;

    String[][] arKota = {{"Jakarta Barat", "Jakarta Pusat", "Jakarta Selatan",
            "Jakarta Timur", "Jakarta Utara"},
            {"Bandung", "Cirebon", "Bekasi"}, {"Semarang",
            "Magelang", "Surakarta"},
            {"Surabaya", "Malang", "Blitar"}, {"Denpasar"}};
    ArrayList<String> listKota = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("OrderOnlineKuy");

        etName = (EditText) findViewById(R.id.editTextNama);
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etAddress = (EditText) findViewById(R.id.editTextAddress);
        etDate = (EditText) findViewById(R.id.editTextDate);

        spProvinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        bOk = (Button) findViewById(R.id.buttonOK);

        rgGender = (RadioGroup) findViewById(R.id.radioGroupGender);

        cbW = (CheckBox) findViewById(R.id.checkBoxW);
        cbS = (CheckBox) findViewById(R.id.checkBoxS);
        cbD = (CheckBox) findViewById(R.id.checkBoxD);
        cbTS = (CheckBox) findViewById(R.id.checkBoxTS);
        cbP = (CheckBox) findViewById(R.id.checkBoxP);


        tvBeli = (TextView) findViewById(R.id.textViewBeli);

        cbW.setOnCheckedChangeListener(this);
        cbS.setOnCheckedChangeListener(this);
        cbD.setOnCheckedChangeListener(this);
        cbTS.setOnCheckedChangeListener(this);
        cbP.setOnCheckedChangeListener(this);

        findViewById(R.id.buttonOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                doClick();
                final String Email = etEmail.getText().toString();

                doOpen();

            }

        });

// initiate the date picker and a button
        date = (EditText) findViewById(R.id.editTextDate);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });
    }

    private void doOpen() {
        String hasil1 = null;


    }


    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void doClick() {

        if (isValid()) {
            StringBuilder builder = new StringBuilder();
            builder.append("Name : ");
            builder.append(etName.getText().toString());
            builder.append("\n");
            builder.append("\n");
            if (rgGender.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton)
                        findViewById(rgGender.getCheckedRadioButtonId());
                builder.append("Gender : " + rb.getText().toString());
                builder.append("\n");
                builder.append("\n");
                builder.append("Address : " + etAddress.getText().toString());
                builder.append("\n");
                builder.append("\n");
                builder.append("Date Of Order : " + etDate.getText().toString());
                builder.append("\n");
                builder.append("\n");
                builder.append("Email : ");
                builder.append(etEmail.getText().toString());
                builder.append("\n");
                builder.append("\n");

                builder.append("Buy : ");
                int startlen = builder.length();
                if (cbW.isChecked()) builder.append(cbW.getText()).append("\n");
                if (cbS.isChecked()) builder.append(cbS.getText()).append("\n");
                if (cbD.isChecked()) builder.append(cbD.getText()).append("\n");
                if (cbTS.isChecked()) builder.append(cbTS.getText()).append("\n");
                if (cbP.isChecked()) builder.append(cbP.getText()).append("\n");

                if (builder.length() == startlen) builder.append("Tidak ada pada pilihan");


                tvHasil.setText(builder);
            }
        }
    }

    private boolean isValid() {
        boolean valid = true;

        String Name = etName.getText().toString();
        String Email = etEmail.getText().toString();


        if (Name.isEmpty()) {
            etName.setError("Nama Belum Diisi");
            valid = false;
        } else if (Name.length() < 3) {
            etName.setError("Nama Minimal 3 Karakter");
            valid = false;
        } else {
            etName.setError(null);
        }
        if (!isValidEmail(Email)) {
            etEmail.setError("Invalid Email");
        } else {
            etEmail.setError(null);
        }
        return valid;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) nBeli += 1;
        else nBeli -= 1;

        tvBeli.setText("Buy (" + nBeli + " Selected)");
    }
}

