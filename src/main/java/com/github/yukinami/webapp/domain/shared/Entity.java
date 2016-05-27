package com.github.yukinami.webapp.domain.shared;

import java.io.Serializable;

/**
 * An entity, as explained in the DDD book.
 */
public interface Entity<T> extends Serializable {

    /**
     * Entities compare by identity, not by attributes.
     *
     * @param other The other entity.
     * @return true if the identities are the same, regardless of other attributes.
     */
    boolean sameIdentityAs(T other);

}
