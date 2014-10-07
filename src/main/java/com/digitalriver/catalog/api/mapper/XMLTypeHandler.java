package com.digitalriver.catalog.api.mapper;

import groovy.util.XmlSlurper;
import groovy.util.slurpersupport.Node;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class XMLTypeHandler extends BaseTypeHandler<Map<String, ?>> {

    private static final Logger logger = LoggerFactory.getLogger(XMLTypeHandler.class);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, ?> parameter, JdbcType jdbcType) throws SQLException {
        //
    }

    @Override
    public Map<String, ?> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.handle(rs.getNString(columnName));
    }

    @Override
    public Map<String, ?> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.handle(rs.getNString(columnIndex));
    }

    @Override
    public Map<String, ?> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.handle(cs.getNString(columnIndex));
    }

    @SuppressWarnings("unchecked")
    protected Map<String, ?> handle(final String xmlContent) {
        final Map<String, Object> result = new HashMap<>();
        try {
            new XmlSlurper().parseText(xmlContent).childNodes().forEachRemaining(node -> {
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
        return result;
    }

}
