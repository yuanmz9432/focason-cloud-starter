/* Copyright 2025 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import java.time.LocalDateTime;
import org.seasar.doma.*;

/**
 * 
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(name = "base007_file_metadata")
public class Base007FileMetadataEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** ユーザー識別子 */
    @Column(name = "uid")
    String uid;
    /** ファイルID */
    @Column(name = "file_id")
    String fileId;
    /** ファイル名 */
    @Column(name = "original_file_name")
    String originalFileName;
    /** オブジェクトキー */
    @Column(name = "object_key")
    String objectKey;
    /** MIMEタイプ */
    @Column(name = "mime_type")
    String mimeType;
    /** 拡張子 */
    @Column(name = "extension")
    String extension;
    /** サイズ */
    @Column(name = "size")
    Long size;
    /** ステータス */
    @Column(name = "status")
    Integer status;
    /** ダウンロード数 */
    @Column(name = "download_count")
    Integer downloadCount;
    /** 最終ダウンロード日時 */
    @Column(name = "last_download_datetime")
    LocalDateTime lastDownloadDatetime;

    /**
     * Returns the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the uid.
     *
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets the uid.
     *
     * @param uid the uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Returns the fileId.
     *
     * @return the fileId
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * Sets the fileId.
     *
     * @param fileId the fileId
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * Returns the originalFileName.
     *
     * @return the originalFileName
     */
    public String getOriginalFileName() {
        return originalFileName;
    }

    /**
     * Sets the originalFileName.
     *
     * @param originalFileName the originalFileName
     */
    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    /**
     * Returns the objectKey.
     *
     * @return the objectKey
     */
    public String getObjectKey() {
        return objectKey;
    }

    /**
     * Sets the objectKey.
     *
     * @param objectKey the objectKey
     */
    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    /**
     * Returns the mimeType.
     *
     * @return the mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Sets the mimeType.
     *
     * @param mimeType the mimeType
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * Returns the extension.
     *
     * @return the extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Sets the extension.
     *
     * @param extension the extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Returns the size.
     *
     * @return the size
     */
    public Long getSize() {
        return size;
    }

    /**
     * Sets the size.
     *
     * @param size the size
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * Returns the status.
     *
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Returns the downloadCount.
     *
     * @return the downloadCount
     */
    public Integer getDownloadCount() {
        return downloadCount;
    }

    /**
     * Sets the downloadCount.
     *
     * @param downloadCount the downloadCount
     */
    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    /**
     * Returns the lastDownloadDatetime.
     *
     * @return the lastDownloadDatetime
     */
    public LocalDateTime getLastDownloadDatetime() {
        return lastDownloadDatetime;
    }

    /**
     * Sets the lastDownloadDatetime.
     *
     * @param lastDownloadDatetime the lastDownloadDatetime
     */
    public void setLastDownloadDatetime(LocalDateTime lastDownloadDatetime) {
        this.lastDownloadDatetime = lastDownloadDatetime;
    }
}
