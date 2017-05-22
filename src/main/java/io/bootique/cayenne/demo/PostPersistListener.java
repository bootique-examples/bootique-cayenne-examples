package io.bootique.cayenne.demo;

import io.bootique.cayenne.Article;
import org.apache.cayenne.Persistent;
import org.apache.cayenne.annotation.PostPersist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostPersistListener {

    private static Logger LOGGER = LoggerFactory.getLogger(PostPersistListener.class);

    @PostPersist(Article.class)
    void postAdd(Persistent object) {
        // do something
        LOGGER.info("NEW ARTICLE {} ", object);
    }
}
