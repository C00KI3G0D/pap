package crm.local.pap.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crm.local.pap.models.Task;

@Repository

public interface TasksRepository extends JpaRepository <Task, UUID> {
    Optional<Task> findByTopic(String topic);
    Boolean existsByTopic(String topic);
} 