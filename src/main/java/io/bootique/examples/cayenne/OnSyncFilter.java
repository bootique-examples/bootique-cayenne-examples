package io.bootique.examples.cayenne;

import org.apache.cayenne.DataChannelSyncFilter;
import org.apache.cayenne.DataChannelSyncFilterChain;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.graph.GraphDiff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnSyncFilter implements DataChannelSyncFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(OnSyncFilter.class);

    @Override
    public GraphDiff onSync(ObjectContext originatingContext, GraphDiff changes, int syncType, DataChannelSyncFilterChain filterChain) {
        LOGGER.info("changes: {}", !changes.isNoop());
        return filterChain.onSync(originatingContext, changes, syncType);
    }
}
