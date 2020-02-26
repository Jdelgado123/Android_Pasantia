package tecsup.example.recyclerview.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Modelo_raised {
    private String id;
    private Shed shed;
    private String date;
    private String food_income,food_deposit,food_consumption,final_deposit,chicken_death;
    private String observation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Shed getShed() {
        return shed;
    }

    public void setShed(Shed shed) {
        this.shed = shed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFood_income() {
        return food_income;
    }

    public void setFood_income(String food_income) {
        this.food_income = food_income;
    }

    public String getFood_deposit() {
        return food_deposit;
    }

    public void setFood_deposit(String food_deposit) {
        this.food_deposit = food_deposit;
    }

    public String getFood_consumption() {
        return food_consumption;
    }

    public void setFood_consumption(String food_consumption) {
        this.food_consumption = food_consumption;
    }

    public String getFinal_deposit() {
        return final_deposit;
    }

    public void setFinal_deposit(String final_deposit) {
        this.final_deposit = final_deposit;
    }

    public String getChicken_death() {
        return chicken_death;
    }

    public void setChicken_death(String chicken_death) {
        this.chicken_death = chicken_death;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
