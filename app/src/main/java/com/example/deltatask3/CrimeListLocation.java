package com.example.deltatask3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.deltatask3.FavouriteActivity.jethiya;
import static com.example.deltatask3.LocationSearch.latitude;
import static com.example.deltatask3.LocationSearch.longitude;
import static com.example.deltatask3.LocationSearch.monthisbest;
import static com.example.deltatask3.LocationSearch.yearisbest;

public class CrimeListLocation extends AppCompatActivity implements CrimeLocationAdapter.OnCrimeListInterface{

    TextView textlong,textlat,DATE;
    float longitudee,latitudee;
    int month,year;
    RecyclerView CRIMELIST;
    LinearLayoutManager mLayoutManager;
    CrimeLocationAdapter cr7;
    static FavDatabase secondfavwordstartingwithf;

    public static final String IDD = "NotAgain";

    public static final String LatForMap="No one";
    public static final String LongForMap="No onex";

    List<String> PERSIS = new Vector<String>();
    List<String> CATEG = new Vector<String>();
    List<String> LAT = new Vector<String>();
    List<String> LONG = new Vector<String>();

    List<Integer> STARC = new Vector<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_list_location);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        textlat=(TextView)findViewById(R.id.textlat);
        textlong=(TextView)findViewById(R.id.textlong);
        DATE=(TextView)findViewById(R.id.DATE);
        CRIMELIST = (RecyclerView) findViewById(R.id.CRIMELIST);
        secondfavwordstartingwithf = new FavDatabase(this);

        Intent intent=getIntent();
        longitudee=intent.getFloatExtra(longitude,90f);
        latitudee=intent.getFloatExtra(latitude,90f);
        month=intent.getIntExtra(monthisbest,0);
        year=intent.getIntExtra(yearisbest,0);


        textlat.setText("Latitude : "+latitudee);
        textlong.setText("Longitude : "+longitudee);
        if(month<10)
            DATE.setText("Record for : 0"+month+"/"+year);
        else
            DATE.setText("Record for : "+month+"/"+year);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://data.police.uk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CrimeListLocationInterface crimeListLocationInterface = retrofit.create(CrimeListLocationInterface.class);

        Call<List<GunahKiList>> call = crimeListLocationInterface.getTHEdataLIST(latitudee,longitudee,""+year+"-"+month);

        call.enqueue(new Callback<List<GunahKiList>>() {
            @Override
            public void onResponse(Call<List<GunahKiList>> call, Response<List<GunahKiList>> response) {

                if(!response.isSuccessful())
                {
                    System.out.println("Server Error.");
                    return;
                }

                List<GunahKiList> posts = response.body();

                for(GunahKiList gunahKiList : posts ){
                    if(gunahKiList.getPersistent_id()!=null || gunahKiList.getLocation().getLatitude()!=null || gunahKiList.getLocation().getLongitude()!=null || gunahKiList.getCategory()!=null) {

                        if(!(gunahKiList.getPersistent_id().equals("")))
                            PERSIS.add(""+gunahKiList.getPersistent_id());
                        else
                        {
                            PERSIS.add("DATA MISSING");
                        }

                        if(gunahKiList.getLocation().getLatitude()!=null)
                            LAT.add("Latitude : " + gunahKiList.getLocation().getLatitude());
                        else
                            LAT.add("Data missing on server.");

                        if(gunahKiList.getLocation().getLongitude()!=null)
                            LONG.add("Longitude : " + gunahKiList.getLocation().getLongitude());
                        else
                            LONG.add("Data missing on server.");

                        if(gunahKiList.getCategory()!=null)
                            CATEG.add("Crime Category : " + gunahKiList.getCategory());
                        else
                            CATEG.add("Data missing on server.");

                        STARC.add(R.drawable.star_white);

                    }
                    else
                    {
                        CATEG.add("Data missing on server.");
                        LONG.add("Data missing on server.");
                        LAT.add("Data missing on server.");
                        PERSIS.add("Data missing on server.");
                    }
                }
                cr7.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GunahKiList>> call, Throwable t) {
                System.out.println("Mission Failed. Aborting.");
            }

        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(PERSIS.size()==0)
                    Toast.makeText(getApplicationContext(),"No record for set input",Toast.LENGTH_SHORT).show();
            }
        }, 4000);


        List<String> list = new ArrayList<String>();

        Cursor res = CrimeListLocation.secondfavwordstartingwithf.getFavCrimes();

        while(res.moveToNext())
        {
            list.add(res.getString(2));
        }

        for(int chh=0; chh<list.size(); chh++)
        {
            System.out.println("The saved lat is : "+list.get(chh));
            System.out.println("Size is : " + list.size());
        }

        CRIMELIST.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        CRIMELIST.setLayoutManager(mLayoutManager);
        cr7 = new CrimeLocationAdapter(getApplicationContext(),this,PERSIS,CATEG,LAT,LONG,STARC, list);
        CRIMELIST.setAdapter(cr7);
    }

    public void backtolocsearch(View cvcv)
    {
        Intent ii = new Intent(this,LocationSearch.class);
        startActivity(ii);
    }

    @Override
    public void onCrimeListClick(String heywassap, String LATI, String LONGI) {
        Intent intint = new Intent(this,CrimeDetail.class);
        intint.putExtra(IDD,heywassap);
        intint.putExtra(jethiya,0);

        intint.putExtra(latitude,latitudee);
        intint.putExtra(longitude,longitudee);
        intint.putExtra(monthisbest,month);
        intint.putExtra(yearisbest,year);

        intint.putExtra(LatForMap,LATI);
        intint.putExtra(LongForMap,LONGI);

        startActivity(intint);
    }

}