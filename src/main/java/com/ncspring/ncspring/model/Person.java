package com.ncspring.ncspring.model;



import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "PERSONS")
public class Person {

    @Id
    @Column(name = "PERSONS_ID", nullable = false)
    private int id;
    @Column(name = "NAME", nullable = false)
    private String name;
//    @JoinColumn(name = "WAY_NAME", nullable = false)
//    private String personWayName;
    private String way;
    @Column(name = "USD", nullable = false)
    private int usd;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "WAY_NAME")
//    private List<Way> ways = new ArrayList<>();
//    private Way personWayName;

    public Person(int id, String name, String way, int usd) {
        this.id = id;
        this.name = name;
        this.way = way;
        this.usd = usd;
    }



    public Person(){}

    @Override
    public String toString() {
        return id + "    " + name + "  " + way + "  " + usd;
    }

}

