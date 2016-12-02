package com.github.yukinami.webapp.domain.shared;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author snowblink on 16/5/25.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<T extends BaseEntity> implements Entity<T> {

    @Id
    @GeneratedValue
    protected Long id;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Override
    public boolean sameIdentityAs(T other) {
        return other != null && this.id.equals(other.id);
    }

    @PrePersist
    public void setCurrentCreatedAt() {
        Timestamp time = Timestamp.valueOf(LocalDateTime.now());
        this.createdAt = time;
        this.updatedAt = time;
    }

    @PreUpdate
    public void setCurrentUpdateAt() {
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        @SuppressWarnings("unchecked")
        final T other = (T) object;
        return sameIdentityAs(other);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
