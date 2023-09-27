package com.ajaz.patientdoctor.services;

import com.ajaz.patientdoctor.models.*;
import com.ajaz.patientdoctor.repositories.DoctorRepository;
import com.ajaz.patientdoctor.repositories.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    public DoctorService(DoctorRepository doctorRepository, PatientRepository patientRepository){
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public Doctor createDoctor(Doctor doctor){
        boolean alreadyExists = false;

        List<Doctor> doctors = doctorRepository.findAll();

        for(Doctor d : doctors){
            if(isSame(d, doctor)){
                alreadyExists = true;
            }
        }

        if(alreadyExists){
            return null;
        }

        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(long id){
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if(doctorOptional.isEmpty()){
            return null;
        }

        return doctorOptional.get();
    }

    public String deleteDoctorById(long id){
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);

        if(doctorOptional.isEmpty()){
            return "doctor with id = " + id + " not found";
        }

        doctorRepository.deleteById(id);

        return "doctor deleted with id = " + id;

    }

    public ResponseEntity<Object> getSuggestedDoctorsByPatientId(Long id){

        Optional<Patient> patientOptional = patientRepository.findById(id);

        if(patientOptional.isEmpty()){
            return new ResponseEntity<>("patient with id = " + id + " does not exist.", HttpStatus.NOT_FOUND);
        }

        Patient patient = patientOptional.get();

        DoctorCity doctorCity = convertDoctorCityFromStringToEnum(patient.getCity());

        DoctorSpeciality doctorSpeciality = getDoctorSpecialityFromPatientSymptom(patient.getPatientSymptom());

        List<Doctor> doctors = doctorRepository.findAll();

        List<Doctor> doctorsInCity = new ArrayList<>();

        for(Doctor d : doctors){
            if(d.getDoctorCity().equals(doctorCity)){
                doctorsInCity.add(d);
            }
        }

        if(doctorsInCity.isEmpty()){
            return new ResponseEntity<>("We are still waiting to expand to your location", HttpStatus.NOT_FOUND);
        }

        List<Doctor> doctorsWithSpeciality = new ArrayList<>();

        for(Doctor d : doctorsInCity){
            if(d.getDoctorSpeciality().equals(doctorSpeciality)){
                doctorsWithSpeciality.add(d);
            }
        }

        if(doctorsWithSpeciality.isEmpty()){
            return new ResponseEntity<>("There isn’t any doctor present at your location for your symptom", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(doctorsWithSpeciality, HttpStatus.OK);


//        return null;
    }

    boolean isSame(Doctor d1, Doctor d2){
        if(d1.getDoctorSpeciality().equals(d2.getDoctorSpeciality()) &&
        d1.getName().equals(d2.getName()) && d1.getEmail().equals(d2.getEmail()) &&
        d1.getDoctorCity().equals(d2.getDoctorCity()) &&
        d1.getPhoneNumber().equals(d2.getPhoneNumber()))
            return true;

        return false;
    }

    public DoctorCity convertDoctorCityFromStringToEnum(String city){
        if(city.equals("DELHI"))
            return DoctorCity.DELHI;
        else if(city.equals("NOIDA"))
            return DoctorCity.NOIDA;
        else if(city.equals("BANGALORE"))
            return DoctorCity.BANGALORE;

        return null;
    }

    public DoctorSpeciality getDoctorSpecialityFromPatientSymptom(PatientSymptom patientSymptom){
        if(patientSymptom.equals(PatientSymptom.ARTHRITIS) || patientSymptom.equals(PatientSymptom.BACK_PAIN) ||
                patientSymptom.equals(PatientSymptom.TISSUE_INJURIES))
            return DoctorSpeciality.ORTHOPEDIC;

        else if(patientSymptom.equals(PatientSymptom.DYSMENORRHEA))
            return DoctorSpeciality.GYNECOLOGY;

        else if(patientSymptom.equals(PatientSymptom.SKIN_INFECTION) || patientSymptom.equals(PatientSymptom.SKIN_BURN))
            return DoctorSpeciality.DERMATOLOGY;
        else if(patientSymptom.equals(PatientSymptom.EAR_PAIN))
            return DoctorSpeciality.ENT;

        return null;
    }
}
