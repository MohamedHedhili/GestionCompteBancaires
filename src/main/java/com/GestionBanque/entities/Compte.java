package com.GestionBanque.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
@Inheritance (strategy =InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_Compte" , discriminatorType=DiscriminatorType.STRING , length = 2)

public abstract class Compte implements  Serializable {
	@Id
	private String code  ; 
	private Date dateCreation  ; 
	private double solde  ; 
	@ManyToOne
	@JoinColumn(name = "CODE_cli")
	private Client client ; 
	@OneToMany(mappedBy = "compte")
	private Collection <Operation> operations ;
	
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Compte(String code, Date dateCreation, double solde, Client client) {
		super();
		this.code = code;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.client = client;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Collection<Operation> getOperations() {
		return operations;
	}
	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}

}
