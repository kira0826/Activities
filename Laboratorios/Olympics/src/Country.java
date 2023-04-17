import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Country implements Comparable<Country>{


    private String name;
    private int gold;
    private int silver;
    private int bronze;


    public Country(int gold, int silver, int bronze, String name) {
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
        this.name = name;
    }

    public Country(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Country obj) {
        int criteria1 = gold - obj.getGold();
        if (criteria1 == 0){
            int criteria2 = silver - obj.getSilver();
            if (criteria2 ==0){
                int criteria3 =bronze - obj.getBronze();
                if(criteria3 == 0 ){
                    return name.compareTo(obj.getName()) ;
                }  else return criteria3 * - 1;

            }else return criteria2 * -1;
        }else return criteria1* -1;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }
}
