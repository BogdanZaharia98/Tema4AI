package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ArrayList <String > l=new ArrayList<>();
        l.add("WA");l.add("SA");l.add("NT");
        Mapp mapp=new Mapp(l);
        mapp.addConstraint("SA","NT");
        mapp.addConstraint("WA","NT");
        mapp.addConstraint("WA","SA");
        mapp.addConstraintC("R",0);
        mapp.addConstraintC("G",0);
        mapp.addConstraintC("B",0);
        mapp.addConstraintC("R",1);
        mapp.addConstraintC("G",1);
        mapp.addConstraintC("G",2);
        mapp.getColors();
        mapp.bkt(mapp.constraintsC,mapp.visited);


    }
}
