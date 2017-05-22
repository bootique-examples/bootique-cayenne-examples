package io.bootique.cayenne.demo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.bootique.cayenne.Article;
import io.bootique.cayenne.Domain;
import io.bootique.cayenne.Tag;
import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.meta.application.CommandMetadata;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

public class InsertDataCommand extends CommandWithMetadata {

    private Provider<ServerRuntime> runtimeProvider;

    @Inject
    public InsertDataCommand(Provider<ServerRuntime> runtimeProvider) {
        super(createMetadata());
        this.runtimeProvider = runtimeProvider;
    }

    private static CommandMetadata createMetadata() {
        return CommandMetadata.builder(InsertDataCommand.class).name("insert").shortName('i').
                description("Insert initial data into db").build();
    }

    @Override
    public CommandOutcome run(Cli cli) {
        ServerRuntime runtime = runtimeProvider.get();

        ObjectContext context = runtime.newContext();

        Domain domain = context.newObject(Domain.class);
        domain.setName("My Site about LinkRest");
        domain.setVhost(Domain.DEFAULT_HOST);

        Article linkRestArticle = context.newObject(Article.class);
        linkRestArticle.setTitle("LinkRest Presentation");
        linkRestArticle.setBody("Here is how to use LinkRest");
        linkRestArticle.setPublishedOn(new Date());

        Article cayenneArticle = context.newObject(Article.class);
        cayenneArticle.setTitle("Cayenne Goodies");
        cayenneArticle.setBody("This is an article about Apache Cayenne");
        cayenneArticle.setPublishedOn(DateUtils.addDays(new Date(), -1));

        Tag linkRestTag = context.newObject(Tag.class);
        linkRestTag.setName("LinkRest");
        linkRestTag.setArticle(linkRestArticle);

        Tag cayenneTag = context.newObject(Tag.class);
        cayenneTag.setName("Cayenne");
        cayenneTag.setArticle(cayenneArticle);

        domain.addToArticles(linkRestArticle);
        domain.addToArticles(cayenneArticle);

        context.commitChanges();

        return CommandOutcome.succeeded();
    }
}
