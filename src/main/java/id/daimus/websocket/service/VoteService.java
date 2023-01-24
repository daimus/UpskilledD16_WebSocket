package id.daimus.websocket.service;

import id.daimus.websocket.dto.VoteResult;
import id.daimus.websocket.entity.Vote;
import id.daimus.websocket.repository.VoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VoteService {
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    public Vote createVote(Vote vote){
        Vote v = voteRepository.save(vote);
        simpMessagingTemplate.convertAndSend("/i/vote-update", v);
        this.getVoteResult();
        return v;
    }

    public VoteResult getVoteResult(){
        VoteResult result = voteRepository.getVoteResult();
        simpMessagingTemplate.convertAndSend("/i/vote-summary", result);
        return result;
    }
}
