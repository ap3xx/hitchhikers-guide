package com.ap3x.hitchhikers.controller;

import com.ap3x.hitchhikers.doc.PlanetControllerSwaggerConfig;
import com.ap3x.hitchhikers.dto.PlanetDTO;
import com.ap3x.hitchhikers.model.PaginatedResponse;
import com.ap3x.hitchhikers.model.Planet;
import com.ap3x.hitchhikers.service.PlanetService;
import com.ap3x.hitchhikers.service.SWService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/planets")
public class PlanetController implements PlanetControllerSwaggerConfig {

    @Autowired
    private SWService swService;
    @Autowired
    private PlanetService planetService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PaginatedResponse<Planet> list(
            @RequestParam(defaultValue = "false") final Boolean sw,
            @RequestParam(defaultValue = "1") final Integer page,
            @RequestParam(required = false) final String name) {
        if (sw)
            return listFromSW(page);
        else {
            return listFromBase(page, name);
        }
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Planet getById(@PathVariable("id") final Integer id) throws NotFoundException {
        return planetService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Planet create(@RequestBody final PlanetDTO planetDTO) {
        return planetService.create(planetDTO);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Planet update(
            @PathVariable("id") final Integer id,
            @RequestBody final PlanetDTO planetDTO) throws NotFoundException {
        return planetService.update(id, planetDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Integer id) throws NotFoundException {
        planetService.delete(id);
    }

    private PaginatedResponse<Planet> listFromSW(final Integer page) {
        return swService.listAll(page);
    }

    private PaginatedResponse<Planet> listFromBase(final Integer page, final String name) {
        return planetService.listAll(page, name);
    }

}
