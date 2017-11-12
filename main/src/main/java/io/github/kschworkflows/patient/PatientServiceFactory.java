package io.github.kschworkflows.patient;

import io.github.kschworkflows.openmrs.CustomizedOpenMRS;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class PatientServiceFactory {

    private static Map<String, Callable<PatientService>> implementations = new HashMap<>();

    static {
        registerImplementation("production", () -> new PatientServiceImpl(CustomizedOpenMRS.getInstance()));
    }

    public static void registerImplementation(String type, Callable<PatientService> factoryMethod) {
        implementations.put(type, factoryMethod);
    }

    public static PatientService getPatientService() {
        return getPatientService("production");
    }

    public static PatientService getPatientService(String type) {
        if (!implementations.containsKey(type)) {
            throw new IllegalArgumentException(String.format("There is no implementation for type '%s' present.", type));
        }
        try {
            return implementations.get(type).call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
