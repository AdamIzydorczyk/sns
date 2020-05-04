package tk.aizydorczyk.sns.operation.domain.comment;

import org.modelmapper.AbstractConverter;

import java.util.Optional;

public class CommentDetailsDtoToCommentDetailsConverter extends AbstractConverter<CommentsDetailsDto, CommentDetails> {

    @Override
    protected CommentDetails convert(CommentsDetailsDto source) {
        return Optional.ofNullable(source)
                .map(commentDetails -> new CommentDetails(
                        commentDetails.getComment(),
                        commentDetails.getPluses(),
                        commentDetails.getMinuses()))
                .orElse(null);
    }
}
