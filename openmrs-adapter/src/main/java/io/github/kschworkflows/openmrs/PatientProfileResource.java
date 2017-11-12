package io.github.kschworkflows.openmrs;

import com.experimental.openmrs.Patient;
import com.experimental.openmrs.resources.PatientProfilResource;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

public class PatientProfileResource extends PatientProfilResource {

    private static final String BAHMNICORE_PATIENTPROFILE_RESOURCE_PATH = "/v1/bahmnicore/patientprofile";

    public PatientProfileResource(CustomizedOpenMRS bahmniImpl) {
        super(bahmniImpl);
    }

    public Patient createPatient(String firstName, String lastName) {
        String payload = getPatientCreationPayload(firstName,lastName);
        HttpResponse<JsonNode> response = openMRS.post(BAHMNICORE_PATIENTPROFILE_RESOURCE_PATH, payload);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Could not create patient. " + response.getBody().toString());
        }
        return parsePatient(response);
    }

    private String getPatientCreationPayload(String firstName, String lastName) {
        String displayName = String.format("%s %s", firstName, lastName);

        //language=JSON
        String json = String.format("{\n" +
                "  \"patient\":{\n" +
                "    \"person\":{\n" +
                "      \"names\":[\n" +
                "        {\n" +
                "          \"givenName\":\"%s\",\n" +
                "          \"familyName\":\"%s\",\n" +
                "          \"display\":\"%s\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"addresses\":[\n" +
                "        {\n" +
                "          \"cityVillage\":\"KS\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"birthdate\":\"2010-10-25T17:23:26.431+0200\",\n" +
                "      \"gender\":\"F\"\n" +
                "    },\n" +
                "    \"identifiers\":[\n" +
                "      {\n" +
                "        \"identifierSourceUuid\":\"c1d8a345-3f10-11e4-adec-0800271c1b75\",\n" +
                "        \"identifierPrefix\":\"GAN\",\n" +
                "        \"identifierType\":\"81433852-3f10-11e4-adec-0800271c1b75\",\n" +
                "        \"preferred\":true,\n" +
                "        \"voided\":false\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"relationships\":[\n" +
                "\n" +
                "  ]\n" +
                "}", firstName, lastName, displayName);
        return json;
    }
}
