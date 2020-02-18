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
    private String id;
    private String userId;
    private String name;
    private String phoneNumber;
    private String place;

}
