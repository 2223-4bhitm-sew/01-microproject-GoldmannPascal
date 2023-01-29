package at.htl.control;

import at.htl.entity.Meeting;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MeetingRepository implements PanacheRepository<Meeting> {
}
