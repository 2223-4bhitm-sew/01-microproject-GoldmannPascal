package at.htl.boundary;

import at.htl.control.CustomerRepository;
import at.htl.control.MeetingRepository;
import at.htl.entity.Meeting;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/meeting")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MeetingResource {

    @Inject
    Logger logger;
    @Inject
    CustomerRepository customerRepository;

    @Inject
    MeetingRepository meetingRepository;

    @GET
    public List<Meeting> findAll(){
        return meetingRepository.findAll().list();
    }

    @GET
    @Path("{id}")
    public Meeting findById(@PathParam("id") long id) {
        logger.info(id);
        return meetingRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(Meeting meeting, @Context UriInfo uriInfo) {
        if (customerRepository.findById(meeting.getCustomer().getId()) == null){
            logger.error("Customer not found");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        meetingRepository.persist(meeting);
        logger.info("Meeting created: " + meeting.getId());
        return Response.created(URI.create(uriInfo.getPath()+"/"+meeting.getId())).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Meeting update(@PathParam("id") Long id, Meeting meeting) {
        Meeting entity = meetingRepository.findById(id);
        if (entity == null) {
            logger.error("Meeting not found: " + id);
            throw new NotFoundException();
        }
        entity.setMeetingDate(meeting.getMeetingDate());
        meeting.setCustomer(meeting.getCustomer());
        logger.info("Meeting updated: " + entity.getId());
        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        var entity = meetingRepository.findById(id);
        if (entity == null) {
            logger.error("Meeting not found: " + id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        meetingRepository.delete(entity);
        logger.info("Meeting deleted: " + entity.getId());
        return Response.ok().build();
    }
}
