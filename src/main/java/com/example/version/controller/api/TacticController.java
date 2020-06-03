package com.example.version.controller.api;

import com.example.version.model.Tactic;
import com.example.version.model.common.ModelBase;
import com.example.version.service.TacticService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tactic")
public class TacticController {

    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private TacticService service;


    @PostMapping
    public ResponseEntity<ModelBase> add(@RequestBody @Valid Tactic tactic) {
        try {
            service.save(tactic);
            return new ResponseEntity<ModelBase>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<ModelBase>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/{id}")
    public ResponseEntity<ModelBase> update(@PathVariable Long id, @RequestBody Tactic tactic) {
        try {
            service.update(id, tactic);
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
    public ResponseEntity<Tactic> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Tactic>> findAll() {
        return ResponseEntity.ok(service.listALL());
    }


}
