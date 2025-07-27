package io.bootique.examples.cayenne;

import io.bootique.examples.cayenne.model.Article;
import org.apache.cayenne.Persistent;
import org.apache.cayenne.annotation.PostPersist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostPersistListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostPersistListener.class);

    @PostPersist(Article.class)
    void postAdd(Persistent object) {
        LOGGER.info("new article {} ", object);
    }
}
