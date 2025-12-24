// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.file.handler;

import com.focason.core.domain.ModuleType;
import com.focason.core.exception.FsEntityNotFoundException;
import com.focason.core.handler.ExportHandler;
import com.focason.core.resource.FileResource;
import com.focason.platform.file.repository.FileTaskRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TemplateExportHandler
 * <p>
 * Handles export logic for TEMPLATE module (moduleType = 1).
 * This handler is responsible for exporting template-related data based on the provided task code.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TemplateExportHandler implements ExportHandler
{
    private static final Logger logger = LoggerFactory.getLogger(TemplateExportHandler.class);

    private final FileTaskRepository fileTaskRepository;

    /**
     * Returns the module type identifier for TEMPLATE module.
     *
     * @return The integer value representing TEMPLATE module (1).
     */
    @Override
    public Integer getModuleType() {
        return ModuleType.TEMPLATE.getValue();
    }

    /**
     * Executes the export process for template data associated with the given task code.
     * <p>
     * This method:
     * 1. Retrieves the export task from the database
     * 2. Validates the task exists and is valid
     * 3. Performs the template-specific export logic
     * 4. Updates the task status upon completion
     *
     * @param taskCode The unique identifier of the export task.
     * @throws FsEntityNotFoundException if the task with the given taskCode is not found.
     */
    @Override
    public void export(String taskCode) {
        logger.info("Starting template export for taskCode: {}", taskCode);

        // Retrieve the export task
        var task = fileTaskRepository.findExportTask(taskCode)
            .orElseThrow(() -> new FsEntityNotFoundException(FileResource.class, taskCode));

        // Validate that the task is for TEMPLATE module
        if (!ModuleType.TEMPLATE.getValue().equals(task.getFileModule())) {
            throw new IllegalArgumentException(
                String.format("Task %s is not a TEMPLATE export task. Module type: %s", taskCode,
                    task.getFileModule()));
        }

        try {
            // TODO: Implement template-specific export logic here
            // Example steps:
            // 1. Parse filterConditions and exportColumns from task
            // 2. Query template data based on filterConditions
            // 3. Generate export file (e.g., Excel, CSV) with exportColumns
            // 4. Upload the generated file to S3 using the task's filePath
            // 5. Update task status to PROCESSED

            logger.info("Template export completed for taskCode: {}", taskCode);
        } catch (Exception e) {
            logger.error("Template export failed for taskCode: {}", taskCode, e);
            throw new RuntimeException("Template export failed: " + e.getMessage(), e);
        }
    }
}
