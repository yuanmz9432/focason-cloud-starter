package com.focason.core.response;

/**
 * FileExportResponse
 *
 * @param id ID
 * @param clientCode クライアントコード
 * @param warehouseCodes 倉庫コード配列
 * @param warehouseCode 倉庫コード
 * @param clientCodes クライアントコード配列
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record FileExportResponse(Integer id,String clientCode,String[]warehouseCodes,String warehouseCode,String[]clientCodes){}
