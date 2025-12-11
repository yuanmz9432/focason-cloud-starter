/*
 * Copyright 2023 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.domain;


import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.seasar.doma.Domain;

@Domain(valueType = String.class, factoryMethod = "of")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FileMimeType
{
    // --- Image Types ---
    // JPEG
    JPEG("image/jpeg", ".jpg", "Joint Photographic Experts Group image"),
    // PNG
    PNG("image/png", ".png", "Portable Network Graphics image"),
    // GIF
    GIF("image/gif", ".gif", "Graphics Interchange Format image"),
    // SVG
    SVG("image/svg+xml", ".svg", "Scalable Vector Graphics image"),

    // --- Document Types ---
    // PDF
    PDF("application/pdf", ".pdf", "Portable Document Format"),
    // DOCX
    DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".docx", "Microsoft Word Document"),
    // XLSX
    XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx", "Microsoft Excel Spreadsheet"),
    // PPTX
    PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation", ".pptx",
        "Microsoft PowerPoint Presentation"),

    // --- Text/Code/Archive Types ---
    // JSON
    JSON("application/json", ".json", "JSON data format"),
    // XML
    XML("application/xml", ".xml", "XML data format"),
    // ZIP
    ZIP("application/zip", ".zip", "ZIP archive"),
    // TEXT
    TEXT("text/plain", ".txt", "Plain text file"),

    // --- Fallback Type ---
    UNKNOWN("application/octet-stream", "", "Binary or unrecognized file type");


    /** The standard MIME type string (Content-Type header value). */
    @Getter(onMethod = @__(@JsonValue))
    private final String value;
    /** Common file extension associated with this MIME type. */
    @Getter(onMethod = @__(@JsonValue))
    private final String extension;
    /** Human-readable description. */
    @Getter(onMethod = @__(@JsonValue))
    private final String description;

    public static FileMimeType of(String value) {
        return Arrays.stream(values())
            .filter(v -> v.getValue().equals(value))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("FileMimeType = '" + value + "' is not supported."));
    }
}
