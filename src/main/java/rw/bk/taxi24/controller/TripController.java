package rw.bk.taxi24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.bk.taxi24.models.Invoice;
import rw.bk.taxi24.models.Trip;
import rw.bk.taxi24.repository.InvoiceRepository;
import rw.bk.taxi24.repository.TripRepository;

import java.util.List;

@RestController
@RequestMapping({"/trip"})
public class TripController {

    private TripRepository repository;
    private InvoiceRepository invoiceRepository;

    TripController(TripRepository tripRepository, InvoiceRepository invoiceRepository){
        this.repository=tripRepository;
        this.invoiceRepository=invoiceRepository;
    }

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Trip> findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Trip create(@RequestBody Trip trip){
        return repository.save(trip);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Invoice> completeTrip(@PathVariable("id") long id,
                                          @RequestBody Trip trip){
        return repository.findById(id)
                .map(record -> {
                    record.setActive(trip.isActive());
                    Trip updated = repository.save(record);
                    Invoice invoice=new Invoice();
                    invoice.setTrip(updated);
                    invoiceRepository.save(invoice);
                    return ResponseEntity.ok().body(invoice);
                }).orElse(ResponseEntity.notFound().build());
    }

}
