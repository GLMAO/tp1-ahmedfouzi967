package org.emp.gl.timer.service;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public interface TimerChangeListener extends PropertyChangeListener {


    String DIXEME_DE_SECONDE_PROP = "dixieme";


    String SECONDE_PROP = "seconde";


    String MINUTE_PROP = "minute";


    String HEURE_PROP = "heure";

    
    @Override
    void propertyChange(PropertyChangeEvent evt);
}
