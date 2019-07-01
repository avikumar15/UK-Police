package com.example.deltatask3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.view.View.VISIBLE;

public class LocationSearch extends AppCompatActivity {

    EditText lat, longg;

    int yearis=9000,monthis=9000;

    Spinner dropdownmonth;
    Spinner dropdownyear;

    TextView questionmark2;

    TextView errormsg;

    float latit=0f,longit=0f;

    public static final String longitude="Pankaj Udas";
    public static final String latitude="Sambhar mein mirchi";
    public static final String yearisbest = "Why this kolaveri";
    public static final String monthisbest = "Accha chlta hu duaon mein yaad rakhn";

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        questionmark2=(TextView)findViewById(R.id.questionmark2);
        dropdownmonth = (Spinner) findViewById(R.id.MONTHSPIN);
        dropdownyear = (Spinner) findViewById(R.id.YEARSPIN);

        List<String> monthq = new ArrayList<String>();{
            monthq.add("Select Month");
            monthq.add("Jan");
        monthq.add("Feb");
        monthq.add("Mar");
        monthq.add("Apr");
        monthq.add("May");
        monthq.add("Jun");
        monthq.add("Jul");
        monthq.add("Aug");
        monthq.add("Sep");
        monthq.add("Oct");
        monthq.add("Nov");
        monthq.add("Dec");
    }
        List<String> yearq = new ArrayList<String>();{
            yearq.add("Select Year");
            yearq.add("2018");
            yearq.add("2019");
        }

        ArrayAdapter<String> adaptermonth = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,monthq);
        ArrayAdapter<String> adapteryear = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,yearq);

        dropdownmonth.setAdapter(adaptermonth);
        dropdownmonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position!=0)
                monthis = position;
                else
                    errormsg.setText("Set Month.");
                System.out.println("INSIDE MONTH");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dropdownyear.setAdapter(adapteryear);
        dropdownyear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position!=0)
                yearis = position + 2017;
                else
                    errormsg.setText("Set Year");
                System.out.println("INSIDE YEAR");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        System.out.println("Month now is & year is : "+monthis+" - "+yearis);

        lat = (EditText) findViewById(R.id.lat);
        longg = (EditText)findViewById(R.id.longg);
        errormsg=(TextView)findViewById(R.id.errormsg);


        errormsg.setVisibility(View.INVISIBLE);

    }

    public void backfromloc(View vback)
    {
        Intent intent = new Intent(this,StartActivity.class);
        startActivity(intent);
    }

    public void searchloc(View vv)
    {
        System.out.println("Month now is & year is : "+monthis+" - "+yearis);

        errormsg.setVisibility(View.INVISIBLE);

        if(!(lat.getText().toString().equals("") || longg.getText().toString().equals("") || monthis==9000 || yearis==9000))
        {
            latit=Float.parseFloat(lat.getText().toString());
            longit=Float.parseFloat(longg.getText().toString());

            if((latit>60.1f || latit<50.1f || longit>1.7f || longit<-7.6f) && ((yearis==2018 || yearis==2019) && (monthis<=12 && monthis>=1)))
            {
                System.out.println("long and lat : "+longit+" and "+latit);
                errormsg.setVisibility(VISIBLE);
                errormsg.setText("ERROR : Wrong coordinates.");
                lat.setText("");
                longg.setText("");
            }

            else if((yearis!=2018 && yearis!=2019) || (monthis>12 || monthis<1))
            {
                System.out.println("year and month : "+yearis+" and "+monthis);
                errormsg.setVisibility(VISIBLE);
                errormsg.setText("ERROR - Data for set month and year not found.");
            }

            else if(((yearis!=2018 && yearis!=2019) || (monthis>12 && monthis<1)) && (latit>60.1 || latit<50.1 || longit>1.7 || longit<-7.6) )
            {
                System.out.println("long and lat : "+longit+" and "+latit);
                System.out.println("year and month : "+yearis+" and "+monthis);
                errormsg.setVisibility(VISIBLE);
                errormsg.setText("ERROR - Wrong input.");
                lat.setText("");
                longg.setText("");
            }

            else{
            Intent intent = new Intent(this, CrimeListLocation.class);
            intent.putExtra(longitude, longit);
            intent.putExtra(latitude, latit);
            intent.putExtra(monthisbest, monthis);
            intent.putExtra(yearisbest, yearis);
            startActivity(intent);
        }
        }

        else
        {
            errormsg.setVisibility(VISIBLE);
        }
    }

    public void DetailApp2(View view)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentImplement sampleFragment = new FragmentImplement();
        fragmentTransaction.add(R.id.fragmentLayout,sampleFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
