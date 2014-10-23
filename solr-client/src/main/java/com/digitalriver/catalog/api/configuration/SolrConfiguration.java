package com.digitalriver.catalog.api.configuration;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;

@Configuration
@PropertySource({"classpath:/solr.properties"})
public class SolrConfiguration {

    @Value("${digitalriver.solr.zoozeeper.host}")
    private String host;

    @Value("${digitalriver.solr.defaultCollection}")
    private String defaultCollection;

    @Bean
    public SolrServer solrServer() {
        final CloudSolrServer server = new CloudSolrServer(host);
        server.setDefaultCollection(defaultCollection);
        return server;
    }

    @Bean
    public SolrOperations solrTemplate() {
        return new SolrTemplate(solrServer());
    }

}
