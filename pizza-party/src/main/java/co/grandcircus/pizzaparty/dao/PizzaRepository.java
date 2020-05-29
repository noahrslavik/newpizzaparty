  
package co.grandcircus.pizzaparty.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.pizzapartyormcomplete.entity.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long>{
	
	List<Pizza> findByTypeContainingIgnoreCase(String name);

	List<Pizza> findByTypeContainingAndVotesGreaterThan(String type, int votes);

}
