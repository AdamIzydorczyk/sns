package tk.aizydorczyk.sns.operation.domain.comment.create

import com.fasterxml.jackson.databind.ObjectMapper
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification
import tk.aizydorczyk.sns.common.infrastructure.converter.ConvertersConfiguration
import tk.aizydorczyk.sns.common.infrastructure.mapper.MapperConfiguration
import tk.aizydorczyk.sns.common.infrastructure.utils.UtilsConfiguration
import tk.aizydorczyk.sns.operation.domain.comment.CommentDto
import tk.aizydorczyk.sns.operation.domain.config.H2JpaConfiguation
import tk.aizydorczyk.sns.operation.domain.config.TestTimeConfiguration
import tk.aizydorczyk.sns.operation.infrastructure.rest.RestConfiguration

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner.class)
@SpringBootTest(classes = [H2JpaConfiguation,
        CreateCommentCommandConfiguration,
        MapperConfiguration,
        CreateCommentController,
        UtilsConfiguration,
        TestTimeConfiguration,
        ConvertersConfiguration,
        RestConfiguration])
@AutoConfigureMockMvc
class CommentCreateControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc

    private static final String INSERT_POST = "INSERT INTO PUBLIC.POST(id, created_at, created_by, deleted, modified_at, modified_by, version, content) VALUES(1, '2012-12-12 12:12:12', '2D1EBC5B7D2741979CF0E84451C5BBB1', false, NULL, NULL, 0, 'TEST_POST')"

    private static final String DELETE_POSTS = "DELETE FROM POST"
    private static final String DELETE_COMMENTS = "DELETE FROM COMMENT"

    @Test
    @SqlGroup([
            @Sql(statements = [INSERT_POST]),
            @Sql(statements = [DELETE_COMMENTS, DELETE_POSTS], executionPhase = AFTER_TEST_METHOD)
    ])
    def "should created comment and return with CREATED status"() throws Exception {
        given:
        def requestBody = asJsonString(new CommentDto("TEST"))
        def testUserUuid = UUID.randomUUID().toString()
        def timeInSeconds = TestTimeConfiguration.TEST_TIME.getEpochSecond()
        when:
        def result = mockMvc.perform(MockMvcRequestBuilders
                .post("/posts/1/comments")
                .content(requestBody)
                .header("userUuid", testUserUuid)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

        then:
        result.andExpect(status().isCreated())
        and:
        result.andExpect(MockMvcResultMatchers.jsonPath('$.comment').value("TEST"))
        result.andExpect(MockMvcResultMatchers.jsonPath('$.createdBy').value(testUserUuid))
        result.andExpect(MockMvcResultMatchers.jsonPath('$.createdAt').value(timeInSeconds))
    }

    static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj)
        } catch (Exception e) {
            throw new RuntimeException(e)
        }
    }
}
