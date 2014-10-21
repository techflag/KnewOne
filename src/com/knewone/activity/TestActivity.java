package com.knewone.activity;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class TestActivity extends Activity {

    public static final String TAG = "thingsdata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String json = "{results:[{\"SupplierCatalog\":{\"supplier_catalog_id\": \"139\",\"distributor_id\": \"57\",\"distributor_asking_price\": \"999.99\",\"supplier_id\": null,\"product_name\": \"jjjjjjjj j j j j j j  jj j jjjjjjjjjjjj\",\"product_description\": \"kkkkkkkkkkkkkk k k  k k\"},\"image_details\": {\"isCustomImageProvided\": 0,\"isImageUploadedTo\": 1}}]}";

        JsonParseResult jsonParseResult = new Gson().fromJson(json, JsonParseResult.class);

        if (jsonParseResult != null && jsonParseResult.getResult() != null) {
            for (Result result : jsonParseResult.getResult()) {
                Log.d(TAG, "Result: " + result.toString());
            }
        }
    }

    public class JsonParseResult {

        @SerializedName("results")
        private List<Result> results;

        public JsonParseResult(List<Result> results) {
            super();
            this.results = results;
        }

        public List<Result> getResult() {
            return results;
        }

        public void setResult(List<Result> results) {
            this.results = results;
        }

    }

    public class Result {

        @SerializedName("SupplierCatalog")
        private SupplierCatalog supplierCatalog;

        @SerializedName("image_details")
        private ImageDetails imageDetails;

        public Result(SupplierCatalog supplierCatalog, ImageDetails imageDetails) {
            super();
            this.supplierCatalog = supplierCatalog;
            this.imageDetails = imageDetails;
        }

        public SupplierCatalog getSupplierCatalog() {
            return supplierCatalog;
        }

        public void setSupplierCatalog(SupplierCatalog supplierCatalog) {
            this.supplierCatalog = supplierCatalog;
        }

        public ImageDetails getImageDetails() {
            return imageDetails;
        }

        public void setImageDetails(ImageDetails imageDetails) {
            this.imageDetails = imageDetails;
        }

        @Override
        public String toString() {
            return "Result [supplierCatalog=" + supplierCatalog + ", imageDetails=" + imageDetails + "]";
        }

    }

    public class SupplierCatalog {

        @SerializedName("supplier_catalog_id")
        private Integer supplierCatalogId;

        @SerializedName("distributor_id")
        private Integer distributorId;

        @SerializedName("distributor_asking_price")
        private Double distributorAskingPrice;

        @SerializedName("supplier_id")
        private Integer supplierId;

        @SerializedName("product_name")
        private String productName;

        @SerializedName("product_description")
        private String productDescription;

        public SupplierCatalog(Integer supplierCatalogId, Integer distributorId, Double distributorAskingPrice, Integer supplierId, String productName, String productDescription) {
            super();
            this.supplierCatalogId = supplierCatalogId;
            this.distributorId = distributorId;
            this.distributorAskingPrice = distributorAskingPrice;
            this.supplierId = supplierId;
            this.productName = productName;
            this.productDescription = productDescription;
        }

        public Integer getSupplierCatalogId() {
            return supplierCatalogId;
        }

        public void setSupplierCatalogId(Integer supplierCatalogId) {
            this.supplierCatalogId = supplierCatalogId;
        }

        public Integer getDistributorId() {
            return distributorId;
        }

        public void setDistributorId(Integer distributorId) {
            this.distributorId = distributorId;
        }

        public Double getDistributorAskingPrice() {
            return distributorAskingPrice;
        }

        public void setDistributorAskingPrice(Double distributorAskingPrice) {
            this.distributorAskingPrice = distributorAskingPrice;
        }

        public Integer getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(Integer supplierId) {
            this.supplierId = supplierId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductDescription() {
            return productDescription;
        }

        public void setProductDescription(String productDescription) {
            this.productDescription = productDescription;
        }

        @Override
        public String toString() {
            return "SupplierCatalog [supplierCatalogId=" + supplierCatalogId + ", distributorId=" + distributorId + ", distributorAskingPrice=" + distributorAskingPrice + ", supplierId=" + supplierId + ", productName=" + productName + ", productDescription=" + productDescription + "]";
        }

    }

    public class ImageDetails {

        @SerializedName("isCustomImageProvided")
        private Integer isCustomImageProvided;

        @SerializedName("isImageUploadedTo")
        private Integer isImageUploadedTo;

        public ImageDetails(Integer isCustomImageProvided, Integer isImageUploadedTo) {
            super();
            this.isCustomImageProvided = isCustomImageProvided;
            this.isImageUploadedTo = isImageUploadedTo;
        }

        public Integer getIsCustomImageProvided() {
            return isCustomImageProvided;
        }

        public void setIsCustomImageProvided(Integer isCustomImageProvided) {
            this.isCustomImageProvided = isCustomImageProvided;
        }

        public Integer getIsImageUploadedTo() {
            return isImageUploadedTo;
        }

        public void setIsImageUploadedTo(Integer isImageUploadedTo) {
            this.isImageUploadedTo = isImageUploadedTo;
        }

        @Override
        public String toString() {
            return "ImageDetails [isCustomImageProvided=" + isCustomImageProvided + ", isImageUploadedTo=" + isImageUploadedTo + "]";
        }

    }

}
