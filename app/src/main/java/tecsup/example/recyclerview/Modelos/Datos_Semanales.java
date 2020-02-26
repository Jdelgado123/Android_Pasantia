package tecsup.example.recyclerview.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Datos_Semanales {

    private String name;
    private List<Shedregister> shedregister;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Shedregister> getShedregister() {
        return shedregister;
    }

    public void setShedregister(List<Shedregister> shedregister) {
        this.shedregister = shedregister;
    }
}
