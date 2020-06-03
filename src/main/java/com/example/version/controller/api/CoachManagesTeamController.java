package com.example.version.controller.api;

import com.example.version.model.CoachManagesTeam;
import com.example.version.model.common.ModelBase;
import com.example.version.repository.CoachManagesTeamRepo;
import com.example.version.service.CoachManagesTeamService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/coachM")
public class CoachManagesTeamController {

    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private CoachManagesTeamService service;

    @Autowired
    private CoachManagesTeamRepo coachManagesTeamRepo;


    @PostMapping
    public ResponseEntity<ModelBase> add(@RequestBody @Valid CoachManagesTeam coachManagesTeam) {
        try {
            service.save(coachManagesTeam);
            return new ResponseEntity<ModelBase>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<ModelBase>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<ModelBase> update(@PathVariable Long id, @RequestBody CoachManagesTeam coachManagesTeam) {
        try {
            service.update(id, coachManagesTeam);
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
    public ResponseEntity<CoachManagesTeam> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));

    }

    @GetMapping
    public ResponseEntity<List<CoachManagesTeam>> findAll() {
        return ResponseEntity.ok(service.listALL());
    }


}
