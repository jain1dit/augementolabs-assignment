package com.augmentolabs.rmzcorp.realestate.response;

import com.augmentolabs.rmzcorp.realestate.entities.City;
import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public class FloorResponse {
    public static Optional<Floor> getFloor() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = ResourceUtils.getFile("classpath:response/mock-floor.json");
        String json = new String(Files.readAllBytes(file.toPath()));
        return Optional.ofNullable(objectMapper.readValue(json, new TypeReference<Floor>() {
        }));
    }
}
