package com.example.version.controller.api;

import com.example.version.model.Player;
import com.example.version.model.common.ModelBase;
import com.example.version.service.PlayerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private PlayerService service;

    @PostMapping
    public ResponseEntity<ModelBase> add(@RequestBody @Valid Player player) {
        try {
            service.save(player);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/{id}")
    public ResponseEntity<ModelBase> update(@PathVariable Long id, @RequestBody Player player) {
        try {
            service.update(id, player);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ModelBase> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));

    }

    @GetMapping
    public ResponseEntity<List<Player>> findAll() {
        return ResponseEntity.ok(service.listALL());
    }


    @GetMapping("/team")
    public ResponseEntity<List<Player>> findTeam() {
        return ResponseEntity.ok(service.listByTeam());
    }
}
