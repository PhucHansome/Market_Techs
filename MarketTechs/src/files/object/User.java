package files.object;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {

    private int id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Role role;

    public User() {

    }

    public User(int id, String username, String password, String name, String phone, String email, String address, Role role ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.role = role;


    }
    public User(String rar){
        String[] strings = rar.split(",");
        this.id = Integer.parseInt(strings[0]);
        this.username = strings[1];
        this.password = strings[2];
        this.name = strings[3];
        this.phone = strings[4];
        this.email = strings[5];
        this.address = strings[6];
        this.role = Role.fromValue(strings[7]);

    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return this.role;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return id + "," + username + "," + password + "," + name + "," + phone + "," + email + "," + address + "," + role ;
    }
}
