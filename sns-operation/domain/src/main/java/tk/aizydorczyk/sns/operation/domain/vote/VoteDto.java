package tk.aizydorczyk.sns.operation.domain.vote;

import tk.aizydorczyk.sns.common.domain.vote.VoteTypes;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

public class VoteDto extends BaseDto {
    private VoteTypes type;

    public VoteDto() {
    }

    public VoteDto(VoteTypes type) {
        this.type = type;
    }

    public VoteTypes getType() {
        return type;
    }

    public void setType(VoteTypes type) {
        this.type = type;
    }
}
