package tecsup.example.recyclerview.Modelos;

import java.util.Date;

public class Modelo {
    private String id;
    private Shed shed;
    private String date;
    private String food_income,food_deposit,food_consumption,final_deposit,chicken_death,package_total,leftover_eggs;
    private String observation,egg_white,egg_break,egg_dirty;

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

    public String getPackage_total() {
        return package_total;
    }

    public void setPackage_total(String package_total) {
        this.package_total = package_total;
    }

    public String getLeftover_eggs() {
        return leftover_eggs;
    }

    public void setLeftover_eggs(String leftover_eggs) {
        this.leftover_eggs = leftover_eggs;
    }

    public String getEgg_white() {
        return egg_white;
    }

    public void setEgg_white(String egg_white) {
        this.egg_white = egg_white;
    }

    public String getEgg_break() {
        return egg_break;
    }

    public void setEgg_break(String egg_break) {
        this.egg_break = egg_break;
    }

    public String getEgg_dirty() {
        return egg_dirty;
    }

    public void setEgg_dirty(String egg_dirty) {
        this.egg_dirty = egg_dirty;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

}

