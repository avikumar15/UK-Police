package com.example.deltatask3;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    public void depinfo (View view1)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void searchloc (View view2)
    {
        Intent intent = new Intent(this,LocationSearch.class);
        startActivity(intent);
    }

    public void fav (View view3)
    {
        Intent intent = new Intent(this,FavouriteActivity.class);
        startActivity(intent);
    }

}
