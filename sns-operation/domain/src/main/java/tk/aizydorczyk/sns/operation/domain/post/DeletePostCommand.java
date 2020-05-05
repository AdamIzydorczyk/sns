package tk.aizydorczyk.sns.operation.domain.post;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteCommand;

import java.util.function.Consumer;

class DeletePostCommand extends BaseDeleteCommand {
    DeletePostCommand(Consumer<Long> deleteById) {
        super(deleteById);
    }
}
