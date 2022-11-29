package com.shared.prices.tracker.domain.exception

import kotlin.reflect.KClass

class ModelNotFoundException(modelClass: KClass<*>, id: Any) :
    RuntimeException("'${modelClass.simpleName}' is not found by id: $id")