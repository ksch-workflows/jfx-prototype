package io.github.kschworkflows.services;

import java.util.List;

public interface PatientService {

    List<Patient> findPatients(String nameOrId);
}
