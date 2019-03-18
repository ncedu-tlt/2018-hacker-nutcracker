package com.ncspring.ncspring.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DIRECTIONS")
public class Way {
    @Id

    @Column(name = "DIRECTION_ID")
    private int id;


    @Column(name = "WAY_NAME")
    private String wayName;


    public Way(String wayName) {
        this.wayName = wayName;
    }

    public Way() {
    }
}
