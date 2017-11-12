package io.github.kschworkflows.patient;

import com.experimental.openmrs.OpenMRS;

import java.util.LinkedList;
import java.util.List;

class PatientServiceImpl implements PatientService {

    private OpenMRS openMRS;

    public PatientServiceImpl(OpenMRS openMRS) {
        this.openMRS = openMRS;
    }

    @Override
    public List<Patient> findPatients(String nameOrId) {
        return toPatients(openMRS.patient().findByNameOrId(nameOrId));
    }

    protected static List<Patient> toPatients(List<com.experimental.openmrs.Patient> openMRSPatients) {
        List<Patient> patients = new LinkedList<>();
        openMRSPatients.forEach(p -> patients.add(toPatient(p)));
        return patients;
    }

    protected static Patient toPatient(com.experimental.openmrs.Patient openMRSPatient) {
        Patient p = new Patient();
        if (openMRSPatient.getPerson() != null) {
            p.setFirstName(openMRSPatient.getPerson().getPreferredName().getGivenName());
            p.setLastName(openMRSPatient.getPerson().getPreferredName().getFamilyName());
        } else {
            String display = checkNotNull(openMRSPatient.getDisplay());
            String[] displayParts = display.split(" - ");

            p.setId(display.split(" - ")[0]);

            String[] names = displayParts[1].split(" ");
            p.setFirstName(names[0]);
            p.setLastName(names[1]);
        }

        return p;
    }

    private static String checkNotNull(String s) {
        if (s == null) {
            throw new NullPointerException();
        }
        return s;
    }
}
