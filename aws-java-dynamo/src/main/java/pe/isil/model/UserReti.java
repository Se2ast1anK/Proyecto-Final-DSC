package pe.isil.model;

import java.util.List;

public class UserReti {

    private String firstName;
    private String lastName;
    private Integer age;
    private List<String> roles;
    private Boolean active;


    public UserReti() {
    }

    public UserReti(String firstName, String lastName, Integer age, List<String> roles, Boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.roles = roles;
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


}
