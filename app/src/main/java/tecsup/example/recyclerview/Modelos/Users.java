package tecsup.example.recyclerview.Modelos;

import java.util.List;

public class Users {
    private String username;
    private String is_staff;
    private List<groups> groups;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<tecsup.example.recyclerview.Modelos.groups> getGroups() {
        return groups;
    }

    public void setGroups(List<tecsup.example.recyclerview.Modelos.groups> groups) {
        this.groups = groups;
    }

    public String getIs_staff() {
        return is_staff;
    }

    public void setIs_staff(String is_staff) {
        this.is_staff = is_staff;
    }
}
