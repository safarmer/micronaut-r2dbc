/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.data.r2dbc.operations;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.transaction.TransactionDefinition;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import org.reactivestreams.Publisher;

import java.util.function.Function;

/**
 * Operations for R2DBC.
 *
 * @author graemerocher
 * @since 1.0.0
 */
public interface R2dbcOperations {
    /**
     * Obtains the connection factory.
     * @return The connection factory
     */
    @NonNull ConnectionFactory connectionFactory();

    /**
     * Execute the given handler with a new connection.
     * @param handler The handler
     * @param <T> The emitted type
     * @return A publisher that emits the result type
     */
    @NonNull <T> Publisher<T> withConnection(@NonNull Function<Connection, Publisher<T>> handler);

    /**
     * Execute the given handler with a new transaction.
     * @param definition The definition
     * @param handler The handler
     * @param <T> The emitted type
     * @return A publisher that emits the result type
     */
    @NonNull <T> Publisher<T> withTransaction(@NonNull TransactionDefinition definition, @NonNull Function<Connection, Publisher<T>> handler);

    /**
     * Execute the given handler with a new transaction.
     * @param handler The handler
     * @param <T> The emitted type
     * @return A publisher that emits the result type
     */
    default @NonNull <T> Publisher<T> withTransaction(@NonNull Function<Connection, Publisher<T>> handler) {
        return withTransaction(TransactionDefinition.DEFAULT, handler);
    }
}