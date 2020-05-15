package tk.aizydorczyk.sns.operation.domain.comment.delete

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import tk.aizydorczyk.sns.common.infrastructure.utils.UtilsConfiguration
import tk.aizydorczyk.sns.operation.domain.config.H2JpaConfiguation
import tk.aizydorczyk.sns.operation.domain.config.TestTimeConfiguration
import tk.aizydorczyk.sns.operation.infrastructure.rest.RestConfiguration

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner.class)
@SpringBootTest(classes = [H2JpaConfiguation,
        DeleteCommentCommandConfiguration,
        DeleteCommentController,
        UtilsConfiguration,
        TestTimeConfiguration,
        RestConfiguration])
@AutoConfigureMockMvc
class CommentDeleteControllerTest extends Specification {

    private static final String INSERT_POST = "INSERT INTO PUBLIC.POST(id, created_at, created_by, deleted, modified_at, modified_by, version, content) VALUES(1, '2012-12-12 12:12:12', '2D1EBC5B7D2741979CF0E84451C5BBB1', false, NULL, NULL, 0, 'TEST_POST')"
    private static final String INSERT_COMMENT = "INSERT INTO PUBLIC.COMMENT(id, created_at, created_by, deleted, modified_at, modified_by, version, post_id, comment) VALUES(1, '2012-12-12 12:12:12', '2D1EBC5B7D2741979CF0E84451C5BBB1', false, NULL, NULL, 0, 1, 'TEST_COMMENT')"

    private static final String DELETE_POSTS = "DELETE FROM POST"
    private static final String DELETE_COMMENTS = "DELETE FROM COMMENT"

    @Autowired
    private MockMvc mockMvc

    @Test
    @SqlGroup([
            @Sql(statements = [INSERT_POST, INSERT_COMMENT]),
            @Sql(statements = [DELETE_COMMENTS, DELETE_POSTS], executionPhase = AFTER_TEST_METHOD)
    ])
    def "should delete comment and return with NO_CONTENT status"() throws Exception {
        given:
        def testUserUuid = UUID.randomUUID().toString()
        when:
        def result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/posts/1/comments/1")
                .header("userUuid", testUserUuid)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

        then:
        result.andExpect(status().isNoContent())
    }

}
