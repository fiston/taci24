package rw.bk.taxi24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.bk.taxi24.Utility;
import rw.bk.taxi24.models.Driver;
import rw.bk.taxi24.repository.DriverRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/drivers"})
public class DriverController {
    private DriverRepository repository;

    DriverController(DriverRepository driverRepository){
        this.repository=driverRepository;
    }

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }
    @GetMapping(path = {"/{id}"})
    public List findById(@PathVariable long id, @RequestParam double latitude,@RequestParam double longitude ){
        List<Driver> drivers=repository.findAll();
        List<Driver> driversInRadius=new ArrayList<>();
        for(Driver d:drivers){
            if(Utility.CoordDistance(d.getLocLat(),d.getLocLong(),latitude,longitude)<=3000)
                driversInRadius.add(d);
        }
        return driversInRadius;
    }

    @GetMapping(path = {"/radius/{rad}"})
    public ResponseEntity<Driver> findAllInRadius(@PathVariable long rad){
        return repository.findById(rad)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Driver create(@RequestBody Driver driver){
        return repository.save(driver);
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<Driver> updateLocation(@PathVariable("id") long id,
                                              @RequestBody Driver driver){
        return repository.findById(id)
                .map(record -> {
                    record.setLocLat(driver.getLocLat());
                    record.setLocLong(driver.getLocLong());
                    Driver updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

}
