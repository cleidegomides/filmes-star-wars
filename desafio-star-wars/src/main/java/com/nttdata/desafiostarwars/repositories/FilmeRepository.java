package com.nttdata.desafiostarwars.repositories;

import com.nttdata.desafiostarwars.entities.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

}
