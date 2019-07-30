package rw.bk.taxi24.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.bk.taxi24.config.Utility;
import rw.bk.taxi24.models.Driver;
import rw.bk.taxi24.repository.DriverRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/drivers"})
@Api(value="Drivers Management", description="Operations pertaining to Drivers Management System")
public class DriverController {
    private DriverRepository repository;

    DriverController(DriverRepository driverRepository){
        this.repository=driverRepository;
    }

    @ApiOperation(value = "View a list of available drivers", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping
    public List findAll(){
        return repository.findAll();
    }
    @ApiOperation(value = "Get a Driver By ID")
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Driver> findAllInRadius(@PathVariable long rad){
        return repository.findById(rad)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Get an Drivers in a given radius")
    @GetMapping(path = {"/radius"})
    public List findById(@RequestParam long rad, @RequestParam double latitude,@RequestParam double longitude ){
        List<Driver> drivers=repository.findAll();
        List<Driver> driversInRadius=new ArrayList<>();
        for(Driver d:drivers){
            if(Utility.CoordDistance(d.getLocLat(),d.getLocLong(),latitude,longitude)<=rad)
                driversInRadius.add(d);
        }
        return driversInRadius;
    }
    @ApiOperation(value = "Save new Driver")
    @PostMapping
    public Driver create(@RequestBody Driver driver){
        return repository.save(driver);
    }

    @ApiOperation(value = "Update existing Driver, especially the location")
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
