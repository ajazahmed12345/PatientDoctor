package com.ajaz.patientdoctor.controllers;

import com.ajaz.patientdoctor.models.Doctor;
import com.ajaz.patientdoctor.services.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private DoctorService doctorService;
    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }
    @PostMapping()
    public ResponseEntity<String> createDoctor(@RequestBody Doctor doctor){
        Doctor savedDoctor = doctorService.createDoctor(doctor);
        return new ResponseEntity<>("Doctor created with id = " + savedDoctor.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/get_doctors/{id}")
    public ResponseEntity<Object> getSuggestedDoctorsByPatientId(@PathVariable("id") Long id){
        return doctorService.getSuggestedDoctorsByPatientId(id);
    }

    @DeleteMapping("/delete_doctor/{id}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable("id") long id){
//        Doctor doctor = doctorService.getDoctorById(id);
//
//        if(doctor == null){
//            return new ResponseEntity<>("doctor to be deleted with id = " + id + " not found.", HttpStatus.NOT_FOUND);
//        }

        String ans = doctorService.deleteDoctorById(id);

        return new ResponseEntity<>(ans, HttpStatus.OK);
    }



}
