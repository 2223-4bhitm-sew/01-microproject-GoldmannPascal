package at.htl.boundary;

import at.htl.control.CustomerRepository;
import at.htl.control.InvoiceRepository;
import at.htl.entity.Invoice;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import org.jboss.logging.Logger;

@Path("/invoice")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class InvoiceResource {

    @Inject
    Logger logger;
    @Inject
    CustomerRepository customerRepository;

    @Inject
    InvoiceRepository invoiceRepository;

    @GET
    public List<Invoice> findAll(){
        return invoiceRepository.findAll().list();
    }

    @GET
    @Path("{id}")
    public Invoice findById(@PathParam("id") long id) {
        logger.info(id);
        return invoiceRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(Invoice invoice, @Context UriInfo uriInfo) {
        if (customerRepository.findById(invoice.getCustomer().getId()) == null){
            logger.error("Customer not found");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        invoiceRepository.persist(invoice);
        logger.info("Invoice created: " + invoice.getId());
        return Response.created(URI.create(uriInfo.getPath()+"/"+invoice.getId())).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Invoice update(@PathParam("id") Long id, Invoice invoice) {
        Invoice entity = invoiceRepository.findById(id);
        if (entity == null) {
            logger.error("Invoice not found: " + id);
            throw new NotFoundException();
        }
        entity.setPrice(invoice.getPrice());
        invoice.setCustomer(invoice.getCustomer());
        logger.info("Invoice updated: " + entity.getId());
        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        var entity = invoiceRepository.findById(id);
        if (entity == null) {
            logger.error("Invoice not found: " + id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        invoiceRepository.delete(entity);
        logger.info("Invoice deleted: " + entity.getId());
        return Response.ok().build();
    }
}
