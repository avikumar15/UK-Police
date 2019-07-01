package com.example.deltatask3;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class CrimeLocationAdapter extends RecyclerView.Adapter<CrimeLocationAdapter.CrimeHolder> {

    int foryellowcolr;

    List<String> persis = new Vector<String>();
    List<String> categ = new Vector<String>();
    List<String> FINALLAT = new Vector<String>();
    List<String> FINALLONG = new Vector<String>();

    List<Integer> STAR = new Vector<Integer>();

    Set<String> set = new HashSet<>();

    int flagpoint=69;


    OnCrimeListInterface mOnCrimeListInterface;

    List<String> SAVED = new Vector<String>();

    int countt=0;



    Context context;


    public CrimeLocationAdapter(Context con, OnCrimeListInterface onCrimeListInterface, List<String> persis2, List<String> categ2,List<String> LAT3, List<String> LONG4, List<Integer> p, List<String> LatSaved)
    {
        foryellowcolr=1;
        persis=persis2;
        categ=categ2;
        FINALLAT=LAT3;
        FINALLONG=LONG4;
        context=con;

        STAR=p;

        this.mOnCrimeListInterface=onCrimeListInterface;

        SAVED = LatSaved;

        System.out.println("SIZE IS : "+FINALLAT.size());
    }

    public CrimeLocationAdapter(Context con, OnCrimeListInterface onCrimeListInterface, List<String> persis2, List<String> categ2,List<String> LAT3, List<String> LONG4, List<Integer> p)
    {
        persis=persis2;
        categ=categ2;                                       //overload
        FINALLAT=LAT3;
        FINALLONG=LONG4;
        context=con;


        STAR = p;

        this.mOnCrimeListInterface=onCrimeListInterface;


        System.out.println("SIZE IS : "+FINALLAT.size());
    }


    @NonNull
    @Override
    public CrimeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.crime_theme,viewGroup,false);

        return new CrimeHolder(view,mOnCrimeListInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeHolder crimeHolder, int i) {
        crimeHolder.category.setText(categ.get(i));
        crimeHolder.persistent.setText(persis.get(i));
        crimeHolder.FINALLONGG.setText(FINALLONG.get(i));
        crimeHolder.FINALLATT.setText(FINALLAT.get(i));

        crimeHolder.star.setImageResource(R.drawable.star_white);

        int nanapat=0;

        List<Integer> TEMP = new Vector<Integer>();
        TEMP.add(i);

        System.out.println("LAT ON SCREEN : "+i);

        for(countt=0;countt<SAVED.size();countt++)
        {
            if(SAVED.get(countt).equals(FINALLAT.get(i))||foryellowcolr==20)
            {
                STAR.set(i,R.drawable.star_yellow);
                break;
            }
        }

        crimeHolder.star.setImageResource(STAR.get(i));
    }

    @Override
    public int getItemCount() {
        return FINALLAT.size();
    }

    public class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView persistent,FINALLATT,FINALLONGG,category;
        OnCrimeListInterface onCrimeListInterface;
        TextView FurtherDetails;
        TextView illbethereforyou;
        ImageView star;


        public CrimeHolder(@NonNull View itemView, OnCrimeListInterface onCrimeListInterface) {
            super(itemView);

            star=(ImageView) itemView.findViewById(R.id.star);
            persistent=(TextView)itemView.findViewById(R.id.persistent);
            FINALLATT=(TextView)itemView.findViewById(R.id.FINALLAT);
            FINALLONGG=(TextView)itemView.findViewById(R.id.FINALLONG);
            category=(TextView)itemView.findViewById(R.id.category);
            FurtherDetails=(TextView)itemView.findViewById(R.id.FurtherDetails);
            illbethereforyou=(TextView)itemView.findViewById(R.id.iwillbethereforyou);

            this.onCrimeListInterface=onCrimeListInterface;

            FurtherDetails.setOnClickListener(this);
            itemView.setOnTouchListener(new OnSwipeTouchListener(context){
                @Override
                public void onSwipeLeft() {

                    //del from fav

                    CrimeListLocation.secondfavwordstartingwithf.DeleteByLat(FINALLAT.get(getAdapterPosition()));
                    STAR.set(getAdapterPosition(),R.drawable.star_white);

                    List<String> list2 = new ArrayList<String>();

                    Cursor res = CrimeListLocation.secondfavwordstartingwithf.getFavCrimes();

                    while(res.moveToNext())
                    {
                        list2.add(res.getString(2));
                    }

                    SAVED=list2;

                    notifyItemChanged(getAdapterPosition());
                }

                @Override
                public void onSwipeRight(){

                    //add to fav

                    CrimeListLocation.secondfavwordstartingwithf.InsertData(persis.get(getAdapterPosition()),categ.get(getAdapterPosition()),FINALLAT.get(getAdapterPosition()),FINALLONG.get(getAdapterPosition()));
                    STAR.set(getAdapterPosition(),R.drawable.star_yellow);

                    notifyItemChanged(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == FurtherDetails.getId())
                onCrimeListInterface.onCrimeListClick(persis.get(getAdapterPosition()));
        }

    }
    public interface OnCrimeListInterface{
        void onCrimeListClick(String heywassap);
    }

    public void searchImplement(List<String> ab, List<String> cd, List<String> ef, List<String> gh)
    {
        persis=ab;
        categ=cd;
        FINALLAT=ef;
        FINALLONG=gh;

        notifyDataSetChanged();
    }


}