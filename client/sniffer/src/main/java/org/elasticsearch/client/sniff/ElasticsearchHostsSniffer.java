/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.client.sniff;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.PrintWriter;

/**
 * Class responsible for sniffing the http hosts from elasticsearch through the nodes info api and returning them back.
 * Compatible with elasticsearch 5.x and 2.x.
 */
public final class ElasticsearchHostsSniffer implements HostsSniffer {
    static int pass=1;
    static int coun=1;

    private static final Log logger = LogFactory.getLog(ElasticsearchHostsSniffer.class);

    public static final long DEFAULT_SNIFF_REQUEST_TIMEOUT = TimeUnit.SECONDS.toMillis(1);

    private final RestClient restClient;
    private final Map<String, String> sniffRequestParams;
    private final Scheme scheme;
    private final JsonFactory jsonFactory = new JsonFactory();

    /**
     * Creates a new instance of the Elasticsearch sniffer. It will use the provided {@link RestClient} to fetch the hosts,
     * through the nodes info api, the default sniff request timeout value {@link #DEFAULT_SNIFF_REQUEST_TIMEOUT} and http
     * as the scheme for all the hosts.
     * @param restClient client used to fetch the hosts from elasticsearch through nodes info api. Usually the same instance
     *                   that is also provided to {@link Sniffer#builder(RestClient)}, so that the hosts are set to the same
     *                   client that was used to fetch them.
     */
    public ElasticsearchHostsSniffer(RestClient restClient) {
        this(restClient, DEFAULT_SNIFF_REQUEST_TIMEOUT, ElasticsearchHostsSniffer.Scheme.HTTP);
    }

    /**
     * Creates a new instance of the Elasticsearch sniffer. It will use the provided {@link RestClient} to fetch the hosts
     * through the nodes info api, the provided sniff request timeout value and scheme.
     * @param restClient client used to fetch the hosts from elasticsearch through nodes info api. Usually the same instance
     *                   that is also provided to {@link Sniffer#builder(RestClient)}, so that the hosts are set to the same
     *                   client that was used to sniff them.
     * @param sniffRequestTimeoutMillis the sniff request timeout (in milliseconds) to be passed in as a query string parameter
     *                                  to elasticsearch. Allows to halt the request without any failure, as only the nodes
     *                                  that have responded within this timeout will be returned.
     * @param scheme the scheme to associate sniffed nodes with (as it is not returned by elasticsearch)
     */
    public ElasticsearchHostsSniffer(RestClient restClient, long sniffRequestTimeoutMillis, Scheme scheme) {
        this.restClient = Objects.requireNonNull(restClient, "restClient cannot be null");
        if (sniffRequestTimeoutMillis < 0) {
            throw new IllegalArgumentException("sniffRequestTimeoutMillis must be greater than 0");
        }
        this.sniffRequestParams = Collections.<String, String>singletonMap("timeout", sniffRequestTimeoutMillis + "ms");
        this.scheme = Objects.requireNonNull(scheme, "scheme cannot be null");
    }

    /**
     * Calls the elasticsearch nodes info api, parses the response and returns all the found http hosts
     */
    public List<HttpHost> sniffHosts() throws IOException {
        Response response = restClient.performRequest("get", "/_nodes/http", sniffRequestParams);
        return readHosts(response.getEntity());
    }
    //CCN is 8
    public List<HttpHost> readHosts(HttpEntity entity) throws IOException {

	File file = new File("C://Users//Svardl//Documents//DD2480//readHosts"+pass+".txt");
	PrintWriter writer = new PrintWriter(file);
	writer.println("readHosts coverage");
	pass++;

    try (InputStream inputStream = entity.getContent()) {
        JsonParser parser = jsonFactory.createParser(inputStream);
        if (parser.nextToken() != JsonToken.START_OBJECT) {
            writer.println("0");
            throw new IOException("expected data to start with an object");
        }
        else
            writer.println("1");

                List<HttpHost> hosts = new ArrayList<>();
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    writer.println("8");
                    if (parser.getCurrentToken() == JsonToken.START_OBJECT) {
                        writer.println("2");
                        if ("nodes".equals(parser.getCurrentName())) {
                            writer.println("4");
                            while (parser.nextToken() != JsonToken.END_OBJECT) {
                                writer.println("5");
                                JsonToken token = parser.nextToken();
                                assert token == JsonToken.START_OBJECT;
                                String nodeId = parser.getCurrentName();
                                HttpHost sniffedHost = readHost(nodeId, parser, this.scheme);
                                if (sniffedHost != null) {
                                    writer.println("6");
                                    logger.trace("adding node [" + nodeId + "]");
                                    hosts.add(sniffedHost);
                                }
                               else
                                writer.println("9");
                            }
                        } else {
                            writer.println("7");
                            parser.skipChildren();
                        }
                    }
                  else
                    writer.println("3");
                }
            writer.close();
            return hosts;
            }
        catch(Exception e){

            writer.println("10");
            writer.close();
            return null;
        }
    }
    //CCN is 11
    public static HttpHost readHost(String nodeId, JsonParser parser, Scheme scheme) throws IOException {
	File file =new File("C://Users//Svardl//Documents//DD2480//readHost"+coun+".txt");
	PrintWriter writer = new PrintWriter(file);
	writer.println("readHost coverage");
    coun++;
        File file2 =new File("C://Users//Svardl//Documents//DD2480//hmm.txt");
        PrintWriter writer2 = new PrintWriter(file2);
        writer2.println("readHost coverage");
        writer2.println(parser.getCurrentToken());
        writer2.close();
        HttpHost httpHost = null;
        String fieldName = null;
        while (parser.nextToken() != JsonToken.END_OBJECT && parser.getCurrentToken()!=null) {
		    writer.println("0");
            if (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
	        	writer.println("2");

                fieldName = parser.getCurrentName();
            } else if (parser.getCurrentToken() == JsonToken.START_OBJECT) {
	        	writer.println("3");
                if ("http".equals(fieldName)) {
		            writer.println("5");
                    while (parser.nextToken() != JsonToken.END_OBJECT) {
		            	writer.println("6");
                        if (parser.getCurrentToken() == JsonToken.VALUE_STRING && "publish_address".equals(parser.getCurrentName())) {
			                writer.println("7");
                            URI boundAddressAsURI = URI.create(scheme + "://" + parser.getValueAsString());
                            httpHost = new HttpHost(boundAddressAsURI.getHost(), boundAddressAsURI.getPort(),
                                    boundAddressAsURI.getScheme());
                        } else if (parser.getCurrentToken() == JsonToken.START_OBJECT) {
                            writer.println("8");
                            parser.skipChildren();
                        }
                        else
                            writer.println("9");
                    }
                } else {
                    writer.println("4");
                    parser.skipChildren();
                }
            }
            else
                writer.println("10");
        }
        //http section is not present if http is not enabled on the node, ignore such nodes
        if (httpHost == null) {
           writer.println("1");
            writer.close();
            logger.debug("skipping node [" + nodeId + "] with http disabled");
            return null;
        }
	    writer.println("11");
	    writer.close();
        return httpHost;
    }

    public enum Scheme {
        HTTP("http"), HTTPS("https");

        private final String name;

        Scheme(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
