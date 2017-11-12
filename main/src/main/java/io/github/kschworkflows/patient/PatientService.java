package io.github.kschworkflows.patient;

import io.github.kschworkflows.patient.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> findPatients(String nameOrId);
}
