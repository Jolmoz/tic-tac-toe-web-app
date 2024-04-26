package com.jolmoz.threelinewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jolmoz.threelinewebapp.model.Game;
public interface GameRepository extends JpaRepository<Game, Long> {
    
}
