package com.example.telegramtestbot.repository;

import com.example.telegramtestbot.model.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ChatRepo extends CrudRepository<Chat, Long> {
    Optional<Chat> findByChatId(String chatId);
}
