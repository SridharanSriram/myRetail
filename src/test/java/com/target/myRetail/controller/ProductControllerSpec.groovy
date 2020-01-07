package com.target.myRetail.controller

import com.target.myRetail.Exception.ProductNotAvailable
import com.target.myRetail.domain.Product
import com.target.myRetail.service.ProductService
import org.springframework.http.ResponseEntity
import spock.lang.Specification
import spock.lang.Unroll

class ProductControllerSpec extends Specification {

    ProductController productController
    ProductService productService

    def setup() {
        productService = Mock(ProductService)
        productController = new ProductController(
                productService: productService
        )
    }

    @Unroll
    def "[TestCase] - test getProductDetail - #scenario"() {
        when:
        ResponseEntity<Product> productResponseEntity =  productController.getProductDetail(productId)

        then:
        1 * productService.getProductById(productId) >> productObject
        Product product = productResponseEntity.body
        assert product?.name == productName
        assert product?.current_price?.value == value

        where:
        scenario              | productId | productObject                                                               | productName   | value
        'with positive value' | '12345'   | new Product(id: 12345, name: 'productName', current_price: ['value':12.0] ) | 'productName' | 12.0
        'with null value'     | '12345'   | null                                                                        | null          | null
    }

    def "[TestCase] - test getProductDetail - exception"() {
        when:
        productController.getProductDetail('productId')

        then:
        1 * productService.getProductById('productId') >> { throw new Exception() }
        thrown(ProductNotAvailable)
    }
}
