package tk.aizydorczyk.sns.operation.domain.post

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper

import java.time.ZoneOffset

class PostMapper implements Mapper {
    @Override
    <DestinationType> DestinationType map(Object source, Class<DestinationType> destinationType) {
        final Post post = (Post) source
        final PostDto postDto = new PostDto(post.getContent())
        postDto.setCreatedAt(post.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
        postDto.setCreatedBy(post.getCreatedBy())
        postDto.setId(post.getId())
        return (DestinationType) postDto
    }
}
