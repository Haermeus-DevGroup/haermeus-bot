package dev.haermeus.haermeusbot.api;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import java.util.List;

public class ApiCenter {

    static ResourcesApi resourcesApi;
    static SectionsApi sectionsApi;

    private final static String BASE_HOST = "http://localhost:8081";

    private ApiCenter() {
    }

    public static ResourcesApi getResourcesApi() {
        if(resourcesApi == null) {
            resourcesApi = JAXRSClientFactory.create(BASE_HOST, ResourcesApi.class, List.of(new JacksonJsonProvider()));
        }
        return resourcesApi;
    }

    public static SectionsApi getSectionsApi() {
        if(sectionsApi == null) {
            sectionsApi = JAXRSClientFactory.create(BASE_HOST, SectionsApi.class, List.of(new JacksonJsonProvider()));
        }
        return sectionsApi;
    }
}
