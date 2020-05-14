package tk.aizydorczyk.sns.operation.domain.post

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper

import java.time.LocalDateTime
import java.time.ZoneOffset

class PostMapper implements Mapper {
    @Override
    <DestinationType> DestinationType map(Object source, Class<DestinationType> destinationType) {
        final Post post = (Post) source
        final PostDto postDto = new PostDto(post.getContent())
        postDto.setCreatedAt(toSeconds(post.getCreatedAt()))
        postDto.setCreatedBy(post.getCreatedBy())
        postDto.setModifiedAt(toSeconds(post.getModifiedAt()))
        postDto.setModifiedBy(post.getModifiedBy())
        postDto.setId(post.getId())
        return (DestinationType) postDto
    }

    private static Long toSeconds(LocalDateTime time) {
        return time?.toEpochSecond(ZoneOffset.UTC)
    }
}
