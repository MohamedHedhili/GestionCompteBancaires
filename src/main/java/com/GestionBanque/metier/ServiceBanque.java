package com.GestionBanque.metier;

import org.springframework.data.domain.Page;

import com.GestionBanque.entities.*;

public interface ServiceBanque {
	public  Compte  consulterCompte(String codeCpte);
	public void  verser (String codeCpte , double montant ) ; 
	public void retirer(String codeCpte , double  montant);
	public void  virement (String  codeCptel , String codeCpte2 , double montant);
	public  Page<Operation> listOperation (String codeCpte ,  int page ,  int size);

}
