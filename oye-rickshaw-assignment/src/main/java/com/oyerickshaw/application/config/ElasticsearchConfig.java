package com.oyerickshaw.application.config;

import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

	private Logger log = LogManager.getLogger(ElasticsearchConfig.class);
	
	@Value("${elasticsearch.url}")
	private String elasticSearchURL;
	
	@Value("${elasticsearch.port}")
	private int port;
	
	@Bean("elasticClient")
	public RestHighLevelClient getElasticSearchHighLevelRestClient() {

		RestHighLevelClient elasticSearchClient = null;
		try {
			elasticSearchClient = new RestHighLevelClient(
					RestClient.builder(new HttpHost(elasticSearchURL, port, "http")));
		} catch (Exception e) {
			log.error("Error initialising elasticsearch Service", e);
		}

		log.info("Elasticsearch Service Initialised successfully");
		return elasticSearchClient;
	}
}
