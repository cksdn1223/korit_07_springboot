package com.example.shoppinglist.service;

import com.example.shoppinglist.dto.ItemsRequestRecord;
import com.example.shoppinglist.entity.Items;
import com.example.shoppinglist.entity.Users;
import com.example.shoppinglist.repository.ItemsRepository;
import com.example.shoppinglist.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemsService {
    private final ItemsRepository itemsRepository;
    private final UsersRepository usersRepository;


    // createItem
    public Items createItem(ItemsRequestRecord itemRecord, UserDetails userDetails) {
        Users user = usersRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
        Items newItem = new Items(itemRecord.itemName(), itemRecord.amount(), user);
        return itemsRepository.save(newItem);
    }
    // updateItem
    public Items updateItem(Long id, ItemsRequestRecord itemRecord, UserDetails userDetails) {
        Users user = usersRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
        Items item = itemsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("아이템을 찾을 수 없습니다."));
        item.setItemName(itemRecord.itemName());
        item.setAmount(itemRecord.amount());
        return itemsRepository.save(item);
    }
    // deleteItem
    public void deleteItem(Long id){
        itemsRepository.deleteById(id);
    }
}
