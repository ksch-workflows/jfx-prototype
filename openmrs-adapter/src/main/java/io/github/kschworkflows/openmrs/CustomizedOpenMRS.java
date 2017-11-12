package io.github.kschworkflows.openmrs;

import com.experimental.openmrs.OpenMRS;

public class CustomizedOpenMRS extends OpenMRS {

    private static CustomizedOpenMRS instance;

    public static void init(String openMRSUrl, String user, String password) {
        instance = new CustomizedOpenMRS(openMRSUrl, user, password);
    }

    public static CustomizedOpenMRS getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Not initialized yet.");
        }
        return instance;
    }

    private CustomizedOpenMRS(String openMRSUrl, String user, String password) {
        super(openMRSUrl, user, password);
    }

    @Override
    public PatientProfileResource patientProfile() {
        return new PatientProfileResource(this);
    }
}
