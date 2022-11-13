package at.htl.control;

import at.htl.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class CustomerRepository {

    @Inject
    EntityManager em;

    public Customer save(Customer customer) {
        return em.merge(customer);
    }

    public List<Customer> findAll() {
        TypedQuery<Customer> query = em
                .createNamedQuery("Customer.findAll", Customer.class);
        return query.getResultList();
    }
}
