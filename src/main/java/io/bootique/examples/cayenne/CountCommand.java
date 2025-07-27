package io.bootique.examples.cayenne;

import io.bootique.examples.cayenne.model.Article;
import io.bootique.examples.cayenne.model.Domain;
import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.meta.application.CommandMetadata;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.ObjectSelect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountCommand extends CommandWithMetadata {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountCommand.class);
    private final Provider<ServerRuntime> runtimeProvider;

    @Inject
    public CountCommand(Provider<ServerRuntime> runtimeProvider) {
        super(createMetadata());
        this.runtimeProvider = runtimeProvider;
    }

    private static CommandMetadata createMetadata() {
        return CommandMetadata.builder(CountCommand.class)
                .name("count")
                .description("Checks row count in articles table for default domain")
                .build();
    }

    @Override
    public CommandOutcome run(Cli cli) {

        ObjectContext context = runtimeProvider.get().newContext();
        Long count = ObjectSelect.query(Article.class)
                .where(Article.DOMAIN.dot(Domain.VHOST).eq(Domain.DEFAULT_HOST))
                .selectCount(context);

        LOGGER.info("Articles count on domain {}: {}", Domain.DEFAULT_HOST, count);

        return CommandOutcome.succeeded();
    }
}
