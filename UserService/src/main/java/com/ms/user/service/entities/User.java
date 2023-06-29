package com.ms.user.service.entities;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_service")
public class User {
    @Id
    @Column(name="ID")
    private String userId;
    @Column(name="NAME",length=20)
    private String name;
    @Column(name="EMAIL")
    private  String email;
    @Column(name="ABOUT")
    private String about;

    //transient means we are not going to save this in DB
    @Transient
    private List<Rating> ratings=new ArrayList<>();
}
