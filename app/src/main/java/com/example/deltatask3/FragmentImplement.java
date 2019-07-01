package com.example.deltatask3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentImplement extends Fragment implements View.OnClickListener {

    View view;
    TextView CLOSEFRAG;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment,container,false);
        CLOSEFRAG = (TextView) view.findViewById(R.id.CLOSEFRAG);
        CLOSEFRAG.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        getActivity().onBackPressed();
    }
}
