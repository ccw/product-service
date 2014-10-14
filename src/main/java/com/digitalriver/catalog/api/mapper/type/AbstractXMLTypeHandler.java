package com.digitalriver.catalog.api.mapper.type;

import groovy.util.XmlSlurper;
import groovy.util.slurpersupport.GPathResult;
import groovy.util.slurpersupport.Node;
import org.apache.ibatis.type.BaseTypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractXMLTypeHandler<T> extends BaseTypeHandler<Map<T, ?>> {

    protected final Logger logger = LoggerFactory.getLogger(AbstractXMLTypeHandler.class);

    @SuppressWarnings("unchecked")
    protected Map<String, ?> handle(final String xmlContent) {
        final Map<String, Object> result = new HashMap<>();
        if (xmlContent != null) {
            try {
                GPathResult xmlRoot = new XmlSlurper().parseText(xmlContent);
                xmlRoot.childNodes().forEachRemaining(node -> {
                    Node next = (Node) node;
                    String key = (String) next.attributes().get("key");
                    switch (next.name()) {
                        case "Collection":
                            result.put(key, ((List<Node>) next.children()).stream().map(Node::text).collect(Collectors.toList()));
                            break;
                        default:
                            result.put(key, next.text());
                            break;
                    }
                });

            } catch (final Exception e) {
                logger.warn("Fail to parse given content", e);
            }
        }
        return result;
    }
}
