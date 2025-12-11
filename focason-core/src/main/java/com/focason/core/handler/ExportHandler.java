// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.handler;

/**
 * ExportHandler
 * <p>
 * Defines the contract for concrete strategies that handle the export logic
 * for specific business modules. Implementations of this interface are automatically
 * collected by the {@link ExportHandlerFactory} for dispatching the correct handler.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ExportHandler
{
    /**
     * Retrieves the unique identifier for the business module that this handler supports.
     * This ID is used by the {@link ExportHandlerFactory} for handler lookup.
     *
     * @return The unique integer type of the supported module.
     */
    Integer getModuleType();

    /**
     * Executes the main export process for the data associated with the given task.
     * This method contains the module-specific export business logic.
     *
     * @param taskCode The unique identifier of the export task (e.g., a queue message ID).
     */
    void export(String taskCode);
}
