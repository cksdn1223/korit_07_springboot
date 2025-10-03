package com.example.todolist.service;

import com.example.todolist.dto.*;
import com.example.todolist.entity.AppUser;
import com.example.todolist.entity.Todo;
import com.example.todolist.exception.AccessDeniedException;
import com.example.todolist.exception.NoContentException;
import com.example.todolist.exception.ResourceNotFoundException;
import com.example.todolist.repository.AppUserRepository;
import com.example.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    // addtodo deletetodo 로그 추가하기위해 추가
    private static final Logger log = LoggerFactory.getLogger(TodoService.class);

    public Todo findTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(id + " 을 가진 Todo를 찾을 수 없습니다."));
    }
    private void validateOwnership(Todo todo, UserDetails userDetails) {
        if (!todo.getAppUser().getUsername().equals(userDetails.getUsername())) {
            throw new AccessDeniedException("이 리소스에 접근할 권한이 없습니다.");
        }
    }
    public void checkContent(String content) {
        if (content == null || content.isBlank()) {
            throw new NoContentException("내용은 비워둘 수 없습니다.");
        }
    }

//    API 구현 로직들
    public TodoRequestRecord findById(Long id) {
        return TodoRequestRecord.from(findTodoById(id));
    }

    public TodoRequestRecord addTodo(TodoRequestRecord todoRequestRecord, UserDetails userDetails) {
        checkContent(todoRequestRecord.content());
        log.info("Adding new todo for user: {}", userDetails.getUsername());
        AppUser appUser = appUserRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException(userDetails.getUsername() + " 을 찾을수 없음"));
        // 생성 시점에는 관계를 설정하지 않음 ⬇️
        Todo newTodo = new Todo(todoRequestRecord.content(), null);
        appUser.addTodo(newTodo); // 편의 메소드를 통해 양방향 관계를 한번에 설정
        return TodoRequestRecord.from(todoRepository.save(newTodo));
        // save 할때 객체 리턴 해줌
    }

    public void deleteTodo(Long id, UserDetails userDetails) {
        Todo todo = findTodoById(id);
        log.warn("Deleting todo id: {} by user: {}", id, userDetails.getUsername()); // 중요 작업은 WARN 레벨로
        validateOwnership(todo, userDetails);
        todoRepository.deleteById(id);
    }

    @Transactional
    public TodoUpdateRecord updateTodoContent(Long id, TodoUpdateRecord todoUpdateRecord, UserDetails userDetails) {
        checkContent(todoUpdateRecord.content());
        Todo todo = findTodoById(id);
        validateOwnership(todo, userDetails);
        todo.setContent(todoUpdateRecord.content());
        return new TodoUpdateRecord(todo.getContent());
    }

    @Transactional
    public TodoCompleteRecord updateTodoStatus(Long id, UserDetails userDetails) {
        Todo todo = findTodoById(id);
        validateOwnership(todo, userDetails);
        todo.setCompleted(!todo.isCompleted());
        return new TodoCompleteRecord(todo.getContent(), todo.isCompleted());
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")  // 이 메소드는 ADMIN 역할만 호출 가능
    public AppUserRecord clearCompletedTodos(UserDetails userdetails) {
        AppUser appUser = appUserRepository.findByUsername(userdetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userdetails.getUsername()));
//        if (!appUser.getRole().equals("ROLE_ADMIN")) { // 이렇게 하지말고 @PreAuthorize("hasRole('ADMIN')") 이거로 간편하게
//            throw new AccessDeniedException("이 리소스에 접근할 권한이 없습니다.");
//        }
        appUser.getTodos().removeIf(Todo::isCompleted);
        return new AppUserRecord(appUser.getUsername(), appUser.getRole(), appUser.getTodos());
    }
}
