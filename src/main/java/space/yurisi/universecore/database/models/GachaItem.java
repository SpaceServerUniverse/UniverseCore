package space.yurisi.universecore.database.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "gacha_item")
public class GachaItem {

    @Id
    @Column(name = "id", unique = true, columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id", columnDefinition = "BIGINT UNSIGNED")
    private Long item_id;

    @Column(name = "item_name", columnDefinition = "VARCHAR(40)")
    private String item_name;

    @Column(name = "item_amount", columnDefinition = "BIGINT UNSIGNED")
    @ColumnDefault("1")
    private Long item_amount;

    @Column(name = "enchantment", columnDefinition = "VARCHAR(26)")
    private String enchantment;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updated_at;

    public GachaItem(
            Long id,
            Long item_id,
            String item_name,
            Long item_amount,
            String enchantment,
            Date created_at,
            Date updated_at
    ) {
        this.id = id;
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_amount = item_amount;
        this.enchantment = enchantment;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public GachaItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Long getItem_amount() {
        return item_amount;
    }

    public void setItem_amount(Long item_amount) {
        this.item_amount = item_amount;
    }

    public String getEnchantment() {
        return enchantment;
    }

    public void setEnchantment(String enchantment) {
        this.enchantment = enchantment;
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
