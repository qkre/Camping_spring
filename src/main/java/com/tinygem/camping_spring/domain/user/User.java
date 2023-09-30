package com.tinygem.camping_spring.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "User")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private int userID;

    @Column(name = "accessLevel", nullable = false)
    private int accessLevel;

    @Column(name = "email")
    private String email;

    @Column(name = "nick", nullable = false)
    private String nick;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "snsID")
    private String snsID;

    @Builder
    public User(int accessLevel, String email, String pwd, String nick, String provider, String snsID){
        this.accessLevel = accessLevel;
        this.email = email;
        this.pwd = pwd;
        this.nick = nick;
        this.provider = provider;
        this.snsID = snsID;
    }


}
