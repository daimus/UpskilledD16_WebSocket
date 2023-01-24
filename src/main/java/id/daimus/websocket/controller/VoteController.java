package id.daimus.websocket.controller;

import id.daimus.websocket.dto.VoteResult;
import id.daimus.websocket.entity.Vote;
import id.daimus.websocket.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class VoteController {
    @Autowired
    VoteService voteService;
    @MessageMapping("/vote")
    @SendTo("/i/vote")
    public void vote(Vote voteInput){
        log.info("Incoming message: {}", voteInput);
        voteService.createVote(voteInput);
    }
}