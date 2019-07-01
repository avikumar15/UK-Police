package com.example.deltatask3;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DepAdapter extends RecyclerView.Adapter<DepAdapter.DepHolder> {

    List<String> depname = new Vector<String>();
    List<String> Detail = new Vector<String>();
    OnListInterface mOnListInterface;

    int[] pickaint;

    int pospos;

    int klm=0;

    public DepAdapter(OnListInterface onListInterface,List<String> hey1, List<String> hey2,int[] imgres)
    {
        this.mOnListInterface=onListInterface;
        depname=hey1;
        Detail=hey2;
        pickaint=imgres;
        pospos=0;
    }

    @NonNull
    @Override
    public DepHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_theme,viewGroup,false);

        return new DepHolder(view, mOnListInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull DepHolder depHolder, final int i) {

        String title = depname.get(i);
        String brief = Detail.get(i);

        depHolder.head.setText(title);
        depHolder.text.setText(brief);
        depHolder.imgIcon.setImageResource(pickaint[i]);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(400, 400);
        depHolder.imgIcon.setLayoutParams(layoutParams);

    }

    @Override
    public int getItemCount() {
        return depname.size();
    }

    public class DepHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgIcon;
        TextView text,head;
        OnListInterface onListInterface;

        public DepHolder(@NonNull View itemView, OnListInterface onListInterface) {
            super(itemView);

            imgIcon=(ImageView) itemView.findViewById(R.id.imgIcon);
            text=(TextView)itemView.findViewById(R.id.text);
            head=(TextView)itemView.findViewById(R.id.head);


            this.onListInterface=onListInterface;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListInterface.onListClick(Detail.get(getAdapterPosition()),depname.get(getAdapterPosition()));
        }
    }


    public interface OnListInterface{
        void onListClick(String pos, String DEPNAME);
    }

    public void SearchImplemented(List l1,List l2)
    {
        depname=l1;
        Detail=l2;
        notifyDataSetChanged();
    }

}
