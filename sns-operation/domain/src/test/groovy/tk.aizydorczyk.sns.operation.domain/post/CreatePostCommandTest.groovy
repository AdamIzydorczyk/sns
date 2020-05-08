package tk.aizydorczyk.sns.operation.domain.post

import org.junit.Before
import org.junit.Test
import spock.lang.Shared
import spock.lang.Specification
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper
import tk.aizydorczyk.sns.operation.domain.support.HashMapRepository
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation

import java.time.LocalDateTime
import java.time.ZoneOffset

import static org.junit.Assert.assertEquals

class CreatePostCommandTest extends Specification {

    @Shared
    private Mapper mapper

    @Before
    void init() {
        this.mapper = new PostMapper()
    }

    @Test
    def "should create new Post by PostDto"() {
        given: "send PostDto with actual time with executing user uuid"
        final PostDto postDto = new PostDto("test")
        final HashMapRepository<Post> postRepository = new HashMapRepository<>()
        final CreatePostCommand createPostCommand = new CreatePostCommand(postRepository.&save, { post -> mapper.map(post, PostDto.class) }, mapper)
        final LocalDateTime testTime = LocalDateTime.of(12, 12, 12, 12, 12, 12)
        final UUID testUserUUid = UUID.fromString("f734bb20-91cb-4925-92ba-93aab2d19a21")

        when: "CreatePostCommand was executed"
        final PostDto result = createPostCommand.execute(postDto, new AuditingInformation(testUserUUid, testTime))

        then: "receive PostDto with assigned sended content, new id and auditing information"
        assertEquals(Long.valueOf(1L), result.getId())
        assertEquals("test", result.getContent())
        assertEquals(Long.valueOf(testTime.toEpochSecond(ZoneOffset.UTC)), result.getCreatedAt())
        assertEquals(testUserUUid, result.getCreatedBy())
    }

}
