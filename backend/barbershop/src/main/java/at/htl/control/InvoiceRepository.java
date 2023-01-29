package at.htl.control;

import at.htl.entity.Invoice;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InvoiceRepository implements PanacheRepository<Invoice> {
}
