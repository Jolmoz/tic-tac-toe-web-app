package com.jolmoz.threelinewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jolmoz.threelinewebapp.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    
}
