package org.popins.dev.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Movie extends PanacheEntity{
	@Column(length=100)
	public String title;
	
	@Column(length=200)
	public String description;
	
	public String director;
	
	public String country;

}
