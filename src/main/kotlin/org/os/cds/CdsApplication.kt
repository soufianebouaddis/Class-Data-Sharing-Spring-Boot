package org.os.cds

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CdsApplication

fun main(args: Array<String>) {
	runApplication<CdsApplication>(*args)
}
