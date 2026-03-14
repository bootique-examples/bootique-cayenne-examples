package io.bootique.examples.cayenne;

import io.bootique.BQCoreModule;
import io.bootique.BQModule;
import io.bootique.Bootique;
import io.bootique.cayenne.v42.CayenneModule;
import io.bootique.di.Binder;
import org.apache.cayenne.configuration.server.ServerModule;

public class App implements BQModule {

    public static void main(String[] args) {
        Bootique.app(args)
                .autoLoadModules()
                .exec()
                .exit();
    }

    @Override
    public void configure(Binder binder) {
        BQCoreModule.extend(binder)
                .addCommand(InsertCommand.class)
                .addCommand(CountCommand.class);

        CayenneModule.extend(binder)

                // non-default Cayenne project name requires an explicit declaration
                .addLocation("classpath:cayenne-myproject.xml")

                // basic Cayenne runtime customizations supported directly by Bootique
                .addListener(PostPersistListener.class)
                .addQueryFilter(OnQueryFilter.class)
                .addSyncFilter(OnSyncFilter.class, false)

                // or you can customize Cayenne runtime via Cayenne modules
                .addModule(cayenneBinder -> ServerModule.setSnapshotCacheSize(cayenneBinder, 100));
    }
}
