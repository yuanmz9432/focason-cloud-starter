package com.focason.core.response;

/**
 * UserFetchResponse
 *
 * @param id ID
 * @param uuid UUID
 * @param username ユーザー名
 * @param email メールアドレス
 * @param phone 電話番号
 * @param companyCode 会社コード
 * @param type タイプ
 * @param isVerified 検証済みフラグ
 * @param warehouseCodes 倉庫コード配列
 * @param clientCodes クライアントコード配列
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record UserFetchResponse(Integer id,String uuid,String username,String email,String phone,String companyCode,Integer type,Integer isVerified,String[]warehouseCodes,String[]clientCodes){}
