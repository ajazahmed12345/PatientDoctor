package com.ajaz.patientdoctor.repositories;

import com.ajaz.patientdoctor.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
