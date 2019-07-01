package com.example.deltatask3;

import java.util.ArrayList;
import java.util.List;

public class TheParticularCrimeClass {

    public class nananani {

        verdict category;
        String date;

        public class verdict {
            String name;

            verdict() {
                name = "";
            }

            public String getName() {
                return name;
            }
        }

        nananani()
        {
            date="";
            category = new verdict();
        }

        public String getDate()
        {
            return date;
        }

        public verdict getCategory()
        {
            return category;
        }
    }
    ArrayList<nananani> outcomes;

    public TheParticularCrimeClass()
    {
        outcomes=new ArrayList<nananani>();
    }

    public ArrayList<nananani> getOutcomes()
    {
        return outcomes;
    }

    public int getSIZE()
    {
        return outcomes.size();
    }

}
