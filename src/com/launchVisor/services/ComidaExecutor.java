package com.launchVisor.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.launchVisor.domain.Comida;
import com.launchVisor.domain.EstadoComida;
import java.util.logging.Logger;

@Component
@Scope("singleton")
public class ComidaExecutor {

    private List<Comida> pool = new LinkedList<>();
    private Long id = 1L;
    private static final Logger LOG = Logger.getLogger(ComidaExecutor.class.getName());

//    @PostConstruct
//    public void initialize() {
//        Runnable taskPoolConsumer;
//        taskPoolConsumer = new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        ComidaExecutor.this.pool.stream().filter(task -> task.isRunning() && task.getDuration() > 0).forEach(task -> {
//                            task.decrementDuration();
//                        });
//                        ComidaExecutor.this.pool.stream().filter(task -> task.isRunning() && task.getDuration() == 0).forEach(task -> task.setStatus(ComidaStatus.SUCCESS));
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        LOG.warning(e.getMessage());
//                    }
//                }
//            }
//        };
//
//        new Thread(taskPoolConsumer).start();
//    }
    public List<Comida> getPoolDisponible() {
        List<Comida> poolAux = new LinkedList<>();
        this.pool.stream().filter((com) -> (com.getEstado().equals(EstadoComida.DISPONIBLE))).forEachOrdered((com) -> {
            poolAux.add(com);
        });
        return poolAux;
    }

    public List<Comida> getPool() {
        return this.pool;
    }

    public void addComida(Comida comida) {
        comida.setId(id);
        comida.setNombre(comida.getNombre().toUpperCase());
        this.pool.add(comida);
        id++;
    }

    public void updateComida(Long id) {
        for (Comida com : this.pool) {
            if (com.getId().equals(id)) {
                com.setEstado(EstadoComida.AGOTADO);
                break;
            }
        }
    }

    public List<Comida> deletePool() {
        id = 1L;
        return this.pool = new LinkedList<>();
    }

}
