package co.grandcircus.pizzaparty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.grandcircus.pizzaparty.dao.PartyRepository;
import co.grandcircus.pizzaparty.dao.PizzaRepository;
import co.grandcircus.pizzapartyormcomplete.entity.Party;
import co.grandcircus.pizzapartyormcomplete.entity.Pizza;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PartyController {
	
	@Autowired
	private PartyRepository partyRepo;
	
	@Autowired
	private PizzaRepository pizzaRepo;
	
	@RequestMapping("/")
	public String main(Model model) {
		
		List<Party> partyList = partyRepo.findAll();
		model.addAttribute("partyList", partyList);
		return "parties-list";
	}
	
	@RequestMapping("/vote")
	public String vote(Model model) {
		List<Pizza> pizzas = pizzaRepo.findByTypeContainingIgnoreCase("pizza");
		List<Pizza> sides = pizzaRepo.findByTypeContainingIgnoreCase("side");
		List<Pizza> drinks = pizzaRepo.findByTypeContainingIgnoreCase("drink");
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("sides", sides);
		model.addAttribute("drinks", drinks);
		return "/vote";
	}
	
	@RequestMapping("/vote/{id}")
	public String castVote(@PathVariable("id") Long id) {
		Pizza addVoteToMe = pizzaRepo.findById(id).orElse(null);
		addVoteToMe.setVotes(addVoteToMe.getVotes() + 1);
		pizzaRepo.save(addVoteToMe);
		return "redirect:/vote";
	}
	
	@PostMapping("/add")
	public String addOption(Pizza partyOption) {
		
		pizzaRepo.save(partyOption);
		
		return "redirect:/vote";
	}
	
	@RequestMapping("/review")
	public String review(Model model) {
		List<Pizza> pizzas = pizzaRepo.findByTypeContainingAndVotesGreaterThan("pizza", 0);
		List<Pizza> sides = pizzaRepo.findByTypeContainingAndVotesGreaterThan("side", 0);
		List<Pizza> drinks = pizzaRepo.findByTypeContainingAndVotesGreaterThan("drink", 0);
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("sides", sides);
		model.addAttribute("drinks", drinks);
		return "/review";
	}
	

}