package com.wecp.educationalresourcedistributionsystem.entity;


import javax.persistence.*;

@Table(name = "registrations") // do not change table name
@Entity
public class EventRegistration {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String status;
   private Long studentId;
   
   @ManyToOne
   private Event event;

   
   
   @ManyToOne
   private User user;

   

   public Long getId() {
      return id;
   }
   public void setId(Long id) {
      this.id = id;
   }
   public String getStatus() {
      return status;
   }
   public void setStatus(String status) {
      this.status = status;
   }
   public Long getStudentId() {
      return studentId;
   }
   public void setStudentId(Long studentId) {
      this.studentId = studentId;
   }
   public Event getEvent() {
      return event;
   }
   public void setEvent(Event event) {
      this.event = event;
   }
}





