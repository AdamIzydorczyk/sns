package tk.aizydorczyk.sns.operation.domain.vote;

import tk.aizydorczyk.sns.common.domain.vote.VoteType;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

public class VoteDto extends BaseDto {
    private VoteType type;

    public VoteDto() {
    }

    public VoteDto(VoteType type) {
        this.type = type;
    }

    public VoteType getType() {
        return type;
    }

    public void setType(VoteType type) {
        this.type = type;
    }
}
