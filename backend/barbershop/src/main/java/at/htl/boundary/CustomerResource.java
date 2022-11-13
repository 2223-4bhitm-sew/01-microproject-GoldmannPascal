package at.htl.boundary;

import at.htl.control.CustomerRepository;
import at.htl.entity.Customer;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

@Path("/customer")
public class CustomerResource {

    @Inject
    Logger logger;

    @Inject
    CustomerRepository customerRepository;

    private List<Customer> customers = new LinkedList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Customer customer, @Context UriInfo uriInfo) throws Exception {
        Customer saved = customerRepository.save(customer);
        customers.add(customer);
        logger.info(customer.getLastName() + " wird gespeichert");
        URI location = uriInfo
                .getAbsolutePathBuilder()
                .path(saved.getId().toString())
                .build();
        return Response.created(location).build();
    }


    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Customer customer) {
        if(customers.size() > 0) {
            customers.remove(0);
        }
        return Response.noContent().build();
    }
}
