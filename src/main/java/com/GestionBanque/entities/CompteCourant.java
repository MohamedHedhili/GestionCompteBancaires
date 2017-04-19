package com.GestionBanque.entities;

import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte{
	private double decouverte ;

	public CompteCourant() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public CompteCourant(String code, Date dateCreation, double solde, Client client, double decouverte) {
		super(code, dateCreation, solde, client);
		this.decouverte = decouverte;
	}



	public double getDecouverte() {
		return decouverte;
	}



	public void setDecouverte(double decouverte) {
		this.decouverte = decouverte;
	} 
	

}
