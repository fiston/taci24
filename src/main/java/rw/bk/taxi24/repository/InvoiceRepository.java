package rw.bk.taxi24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.bk.taxi24.models.Invoice;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice,Long> {
}
