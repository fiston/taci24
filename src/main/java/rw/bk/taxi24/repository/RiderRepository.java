package rw.bk.taxi24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.bk.taxi24.models.Rider;

@Repository
public interface RiderRepository  extends JpaRepository<Rider,Integer> {
}
