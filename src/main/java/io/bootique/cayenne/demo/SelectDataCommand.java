package io.bootique.cayenne.demo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.bootique.cayenne.Article;
import io.bootique.cayenne.Domain;
import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.meta.application.CommandMetadata;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.ObjectSelect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SelectDataCommand extends CommandWithMetadata {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelectDataCommand.class);

    private Provider<ServerRuntime> runtimeProvider;

    @Inject
    public SelectDataCommand(Provider<ServerRuntime> runtimeProvider) {
        super(createMetadata());
        this.runtimeProvider = runtimeProvider;
    }

    private static CommandMetadata createMetadata() {
        return CommandMetadata.builder(SelectDataCommand.class)
                .name("select")
                .description("Select data from db")
                .build();
    }

    @Override
    public CommandOutcome run(Cli cli) {
        ServerRuntime runtime = runtimeProvider.get();

        ObjectContext context = runtime.newContext();
        List<Article> articleList = ObjectSelect.query(Article.class)
                .where(Article.DOMAIN.dot(Domain.VHOST).eq(Domain.DEFAULT_HOST))
                .select(context);

        LOGGER.info("Articles count on domain {}: {}", Domain.DEFAULT_HOST, articleList.size());

        return CommandOutcome.succeeded();
    }
}
