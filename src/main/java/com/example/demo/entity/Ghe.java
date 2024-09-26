package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Ghe")
public class Ghe {
	
	@Id
	@Column(name = "ma_ghe")
	private String maGhe;

	
	public Ghe() {
		
	}
	public Ghe(String maGhe) {
		this.maGhe = maGhe;
	}
	
	public String getMaGhe() {
		return maGhe;
	}
	public void setMaGhe(String maGhe) {
		this.maGhe = maGhe;
	}
	
	
	@Override
	public String toString() {
		return "Ghe [maGhe=" + maGhe + "]";
	}
	
	
}
