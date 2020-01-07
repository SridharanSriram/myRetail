package com.target.myRetail.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.target.myRetail.domain.Price
import com.target.myRetail.domain.Product
import com.target.myRetail.repository.PriceRepository
import com.target.myRetail.repository.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification
import spock.lang.Unroll

class ProductServiceSpec extends Specification {

    ProductService productService
    ProductRepository productRepository
    PriceRepository priceRepository

    def setup() {
        productRepository = Mock(ProductRepository)
        priceRepository = Mock(PriceRepository)
        productService = new ProductService(
           productRepository: productRepository,
           priceRepository: priceRepository
        )
    }

//    @Unroll
//    def '[TestCase] - test getProductById - #scenario '() {
//        given:
//        ObjectMapper objectMapper = Mock(ObjectMapper)
//
//        when:
//        Product product = productService.getProductById(productId)
//        then:
//        1 * priceRepository.findById(12345) >> new Optional<Price>(priceObject)
//        1 * productRepository.getProductInfoById(productId) >> new ResponseEntity<>("[:]", HttpStatus.OK)
//        1 * objectMapper.readValue(_, _) >> productName
//        product
//
//        where:
//        scenario       | productId | priceObject                                            |productName                                                      | value | currencyCode | productNameResult
//        'with values'  |'12345'    | new Price(id: 12345, value: 12.0, currencyCode: 'USD') | ['product' : ['item':['product_description':['title': 'productName']]]] | 12.0 | 'USD' | 'productName'
//    }
}
