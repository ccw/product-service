package com.digitalriver.catalog.api.controller

import com.digitalriver.catalog.api.StoreServiceApp
import org.apache.http.HttpStatus
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Created by ccw on 2014/10/31.
 */
class FetchProductControllerSpec extends Specification {

    @Shared
    @AutoCleanup
    ConfigurableApplicationContext context

    def setupSpec() {
        context = (ConfigurableApplicationContext) Executors.newSingleThreadExecutor().submit({
            SpringApplication.run(StoreServiceApp)
        }).get(60, TimeUnit.SECONDS)
    }

    def "test" () {
        expect:
        ResponseEntity entity = new RestTemplate().getForEntity(url, String)
        entity.statusCode == HttpStatus.SC_OK

        where:
        url = 'http://localhost:9090/pd/en_US/123456'
    }

}
