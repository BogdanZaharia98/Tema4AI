package com.company;

import java.util.ArrayList;
import java.util.List;

public class Mapp {

    List<String> regions;
    List<ArrayList<String>> constraints=new ArrayList<>();
    List<ArrayList<String>> constraintsC=new ArrayList<>();
    int ind=0;
    List<String> colors=new ArrayList<>();
    ArrayList<Integer> visited=new ArrayList<>();

    public Mapp (ArrayList<String> regions){
        this.regions=regions;
        for (int i=0;i<regions.size();i++) visited.add(0);
        constraintsC.add(new ArrayList<>());
    }
    public void addConstraint(String s1, String s2){
        ArrayList<String> l=new ArrayList<String>();
        boolean foundS1=false;
        boolean foundS2=false;
        for(int i=0;i<regions.size();i++)
            if(regions.get(i).equals(s1))
                foundS1=true;
            else if(regions.get(i).equals(s2))
                foundS2=true;
        if(foundS1&&foundS2){
            l.add(s1);
            l.add(s2);
            constraints.add(l);
        }
        else System.out.println("Region not on the map");
    }
    public void addConstraintC(String s1,int indx){
        if (indx>=regions.size()) System.out.println("Region not on the map");
        else
        if(indx>ind){
            ArrayList <String>l1=new ArrayList();
            l1.add(s1);
            constraintsC.add(l1);
            ind++;
        }
        else {
            ArrayList<String> l = constraintsC.get(indx);
            l.add(s1);
            constraintsC.set(indx,l);
        }

    }


    public void getColors(){
        colors.add(constraintsC.get(0).get(0));
        for(int i=0;i<constraintsC.size();i++){
            for (int j=0;j<constraintsC.get(i).size();j++){
                boolean add=true;
                for (int k=0;k<colors.size();k++)
                    if(colors.get(k).equals(constraintsC.get(i).get(j)))
                        add=false;
                if(add) colors.add(constraintsC.get(i).get(j));
            }
        }
        System.out.println("Colors: ");
        for(int i=0;i<colors.size();i++)
            System.out.print(colors.get(i)+ ", ");
        System.out.println();
    }

    public boolean forwardCheck(List<ArrayList<String>> cState,String color,int indx){
        for(int i=0;i<cState.size();i++){

            if(i==indx){
                ArrayList<String> list=new ArrayList<>();
                list.add(color);
                cState.set(i,list);

            }
            else
            for (int j=0;j<cState.get(i).size();j++){

                    for(int k=0;k< constraints.size();k++) {
                        if(constraints.get(k).get(0).equals(regions.get(i))) {
                            if(constraints.get(k).get(1).equals(regions.get(indx)))
                                if (cState.get(i).get(j).equals(color)&&indx!=i)
                                cState.get(i).remove(j);
                        }
                        else{
                            if(constraints.get(k).get(1).equals(regions.get(i)))
                                if(constraints.get(k).get(0).equals(regions.get(indx)))
                                    if (cState.get(i).get(j).equals(color)&&indx!=i)
                                    cState.get(i).remove(j);
                        }
                    }

            }
        }

        boolean isValid=true;
        for(int i=0;i<cState.size();i++){
            if(cState.get(i).size()==0)
                isValid=false;
        }
        return isValid;
    }


    public List<ArrayList<String>> transition(List<ArrayList<String>> cState,String color,int indx){

        for(int i=0;i<cState.size();i++){

            if(i==indx){
                ArrayList<String> list=new ArrayList<>();
                list.add(color);
                cState.set(i,list);

            }
            else

            for (int j=0;j<cState.get(i).size();j++){


                    for(int k=0;k< constraints.size();k++) {
                        if(constraints.get(k).get(0).equals(regions.get(i))) {
                            if(constraints.get(k).get(1).equals(regions.get(indx)))
                                if (cState.get(i).get(j).equals(color)&&indx!=i)
                                cState.get(i).remove(j);
                        }
                        else{
                            if(constraints.get(k).get(1).equals(regions.get(i)))
                                if(constraints.get(k).get(0).equals(regions.get(indx)))
                                    if (cState.get(i).get(j).equals(color)&&indx!=i)
                                    cState.get(i).remove(j);
                        }
                    }
                }
            }

        System.out.println("color used:"+color+" , index used: "+indx);
        for (int l = 0; l < cState.size(); l++) {
            System.out.print(" { ");
            for (int m = 0; m < cState.get(l).size(); m++) {
                System.out.print(cState.get(l).get(m) + ", ");
            }
            System.out.println("}");
        }

        return cState;
    }

    public boolean bkt(List<ArrayList<String>> cState,ArrayList<Integer> visited){
        ///if all regions have exactly one color, print state and return
        boolean isFinal=true;
        for(int i=0;i<cState.size();i++) {
            if (cState.get(i).size() != 1)
                isFinal = false;
        }
            if(isFinal) {
                for(int i=0;i<cState.size();i++){
                    System.out.print("{ ");
                    for (int j=0;j<cState.get(i).size();j++){
                        System.out.print(cState.get(i).get(j)+", ");
                    }
                    System.out.println("}");
                }
            return true;
        }


        ///Minimum remaining values
        ArrayList<Integer> sume=new ArrayList<>();
        for(int i=0;i<cState.size();i++){
            int sum=0;
            for (int j=0;j<cState.get(i).size();j++){
                sum++;
            }
            sume.add(sum);
        }
        isFinal=false;
        while(!isFinal) {
            isFinal=true;
            for (int i = 0; i < cState.size()-1; i++) {
                if(sume.get(i+1)<sume.get(i)) {
                    ArrayList <String> tmp=cState.get(i);
                    int tmpI=sume.get(i);
                    cState.set(i,cState.get(i+1));
                    cState.set(i+1,tmp);
                    sume.set(i,sume.get(i+1));
                    sume.set(i+1,tmpI);
                    isFinal=false;
                }

            }

        }



            for(int j=0;j<cState.size();j++){
                for (int k=0;k<cState.get(j).size();k++){
                    for(int i=0;i<colors.size();i++){

                        if(colors.get(i).equals(cState.get(j).get(k))) {

                            if(forwardCheck(cState,colors.get(i),j)) {

                                if(visited.get(j)==0) {
                                    List<ArrayList<String>> list=transition(cState,colors.get(i),j);
                                    visited.set(j, 1);
                                    if (bkt(list, visited)) {

                                        return true;
                                    }
                                }

                            }
                        }
                    }
                }
            }




       return false;
    }

}
