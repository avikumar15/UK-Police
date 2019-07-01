package com.example.deltatask3;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements DepAdapter.OnListInterface {

    RecyclerView rv;
    LinearLayoutManager mLayoutManager;
    int klm=0;
    DepAdapter dep;
    EditText search;
    TextView questionmark;

    int[] IMAGE_RES = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    List<String> first = new Vector<String>();
    List<String> second = new Vector<String>();


    public static final String Some = "nasdkj";
    public static final String DEPP = "sasta_nasha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        questionmark = (TextView) findViewById(R.id.questionmark);

        {

            for(int i=2;i<46;i++)
                IMAGE_RES[i] = R.drawable.avon;

            IMAGE_RES[0] = R.drawable.avon;
            IMAGE_RES[1] = R.drawable.bedfordshire;
            IMAGE_RES[2] = R.drawable.cambridge;
            IMAGE_RES[3] = R.drawable.cheshire;
            IMAGE_RES[4] = R.drawable.londonselected;
            IMAGE_RES[5] = R.drawable.cleveselected;
            IMAGE_RES[6] = R.drawable.cumbria;
            IMAGE_RES[7] = R.drawable.derbyshire;
            IMAGE_RES[8] = R.drawable.devon;
            IMAGE_RES[9] = R.drawable.dorset;
            IMAGE_RES[10] = R.drawable.durham;
            IMAGE_RES[11] = R.drawable.dyfed;
            IMAGE_RES[12] = R.drawable.essex;
            IMAGE_RES[13] = R.drawable.gloucestershire;
            IMAGE_RES[14] = R.drawable.manchester;
            IMAGE_RES[15] = R.drawable.gwent;
            IMAGE_RES[16] = R.drawable.hampshire;
            IMAGE_RES[17] =R.drawable.hertfordshireselected;
            IMAGE_RES[18] = R.drawable.humberside;
            IMAGE_RES[19] = R.drawable.kent;
            IMAGE_RES[20] = R.drawable.lancashire;
            IMAGE_RES[21] = R.drawable.leicselected;
            IMAGE_RES[22] = R.drawable.lincolnshire;
            IMAGE_RES[23] = R.drawable.mereyside;
            IMAGE_RES[24] = R.drawable.metropolitan;
            IMAGE_RES[25] = R.drawable.norfolk;
            IMAGE_RES[26] = R.drawable.northwalessel;
            IMAGE_RES[27] = R.drawable.yorkshire;
            IMAGE_RES[28] = R.drawable.northamptonshire;
            IMAGE_RES[29] = R.drawable.northumbria;
            IMAGE_RES[30] = R.drawable.nottinghamshire;
            IMAGE_RES[31] = R.drawable.ireland;
            IMAGE_RES[32] = R.drawable.south_walessel;
            IMAGE_RES[33] = R.drawable.south_yorkshire;
            IMAGE_RES[34] = R.drawable.staffordshire;
            IMAGE_RES[35] = R.drawable.suffolk;
            IMAGE_RES[36] = R.drawable.surrey;
            IMAGE_RES[37] = R.drawable.sussex;
            IMAGE_RES[38] = R.drawable.thamessel;
            IMAGE_RES[39] = R.drawable.warwickshire;
            IMAGE_RES[40] = R.drawable.westmersel;
            IMAGE_RES[41] = R.drawable.westmidsel;
            IMAGE_RES[42] = R.drawable.westyorksel;
            IMAGE_RES[43] = R.drawable.wiltshire;

        }      //setting imgicon

        Toast.makeText(getApplicationContext(),"Fetching Data...",Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.police.uk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonHolder jsonHolder = retrofit.create(JsonHolder.class);

        Call<List<FetchData>> call = jsonHolder.getDATA();

        call.enqueue(new Callback<List<FetchData>>() {
            @Override
            public void onResponse(Call<List<FetchData>> call, Response<List<FetchData>> response) {

                if(!response.isSuccessful())
                {
                    System.out.println("Server Error");
                    return;
                }

                List<FetchData>  posts = response.body();

                for (FetchData fetchData : posts ){

                    first.add(fetchData.getName());
                    second.add(fetchData.getId());
                    klm++;
                }
                dep.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<FetchData>> call, Throwable t) {
                System.out.println("FAILED TO FETCH");
            }
        });



        rv = (RecyclerView) findViewById(R.id.DepList);
        search=(EditText)findViewById(R.id.search_name);
        rv.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        rv.setLayoutManager(mLayoutManager);
        dep = new DepAdapter(this,first,second,IMAGE_RES);
        rv.setAdapter(dep);


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }
        });
    }

    public void filter(String sss) {

        List<String> first2 = new Vector<String>();
        List<String> second2 = new Vector<String>();
        int i;

        if (!sss.equals("")) {
            for (i = 0; i < dep.depname.size(); i++) {
                if (dep.depname.get(i).toString().toLowerCase().contains(sss.toLowerCase())) {
                    first2.add(dep.depname.get(i));
                    second2.add(dep.Detail.get(i));
                    System.out.println(dep.depname.get(i)+" sss is - "+sss+" ");
                }
            }
            dep.SearchImplemented(first2, second2);
        }
        else
            dep.SearchImplemented(first,second);
    }


    public void backfromdep(View v)                 //back to start
    {
        Intent intent = new Intent(this,StartActivity.class);
        startActivity(intent);
    }

    @Override
    public void onListClick(String pos,String DEPNAME) {

        Intent intent = new Intent(this,DepartementDetails.class);
        System.out.println("Clicked @ pos : "+pos);
        intent.putExtra("Some",pos);
        intent.putExtra("sasta_nasha",DEPNAME);
        startActivity(intent);
    }

    public void DetailApp(View vview)
    {
        addFragment();
    }

    public void addFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentImplement sampleFragment = new FragmentImplement();
        fragmentTransaction.add(R.id.fragmentLayout,sampleFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}