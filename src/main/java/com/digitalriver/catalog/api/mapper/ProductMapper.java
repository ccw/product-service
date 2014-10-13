package com.digitalriver.catalog.api.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public interface ProductMapper {

    @Select("WITH pid AS (" +
            "  SELECT product_id FROM cat_product WHERE product_id = #{productID} and parent_product_id is null" +
            "  UNION" +
            "  SELECT parent_product_id as product_id FROM cat_product WHERE product_id = #{productID}" +
            ")" +
            "SELECT product_id," +
            "       external_reference_id," +
            "       parent_product_id," +
            "       states " +
            "  FROM CAT_PRODUCT " +
            " WHERE product_id in (select product_id from pid)")
    @ResultType(HashMap.class)
    @Results({
        @Result(property = "STATES", column = "STATES", javaType = Map.class, jdbcType = JdbcType.VARCHAR, typeHandler = VarCharXMLTypeHandler.class)
    })
    Map<String, ?> getMetadata(@Param("productID") String productID);

    @Select("WITH pid AS (" +
            "  SELECT #{productID} as product_id FROM dual " +
            "  UNION" +
            "  SELECT product_id as product_id FROM cat_product WHERE parent_product_id = #{productID}" +
            ")" +
            "SELECT PD.product_id, " +
            "       PD.product_data_id, " +
            "       PD.display_name, " +
            "       NVL(PI.PARENT_PRODUCT_ID, '') BASE_PRODUCT_ID, " +
            "       (CASE WHEN PI.parent_product_id is null THEN '0' ELSE '1' END) IS_VARIATION, " +
            "       PD.short_description, " +
            "       TO_CHAR(PD.long_description) as long_description, " +
            "       PD.thumbnail, " +
            "       PD.mfr_partnumber, " +
            "       PD.sku, " +
            "       PD.detail_image, " +
            "       PD.is_viewable, " +
            "       PD.is_orderable, " +
            "       PD.is_reviewable, " +
            "       PD.keywords, " +
            "       XMLTYPE(PD.extended_attributes) as extended_attributes " +
            "  FROM CAT_PRODUCT_DATA PD JOIN CAT_PRODUCT PI ON PD.PRODUCT_ID = PI.PRODUCT_ID" +
            " WHERE PD.product_id in (SELECT product_id FROM pid)" +
            "   and PD.version = #{version} " +
            "   and PD.locale = #{locale}")
    @ResultType(HashMap.class)
    @Results({
        @Result(property = "IS_VARIATION", column = "IS_VARIATION", javaType = Boolean.class, jdbcType = JdbcType.VARCHAR),
        @Result(property = "IS_VIEWABLE", column = "IS_VIEWABLE", javaType = Boolean.class, jdbcType = JdbcType.VARCHAR),
        @Result(property = "IS_ORDERABLE", column = "IS_ORDERABLE", javaType = Boolean.class, jdbcType = JdbcType.VARCHAR),
        @Result(property = "IS_REVIEWABLE", column = "IS_REVIEWABLE", javaType = Boolean.class, jdbcType = JdbcType.VARCHAR),
        @Result(property = "EXTENDED_ATTRIBUTES", column = "EXTENDED_ATTRIBUTES", javaType = Map.class, jdbcType = JdbcType.CLOB, typeHandler = ClobXMLTypeHandler.class)
    })
    List<Map<String, ?>> getDisplayData(@Param("productID") String productID, @Param("version") Integer version, @Param("locale") String locale);

}
