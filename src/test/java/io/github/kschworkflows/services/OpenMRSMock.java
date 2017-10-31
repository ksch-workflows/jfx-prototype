package io.github.kschworkflows.services;

import com.experimental.openmrs.OpenMRS;
import com.experimental.openmrs.resources.PatientResource;

import static org.mockito.Mockito.mock;

public class OpenMRSMock extends OpenMRS {

    public OpenMRSMock() {
        super("http://openmrs", "user", "password");
    }

    @Override
    public PatientResource patient() {
        return mock(PatientResource.class);
    }
}
