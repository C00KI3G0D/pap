package crm.local.pap.models;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")

public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID taskid;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Duration time;

    @Column(nullable = false)
    private String state;
    
    @Column(nullable = false)
    private String topic;

    @Column(nullable = false)
    private String notes;
    

    @ManyToOne(cascade  = CascadeType.PERSIST)
    @JoinColumn(name = "responsible_id", nullable = false)
    private User responsible;

    
    @ManyToOne(cascade  = CascadeType.PERSIST)
    @JoinColumn(name = "employee_id", nullable = false)
    private User employee;

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }
}
