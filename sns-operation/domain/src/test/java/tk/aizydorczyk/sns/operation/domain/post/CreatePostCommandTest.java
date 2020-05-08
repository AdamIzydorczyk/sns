package tk.aizydorczyk.sns.operation.domain.post;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.support.HashMapRepository;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class CreatePostCommandTest {

    private Mapper mapper;

    @Before
    public void init() {
        this.mapper = new Mapper() {
            @Override
            public <DestinationType> DestinationType map(Object source, Class<DestinationType> destinationType) {
                if (source.getClass().equals(Post.class) && destinationType.equals(PostDto.class)) {
                    final Post post = (Post) source;
                    final PostDto postDto = new PostDto(post.getContent());
                    postDto.setCreatedAt(post.getCreatedAt().toEpochSecond(ZoneOffset.UTC));
                    postDto.setCreatedBy(post.getCreatedBy());
                    postDto.setId(post.getId());
                    return (DestinationType) postDto;
                }

                throw new RuntimeException();
            }
        };
    }

    @Test
    public void shouldCreatePost() {
        //given
        final PostDto postDto = new PostDto("test");
        final HashMapRepository<Post> postRepository = new HashMapRepository<>();
        final CreatePostCommand createPostCommand = new CreatePostCommand(postRepository::save, post -> mapper.map(post, PostDto.class), mapper);
        final LocalDateTime testTime = LocalDateTime.of(12, 12, 12, 12, 12, 12);
        final UUID testUserUUid = UUID.fromString("f734bb20-91cb-4925-92ba-93aab2d19a21");

        //when
        final PostDto result = createPostCommand.execute(postDto, new AuditingInformation(testUserUUid, testTime));

        //then
        Assert.assertSame(1L, result.getId());
        Assert.assertEquals("test", result.getContent());
        Assert.assertEquals(result.getCreatedAt(), Long.valueOf(testTime.toEpochSecond(ZoneOffset.UTC)));
        Assert.assertEquals(result.getModifiedBy(), result.getCreatedBy());
    }

}
