package crm.local.pap.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.local.pap.dtos.TaskDTO;
import crm.local.pap.dtos.TaskDisplayDTO;
import crm.local.pap.models.Task;
import crm.local.pap.models.User;
import crm.local.pap.repositories.TasksRepository;
import crm.local.pap.repositories.UserRepository;

@Service
public class TaskService {

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TaskDisplayDTO> getTasksForDisplay() {
        return tasksRepository.findAll().stream().map(task -> {

            TaskDisplayDTO dto = new TaskDisplayDTO();
            dto.setTaskId(task.getTaskid().toString());
            dto.setTopic(task.getTopic());
            dto.setState(task.getState());

            if (task.getResponsible() != null) {
                dto.setClientName(task.getResponsible().getFirstName());
                dto.setResponsibleName(task.getResponsible().getFirstName() + " " + task.getResponsible().getLastName());
            } else {
                dto.setClientName("N/A");
                dto.setResponsibleName("N/A");
            }
            return dto;
        }).collect(Collectors.toList());
    }

    public List<Task> getTasks() {
        return tasksRepository.findAll();
    }


    public Task createTask(TaskDTO taskDTO, String employeeUsername) {
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setPrice(taskDTO.getPrice());
        task.setTime(taskDTO.getTime());
        task.setState(taskDTO.getState());
        task.setTopic(taskDTO.getTopic());
        task.setNotes(taskDTO.getNotes());


        User employee = userRepository.findByEmail(employeeUsername)
                .orElseThrow(() -> new RuntimeException("Funcionário autenticado não encontrado."));
        task.setEmployee(employee);


        if (taskDTO.getResponsibleFirstName() != null && taskDTO.getResponsibleLastName() != null) {
            User responsible = userRepository.findByFirstNameAndLastName(
                    taskDTO.getResponsibleFirstName(),
                    taskDTO.getResponsibleLastName()
            ).orElseThrow(() -> new RuntimeException("Cliente responsável não encontrado com o nome: " +
                    taskDTO.getResponsibleFirstName() + " " + taskDTO.getResponsibleLastName()));
            task.setResponsible(responsible);
        } else {
            throw new RuntimeException("O nome e o apelido do cliente responsável são obrigatórios.");
        }


        return tasksRepository.save(task);
    }
}