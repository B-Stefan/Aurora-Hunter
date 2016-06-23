package de.hs_bremen.aurora_hunter.exceptions;

import de.hs_bremen.aurora_hunter.ui.fragments.PredictionFragment;

public class PredictionModeNotValidException extends RuntimeException {

    public PredictionModeNotValidException(PredictionFragment.NOTIFICATION_MODE mode){
        super("Mode not valid for this method:  " + mode.toString());
    }
}
