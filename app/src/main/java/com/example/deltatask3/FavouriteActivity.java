package com.example.deltatask3;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.provider.ContactsContract;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

import static com.example.deltatask3.CrimeListLocation.IDD;

public class FavouriteActivity extends AppCompatActivity implements CrimeLocationAdapter.OnCrimeListInterface{


    List<String> FavPID = new Vector<String>();
    List<String> FavCat = new Vector<String>();
    List<String> FavLat = new Vector<String>();
    List<String> FavLong = new Vector<String>();
    List<Integer> STARC = new Vector<Integer>();

    CrimeLocationAdapter CR;
    LinearLayoutManager mLayoutManager;
    RecyclerView heyshona;
    EditText searchfav;

    public static final String jethiya="champakiya";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        heyshona=(RecyclerView)findViewById(R.id.THEFAVLIST);
        searchfav=(EditText)findViewById(R.id.searchfav);

        CrimeListLocation.secondfavwordstartingwithf= new FavDatabase(this);

        Cursor res = CrimeListLocation.secondfavwordstartingwithf.getFavCrimes();

        if(res.getCount() == 0)
        {
            Toast.makeText(getApplicationContext(),"Nothing in favourites", Toast.LENGTH_SHORT).show();
            return;
        }

        while(res.moveToNext())
        {
            STARC.add(R.drawable.star_yellow);
            FavPID.add(res.getString(0));
            FavCat.add(res.getString(1));
            FavLat.add(res.getString(2));
            FavLong.add(res.getString(3));
        }

        if(FavPID.size()>0)
        {
            CR = new CrimeLocationAdapter(getApplicationContext(),this,FavPID,FavCat,FavLat,FavLong,STARC);
        }

        heyshona.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        heyshona.setLayoutManager(mLayoutManager);
        heyshona.setAdapter(CR);


        searchfav.addTextChangedListener(new TextWatcher() {
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

    public void filter(String sss)
    {
        List<String> PID = new Vector<String>();
        List<String> CATCAT = new Vector<String>();
        List<String> LATLAT = new Vector<String>();
        List<String> LONGLONG = new Vector<String>();

        int i;
        if(!sss.equals(""))
        {
            for(i=0;i<CR.FINALLAT.size();i++)
            {
                if((CR.persis.get(i)).toLowerCase().contains(sss.toLowerCase()))
                {
                    PID.add(CR.persis.get(i));
                    CATCAT.add(CR.categ.get(i));
                    LATLAT.add(CR.FINALLAT.get(i));
                    LONGLONG.add(CR.FINALLONG.get(i));

                }
            }
            CR.searchImplement(PID,CATCAT,LATLAT,LONGLONG);
        }
        else
            CR.searchImplement(FavPID,FavCat,FavLat,FavLong);
    }

    public void backfromfav(View v)
    {
        Intent intent = new Intent(this,StartActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCrimeListClick(String heywassap) {
        System.out.println("Thank you, next");
        Intent intint = new Intent(this,CrimeDetail.class);
        intint.putExtra(IDD,heywassap);
        intint.putExtra(jethiya,1);
        startActivity(intint);
    }

    public void DetailApp3(View view)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentImplement sampleFragment = new FragmentImplement();
        fragmentTransaction.add(R.id.fragmentLayout,sampleFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
