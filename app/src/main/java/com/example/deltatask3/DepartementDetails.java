package com.example.deltatask3;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DepartementDetails extends AppCompatActivity {

    String idofdep;
    String nameofdep;
    ToGetDepDesc ggg;

    TextView tex,TEZ,DESC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departement_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tex=(TextView)findViewById(R.id.tex);
        TEZ=(TextView)findViewById(R.id.NAMEIT);
        DESC=(TextView)findViewById(R.id.DESC);

        Intent intent=getIntent();
        idofdep=intent.getStringExtra("Some");
        nameofdep=intent.getStringExtra("sasta_nasha");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.police.uk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ggg = retrofit.create(ToGetDepDesc.class);

        Call<DepDesc> call = ggg.getDESC(idofdep);

        call.enqueue(new Callback<DepDesc>() {
            @Override
            public void onResponse(Call<DepDesc> call, Response<DepDesc> response) {

                if(!response.isSuccessful())
                {
                    System.out.println("Server Error");
                    return;
                }

                DepDesc  posts = response.body();
                if(posts.getDescription()!=null)
                {
                    String result = Html.fromHtml(posts.getDescription()).toString();
                    DESC.setText(result);
                }
                else
                    DESC.setText("Oops no data available on the server.");
            }

            @Override
            public void onFailure(Call<DepDesc> call, Throwable t) {
                System.out.println("FAILED TO FETCH");
            }
        });

        tex.setText("Department ID - "+idofdep);
        TEZ.setText("Department name - "+nameofdep);

    }

    public void backfromspec(View vv)
    {
        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);
    }

}
