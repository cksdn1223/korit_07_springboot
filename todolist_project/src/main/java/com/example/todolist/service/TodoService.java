package com.example.todolist.service;

import com.example.todolist.dto.AccountCredentialsRecord;
import com.example.todolist.dto.AppUserRecord;
import com.example.todolist.dto.TodoRequestRecord;
import com.example.todolist.dto.TodoUpdateRecord;
import com.example.todolist.entity.AppUser;
import com.example.todolist.entity.Todo;
import com.example.todolist.repository.AppUserRepository;
import com.example.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final AppUserRepository appUserRepository;

    public TodoRequestRecord findById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + " 을 찾을 수 없습니다."));
        return TodoRequestRecord.from(todo);
    }

    public TodoRequestRecord addTodo(String content, String username){
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("찾을수 없음"));
        Todo todo = new Todo(content, appUser);
        return TodoRequestRecord.from(todoRepository.save(todo));
    }

    public void deleteTodo(Long id){
        todoRepository.deleteById(id);
    }

    @Transactional
    public Optional<TodoUpdateRecord> updateTodoContent(Long id, String content){
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setContent(content);
                    return new TodoUpdateRecord(content);
                });
    }

    @Transactional
    public Optional<TodoRequestRecord> updateTodoStatus(Long id){
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setCompleted(!todo.isCompleted());
                    return TodoRequestRecord.from(todo);
                });
    }

    @Transactional
    public ResponseEntity<AppUserRecord> clearCompletedTodos(UserDetails userdetails){
        AppUser appUser = appUserRepository.findByUsername(userdetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found: " + userdetails.getUsername()));
        appUser.getTodos().removeIf(Todo::isCompleted);
        return new ResponseEntity<>(new AppUserRecord(appUser.getUsername(), appUser.getRole(), appUser.getTodos()), HttpStatus.OK);
    }


}

