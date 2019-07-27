package rw.bk.taxi24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.bk.taxi24.models.Driver;

@Repository
public interface DriverRepository  extends JpaRepository<Driver,Integer> {
}
