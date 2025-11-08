package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import java.beans.PropertyChangeEvent;


public class CompteARebours implements TimerChangeListener {

    private int compteur;
    private final TimerService timerService;
    private boolean actif;


    public CompteARebours(int initial, TimerService timerService) {
        this.compteur = initial;
        this.timerService = timerService;
        this.actif = true;

        // S'inscrire au service de temps
        timerService.addTimeChangeListener(this);

        System.out.println("⏳ Compte à rebours démarré avec " + initial + " secondes");
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName()) && actif) {
            tick();
        }
    }


    private void tick() {
        compteur--;

        if (compteur > 0) {
            System.out.println("⏳ " + compteur + " seconde(s) restante(s)");
        } else {
            System.out.println("💥 BOOM! Compte à rebours terminé!");
            arreter();
        }
    }


    public void arreter() {
        if (actif) {
            actif = false;
            timerService.removeTimeChangeListener(this);
            System.out.println("⏹️  Compte à rebours arrêté");
        }
    }


    public void redemarrer(int nouvelleValeur) {
        if (!actif) {
            actif = true;
            compteur = nouvelleValeur;
            timerService.addTimeChangeListener(this);
            System.out.println("🔄 Compte à rebours redémarré avec " + nouvelleValeur + " secondes");
        }
    }


    public int getCompteur() {
        return compteur;
    }


    public boolean isActif() {
        return actif;
    }
}
