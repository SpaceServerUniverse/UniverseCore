package space.yurisi.universecore.database.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "gacha_info")
public class GachaInfo {

    @Id
    @Column(name = "id", unique = true, columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gacha_name")
    private String gacha_name;

    @Column(name = "gacha_description")
    private String gacha_description;

    @Column(name = "price")
    private Long price;

    @Column(name = "is_active")
    @ColumnDefault("true")
    private Boolean is_active;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updated_at;

    public GachaInfo(
            Long id,
            String gacha_name,
            String gacha_description,
            Long price,
            Boolean is_active,
            Date created_at,
            Date updated_at
    ) {
        this.id = id;
        this.gacha_name = gacha_name;
        this.gacha_description = gacha_description;
        this.price = price;
        this.is_active = is_active;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public GachaInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGacha_name() {
        return gacha_name;
    }

    public void setGacha_name(String gacha_name) {
        this.gacha_name = gacha_name;
    }

    public String getGacha_description() {
        return gacha_description;
    }

    public void setGacha_description(String gacha_description) {
        this.gacha_description = gacha_description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
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
