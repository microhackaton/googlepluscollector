package com.ofg.microservice.gplus

import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

import static org.springframework.util.Assert.notNull
import static org.springframework.util.StringUtils.hasText

@TypeChecked
@RestController
class GPlusCollectorController {
    private GPlusCollector collectorWorker

    @Autowired
    GPlusCollectorController(GPlusCollector collectorWorker) {
        this.collectorWorker = collectorWorker
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/{gPlusLogin}/{pairId}", produces="application/json", method = RequestMethod.GET)
    void getTweets(@PathVariable String gPlusLogin, @PathVariable Long pairId) {
        hasText(gPlusLogin); notNull(pairId)
        collectorWorker.collectAndPassToAnalyzers(gPlusLogin, pairId)
    }
}