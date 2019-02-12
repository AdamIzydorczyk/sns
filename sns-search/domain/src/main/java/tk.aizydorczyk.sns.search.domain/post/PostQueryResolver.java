package tk.aizydorczyk.sns.search.domain.post;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import tk.aizydorczyk.sns.common.domain.post.Post;
import tk.aizydorczyk.sns.common.domain.post.PostDto;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class PostQueryResolver implements GraphQLQueryResolver {

    private final Supplier<List<Post>> findAllPosts;
    private final Function<Post, PostDto> mapToPostDto;

    PostQueryResolver(Supplier<List<Post>> findAllPosts, Function<Post,
            PostDto> mapToPostDto) {
        this.findAllPosts = findAllPosts;
        this.mapToPostDto = mapToPostDto;
    }

    public List<PostDto> posts() {
        return findAllPosts.get().stream()
                .map(mapToPostDto)
                .collect(toList());
    }
}
