package back.SportApp.Spring.model;


import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CalistrackEntity {

    @Column(name = "creation_user")
    protected String creationUser;

    @Column(name = "creation_date")
    protected Date creationDate;

    @Column(name = "creation_reason")
    protected String creationReason;

    @Column(name = "modification_user")
    protected String modificationUser;

    @Column(name = "modification_date")
    protected Date modificationDate;

    @Column(name = "modification_reason")
    protected String modificationReason;

    public abstract long getId();

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(final String creationUser) {
        this.creationUser = creationUser;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationReason() {
        return creationReason;
    }

    public void setCreationReason(final String creationReason) {
        this.creationReason = creationReason;
    }

    public String getModificationUser() {
        return modificationUser;
    }

    public void setModificationUser(final String modificationUser) {
        this.modificationUser = modificationUser;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(final Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getModificationReason() {
        return modificationReason;
    }

    public void setModificationReason(final String modificationReason) {
        this.modificationReason = modificationReason;
    }

    @Override
    public boolean equals(final Object obj) {
        return obj == this || obj instanceof final CalistrackEntity entity && entity.getId() == getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}

