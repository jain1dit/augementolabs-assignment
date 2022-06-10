package com.augmentolabs.rmzcorp.realestate.response;

import com.augmentolabs.rmzcorp.realestate.entities.Zone;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

public class ZoneResponse {

    public static Optional<Zone>  getZones() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = ResourceUtils.getFile("classpath:response/mock-zones.json");
        String json = new String(Files.readAllBytes(file.toPath()));
        return Optional.ofNullable(objectMapper.readValue(json, new TypeReference<Zone>() {
        }));
    }
}
