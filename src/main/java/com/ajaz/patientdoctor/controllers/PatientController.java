package com.ajaz.patientdoctor.controllers;

import com.ajaz.patientdoctor.models.Patient;
import com.ajaz.patientdoctor.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<String> createPatient(@RequestBody Patient patient){
        Patient savedPatient = patientService.createPatient(patient);
        return new ResponseEntity<>("Patient craeted with id = " + savedPatient.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete_patient/{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable("id") long id){
        String ans = patientService.deletePatientById(id);

        return new ResponseEntity<>(ans, HttpStatus.OK);
    }
}
