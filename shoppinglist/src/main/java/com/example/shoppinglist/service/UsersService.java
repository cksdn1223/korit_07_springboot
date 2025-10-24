package com.example.shoppinglist.service;

import com.example.shoppinglist.entity.Items;
import com.example.shoppinglist.entity.Users;
import com.example.shoppinglist.repository.ItemsRepository;
import com.example.shoppinglist.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final ItemsRepository itemsRepository;

    // 로그인한 유저의 모든 아이템 불러오기
    public List<Items> getItems(UserDetails userDetails){
        Users user = usersRepository.findByUsername(userDetails.getUsername())
                        .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
        return usersRepository.findItemsByUserId(user.getId());
    }
}
