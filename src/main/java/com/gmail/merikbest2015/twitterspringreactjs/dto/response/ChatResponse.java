package com.gmail.merikbest2015.twitterspringreactjs.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ChatResponse {
    private Long id;
    private List<ChatParticipantResponse> participants;
}