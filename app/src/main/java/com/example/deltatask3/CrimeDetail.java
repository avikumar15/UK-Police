package com.example.deltatask3;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.deltatask3.CrimeListLocation.IDD;
import static com.example.deltatask3.FavouriteActivity.jethiya;
import static com.example.deltatask3.LocationSearch.latitude;
import static com.example.deltatask3.LocationSearch.longitude;
import static com.example.deltatask3.LocationSearch.monthisbest;
import static com.example.deltatask3.LocationSearch.yearisbest;

public class CrimeDetail extends AppCompatActivity {

    TextView crimedesc;
    TextView CRIMEDETAIL;
    TextView putstatushere;

    float longg,latt;
    int yearr, monthh;

    String intented;
    ToGetCrimeDetails ttt;
    int klmm=0;
    int choice=99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_detail);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        crimedesc=(TextView)findViewById(R.id.crimedesc);
        CRIMEDETAIL=(TextView)findViewById(R.id.CRIMEDETAIL);
        putstatushere=(TextView)findViewById(R.id.putstatushere);
        putstatushere.setVisibility(View.VISIBLE);
        CRIMEDETAIL.setVisibility(View.VISIBLE);
        crimedesc.setVisibility(View.VISIBLE);

        Intent inn = getIntent();
        intented=inn.getStringExtra(IDD);
        choice=inn.getIntExtra(jethiya,99);

        longg=inn.getFloatExtra(longitude,90f);
        latt=inn.getFloatExtra(latitude,90f);
        monthh=inn.getIntExtra(monthisbest,0);
        yearr=inn.getIntExtra(yearisbest,0);

        System.out.println(longg+" "+latt+" "+monthh+"-"+yearr);


        if(!(intented.equals("Data missing on server.") || intented.equals("Persistence id : ") || intented.equals("") ))
        {
            crimedesc.setText(""+intented);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://data.police.uk/api/outcomes-for-crime/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ttt=retrofit.create(ToGetCrimeDetails.class);

            Call<TheParticularCrimeClass> call = ttt.getTheInfo(intented);

            call.enqueue(new Callback<TheParticularCrimeClass>() {
                @Override
                public void onResponse(Call<TheParticularCrimeClass> call, Response<TheParticularCrimeClass> response) {
                    if (!response.isSuccessful()) {
                        System.out.println("Server Error");
                        Toast.makeText(getApplicationContext(),"Data missing", Toast.LENGTH_LONG).show();
                        return;
                    }

                    TheParticularCrimeClass posts = response.body();
                    for (klmm = 0; klmm < posts.getSIZE(); klmm++) {
                        if (posts.getOutcomes().get(klmm).getCategory().name != null && posts.getOutcomes().get(klmm).getDate() != null) {
                            String result = Html.fromHtml(posts.getOutcomes().get(klmm).getCategory().name).toString();
                            CRIMEDETAIL.setText("Date of verdict : " + posts.getOutcomes().get(klmm).getDate());
                            putstatushere.setText("Status : " + result);

                            if(CRIMEDETAIL.equals(""))
                            {
                                crimedesc.setText("Data not found.");
                                putstatushere.setVisibility(View.INVISIBLE);
                                CRIMEDETAIL.setVisibility(View.INVISIBLE);
                            }
                        } else
                        {
                            putstatushere.setVisibility(View.INVISIBLE);
                            CRIMEDETAIL.setText("Oops no data available on the server."+"\n");
                        }
                    }
                }
                @Override
                public void onFailure(Call<TheParticularCrimeClass> call, Throwable t) {
                    System.out.println("ABORT RIGHT NOWWW");
                }
            });
        }
        else
            crimedesc.setText("Data missing on server.");

        if (putstatushere.equals("Loading..."))
        {
            putstatushere.setText("Data not found.");
        }

    }

    public void gobacktocrimelist(View vello)
    {
        if(choice==1) {
            Intent inni = new Intent(this, FavouriteActivity.class);
            startActivity(inni);
        }
        else
        {
            Intent inni = new Intent(this,CrimeListLocation.class);
            inni.putExtra(longitude,longg);
            inni.putExtra(latitude,latt);
            inni.putExtra(monthisbest,monthh);
            inni.putExtra(yearisbest,yearr);
            startActivity(inni);
        }
    }

}
