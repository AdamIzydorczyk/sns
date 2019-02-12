package tk.aizydorczyk.sns.operation.domain.post;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteCommand;

import java.util.function.Consumer;

public class DeletePostCommand extends BaseDeleteCommand {
    public DeletePostCommand(Consumer<Long> deleteById) {
        super(deleteById);
    }
}
