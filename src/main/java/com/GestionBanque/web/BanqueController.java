package com.GestionBanque.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.GestionBanque.entities.Compte;
import com.GestionBanque.entities.Operation;
import com.GestionBanque.metier.ServiceBanque;

@Controller
public class BanqueController {
	@Autowired
	private ServiceBanque serviceBan ;
	@RequestMapping("/operations" )
	public String index()
	{return"comptes";}
	
	@RequestMapping("/consulterCompte" )
	public String consulter(Model  model ,String codeCpte , @RequestParam(name="page" ,defaultValue="0")int page ,  @RequestParam(name="size" ,defaultValue="4") int size  )
	{  model.addAttribute("codeCompte", codeCpte);
		
		try{
		Compte cp = serviceBan.consulterCompte(codeCpte);
		Page<Operation> pageOperations =  serviceBan.listOperation(codeCpte, page, size);
		model.addAttribute("listeOperations", pageOperations.getContent()) ;
		int [] pages = new int [pageOperations.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("compte", cp) ;
	}catch(Exception e){
		model.addAttribute("exception", e) ;
	}
		
		return"comptes";}
	@RequestMapping(value = "/saveOperation", method=RequestMethod.POST)
	public String saveOperation(Model model ,  String typeOperation ,String codeCompte ,  double montant ,String codeCompte2)
	{try{
		if (typeOperation.equals("VERS"))
		{
		serviceBan.verser(codeCompte, montant);
		}
		else if (typeOperation.equals("RETR"))
		{ serviceBan.retirer(codeCompte, montant);
		}
		else if (typeOperation.equals("VIR"))
		{ serviceBan.virement(codeCompte,  codeCompte2, montant);
			
		}
	} catch(Exception e) {
		
		model.addAttribute("error", e);
		return "redirect:/consulterCompte?codeCpte="+codeCompte+"&error="+e.getMessage();
	}
		
		
		
		return "redirect:/consulterCompte?codeCpte="+codeCompte;}
	

}

