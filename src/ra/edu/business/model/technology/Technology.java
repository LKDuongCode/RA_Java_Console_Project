package ra.edu.business.model.technology;

import java.time.LocalDateTime;

public class Technology {
    private int id;
    private String name;
    private TechnologyStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Technology() {
    }

    public Technology(int id, String name, TechnologyStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;

    }

    public Technology(int id, String name, TechnologyStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TechnologyStatus getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(TechnologyStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

