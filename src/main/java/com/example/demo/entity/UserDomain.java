package com.example.demo.entity;


import com.example.demo.enums.RestaurantsEnum;
import com.example.demo.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "UserDomain.findByUsername", query = "SELECT u FROM UserDomain u WHERE u.username = :username")
})
public class UserDomain {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private UserTypeEnum type;

    @Column
//    @Enumerated(EnumType.STRING)
    private RestaurantsEnum restaurant;


}
