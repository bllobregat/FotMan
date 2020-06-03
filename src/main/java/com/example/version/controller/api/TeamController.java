package com.example.version.controller.api;

import com.example.version.model.Team;
import com.example.version.model.common.ModelBase;
import com.example.version.service.TeamService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private TeamService service;


    @PostMapping
    public ResponseEntity<ModelBase> add(@RequestBody @Valid Team team) {
        try {
            service.save(team);
            return new ResponseEntity<ModelBase>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<ModelBase>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/{id}")
    public ResponseEntity<ModelBase> update(@PathVariable Long id, @RequestBody Team team) {
        try {
            service.update(id, team);
            return new ResponseEntity<ModelBase>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<ModelBase>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ModelBase> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<ModelBase>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<ModelBase>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Team>> findAll() {
        return ResponseEntity.ok(service.listALL());
    }


}
