package co.edu.ufps.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.Entities.Persona;

public interface PersonaDao extends JpaRepository<Persona, Integer>{

}
