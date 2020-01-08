package com.target.myRetail.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.myRetail.Exception.MisMatchException;
import com.target.myRetail.domain.Price;
import com.target.myRetail.domain.Product;
import com.target.myRetail.repository.ProductRepository;
import com.target.myRetail.repository.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceRepository priceRepository;

    /**
     * @param productId
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Product getProductById(String productId) throws Exception {
        logger.info("start - service - getProductById");
        Product product = new Product();
        // Db call.
        Optional<Price> optionalPrice = priceRepository.findById(Integer.parseInt(productId));
        if(optionalPrice.isPresent()) {
            Price price = optionalPrice.get();
            product.setId(price.getId());
            Map<String, Object> currentPrice = new HashMap<String, Object>();
            currentPrice.put("value", price.getValue());
            currentPrice.put("currency_code", price.getCurrencyCode());
            product.setCurrent_price(currentPrice);
            // API call.
            product.setName(getProductName(productId));
            logger.info("Product Name from API   " + product.getName());
        } else {
           throw new MisMatchException();
        }
        logger.info("end - service - getProductById");
        return product;
    }

    private String getProductName(String productId) throws Exception {
        logger.info("start - service - getProductName");
        ObjectMapper infoMapper = new ObjectMapper();
        ResponseEntity<String> response = productRepository.getProductInfoById(productId);

        Map<String, Map> infoMap = infoMapper.readValue(response.getBody(), Map.class);
        Map<String,Map> productMap = infoMap.get("product");
        Map<String,Map> itemMap = productMap.get("item");
        Map<String,String> productDescriptionMap = itemMap.get("product_description");
        logger.info("end - service - getProductName");
        return productDescriptionMap.get("title");
    }

    public Price savePriceDetail(Price price) {
        logger.info("start/end - service - savePriceDetail");
        return priceRepository.save(price);
    }

    public Price updatePriceDetail(Price price) {
        Price priceObj;
        logger.info("start - service - updatePriceDetail");
        Optional<Price> optionalPrice = priceRepository.findById(price.getId());
        if(optionalPrice.isPresent()) {
            Price updatePriceObj = optionalPrice.get();
            updatePriceObj.setCurrencyCode(price.getCurrencyCode());
            updatePriceObj.setValue(price.getValue());
            priceObj = priceRepository.save(updatePriceObj);
        } else {
            throw new MisMatchException();
        }
        logger.info("end - service - updatePriceDetail");
        return priceObj;
    }
}
