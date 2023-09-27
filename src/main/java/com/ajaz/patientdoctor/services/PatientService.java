package com.ajaz.patientdoctor.services;

import com.ajaz.patientdoctor.models.Patient;
import com.ajaz.patientdoctor.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }
    public Patient createPatient(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient getPatientById(long id){
        Optional<Patient> patientOptional = patientRepository.findById(id);

        if(patientOptional.isEmpty()){
            return null;
        }

        return patientOptional.get();
    }

    public String deletePatientById(long id){
        Optional<Patient> patientOptional = patientRepository.findById(id);

        if(patientOptional.isEmpty()){
            return "patient with id = " + id + " not found";
        }

        patientRepository.deleteById(id);

        return "patient deleted with id = " + id;

    }
}
