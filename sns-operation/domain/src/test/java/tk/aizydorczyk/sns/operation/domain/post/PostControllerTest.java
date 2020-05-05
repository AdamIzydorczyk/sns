package tk.aizydorczyk.sns.operation.domain.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tk.aizydorczyk.sns.common.infrastructure.converter.ConvertersConfiguration;
import tk.aizydorczyk.sns.common.infrastructure.mapper.MapperConfiguration;
import tk.aizydorczyk.sns.common.infrastructure.time.TimeConfiguration;
import tk.aizydorczyk.sns.common.infrastructure.utils.UtilsConfiguration;
import tk.aizydorczyk.sns.operation.domain.config.H2JpaConfiguation;
import tk.aizydorczyk.sns.operation.infrastructure.rest.RestConfiguration;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {H2JpaConfiguation.class,
        PostConfiguration.class,
        MapperConfiguration.class,
        PostController.class,
        UtilsConfiguration.class,
        TimeConfiguration.class,
        ConvertersConfiguration.class,
        RestConfiguration.class})
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreatePostWithExistedIdAndContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/posts")
                .content(asJsonString(new PostDto("TEST")))
                .header("userUuid", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("TEST"));
    }

    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
