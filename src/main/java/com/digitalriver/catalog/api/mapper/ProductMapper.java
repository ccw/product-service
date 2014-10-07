package com.digitalriver.catalog.api.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public interface ProductMapper {

    @Select("SELECT product_id, external_reference_id, states FROM CAT_PRODUCT WHERE product_id = #{productID}")
    @ResultType(HashMap.class)
    @Results({
        @Result(property = "STATES", column = "STATES", javaType = Map.class, jdbcType = JdbcType.VARCHAR, typeHandler = XMLTypeHandler.class)
    })
    Map<String, ?> getMetadata(@Param("productID") String productID);

    @Select("SELECT product_id, " +
            "       product_data_id, " +
            "       display_name, " +
            "       short_description, " +
            "       to_char(long_description) as long_description, " +
            "       thumbnail, " +
            "       mfr_partnumber, " +
            "       sku, " +
            "       detail_image, " +
            "       is_viewable, " +
            "       is_orderable, " +
            "       is_reviewable, " +
            "       keywords, " +
            "       to_char(extended_attributes) as extended_attributes " +
            "  FROM CAT_PRODUCT_DATA " +
            " WHERE product_id = #{productID} " +
            "   and version = #{version} " +
            "   and locale = #{locale}")
    @ResultType(HashMap.class)
    @Results({
            @Result(property = "EXTENDED_ATTRIBUTES", column = "EXTENDED_ATTRIBUTES", javaType = Map.class, jdbcType = JdbcType.VARCHAR, typeHandler = XMLTypeHandler.class)
    })
    Map<String, ?> getDisplayData(@Param("productID") String productID, @Param("version") Integer version, @Param("locale") String locale);

}
