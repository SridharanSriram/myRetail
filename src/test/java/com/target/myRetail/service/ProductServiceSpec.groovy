package com.target.myRetail.service

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

    @Unroll
    def '[TestCase] - test getProductById - #scenario '() {
        when:
        Product product = productService.getProductById(productId)

        then:
        1 * priceRepository.findById(12345) >> new Optional<Price>(priceObject)
        1 * productRepository.getProductInfoById(productId) >> new ResponseEntity<>("{\"product\":{\"item\": {\"product_description\":{\"title\":\"productName\"}}}}", HttpStatus.OK)
        assert product.current_price.value == value
        assert product.current_price.currency_code == currencyCode
        assert product.name == productNameResult

        where:
        scenario          | productId | priceObject                                            |productName                                                              | value | currencyCode | productNameResult
        'with values'     |'12345'    | new Price(id: 12345, value: 12.0, currencyCode: 'USD') | ['product' : ['item':['product_description':['title': 'productName']]]] | 12.0  | 'USD'        | 'productName'
        'without values'  |'12345'    | new Price()                                            | ['product' : ['item':['product_description':['title': 'productName']]]] | null  | null         | 'productName'
    }

    def '[TestCase] - test getProductById - exception '() {
        when:
        Product product = productService.getProductById('12345')

        then:
        1 * priceRepository.findById(_) >>  {throw new Exception()}
        thrown(Exception)
    }

    def '[TestCase] - test savePriceDetail '() {
        when:
        Price price = productService.savePriceDetail(new Price())

        then:
        1 * priceRepository.save(_) >>  new Price(value: 12.0, currencyCode: 'INR')
        assert price.value == 12.0
        assert price.currencyCode == 'INR'
    }

    @Unroll
    def '[TestCase] - test updatePriceDetail - #scenario '() {
        when:
        Price priceOb = productService.updatePriceDetail(price)

        then:
        1 * priceRepository.findById(12345) >> new Optional<Price>(priceObject)
        1 * priceRepository.save(priceObject) >> priceObject
        assert priceOb.value == value
        assert priceOb.currencyCode == currencyCode

        where:
        scenario          | price                  | priceObject                                            | value | currencyCode
        'with values'     | new Price(id: 12345, value: 12.0, currencyCode: 'INR')   | new Price(id: 12345, value: 12.0, currencyCode: 'USD')  | 12.0  | 'INR'
    }

    def '[TestCase] - test updatePriceDetail - exception '() {
        when:
        Price priceOb = productService.updatePriceDetail(new Price())

        then:
        1 * priceRepository.findById(_) >> { throw new Exception()}
        thrown(Exception)
    }
}
