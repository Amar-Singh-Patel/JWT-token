package com.rpsa.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "cdponame")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cdponame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Primary Key

    private String cdpo;
    
    private String cdponame;

	public Integer getId() {
		return id;
	}

	public String getCdpo() {
		return cdpo;
	}

	public String getCdponame() {
		return cdponame;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCdpo(String cdpo) {
		this.cdpo = cdpo;
	}

	public void setCdponame(String cdponame) {
		this.cdponame = cdponame;
	}

}