package com.GestionBanque.metier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GestionBanque.dao.*;
import com.GestionBanque.entities.Compte;
import com.GestionBanque.entities.CompteCourant;
import com.GestionBanque.entities.Operation;
import com.GestionBanque.entities.Retrait;
import com.GestionBanque.entities.Versement;
@Transactional
@Service
public class ImplementationService implements  ServiceBanque {
   @Autowired
	private  CompteRepository  compteRepository ;
   @Autowired
   private OperationRepository operationRepository ;
	@Override
	public Compte consulterCompte(String codeCpte) {
Compte cp  = ((CrudRepository<Compte, String>) compteRepository).findOne(codeCpte);
		return cp;
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte cp  =  consulterCompte(codeCpte);
		Versement v  =  new Versement((java.sql.Date) new  Date (), montant , cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()+montant);
		((JpaRepository<Compte, String>) compteRepository).save(cp);
	}

	@Override
	public void retirer(String codeCpte, double montant) {

		Compte cp  =  consulterCompte(codeCpte);
		double  decouvert =0 ;
		if(cp instanceof  CompteCourant )
			decouvert  =  ((CompteCourant) cp).getDecouverte() ;
		if (cp.getSolde() + decouvert > montant)
		{Retrait r  =  new Retrait((java.sql.Date) new  Date (), montant , cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde() - montant);
		((JpaRepository<Compte, String>) compteRepository).save(cp);}
	}

	@Override
	public void virement(String codeCptel, String codeCpte2, double montant) {
		retirer(codeCptel, montant);
		verser(codeCpte2, montant);
		
		
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, int page, int size) {
		return operationRepository.listOperation(codeCpte, new PageRequest(page, size));
	}

}
