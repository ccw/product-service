package com.digitalriver.catalog.api.domain;

import org.springframework.data.annotation.Id;
import org.apache.solr.client.solrj.beans.Field;

import java.util.Map;

public class Product {

    @Id
    @Field
    private String productDataId;

    @Field
    private String productId;

    @Field
    private String baseProductId;

    @Field
    private String locale;

    @Field
    private String sku;

    @Field
    private String displayName;

//    @Field
//    private String shortDescription;

//    @Field
//    private String longDescription;

//    @Field
//    private String keywords;

//    @Field
//    private String thumbnail;

//    @Field
//    private String mfrPartNumber;

//    @Field
//    private String detailImage;

    @Field
    private Boolean isVariation;

    @Field
    private Boolean isOrderable;

    @Field("ext_*")
    private Map<String, String> extendAttributes;

    public String getProductDataId() {
        return productDataId;
    }

    public void setProductDataId(String productDataId) {
        this.productDataId = productDataId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBaseProductId() {
        return baseProductId;
    }

    public void setBaseProductId(String baseProductId) {
        this.baseProductId = baseProductId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

//    public String getShortDescription() {
//        return shortDescription;
//    }
//
//    public void setShortDescription(String shortDescription) {
//        this.shortDescription = shortDescription;
//    }
//
//    public String getLongDescription() {
//        return longDescription;
//    }

//    public void setLongDescription(String longDescription) {
//        this.longDescription = longDescription;
//    }

//    public String getKeywords() {
//        return keywords;
//    }
//
//    public void setKeywords(String keywords) {
//        this.keywords = keywords;
//    }

//    public String getThumbnail() {
//        return thumbnail;
//    }
//
//    public void setThumbnail(String thumbnail) {
//        this.thumbnail = thumbnail;
//    }
//
//    public String getMfrPartNumber() {
//        return mfrPartNumber;
//    }
//
//    public void setMfrPartNumber(String mfrPartNumber) {
//        this.mfrPartNumber = mfrPartNumber;
//    }
//
//    public String getDetailImage() {
//        return detailImage;
//    }
//
//    public void setDetailImage(String detailImage) {
//        this.detailImage = detailImage;
//    }

    public Boolean getIsVariation() {
        return isVariation;
    }

    public void setIsVariation(Boolean isVariation) {
        this.isVariation = isVariation;
    }

    public Boolean getIsOrderable() {
        return isOrderable;
    }

    public void setIsOrderable(Boolean isOrderable) {
        this.isOrderable = isOrderable;
    }

    public Map<String, String> getExtendAttributes() {
        return extendAttributes;
    }

    public void setExtendAttributes(Map<String, String> extendAttributes) {
        this.extendAttributes = extendAttributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return productDataId.equals(product.productDataId);

    }

    @Override
    public int hashCode() {
        return productDataId.hashCode();
    }

}
