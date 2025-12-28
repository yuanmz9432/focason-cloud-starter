-- Project Name : Focason Cloud
-- Date/Time    : 2025/12/28 14:56:17
-- Author       : Focason Lab Team
-- RDBMS Type   : MySQL
-- Application  : A5:SQL Mk-2

-- ユーザートークン
drop table if exists `base003_user_token` cascade;

create table `base003_user_token`
(
    `id`            INT(11) auto_increment not null comment '行ID',
    `uid`           VARCHAR(36)  not null comment 'ユーザー識別子',
    `access_token`  VARCHAR(255) not null comment 'アクセストークン',
    `refresh_token` VARCHAR(255) not null comment 'リフレッシュトークン',
    `expires_at`    DATETIME     not null comment '有効期限',
    `device_id`     VARCHAR(36)  not null comment 'デバイスID',
    `device_type`   INT(1) not null comment 'デバイスタイプ',
    `user_agent`    VARCHAR(128) comment 'ユーザーエージェント',
    `ip_address`    VARCHAR(36) comment 'IPアドレス',
    `created_by`    VARCHAR(36)  not null comment '作成者',
    `created_at`    DATETIME     not null comment '作成日時',
    `modified_by`   VARCHAR(36)  not null comment '更新者',
    `modified_at`   DATETIME     not null comment '更新日時',
    `is_deleted`    INT(1) not null comment '削除フラグ',
    constraint `base003_user_token_PKC` primary key (`id`)
) comment 'ユーザートークン';

-- 通知既読
drop table if exists `base005_notification_read` cascade;

create table `base005_notification_read`
(
    `id`              INT(11) auto_increment not null comment '行ID',
    `notification_id` VARCHAR(36) not null comment '通知ID',
    `uid`             VARCHAR(36) not null comment 'ユーザー識別子',
    `is_read`         INT(1) comment '既読フラグ',
    `read_at`         DATETIME comment '既読日時',
    `created_by`      VARCHAR(36) not null comment '作成者',
    `created_at`      DATETIME    not null comment '作成日時',
    `modified_by`     VARCHAR(36) not null comment '更新者',
    `modified_at`     DATETIME    not null comment '更新日時',
    `is_deleted`      INT(1) not null comment '削除フラグ',
    constraint `base005_notification_read_PKC` primary key (`id`)
) comment '通知既読';

-- メールログ
drop table if exists `base006_email_log` cascade;

create table `base006_email_log`
(
    `id`            INT(11) auto_increment not null comment '行ID',
    `uid`           VARCHAR(36)  not null comment 'ユーザー識別子',
    `email`         VARCHAR(128) not null comment 'メールアドレス',
    `subject`       VARCHAR(36) comment 'メール件名',
    `content`       TEXT comment 'メール本文',
    `status`        INT(1) not null comment '送信状態:0: 未送信, 1: 送信成功, 2: 送信失敗',
    `template_code` VARCHAR(36) comment 'テンプレートコード',
    `error_message` TEXT comment 'エラー内容',
    `created_by`    VARCHAR(36)  not null comment '作成者',
    `created_at`    DATETIME     not null comment '作成日時',
    `modified_by`   VARCHAR(36)  not null comment '更新者',
    `modified_at`   DATETIME     not null comment '更新日時',
    `is_deleted`    INT(1) not null comment '削除フラグ',
    constraint `base006_email_log_PKC` primary key (`id`)
) comment 'メールログ';

-- ファイルメタデータ
drop table if exists `base007_file_metadata` cascade;

create table `base007_file_metadata`
(
    `id`                     INT(11) auto_increment not null comment '行ID',
    `uid`                    VARCHAR(36)      not null comment 'ユーザー識別子',
    `file_id`                VARCHAR(36)      not null comment 'ファイルID',
    `original_file_name`     VARCHAR(128)     not null comment 'ファイル名',
    `object_key`             VARCHAR(255)     not null comment 'オブジェクトキー',
    `mime_type`              VARCHAR(255)     not null comment 'MIMEタイプ',
    `extension`              VARCHAR(36)      not null comment '拡張子',
    `size`                   BIGINT default 0 not null comment 'サイズ',
    `status`                 INT(1) not null comment 'ステータス',
    `download_count`         INT(15) default 0 not null comment 'ダウンロード数',
    `last_download_datetime` DATETIME comment '最終ダウンロード日時',
    `created_by`             VARCHAR(36)      not null comment '作成者',
    `created_at`             DATETIME         not null comment '作成日時',
    `modified_by`            VARCHAR(36)      not null comment '更新者',
    `modified_at`            DATETIME         not null comment '更新日時',
    `is_deleted`             INT(1) not null comment '削除フラグ',
    constraint `base007_file_metadata_PKC` primary key (`id`)
) comment 'ファイルメタデータ';

-- ユーザー
drop table if exists `base001_user` cascade;

create table `base001_user`
(
    `id`          INT(11) auto_increment not null comment '行ID',
    `uid`         VARCHAR(36)  not null comment 'ユーザー識別子',
    `username`    VARCHAR(36) comment 'ユーザー名',
    `email`       VARCHAR(128) not null comment 'メールアドレス',
    `password`    VARCHAR(128) comment 'パスワード',
    `status`      INT(1) default 0 not null comment 'ステータス:0:無効, 1:有効',
    `created_by`  VARCHAR(36)  not null comment '作成者',
    `created_at`  DATETIME     not null comment '作成日時',
    `modified_by` VARCHAR(36)  not null comment '更新者',
    `modified_at` DATETIME     not null comment '更新日時',
    `is_deleted`  INT(1) not null comment '削除フラグ',
    constraint `base001_user_PKC` primary key (`id`)
) comment 'ユーザー';

-- 通知
drop table if exists `base004_notification` cascade;

create table `base004_notification`
(
    `id`              INT(11) auto_increment not null comment '行ID',
    `notification_id` VARCHAR(36) not null comment '通知ID',
    `title`           VARCHAR(36) not null comment 'タイトル',
    `content`         TEXT        not null comment '本文',
    `status`          INT(1) not null comment 'ステータス',
    `type`            INT(1) not null comment '通知タイプ:1: システム通知, 2: メール通知, 3: アプリ内通知, 4: セキュリティ通知',
    `notify_at`       DATETIME    not null comment '通知日時',
    `created_by`      VARCHAR(36) not null comment '作成者',
    `created_at`      DATETIME    not null comment '作成日時',
    `modified_by`     VARCHAR(36) not null comment '更新者',
    `modified_at`     DATETIME    not null comment '更新日時',
    `is_deleted`      INT(1) not null comment '削除フラグ',
    constraint `base004_notification_PKC` primary key (`id`)
) comment '通知';

