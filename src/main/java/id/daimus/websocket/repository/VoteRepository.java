package id.daimus.websocket.repository;

import id.daimus.websocket.dto.VoteResult;
import id.daimus.websocket.entity.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {
    @Query(name = "get_vote_result")
    VoteResult getVoteResult();
}
