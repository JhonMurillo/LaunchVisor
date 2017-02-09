package com.launchVisor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.launchVisor.domain.Comida;

@RestController
@RequestMapping("/api/comida")
public class ComidaService {

    @Autowired
    private ComidaExecutor comidaExecutor;

    @RequestMapping(method = RequestMethod.GET)
    public List<Comida> getComidas() {
        return this.comidaExecutor.getPool();
    }

    @RequestMapping(value = "disponible", method = RequestMethod.GET)
    public List<Comida> getComidasDisponble() {
        return this.comidaExecutor.getPoolDisponible();
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public List<Comida> deletePool() {
        return this.comidaExecutor.deletePool();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addComida(@RequestBody Comida taskToAdd) {
        this.comidaExecutor.addComida(taskToAdd);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void updateComida(@RequestBody Long id) {
        this.comidaExecutor.updateComida(id);
    }

}
