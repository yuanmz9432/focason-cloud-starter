// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.handler;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ExportHandlerFactory
 * <p>
 * Implements the Factory Pattern (or Service Locator Pattern) for retrieving
 * specific {@link ExportHandler} implementations based on a module type identifier.
 * <p>
 * This factory automatically collects all beans implementing {@link ExportHandler}
 * during application startup and maps them by their unique module type (usually an Integer ID),
 * allowing for dynamic dispatch of export logic based on the requested business module.
 * </p>
 * <p>
 * It is positioned in the 'core' module as an infrastructure component responsible for
 * dispatching strategy implementations (ExportHandlers). [Image of Strategy Pattern Diagram]
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class ExportHandlerFactory
{
    /**
     * A map storing all {@link ExportHandler} implementations, keyed by their unique
     * module type identifier defined via {@link ExportHandler#getModuleType()}.
     */
    private final Map<Integer, ExportHandler> handlers;

    /**
     * Constructor used for dependency injection by Spring.
     * <p>
     * Spring automatically collects all beans that implement the {@link ExportHandler}
     * interface and passes them as a list. The constructor then converts this list
     * into a Map for efficient lookup.
     * </p>
     *
     * @param handlerList A list of all available {@link ExportHandler} implementations.
     */
    @Autowired
    public ExportHandlerFactory(List<ExportHandler> handlerList) {
        // Collect all handler implementations and map them by their module type.
        handlers = handlerList.stream()
            .collect(Collectors.toMap(ExportHandler::getModuleType, Function.identity()));
    }

    /**
     * Retrieves the appropriate {@link ExportHandler} instance for the given module type.
     *
     * @param moduleType The unique integer identifier of the module (e.g., 1 for User Module, 2 for Product Module).
     * @return The specific ExportHandler implementation for the module.
     * @throws IllegalArgumentException if no handler is registered for the provided module type.
     */
    public ExportHandler getHandler(Integer moduleType) {
        ExportHandler handler = handlers.get(moduleType);
        if (handler == null) {
            // Throw a specific exception if no suitable handler is found.
            throw new IllegalArgumentException("No export handler found for module type: " + moduleType);
        }
        return handler;
    }
}
