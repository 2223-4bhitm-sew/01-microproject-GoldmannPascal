package at.htl.control;

import at.htl.entity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer>{


}
