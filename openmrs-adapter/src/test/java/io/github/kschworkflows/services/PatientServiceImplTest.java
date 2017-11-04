package io.github.kschworkflows.services;

import com.experimental.openmrs.Name;
import com.experimental.openmrs.OpenMRS;
import com.experimental.openmrs.Person;
import com.experimental.openmrs.resources.PatientResource;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

public class PatientServiceImplTest {

    private PatientService patientService;

    private OpenMRS openMRS;

    @Before
    public void setup() {
        openMRS = mock(OpenMRS.class);
        patientService = new PatientServiceImpl(openMRS);
    }

    @Test
    public void should_find_patients() {

        // GIVEN
        PatientResource mockPatientResource = mock(PatientResource.class);
        given(mockPatientResource.findByNameOrId(anyString())).willReturn(getTestPatients());
        given(openMRS.patient()).willReturn(mockPatientResource);
        // FIXME "Everytime a mock returns a mock a fairy dies."

        // WHEN
        List<Patient> retrievedPatients = patientService.findPatients("Joe");

        // THEN‚
        assertEquals("Could not retrieve the expected number of patients", 2, retrievedPatients.size());
    }

    @Test
    public void should_parse_patient_details_from_display_text() {

        // GIVEN
        com.experimental.openmrs.Patient openMRSPatient = createPatient("GAN203007 - John Doe");

        // WHEN
        Patient transformedPatient = PatientServiceImpl.toPatient(openMRSPatient);

        // THEN
        assertEquals("John", transformedPatient.getFirstName());
        assertEquals("Doe", transformedPatient.getLastName());
        assertEquals("GAN203007", transformedPatient.getId());
    }

    private List<com.experimental.openmrs.Patient> getTestPatients() {
        List<com.experimental.openmrs.Patient> patients = new LinkedList<>();

        patients.add(createPatient("John", "Doe"));
        patients.add(createPatient("Joe", "Doe"));

        return patients;
    }

    private com.experimental.openmrs.Patient createPatient(String firstName, String lastName) {
        com.experimental.openmrs.Patient patient = new com.experimental.openmrs.Patient();

        Person person = new Person();
        Name name = new Name();
        name.setFamilyName(lastName);
        name.setGivenName(firstName);
        person.setPreferredName(name);

        patient.setPerson(person);

        return patient;
    }

    private com.experimental.openmrs.Patient createPatient(String display) {
        return new com.experimental.openmrs.Patient(display);
    }
}
