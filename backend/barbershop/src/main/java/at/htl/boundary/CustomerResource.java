package at.htl.boundary;

import at.htl.control.CustomerRepository;
import at.htl.entity.Customer;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerRepository customerRepository;

    @GET
    public List<Customer> findAll(){
        return customerRepository.findAll().list();
    }

    @POST
    @Transactional
    public Response create(Customer customer) throws Exception {
        customerRepository.persist(customer);
        return Response.created(URI.create("/customer/"+customer.getId())).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Customer update(@PathParam("id") Long id, Customer customer){
        var entity = customerRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setSex(customer.getSex());
        return  entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id){
        var entity = customerRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        customerRepository.delete(entity);
    }
}
