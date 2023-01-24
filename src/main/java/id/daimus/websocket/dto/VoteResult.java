package id.daimus.websocket.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedNativeQuery(
        name = "get_vote_result",
        query = "SELECT (SELECT COUNT(id) FROM VOTES WHERE CANDIDATE_ID = 1) AS candidate1, (SELECT COUNT(id) FROM VOTES WHERE CANDIDATE_ID = 2) AS candidate2, (SELECT COUNT(id) FROM VOTES WHERE CANDIDATE_ID = 3) AS candidate3",
        resultSetMapping = "vote_result_dto"
)
@SqlResultSetMapping(
        name = "vote_result_dto",
        classes = @ConstructorResult(
                targetClass = VoteResult.class,
                columns = {
                        @ColumnResult(name = "candidate1", type = Integer.class),
                        @ColumnResult(name = "candidate2", type = Integer.class),
                        @ColumnResult(name = "candidate3", type = Integer.class)
                }
        )
)
public class VoteResult {
    private Integer candidate1;
    private Integer candidate2;
    private Integer candidate3;
    @Id
    private Long id;
}
