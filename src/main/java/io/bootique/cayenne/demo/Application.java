package io.bootique.cayenne.demo;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.BQCoreModule;
import io.bootique.Bootique;
import io.bootique.cayenne.CayenneModule;
import org.apache.cayenne.configuration.Constants;
import org.apache.cayenne.configuration.server.ServerModule;

public class Application implements Module {
    public static final void main(String[] args) {
        Bootique.app(args).module(Application.class).autoLoadModules().run();
    }

    @Override
    public void configure(Binder binder) {
        BQCoreModule.extend(binder)
                .addCommand(InsertDataCommand.class)
                .addCommand(SelectDataCommand.class);

        CayenneModule.extend(binder)
                //non-default Cayenne project name requires an explicit declaration
                .addProject("cayenne-myproject.xml")
                .addModule(cayenneBinder -> {
                    //override Cayenne module settings, e.g. this one
                    ServerModule.contributeProperties(cayenneBinder).put(Constants.JDBC_MAX_QUEUE_WAIT_TIME, "0");
                }).addListener(PostPersistListener.class);

    }
}
