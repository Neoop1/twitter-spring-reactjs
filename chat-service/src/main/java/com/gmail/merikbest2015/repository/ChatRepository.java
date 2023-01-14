package com.gmail.merikbest2015.repository;

import com.gmail.merikbest2015.commons.models.Chat;
import com.gmail.merikbest2015.repository.projection.ChatProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT c FROM Chat c WHERE c.id = :chatId")
    ChatProjection getChatById(@Param("chatId") Long chatId);

    @Query("SELECT chat FROM Chat chat " +
            "JOIN chat.participants chatParticipant " +
            "WHERE chat.id = :chatId " +
            "AND chatParticipant.user.id = :userId")
    Optional<ChatProjection> getChatById(@Param("chatId") Long chatId, @Param("userId") Long userId);

    @Query("SELECT CASE WHEN count(chatParticipant) > 0 THEN true ELSE false END " +
        "FROM Chat chat " +
        "JOIN chat.participants chatParticipant " +
        "WHERE chat.id = :chatId " +
        "AND chatParticipant.user.id = :userId")
    boolean getChatByUserId(@Param("chatId") Long chatId, @Param("userId") Long userId);
}