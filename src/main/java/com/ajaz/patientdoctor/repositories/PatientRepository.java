package com.ajaz.patientdoctor.repositories;

import com.ajaz.patientdoctor.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
