package space.yurisi.universecore.database.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "gacha_content")
public class GachaContent {

    @Id
    @Column(name = "id", unique = true, columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gacha_id", columnDefinition = "BIGINT UNSIGNED")
    private Long gacha_id;

    @Column(name = "gacha_item_id", columnDefinition = "BIGINT UNSIGNED")
    private Long gacha_item_id;

    @Column(name = "rarelity", columnDefinition = "BIGINT UNSIGNED")
    private Long rarelity;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updated_at;

    public GachaContent(
            Long id,
            Long gacha_id,
            Long gacha_item_id,
            Long rarelity,
            Date created_at,
            Date updated_at
    ) {
        this.id = id;
        this.gacha_id = gacha_id;
        this.gacha_item_id = gacha_item_id;
        this.rarelity = rarelity;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public GachaContent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGacha_id() {
        return gacha_id;
    }

    public void setGacha_id(Long gacha_id) {
        this.gacha_id = gacha_id;
    }

    public Long getGacha_item_id() {
        return gacha_item_id;
    }

    public void setGacha_item_id(Long gacha_item_id) {
        this.gacha_item_id = gacha_item_id;
    }

    public Long getRarelity() {
        return rarelity;
    }

    public void setRarelity(Long rarelity) {
        this.rarelity = rarelity;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
