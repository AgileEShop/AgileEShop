package cn.nju.edu.eshop.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReceiveAddress implements Serializable {
    @Id
    private int id;
    private String userId;
    private String name;
    private String phoneNumber;
    private String place;

//    public UserReceiveAddress(int id, String userId, String name, String phoneNumber, String place) {
//        this.id = id;
//        this.userId = userId;
//        this.name = name;
//        this.phoneNumber = phoneNumber;
//        this.place = place;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "UserReceiveAddress{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
