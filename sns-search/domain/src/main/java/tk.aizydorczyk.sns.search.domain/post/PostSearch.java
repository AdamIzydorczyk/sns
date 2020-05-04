package tk.aizydorczyk.sns.search.domain.post;

import tk.aizydorczyk.sns.search.infrastructure.jpa.BaseSearchEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class PostSearch extends BaseSearchEntity {
    private String content;
}
