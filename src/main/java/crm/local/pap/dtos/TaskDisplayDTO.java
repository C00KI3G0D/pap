package crm.local.pap.dtos;

import lombok.Data;

@Data
public class TaskDisplayDTO {
    private String taskId;
    private String clientName;
    private String topic;
    private String state;
    private String responsibleName;
}