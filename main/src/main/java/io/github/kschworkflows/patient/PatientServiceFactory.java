package io.github.kschworkflows.patient;

import static io.github.kschworkflows.ClinicalServiceApplication.getApplicationContext;

public class PatientServiceFactory {

    public static PatientService getPatientService() {
        return getApplicationContext().getInstance(PatientService.class);
    }
}
