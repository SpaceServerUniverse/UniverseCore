package space.yurisi.universecore.database.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "land_permissions")
public class LandPermission {
    @Id
    @Column(name = "id", columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "land_id", columnDefinition = "BIGINT UNSIGNED NOT NULL")
    private Long land_id;

    @Column(name = "uuid", columnDefinition = "VARCHAR(255) NOT NULL")
    private String uuid;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    public LandPermission (
            Long id,
            Long land_id,
            String uuid,
            Date created_at
    ) {
        this.id = id;
        this.land_id = land_id;
        this.uuid= uuid;
        this.created_at = created_at;
    }

    public LandPermission () {

    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Long getLand_id() {
        return land_id;
    }

    public void setLand_id(Long land_id) {
        this.land_id = land_id;
    }

    public String getUUID () {
        return uuid;
    }

    public void setUUID (Long user_id) {
        this.uuid = uuid;
    }

    public Date getCreated_at () {
        return created_at;
    }

    public void setCreated_at (Date created_at) {
        this.created_at = created_at;
    }

    @PrePersist
    protected void onCreate () {
        this.created_at = new Date ();
    }
}
