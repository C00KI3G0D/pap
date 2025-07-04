package crm.local.pap.dtos;

import java.math.BigDecimal;
import java.time.Duration;

import lombok.Data;

@Data
public class TaskDTO {

     private String name;
    private BigDecimal price;
    private Duration time;
    private String state;
    private String topic;
    private String notes;
    private String responsibleFirstName; 
    private String responsibleLastName;
    
}
