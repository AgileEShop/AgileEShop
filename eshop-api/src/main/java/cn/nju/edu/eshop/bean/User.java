package cn.nju.edu.eshop.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String nickname;
    @Column
    private String phone;
    @Column
    private int status;

    @Column
    private Timestamp createTime;
    @Column
    private Timestamp modifyTime;
}
