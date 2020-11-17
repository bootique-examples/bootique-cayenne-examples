package io.bootique.cayenne.demo;

import io.bootique.BQCoreModule;
import io.bootique.BaseModule;
import io.bootique.Bootique;
import io.bootique.cayenne.CayenneModule;
import io.bootique.di.Binder;
import io.bootique.meta.application.OptionMetadata;
import org.apache.cayenne.configuration.server.ServerModule;

public class Application extends BaseModule {

    private static final String DATASOURCE_OPTION = "datasource";
    private static final String CREATE_SCHEMA_OPTION = "create-schema";

    public static void main(String[] args) {
        Bootique.app(args)
                .args("--config=classpath:config.yml")
                .autoLoadModules()
                .module(Application.class)
                .exec()
                .exit();
    }

    private static OptionMetadata datasourceOption() {
        return OptionMetadata.builder(DATASOURCE_OPTION)
                .description("Select datasource to use. By default 'mysql' is used. Optional.")
                .valueOptionalWithDefault("derby, mysql", "mysql")
                .build();
    }

    private static OptionMetadata createSchemaOption() {
        return OptionMetadata.builder(CREATE_SCHEMA_OPTION)
                .description("Create schema. False by default. Optional.")
                .shortName('r')
                .valueOptionalWithDefault("true | false", "true")
                .build();
    }

    @Override
    public void configure(Binder binder) {
        BQCoreModule.extend(binder)
                .addOption(datasourceOption())
                .addOption(createSchemaOption())
                .mapConfigPath(DATASOURCE_OPTION, "cayenne.datasource")
                .mapConfigPath(CREATE_SCHEMA_OPTION, "cayenne.createSchema")
                .addCommand(InsertDataCommand.class)
                .addCommand(SelectDataCommand.class);

        CayenneModule.extend(binder)
                // non-default Cayenne project name requires an explicit declaration
                .addProject("cayenne-myproject.xml")
                // basic Cayenne runtime customizations supported directly by Bootique
                .addListener(PostPersistListener.class)
                .addFilter(MyDataChannelFilter.class)
                // or you can customize Cayenne runtime directly
                .addModule(cayenneBinder
                        -> ServerModule.setSnapshotCacheSize(cayenneBinder, 100)
                );
    }
}
