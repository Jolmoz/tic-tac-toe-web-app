package com.jolmoz.threelinewebapp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jolmoz.threelinewebapp.control.ThreeLineControl;
import com.jolmoz.threelinewebapp.dto.GameDTO;
import com.jolmoz.threelinewebapp.dto.PlayerDTO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ThreeLineService")
public class ThreeLineService {
    @Autowired
    ThreeLineControl threeLineControl;

    @PostMapping("/newGame")
    public ResponseEntity<Object> newGame(@RequestBody List<PlayerDTO> players, @RequestParam String gameName) {
        try {
            if (players.size() != 2) {
                throw new Exception("Players not valid");
            }
            return ResponseEntity.ok().body(threeLineControl.newGame(players.get(0), players.get(1), gameName));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getAllGames")
    public ResponseEntity<Object> getAllGames() {
        try {
            return ResponseEntity.ok().body(threeLineControl.getAllGames());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getGame")
    public ResponseEntity<Object> getGame(@RequestParam long gameId) {
        try {
            return ResponseEntity.ok().body(threeLineControl.getGame(gameId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/saveGame")
    public ResponseEntity<Object> saveGame(@RequestBody GameDTO gameDTO) {
        try {
            return ResponseEntity.ok().body(threeLineControl.saveGame(gameDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/makeMove")
    public ResponseEntity<Object> makeMove(@RequestBody GameDTO gameDTO, @RequestParam int x, @RequestParam int y) {
        try {
            return ResponseEntity.ok().body(threeLineControl.makeMove(gameDTO, x, y));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
