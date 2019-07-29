package rw.bk.taxi24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.bk.taxi24.models.Rider;
import rw.bk.taxi24.repository.RiderRepository;

import java.util.List;

@RestController
@RequestMapping({"/rider"})
public class RiderController {
    private RiderRepository repository;

    RiderController(RiderRepository riderRepository){
        this.repository=riderRepository;
    }

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Rider> findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Rider create(@RequestBody Rider rider){
        return repository.save(rider);
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<Rider> updateLocation(@PathVariable("id") long id,
                                             @RequestBody Rider rider){
        return repository.findById(id)
                .map(record -> {
                    record.setLocLat(rider.getLocLat());
                    record.setLocLong(rider.getLocLong());
                    Rider updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

}
