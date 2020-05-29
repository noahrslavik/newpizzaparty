package co.grandcircus.pizzaparty.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.pizzapartyormcomplete.entity.Party;

public interface PartyRepository extends JpaRepository<Party, Long> {
	
}
