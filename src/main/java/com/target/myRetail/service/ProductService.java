package com.target.myRetail.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.myRetail.domain.Product;
import com.target.myRetail.feignClient.ProductClient;
import com.target.myRetail.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import java.util.Map;

@Service
public class ProductService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductClient productClient;

    @Autowired
    private ProductRepository productRepository;

    /**
     * @param productId
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Product getProductById(String productId) throws JsonParseException, JsonMappingException, IOException {
        // Db call.
        Product product = productRepository.getProductByproductId(productId);
        // API call.
        product.setProductName(getProductName(productId));
        logger.info("Product Name from API   "+ product.getProductName());
        return product;
    }

    private String getProductName(String productId) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper infoMapper = new ObjectMapper();
        ResponseEntity<String> response = productClient.getProductInfoById(productId);

        Map<String, Map> infoMap = infoMapper.readValue(response.getBody(), Map.class);
        Map<String,Map> productMap = infoMap.get("product");
        Map<String,Map> itemMap = productMap.get("item");
        Map<String,String> productDescriptionMap = itemMap.get("product_description");

        return productDescriptionMap.get("title");
    }
}
