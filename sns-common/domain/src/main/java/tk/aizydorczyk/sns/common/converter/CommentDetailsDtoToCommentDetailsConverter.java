package tk.aizydorczyk.sns.common.converter;

import org.modelmapper.AbstractConverter;
import tk.aizydorczyk.sns.common.domain.comment.CommentDetails;
import tk.aizydorczyk.sns.common.domain.comment.CommentsDetailsDto;

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
