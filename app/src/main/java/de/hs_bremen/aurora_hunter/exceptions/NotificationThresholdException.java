package de.hs_bremen.aurora_hunter.exceptions;

public class NotificationThresholdException extends  Exception {

    public static final double MIN_THRESHOLD = 0.0;

    public static final double MAX_THRESHOLD = 1.0;


    private final double mThreshold;

    public NotificationThresholdException(final double threshold){
        super(String.format("Threshold of: %.3f  is not allowed. Must between %.3f and %.3f",threshold,MIN_THRESHOLD,MAX_THRESHOLD));
        this.mThreshold = threshold;
    }


    public double getThreshold() {
        return mThreshold;
    }
}
