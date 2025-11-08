package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import java.beans.PropertyChangeEvent;


public class Horloge implements TimerChangeListener {

    private final String name;
    private TimerService timerService;


    public Horloge(String name) {
        this.name = name;
        System.out.println("Horloge " + name + " initialized!");
    }


    public void setTimerService(TimerService service) {
        // Se désinscrire de l'ancien service si existant
        if (this.timerService != null) {
            this.timerService.removeTimeChangeListener(this);
        }

        // Affecter le nouveau service
        this.timerService = service;

        // S'inscrire au nouveau service
        if (service != null) {
            service.addTimeChangeListener(this);
        }
    }


    public void afficherHeure() {
        if (timerService != null) {
            System.out.println(name + " affiche " +
                    formatNumber(timerService.getHeures()) + ":" +
                    formatNumber(timerService.getMinutes()) + ":" +
                    formatNumber(timerService.getSecondes()));
        } else {
            System.out.println(name + " : Service de temps non disponible");
        }
    }


    private String formatNumber(int number) {
        return (number < 10 ? "0" : "") + number;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            afficherHeure();
        }
    }


    public String getName() {
        return name;
    }
}
