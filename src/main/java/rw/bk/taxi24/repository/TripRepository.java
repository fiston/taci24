package rw.bk.taxi24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.bk.taxi24.models.Trip;

import java.util.List;

@Repository
public interface TripRepository  extends JpaRepository<Trip,Long> {
}
