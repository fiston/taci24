package rw.bk.taxi24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.bk.taxi24.models.DriverShift;
import rw.bk.taxi24.repository.DriverShiftRepository;

import java.util.List;

@RestController
@RequestMapping({"/driver-shift"})
public class DriverShiftController {
    private DriverShiftRepository repository;

    DriverShiftController(DriverShiftRepository driverShiftRepository){
        this.repository=driverShiftRepository;
    }

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<DriverShift> findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DriverShift create(@RequestBody DriverShift driverShift){
        return repository.save(driverShift);
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<DriverShift> updateStatus(@PathVariable("id") long id,
                                             @RequestBody DriverShift driverShift){
        return repository.findById(id)
                .map(record -> {
                    record.setEndTime(driverShift.getEndTime());
                    DriverShift updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

}
