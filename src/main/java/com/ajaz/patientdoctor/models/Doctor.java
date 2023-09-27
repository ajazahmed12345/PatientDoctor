package com.ajaz.patientdoctor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private DoctorCity doctorCity;
    private String email;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private DoctorSpeciality doctorSpeciality;
}
