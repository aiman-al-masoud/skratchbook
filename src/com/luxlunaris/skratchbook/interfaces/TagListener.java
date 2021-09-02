package com.luxlunaris.skratchbook.interfaces;

import com.luxlunaris.skratchbook.model.Tag;

/**
 * Used to listen to the Settings facade controller for live updates
 * on the value of specified Setting's Tag/s.
 */
public interface TagListener {

    /**
     * Notify listener that given tag got updated.
     * @param tag
     */
    void onTagUpdated(Tag tag);



}
