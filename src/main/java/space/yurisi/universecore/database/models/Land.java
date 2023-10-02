package space.yurisi.universecore.database.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "lands")
public class Land {
    @Id
    @Column(name = "id", columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", columnDefinition = "BIGINT UNSIGNED NOT NULL")
    private Long user_id;

    @Column(name = "start_x", nullable = false)
    private int start_x;

    @Column(name = "start_z", nullable = false)
    private int start_z;

    @Column(name = "end_x", nullable = false)
    private int end_x;

    @Column(name = "end_z", nullable = false)
    private int end_z;

    @Column(name = "world_name", columnDefinition = "VARCHAR(255) NOT NULL")
    private String world_name;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    public Land (
            Long id,
            Long user_id,
            int start_x,
            int start_z,
            int end_x,
            int end_z,
            String world_name,
            Date created_at
    ) {
        this.id = id;
        this.user_id = user_id;
        this.start_x = start_x;
        this.start_z = start_z;
        this.end_x = end_x;
        this.end_z = end_z;
        this.world_name = world_name;
        this.created_at = created_at;
    }

    public Land () {

    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Long getUser_id () {
        return user_id;
    }

    public void setUser_id (Long user_id) {
        this.user_id = user_id;
    }

    public int getStart_x () {
        return start_x;
    }

    public void setStart_x (int start_x) {
        this.start_x = start_x;
    }

    public int getStart_z () {
        return start_z;
    }

    public void setStart_z (int start_z) {
        this.start_z = start_z;
    }

    public int getEnd_x () {
        return end_x;
    }

    public void setEnd_x (int end_x) {
        this.end_x = end_x;
    }

    public int getEnd_z () {
        return end_z;
    }

    public void setEnd_z (int end_z) {
        this.end_z = end_z;
    }

    public String getWorld_name () {
        return world_name;
    }

    public void setWorld_name (String world_name) {
        this.world_name = world_name;
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
