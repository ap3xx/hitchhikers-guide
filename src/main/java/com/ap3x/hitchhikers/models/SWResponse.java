package com.ap3x.hitchhikers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SWResponse {

    private String next;
    private String previous;
    private List<SWPlanet> results;
}