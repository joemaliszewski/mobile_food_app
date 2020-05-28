package cs.bham.ac.uk.assignment3.entity;

import android.util.Log;
import java.util.Objects;

import java.io.Serializable;


public class Food implements Serializable {
    private Integer id;

    private String name;
    private String meal;
    private Integer time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //time
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    //name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //meal
    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }



    @Override
    public boolean equals(Object object) {



        if(object instanceof Food){

            //for food instance
            Food f_in = (Food) object;

            if (this.id .equals(f_in.id) ) {

                return true;
            }
        }

        //found
        if(this == object){
            return true;
        }

        //empty
        if(object == null){

            return false;

        }



        return false;
    }

}