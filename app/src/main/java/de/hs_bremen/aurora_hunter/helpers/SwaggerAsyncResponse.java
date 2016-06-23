package de.hs_bremen.aurora_hunter.helpers;

import de.hs_bremen.aurora_hunter.commons.notifications.ApiException;

/**
 * @author FJ,SB,AC
 * @param <G>
 */
public class SwaggerAsyncResponse<G> {
    public G response = null;
    public ApiException err = null;
}
