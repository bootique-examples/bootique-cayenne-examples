package io.bootique.cayenne.demo;

import org.apache.cayenne.DataChannel;
import org.apache.cayenne.DataChannelFilter;
import org.apache.cayenne.DataChannelFilterChain;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.QueryResponse;
import org.apache.cayenne.graph.GraphDiff;
import org.apache.cayenne.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyDataChannelFilter implements DataChannelFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyDataChannelFilter.class);

    @Override
    public void init(DataChannel channel) {
    }

    @Override
    public QueryResponse onQuery(ObjectContext originatingContext, Query query, DataChannelFilterChain filterChain) {
        LOGGER.info("Filter.onQuery " + query);
        return filterChain.onQuery(originatingContext, query);
    }

    @Override
    public GraphDiff onSync(ObjectContext originatingContext, GraphDiff changes, int syncType, DataChannelFilterChain filterChain) {
        LOGGER.info("Filter.onSync " + changes);
        return filterChain.onSync(originatingContext, changes, syncType);
    }
}
