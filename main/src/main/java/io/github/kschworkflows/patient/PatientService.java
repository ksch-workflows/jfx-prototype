package io.github.kschworkflows.patient;

import java.util.List;

public interface PatientService {

    List<Patient> findPatients(String nameOrId);
}
