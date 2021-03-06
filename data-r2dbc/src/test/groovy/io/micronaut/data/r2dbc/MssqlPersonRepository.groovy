package io.micronaut.data.r2dbc

import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.tck.repositories.PersonReactiveRepository

@R2dbcRepository(dialect = Dialect.SQL_SERVER)
interface MssqlPersonRepository extends PersonReactiveRepository {
}
