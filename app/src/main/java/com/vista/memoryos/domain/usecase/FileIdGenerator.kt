package com.vista.memoryos.domain.usecase

import java.util.UUID
import javax.inject.Inject

class FileIdGenerator @Inject constructor() {

    fun generate(): String {
        return UUID.randomUUID().toString()
    }
}