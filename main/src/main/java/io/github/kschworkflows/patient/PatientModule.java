package io.github.kschworkflows.patient;

import com.google.inject.AbstractModule;
import io.github.kschworkflows.openmrs.CustomizedOpenMRS;

public class PatientModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PatientService.class).toInstance(new PatientServiceImpl(CustomizedOpenMRS.getInstance()));
    }
}
