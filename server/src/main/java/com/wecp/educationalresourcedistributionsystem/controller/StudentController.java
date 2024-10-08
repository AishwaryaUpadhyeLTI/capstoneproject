package com.wecp.educationalresourcedistributionsystem.controller;

import com.wecp.educationalresourcedistributionsystem.entity.Event;
import com.wecp.educationalresourcedistributionsystem.entity.EventRegistration;
import com.wecp.educationalresourcedistributionsystem.service.EventService;
import com.wecp.educationalresourcedistributionsystem.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private EventService eventService;


    @PostMapping("/api/student/register/{eventId}")
    public ResponseEntity<EventRegistration> registerForEvent(@PathVariable Long eventId, @RequestBody EventRegistration registration) {
        EventRegistration createdRegistration = registrationService.registerForEvent(eventId, registration);
       
        return new ResponseEntity<EventRegistration>(createdRegistration, HttpStatus.CREATED);
    }

    @GetMapping("/api/student/registration-status/{studentId}")
    public ResponseEntity<List<EventRegistration>> viewRegistrationStatus(@PathVariable Long studentId) {
        List<EventRegistration> registrations = registrationService.getRegistrationsByStudentId(studentId);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/api/student/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

}