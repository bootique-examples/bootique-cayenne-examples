package io.bootique.examples.cayenne;

import io.bootique.examples.cayenne.model.Article;
import io.bootique.examples.cayenne.model.Domain;
import io.bootique.examples.cayenne.model.Tag;
import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.meta.application.CommandMetadata;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;

import java.time.LocalDateTime;

public class InsertCommand extends CommandWithMetadata {

    private final Provider<ServerRuntime> runtimeProvider;

    @Inject
    public InsertCommand(Provider<ServerRuntime> runtimeProvider) {
        super(createMetadata());
        this.runtimeProvider = runtimeProvider;
    }

    private static CommandMetadata createMetadata() {
        return CommandMetadata.builder(InsertCommand.class)
                .name("insert")
                .description("Insert test data the DB")
                .build();
    }

    @Override
    public CommandOutcome run(Cli cli) {

        ObjectContext context = runtimeProvider.get().newContext();

        Domain domain = context.newObject(Domain.class);
        domain.setName("My Site about LinkRest");
        domain.setVhost(Domain.DEFAULT_HOST);

        Article bootiqueArticle = context.newObject(Article.class);
        bootiqueArticle.setTitle("Bootique Presentation");
        bootiqueArticle.setBody("Here is how to use Bootique");
        bootiqueArticle.setPublishedOn(LocalDateTime.now());

        Article cayenneArticle = context.newObject(Article.class);
        cayenneArticle.setTitle("Cayenne Goodies");
        cayenneArticle.setBody("This is an article about Apache Cayenne");
        cayenneArticle.setPublishedOn(LocalDateTime.now());

        Tag bootiqueTag = context.newObject(Tag.class);
        bootiqueTag.setName("LinkRest");
        bootiqueTag.setArticle(bootiqueArticle);

        Tag cayenneTag = context.newObject(Tag.class);
        cayenneTag.setName("Cayenne");
        cayenneTag.setArticle(cayenneArticle);

        domain.addToArticles(bootiqueArticle);
        domain.addToArticles(cayenneArticle);

        context.commitChanges();

        return CommandOutcome.succeeded();
    }
}
