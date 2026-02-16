package com.example.taskmanager.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;

class TaskServiceTest {
    @Test
    void testCreateTask() {
        TaskRepository repo = Mockito.mock(TaskRepository.class);
        Task t = new Task("Test","desc","alice");
        when(repo.save(any(Task.class))).thenReturn(t);
        TaskService s = new TaskService(repo);
        Task created = s.createTask(t);
        assertEquals("Test", created.getTitle());
    }

    @Test
    void testUpdateStatusNotFound() {
        TaskRepository repo = Mockito.mock(TaskRepository.class);
        when(repo.findById(1L)).thenReturn(Optional.empty());
        TaskService s = new TaskService(repo);
        try {
            s.updateStatus(1L, Task.Status.COMPLETED, "alice");
        } catch (TaskService.ResourceNotFoundException ex) {
            assertEquals("Task not found", ex.getMessage());
        }
    }
}
