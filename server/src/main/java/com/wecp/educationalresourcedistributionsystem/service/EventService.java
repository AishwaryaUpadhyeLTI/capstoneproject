package com.wecp.educationalresourcedistributionsystem.service;

import com.wecp.educationalresourcedistributionsystem.entity.Event;
import com.wecp.educationalresourcedistributionsystem.entity.Resource;
import com.wecp.educationalresourcedistributionsystem.repository.EventRepository;
import com.wecp.educationalresourcedistributionsystem.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;;

    @Autowired
    private ResourceRepository resourceRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getAllEventsSortedByName(boolean ascending) {
        Sort sort = Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, "name");
        return eventRepository.findAll(sort);
    }

    public Event updateEvent(Long EventId, Event updateEvent) {
        Event existingEvent = eventRepository.findById(EventId).orElseThrow(EntityNotFoundException::new);
        existingEvent.setName(updateEvent.getName());
        existingEvent.setDescription(updateEvent.getDescription());
        existingEvent.setMaterials(updateEvent.getMaterials());
        existingEvent.setDate(updateEvent.getDate());
        return eventRepository.save(existingEvent);
    }

    public Event allocateResourceToEvent(Long eventId, Long resourceId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new EntityNotFoundException("Resource not found"));
        event.getResourceAllocations().add(resource);
        resource.setEvent(event);
        return eventRepository.save(event);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);

    }

}