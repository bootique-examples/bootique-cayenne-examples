package io.bootique.examples.cayenne;

import org.apache.cayenne.DataChannelQueryFilter;
import org.apache.cayenne.DataChannelQueryFilterChain;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.QueryResponse;
import org.apache.cayenne.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OnQueryFilter implements DataChannelQueryFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(OnQueryFilter.class);

    @Override
    public QueryResponse onQuery(ObjectContext originatingContext, Query query, DataChannelQueryFilterChain filterChain) {
        LOGGER.info("on query {}", query.getClass().getSimpleName());
        return filterChain.onQuery(originatingContext, query);
    }
}
