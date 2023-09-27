package com.ajaz.patientdoctor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;
    private String name;
    private String city;
    private String email;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private PatientSymptom patientSymptom;
}
