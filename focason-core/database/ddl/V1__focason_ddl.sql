-- Project Name : Focason Cloud
-- Date/Time    : 2025/12/13 21:40:16
-- Author       : Focason Lab Team
-- RDBMS Type   : MySQL
-- Application  : A5:SQL Mk-2

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

-- ユーザー認証
drop table if exists `base002_user_verification` cascade;

create table `base002_user_verification`
(
    `id`                INT(11) auto_increment not null comment '行ID',
    `uid`               VARCHAR(36) not null comment 'ユーザー識別子',
    `verification_code` VARCHAR(36) not null comment '認証コード',
    `expires_at`        DATETIME    not null comment '認証期限',
    `verified_at`       DATETIME comment '認証日時',
    `created_by`        VARCHAR(36) not null comment '作成者',
    `created_at`        DATETIME    not null comment '作成日時',
    `modified_by`       VARCHAR(36) not null comment '更新者',
    `modified_at`       DATETIME    not null comment '更新日時',
    `is_deleted`        INT(1) not null comment '削除フラグ',
    constraint `base002_user_verification_PKC` primary key (`id`)
) comment 'ユーザー認証';

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
    `uid`           VARCHAR(36) comment 'ユーザー識別子',
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

-- タスク明細
drop table if exists `io002_task_detail` cascade;

create table `io002_task_detail`
(
    `id`            INT(11) auto_increment not null comment '行ID',
    `task_code`     VARCHAR(36) not null comment 'タスクコード',
    `record_order`  INT(11) not null comment 'レコード順番',
    `status`        INT(2) default  1 not null comment 'ステータス',
    `item1`         VARCHAR(255) comment '項目1',
    `item2`         VARCHAR(255) comment '項目2',
    `item3`         VARCHAR(255) comment '項目3',
    `item4`         VARCHAR(255) comment '項目4',
    `item5`         VARCHAR(255) comment '項目5',
    `item6`         VARCHAR(255) comment '項目6',
    `item7`         VARCHAR(255) comment '項目7',
    `item8`         VARCHAR(255) comment '項目8',
    `item9`         VARCHAR(255) comment '項目9',
    `item10`        VARCHAR(255) comment '項目10',
    `item11`        VARCHAR(255) comment '項目11',
    `item12`        VARCHAR(255) comment '項目12',
    `item13`        VARCHAR(255) comment '項目13',
    `item14`        VARCHAR(255) comment '項目14',
    `item15`        VARCHAR(255) comment '項目15',
    `item16`        VARCHAR(255) comment '項目16',
    `item17`        VARCHAR(255) comment '項目17',
    `item18`        VARCHAR(255) comment '項目18',
    `item19`        VARCHAR(255) comment '項目19',
    `item20`        VARCHAR(255) comment '項目20',
    `error_message` TEXT comment '異常情報',
    `created_by`    VARCHAR(36) not null comment '作成者',
    `created_at`    DATETIME    not null comment '作成日時',
    `modified_by`   VARCHAR(36) not null comment '更新者',
    `modified_at`   DATETIME    not null comment '更新日時',
    `is_deleted`    INT(1) not null comment '削除フラグ',
    constraint `io002_task_detail_PKC` primary key (`id`)
) comment 'タスク明細';

-- 在庫
drop table if exists `iv004_inventory` cascade;

create table `iv004_inventory`
(
    `id`               INT(11) auto_increment not null comment '行ID',
    `warehouse_code`   VARCHAR(10)  not null comment '倉庫コード',
    `client_code`      VARCHAR(10)  not null comment '荷主コード',
    `product_sku`      VARCHAR(36)  not null comment '商品SKU',
    `zone_code`        VARCHAR(10) comment 'ゾーンコード',
    `shelf_unit_code`  VARCHAR(128) comment '棚ユニットコード',
    `lot_number`       VARCHAR(128) not null comment 'ロット番号',
    `actual_stock`     INT(11) default 0 not null comment '実在庫数量:物理的にカウントされた在庫数',
    `available_stock`  INT(11) default 0 not null comment '利用可能在庫数量:顧客や顧客注文に対して利用可能な在庫数',
    `reserved_stock`   INT(11) default 0 not null comment '予約在庫数量:顧客が商品を注文したが、まだ出荷されていない在庫数',
    `back_order_stock` INT(11) default 0 not null comment 'バックオーダー在庫数量:現在は在庫切れであり、注文された商品の数量',
    `lead_time_stock`  INT(11) default 0 not null comment 'リードタイム在庫数量:供給者からの補充注文が到着するまでの間の在庫数',
    `expiry_date`      DATETIME     not null comment '賞味期限',
    `inventory_status` INT(2) default 1 not null comment '在庫ステータス:1:未検査, 2:検査合格, 3:検査不合格',
    `remark`           VARCHAR(255) comment '備考',
    `created_by`       VARCHAR(36)  not null comment '作成者',
    `created_at`       DATETIME     not null comment '作成日時',
    `modified_by`      VARCHAR(36)  not null comment '更新者',
    `modified_at`      DATETIME     not null comment '更新日時',
    `is_deleted`       INT(1) not null comment '削除フラグ',
    constraint `iv004_inventory_PKC` primary key (`id`)
) comment '在庫';

create index `idx_warehouse_code`
    on `iv004_inventory` (`warehouse_code`);

create index `idx_client_product`
    on `iv004_inventory` (`client_code`);

-- 入庫棚上げ
drop table if exists `iv007_inbound_shelving` cascade;

create table `iv007_inbound_shelving`
(
    `id`                    INT(11) auto_increment not null comment '行ID',
    `inbound_shelving_code` VARCHAR(10) not null comment '入庫棚上げコード',
    `inbound_item_code`     VARCHAR(10) not null comment '入庫部品コード',
    `shelf_unit_code`       VARCHAR(10) not null comment '棚ユニットコード',
    `quantity`              INT(11) not null comment '数量',
    `created_by`            VARCHAR(36) not null comment '作成者',
    `created_at`            DATETIME    not null comment '作成日時',
    `modified_by`           VARCHAR(36) not null comment '更新者',
    `modified_at`           DATETIME    not null comment '更新日時',
    `is_deleted`            INT(1) not null comment '削除フラグ',
    constraint `iv007_inbound_shelving_PKC` primary key (`id`)
) comment '入庫棚上げ';

-- 出庫
drop table if exists `iv008_outbound` cascade;

create table `iv008_outbound`
(
    `id`              INT(11) auto_increment not null comment '行ID',
    `outbound_code`   VARCHAR(10) not null comment '出庫コード',
    `warehouse_code`  VARCHAR(10) not null comment '倉庫コード',
    `client_code`     VARCHAR(10) not null comment '倉庫コード',
    `product_sku`     VARCHAR(36) not null comment '商品SKU',
    `quantity`        INT(11) default 0 not null comment '数量',
    `shelf_unit_code` VARCHAR(10) not null comment '棚ユニットコード',
    `outbound_date`   DATE        not null comment '出庫日付',
    `outbound_status` INT(2) default 1 not null comment '出庫ステータス:1:未処理, 2:処理中, 3:完了, 4:キャンセル, 5:異常',
    `outbound_type`   INT(2) not null comment '出庫タイプ:1:正規出庫, 2:返品, 3:サンプル出庫, 4:破棄, 5:移動',
    `destination`     VARCHAR(128) comment '出庫先',
    `manager`         VARCHAR(128) comment '担当者',
    `remark`          VARCHAR(255) comment '備考',
    `created_by`      VARCHAR(36) not null comment '作成者',
    `created_at`      DATETIME    not null comment '作成日時',
    `modified_by`     VARCHAR(36) not null comment '更新者',
    `modified_at`     DATETIME    not null comment '更新日時',
    `is_deleted`      INT(1) not null comment '削除フラグ',
    constraint `iv008_outbound_PKC` primary key (`id`)
) comment '出庫';

create index `idx_warehouse_code`
    on `iv008_outbound` (`warehouse_code`);

create index `idx_outbound_code`
    on `iv008_outbound` (`outbound_code`);

-- 在庫移動履歴
drop table if exists `iv101_inventory_movement_history` cascade;

create table `iv101_inventory_movement_history`
(
    `id`                          INT(11) auto_increment not null comment '行ID',
    `warehouse_code`              VARCHAR(10)  not null comment '倉庫コード',
    `client_code`                 VARCHAR(10)  not null comment '荷主コード',
    `product_sku`                 VARCHAR(36)  not null comment '商品SKU',
    `quantity`                    INT(11) not null comment '数量',
    `source_shelf_unit_code`      VARCHAR(10) comment '移動元:棚ユニットコード',
    `destination_shelf_unit_code` VARCHAR(10) comment '移動先:棚ユニットコード',
    `operator`                    VARCHAR(128) not null comment '作業員',
    `operation_datetime`          DATETIME     not null comment '操作日時',
    `operation_type`              INT(2) not null comment '操作タイプ:1:受領上棚, 2:ピッキング移動, 3:補充移動, 4:調達移動, 5:返品処理, 6:在庫整理, 7:品質検査移動',
    `remark`                      VARCHAR(255) comment '備考',
    `created_by`                  VARCHAR(36)  not null comment '作成者',
    `created_at`                  DATETIME     not null comment '作成日時',
    `modified_by`                 VARCHAR(36)  not null comment '更新者',
    `modified_at`                 DATETIME     not null comment '更新日時',
    `is_deleted`                  INT(1) not null comment '削除フラグ',
    constraint `iv101_inventory_movement_history_PKC` primary key (`id`)
) comment '在庫移動履歴';

create index `idx_warehouse_code`
    on `iv101_inventory_movement_history` (`warehouse_code`);

-- 在庫調整履歴
drop table if exists `iv102_inventory_adjustment_history` cascade;

create table `iv102_inventory_adjustment_history`
(
    `id`                     INT(11) auto_increment not null comment '行ID',
    `warehouse_code`         VARCHAR(10)  not null comment '倉庫コード',
    `client_code`            VARCHAR(10)  not null comment '荷主コード',
    `product_sku`            VARCHAR(36)  not null comment '商品SKU',
    `quantity`               INT(11) not null comment '数量',
    `quantity_before_change` INT(11) not null comment '調整前数量',
    `quantity_after_change`  INT(11) not null comment '調整後数量',
    `operator`               VARCHAR(128) not null comment '作業員',
    `operation_datetime`     DATETIME     not null comment '操作日時',
    `operation_type`         INT(2) not null comment '操作タイプ:1:新規, 2:在庫増加, 3:在庫減少, 4:在庫移動',
    `remark`                 VARCHAR(255) comment '備考',
    `created_by`             VARCHAR(36)  not null comment '作成者',
    `created_at`             DATETIME     not null comment '作成日時',
    `modified_by`            VARCHAR(36)  not null comment '更新者',
    `modified_at`            DATETIME     not null comment '更新日時',
    `is_deleted`             INT(1) not null comment '削除フラグ',
    constraint `iv102_inventory_adjustment_history_PKC` primary key (`id`)
) comment '在庫調整履歴';

create index `idx_warehouse_code`
    on `iv102_inventory_adjustment_history` (`warehouse_code`);

-- 棚ユニット履歴
drop table if exists `iv103_shelf_unit_log` cascade;

create table `iv103_shelf_unit_log`
(
    `id`                     INT(11) auto_increment not null comment '行ID',
    `warehouse_code`         VARCHAR(10)  not null comment '倉庫コード',
    `client_code`            VARCHAR(10)  not null comment '荷主コード',
    `product_sku`            VARCHAR(36) comment '商品SKU',
    `quantity`               INT(11) comment '数量',
    `quantity_before_change` INT(11) not null comment '調整前数量',
    `quantity_after_change`  INT(11) not null comment '調整後数量',
    `operator`               VARCHAR(128) not null comment '作業員',
    `operation_datetime`     DATETIME     not null comment '操作日時',
    `operation_type`         INT(2) not null comment '操作タイプ:1:新規, 2:在庫増加, 3:在庫減少, 4:在庫移動',
    `remark`                 VARCHAR(255) comment '備考',
    `created_by`             VARCHAR(36)  not null comment '作成者',
    `created_at`             DATETIME     not null comment '作成日時',
    `modified_by`            VARCHAR(36)  not null comment '更新者',
    `modified_at`            DATETIME     not null comment '更新日時',
    `is_deleted`             INT(1) not null comment '削除フラグ',
    constraint `iv103_shelf_unit_log_PKC` primary key (`id`)
) comment '棚ユニット履歴';

create index `idx_warehouse_code`
    on `iv103_shelf_unit_log` (`warehouse_code`);

-- 入庫履歴
drop table if exists `iv104_inbound_log` cascade;

create table `iv104_inbound_log`
(
    `id`                 INT(11) auto_increment not null comment '行ID',
    `warehouse_code`     VARCHAR(10)  not null comment '倉庫コード',
    `client_code`        VARCHAR(10) comment '荷主コード',
    `product_sku`        VARCHAR(36) comment '商品SKU',
    `quantity`           INT(11) comment '数量',
    `shelf_unit_code`    VARCHAR(10) comment '棚ユニットコード',
    `operator`           VARCHAR(128) not null comment '作業員',
    `operation_datetime` DATETIME     not null comment '操作日時',
    `operation_type`     INT(2) not null comment '操作タイプ:1:仕入れ受領, 2:入庫済み, 3:キャンセル',
    `remark`             VARCHAR(255) comment '備考',
    `created_by`         VARCHAR(36)  not null comment '作成者',
    `created_at`         DATETIME     not null comment '作成日時',
    `modified_by`        VARCHAR(36)  not null comment '更新者',
    `modified_at`        DATETIME     not null comment '更新日時',
    `is_deleted`         INT(1) not null comment '削除フラグ',
    constraint `iv104_inbound_log_PKC` primary key (`id`)
) comment '入庫履歴';

create index `idx_warehouse_code`
    on `iv104_inbound_log` (`warehouse_code`);

-- 出庫操作履歴
drop table if exists `iv105_stock_withdrawal_operation_log` cascade;

create table `iv105_stock_withdrawal_operation_log`
(
    `id`                 INT(11) auto_increment not null comment '行ID',
    `warehouse_code`     VARCHAR(10)  not null comment '倉庫コード',
    `product_sku`        VARCHAR(36) comment '商品SKU',
    `quantity`           INT(11) comment '数量',
    `shelf_unit_code`    VARCHAR(10) comment '棚ユニットコード',
    `operator`           VARCHAR(128) not null comment '作業員',
    `operation_datetime` DATETIME     not null comment '操作日時',
    `operation_type`     INT(2) not null comment '操作タイプ:1:出庫開始, 2:出庫承認, 3:梱包, 4:出荷, 5:キャンセル処理, 6:異常報告',
    `remark`             VARCHAR(255) comment '備考',
    `created_by`         VARCHAR(36)  not null comment '作成者',
    `created_at`         DATETIME     not null comment '作成日時',
    `modified_by`        VARCHAR(36)  not null comment '更新者',
    `modified_at`        DATETIME     not null comment '更新日時',
    `is_deleted`         INT(1) not null comment '削除フラグ',
    constraint `iv105_stock_withdrawal_operation_log_PKC` primary key (`id`)
) comment '出庫操作履歴';

create index `idx_warehouse_code`
    on `iv105_stock_withdrawal_operation_log` (`warehouse_code`);

-- 倉庫_荷主関係
drop table if exists `mg004_warehouse_client` cascade;

create table `mg004_warehouse_client`
(
    `id`             INT(11) auto_increment not null comment '行ID',
    `warehouse_code` VARCHAR(10)  not null comment '倉庫コード',
    `client_code`    VARCHAR(10)  not null comment '荷主コード',
    `created_by`     VARCHAR(128) not null comment '作成者',
    `created_at`     DATETIME     not null comment '作成日時',
    `modified_by`    VARCHAR(128) not null comment '更新者',
    `modified_at`    DATETIME     not null comment '更新日時',
    `is_deleted`     INT(11) not null comment '削除フラグ',
    constraint `mg004_warehouse_client_PKC` primary key (`id`)
) comment '倉庫_荷主関係';

-- 倉庫_ユーザー関係
drop table if exists `mg006_warehouse_user` cascade;

create table `mg006_warehouse_user`
(
    `id`             INT(11) auto_increment not null comment '行ID',
    `uuid`           VARCHAR(36) not null comment 'UUID',
    `warehouse_code` VARCHAR(10) not null comment '倉庫コード',
    `created_by`     VARCHAR(36) not null comment '作成者',
    `created_at`     DATETIME    not null comment '作成日時',
    `modified_by`    VARCHAR(36) not null comment '更新者',
    `modified_at`    DATETIME    not null comment '更新日時',
    `is_deleted`     INT(1) not null comment '削除フラグ',
    constraint `mg006_warehouse_user_PKC` primary key (`id`)
) comment '倉庫_ユーザー関係';

-- サプライヤー
drop table if exists `mg007_supplier` cascade;

create table `mg007_supplier`
(
    `id`            INT(11) auto_increment not null comment '行ID',
    `supplier_code` VARCHAR(10)  not null comment 'サプライヤーコード',
    `client_code`   VARCHAR(10)  not null comment '荷主コード',
    `supplier_name` VARCHAR(128) not null comment 'サプライヤー名称',
    `created_by`    VARCHAR(36)  not null comment '作成者',
    `created_at`    DATETIME     not null comment '作成日時',
    `modified_by`   VARCHAR(36)  not null comment '更新者',
    `modified_at`   DATETIME     not null comment '更新日時',
    `is_deleted`    INT(1) not null comment '削除フラグ',
    constraint `mg007_supplier_PKC` primary key (`id`)
) comment 'サプライヤー';

-- 依頼主
drop table if exists `mg008_sponsor` cascade;

create table `mg008_sponsor`
(
    `id`           INT(11) auto_increment not null comment '行ID',
    `sponsor_code` VARCHAR(10)  not null comment '依頼主コード',
    `client_code`  VARCHAR(10)  not null comment '荷主コード',
    `sponsor_name` VARCHAR(128) not null comment '依頼主名前',
    `created_by`   VARCHAR(36)  not null comment '作成者',
    `created_at`   DATETIME     not null comment '作成日時',
    `modified_by`  VARCHAR(36)  not null comment '更新者',
    `modified_at`  DATETIME     not null comment '更新日時',
    `is_deleted`   INT(1) not null comment '削除フラグ',
    constraint `mg008_sponsor_PKC` primary key (`id`)
) comment '依頼主';

-- 商品マッピング
drop table if exists `mg010_product_mapping` cascade;

create table `mg010_product_mapping`
(
    `id`                   INT(11) auto_increment not null comment '行ID',
    `client_code`          VARCHAR(10) not null comment '荷主コード',
    `product_sku`          VARCHAR(36) not null comment '商品SKU',
    `external_product_sku` VARCHAR(36) not null comment '外部商品SKU',
    `external_system`      VARCHAR(128) comment '外部システム',
    `created_by`           VARCHAR(36) not null comment '作成者',
    `created_at`           DATETIME    not null comment '作成日時',
    `modified_by`          VARCHAR(36) not null comment '更新者',
    `modified_at`          DATETIME    not null comment '更新日時',
    `is_deleted`           INT(1) not null comment '削除フラグ',
    constraint `mg010_product_mapping_PKC` primary key (`id`)
) comment '商品マッピング';

-- 配送方法マッピング
drop table if exists `mg011_delivery_method_mapping` cascade;

create table `mg011_delivery_method_mapping`
(
    `id`                          INT(11) auto_increment not null comment '行ID',
    `client_code`                 VARCHAR(10)  not null comment '荷主コード',
    `delivery_method_code`        VARCHAR(10)  not null comment '配送方法コード',
    `external_delivery_method`    VARCHAR(128) not null comment '外部配送方法',
    `external_delivery_time_slot` VARCHAR(128) not null comment '外部配送時間帯',
    `external_system`             VARCHAR(128) not null comment '外部システム',
    `created_by`                  VARCHAR(36)  not null comment '作成者',
    `created_at`                  DATETIME     not null comment '作成日時',
    `modified_by`                 VARCHAR(36)  not null comment '更新者',
    `modified_at`                 DATETIME     not null comment '更新日時',
    `is_deleted`                  INT(1) not null comment '削除フラグ',
    constraint `mg011_delivery_method_mapping_PKC` primary key (`id`)
) comment '配送方法マッピング';

-- 支払方法マッピング
drop table if exists `mg012_payment_method_mapping` cascade;

create table `mg012_payment_method_mapping`
(
    `id`                      INT(11) auto_increment not null comment '行ID',
    `client_code`             VARCHAR(10)  not null comment '荷主コード',
    `payment_method`          VARCHAR(128) not null comment '支払方法',
    `external_payment_method` VARCHAR(128) not null comment '外部支払方法',
    `created_by`              VARCHAR(36)  not null comment '作成者',
    `created_at`              DATETIME     not null comment '作成日時',
    `modified_by`             VARCHAR(36)  not null comment '更新者',
    `modified_at`             DATETIME     not null comment '更新日時',
    `is_deleted`              INT(1) not null comment '削除フラグ',
    constraint `mg012_payment_method_mapping_PKC` primary key (`id`)
) comment '支払方法マッピング';

-- 契約
drop table if exists `mg013_contract` cascade;

create table `mg013_contract`
(
    `id`                  INT(11) auto_increment not null comment '行ID',
    `contract_code`       VARCHAR(10)  not null comment '契約コード',
    `contract_type`       INT(2) not null comment '契約種類:1:倉庫契約, 2:ストア契約',
    `contract_plan`       INT(2) not null comment '契約プラン:1:スタンダード, 2:プレミアム',
    `contract_start_date` DATE         not null comment '契約開始日',
    `contract_end_date`   DATE         not null comment '契約終了日',
    `contractor_code`     VARCHAR(10)  not null comment '契約先コード',
    `manager_name`        VARCHAR(128) not null comment '担当者_名前',
    `manager_phone`       VARCHAR(128) not null comment '担当者_電話番号',
    `manager_email`       VARCHAR(255) not null comment '担当者_メールアドレス',
    `created_by`          VARCHAR(36)  not null comment '作成者',
    `created_at`          DATETIME     not null comment '作成日時',
    `modified_by`         VARCHAR(36)  not null comment '更新者',
    `modified_at`         DATETIME     not null comment '更新日時',
    `is_deleted`          INT(1) not null comment '削除フラグ',
    constraint `mg013_contract_PKC` primary key (`id`)
) comment '契約';

-- ファイル内容
drop table if exists `mg015_file_content` cascade;

create table `mg015_file_content`
(
    `id`           INT(11) auto_increment not null comment '行ID',
    `file_code`    VARCHAR(10)  not null comment 'ファイルコード',
    `sheet_number` INT(11) default 1 not null comment 'シート番号',
    `value_1`      VARCHAR(128) comment '項目1',
    `value_2`      VARCHAR(128) comment '項目2',
    `value_3`      VARCHAR(128) comment '項目3',
    `value_4`      VARCHAR(128) comment '項目4',
    `value_5`      VARCHAR(128) comment '項目5',
    `value_6`      VARCHAR(128) comment '項目6',
    `value_7`      VARCHAR(128) comment '項目7',
    `value_8`      VARCHAR(128) comment '項目8',
    `value_9`      VARCHAR(128) comment '項目9',
    `value_10`     VARCHAR(128) comment '項目10',
    `value_11`     VARCHAR(128) comment '項目11',
    `value_12`     VARCHAR(128) comment '項目12',
    `value_13`     VARCHAR(128) comment '項目13',
    `value_14`     VARCHAR(128) comment '項目14',
    `value_15`     VARCHAR(128) comment '項目15',
    `value_16`     VARCHAR(128) comment '項目16',
    `value_17`     VARCHAR(128) comment '項目17',
    `value_18`     VARCHAR(128) comment '項目18',
    `value_19`     VARCHAR(128) comment '項目19',
    `value_20`     VARCHAR(128) comment '項目20',
    `created_by`   VARCHAR(128) not null comment '作成者',
    `created_at`   DATETIME     not null comment '作成日時',
    `modified_by`  VARCHAR(128) not null comment '更新者',
    `modified_at`  DATETIME     not null comment '更新日時',
    `is_deleted`   INT(11) not null comment '削除フラグ',
    constraint `mg015_file_content_PKC` primary key (`id`)
) comment 'ファイル内容';

-- 受注明細
drop table if exists `od002_order_detail` cascade;

create table `od002_order_detail`
(
    `id`                   INT(11) auto_increment not null comment '行ID',
    `order_code`           VARCHAR(10) not null comment '受注コード',
    `product_sku`          VARCHAR(36) not null comment '商品SKU',
    `product_name`         VARCHAR(128) comment '商品名称',
    `quantity`             INT(11) comment '数量',
    `excluded_unit_price`  DECIMAL(15, 2) comment '税抜き単価',
    `subtotal_amount`      DECIMAL(15, 2) comment '商品の小計金額',
    `tax`                  DECIMAL(15, 2) comment '税金',
    `total_product_amount` DECIMAL(15, 2) comment '商品の合計金額',
    `discount_amount`      DECIMAL(15, 2) comment '割引金額',
    `adjustment_amount`    DECIMAL(15, 2) comment '調整金額',
    `total_amount`         DECIMAL(15, 2) comment '総金額',
    `created_by`           VARCHAR(36) not null comment '作成者',
    `created_at`           DATETIME    not null comment '作成日時',
    `modified_by`          VARCHAR(36) not null comment '更新者',
    `modified_at`          DATETIME    not null comment '更新日時',
    `is_deleted`           INT(1) not null comment '削除フラグ',
    constraint `od002_order_detail_PKC` primary key (`id`)
) comment '受注明細';

create unique index `idx_order_product`
    on `od002_order_detail` (`order_code`);

-- 受注分割
drop table if exists `od003_order_split` cascade;

create table `od003_order_split`
(
    `id`                  INT(11) auto_increment not null comment '行ID',
    `order_split_code`    VARCHAR(10) not null comment '受注分割コード',
    `original_order_code` VARCHAR(10) not null comment '分割元受注コード',
    `split_order_code`    VARCHAR(10) not null comment '分割先受注コード',
    `split_datetime`      DATETIME    not null comment '分割日時',
    `created_by`          VARCHAR(36) not null comment '作成者',
    `created_at`          DATETIME    not null comment '作成日時',
    `modified_by`         VARCHAR(36) not null comment '更新者',
    `modified_at`         DATETIME    not null comment '更新日時',
    `is_deleted`          INT(1) not null comment '削除フラグ',
    constraint `od003_order_split_PKC` primary key (`id`)
) comment '受注分割';

-- 受注統合
drop table if exists `od004_order_consolidation` cascade;

create table `od004_order_consolidation`
(
    `id`                       INT(11) auto_increment not null comment '行ID',
    `order_consolidation_code` VARCHAR(10) not null comment '受注統合コード',
    `consolidated_order_code`  VARCHAR(10) not null comment '統合先受注コード',
    `original_order_code`      VARCHAR(10) not null comment '統合元受注コード',
    `consolidation_datetime`   DATETIME    not null comment '統合日時',
    `created_by`               VARCHAR(36) not null comment '作成者',
    `created_at`               DATETIME    not null comment '作成日時',
    `modified_by`              VARCHAR(36) not null comment '更新者',
    `modified_at`              DATETIME    not null comment '更新日時',
    `is_deleted`               INT(1) not null comment '削除フラグ',
    constraint `od004_order_consolidation_PKC` primary key (`id`)
) comment '受注統合';

-- 受注操作履歴
drop table if exists `od101_order_operation_log` cascade;

create table `od101_order_operation_log`
(
    `id`                       INT(11) auto_increment not null comment '行ID',
    `order_operation_log_code` VARCHAR(10)  not null comment '受注操作履歴コード',
    `order_code`               VARCHAR(10)  not null comment '受注コード',
    `operator`                 VARCHAR(128) not null comment '作業員',
    `operation_datetime`       DATETIME     not null comment '操作日時',
    `operation_type`           INT(2) not null comment '操作タイプ:1:出荷依頼, 2:受注調整, 3:キャンセル',
    `remark`                   VARCHAR(255) comment '備考',
    `created_by`               VARCHAR(36)  not null comment '作成者',
    `created_at`               DATETIME     not null comment '作成日時',
    `modified_by`              VARCHAR(36)  not null comment '更新者',
    `modified_at`              DATETIME     not null comment '更新日時',
    `is_deleted`               INT(1) not null comment '削除フラグ',
    constraint `od101_order_operation_log_PKC` primary key (`id`)
) comment '受注操作履歴';

-- 構成品
drop table if exists `pd002_set_component` cascade;

create table `pd002_set_component`
(
    `id`                 INT(11) auto_increment not null comment '行ID',
    `client_code`        VARCHAR(10)  not null comment '荷主コード',
    `parent_product_sku` VARCHAR(36)  not null comment '親商品SKU',
    `child_product_sku`  VARCHAR(36)  not null comment '子商品SKU',
    `quantity`           INT(11) default 1 not null comment '数量',
    `created_by`         VARCHAR(128) not null comment '作成者',
    `created_at`         DATETIME     not null comment '作成日時',
    `modified_by`        VARCHAR(128) not null comment '更新者',
    `modified_at`        DATETIME     not null comment '更新日時',
    `is_deleted`         INT(11) not null comment '削除フラグ',
    constraint `pd002_set_component_PKC` primary key (`id`)
) comment '構成品';

-- 商品オプション
drop table if exists `pd003_product_option` cascade;

create table `pd003_product_option`
(
    `id`           INT(11) auto_increment not null comment '行ID',
    `client_code`  VARCHAR(10)  not null comment '荷主コード',
    `product_sku`  VARCHAR(36)  not null comment '商品SKU',
    `option_key`   VARCHAR(128) not null comment 'オプションキー',
    `option_value` VARCHAR(128) not null comment 'オプション値',
    `created_by`   VARCHAR(36)  not null comment '作成者',
    `created_at`   DATETIME     not null comment '作成日時',
    `modified_by`  VARCHAR(36)  not null comment '更新者',
    `modified_at`  DATETIME     not null comment '更新日時',
    `is_deleted`   INT(1) not null comment '削除フラグ',
    constraint `pd003_product_option_PKC` primary key (`id`)
) comment '商品オプション';

-- 出荷明細
drop table if exists `sp002_shipment_detail` cascade;

create table `sp002_shipment_detail`
(
    `id`                  INT(11) auto_increment not null comment '行ID',
    `shipment_code`       VARCHAR(10)    not null comment '出荷コード',
    `product_sku`         VARCHAR(36)    not null comment '商品SKU',
    `product_name`        VARCHAR(128)   not null comment '商品名称',
    `quantity`            INT(11) not null comment '数量',
    `excluded_unit_price` DECIMAL(15, 2) not null comment '税抜き単価',
    `subtotal_price`      DECIMAL(15, 2) not null comment '商品の小計金額:商品の小計金額=税抜き単価*数量',
    `tax`                 DECIMAL(15, 2) not null comment '税金',
    `total_price`         DECIMAL(15, 2) not null comment '商品の合計金額:商品の合計金額=商品の小計金額+税金',
    `discount_amount`     DECIMAL(15, 2) not null comment '商品の割引金額',
    `total_amount`        DECIMAL(15, 2) not null comment '商品の総金額:総金額=商品の合計金額-商品の割引金額',
    `allocated_amount`    INT(11) not null comment '引当済み数量',
    `allocation_status`   INT(2) not null comment '引当ステータス:1:未処理, 2:一部引当済み, 3:引当済み',
    `created_by`          VARCHAR(36)    not null comment '作成者',
    `created_at`          DATETIME       not null comment '作成日時',
    `modified_by`         VARCHAR(36)    not null comment '更新者',
    `modified_at`         DATETIME       not null comment '更新日時',
    `is_deleted`          INT(1) not null comment '削除フラグ',
    constraint `sp002_shipment_detail_PKC` primary key (`id`)
) comment '出荷明細';

create index `idx_shipment_code`
    on `sp002_shipment_detail` (`shipment_code`);

-- 出荷統合
drop table if exists `sp003_shipment_consolidation` cascade;

create table `sp003_shipment_consolidation`
(
    `id`                          INT(11) auto_increment not null comment '行ID',
    `shipment_consolidation_code` VARCHAR(10) not null comment '出荷統合コード',
    `consolidated_shipment_code`  VARCHAR(10) not null comment '統合先出荷コード',
    `original_shipment_code`      VARCHAR(10) not null comment '統合元出荷コード',
    `consolidation_datetime`      DATETIME    not null comment '統合日時',
    `created_by`                  VARCHAR(36) not null comment '作成者',
    `created_at`                  DATETIME    not null comment '作成日時',
    `modified_by`                 VARCHAR(36) not null comment '更新者',
    `modified_at`                 DATETIME    not null comment '更新日時',
    `is_deleted`                  INT(1) not null comment '削除フラグ',
    constraint `sp003_shipment_consolidation_PKC` primary key (`id`)
) comment '出荷統合';

-- 受注分割
drop table if exists `sp004_shipment_split` cascade;

create table `sp004_shipment_split`
(
    `id`                     INT(11) auto_increment not null comment '行ID',
    `shipment_split_code`    VARCHAR(10) not null comment '受注分割コード',
    `original_shipment_code` VARCHAR(10) not null comment '分割元受注コード',
    `split_shipment_code`    VARCHAR(10) not null comment '分割先受注コード',
    `split_datetime`         DATETIME    not null comment '分割日時',
    `created_by`             VARCHAR(36) not null comment '作成者',
    `created_at`             DATETIME    not null comment '作成日時',
    `modified_by`            VARCHAR(36) not null comment '更新者',
    `modified_at`            DATETIME    not null comment '更新日時',
    `is_deleted`             INT(1) not null comment '削除フラグ',
    constraint `sp004_shipment_split_PKC` primary key (`id`)
) comment '受注分割';

-- 出荷操作履歴
drop table if exists `sp101_shipment_operation_log` cascade;

create table `sp101_shipment_operation_log`
(
    `id`                 INT(11) auto_increment not null comment '行ID',
    `shipment_code`      VARCHAR(10)  not null comment '出荷コード',
    `operator`           VARCHAR(128) not null comment '作業員',
    `operation_datetime` DATETIME     not null comment '操作日時',
    `operation_type`     INT(2) not null comment '操作タイプ:1:出荷準備, 2:引当, 3:検品, 4:梱包, 5:出荷指示送信, 6:出荷済み, 7:出荷一時停止, 8:出荷調整, 9:出荷再開',
    `remark`             VARCHAR(255) comment '備考',
    `created_by`         VARCHAR(36)  not null comment '作成者',
    `created_at`         DATETIME     not null comment '作成日時',
    `modified_by`        VARCHAR(36)  not null comment '更新者',
    `modified_at`        DATETIME     not null comment '更新日時',
    `is_deleted`         INT(1) not null comment '削除フラグ',
    constraint `sp101_shipment_operation_log_PKC` primary key (`id`)
) comment '出荷操作履歴';

-- ファイルタスク
drop table if exists `io001_file_task` cascade;

create table `io001_file_task`
(
    `id`                INT(11) auto_increment not null comment '行ID',
    `task_code`         VARCHAR(36)  not null comment 'タスクコード',
    `task_type`         INT(2) not null comment 'タスクタイプ',
    `receive_date`      DATE         not null comment '受信日',
    `process_order`     INT(11) not null comment '処理順位',
    `business_unit`     VARCHAR(128) not null comment '営業単位',
    `file_module`       INT(11) not null comment 'ファイルモジュール',
    `file_name`         VARCHAR(128) not null comment 'ファイル名称',
    `file_path`         VARCHAR(255) not null comment 'ファイルパス',
    `file_type`         INT(2) not null comment 'ファイルタイプ',
    `filter_conditions` TEXT comment '検索条件',
    `export_columns`    TEXT comment '出力項目',
    `status`            INT(2) default 1 not null comment 'ステータス',
    `error_message`     TEXT comment '異常情報',
    `created_by`        VARCHAR(36)  not null comment '作成者',
    `created_at`        DATETIME     not null comment '作成日時',
    `modified_by`       VARCHAR(36)  not null comment '更新者',
    `modified_at`       DATETIME     not null comment '更新日時',
    `is_deleted`        INT(1) not null comment '削除フラグ',
    constraint `io001_file_task_PKC` primary key (`id`)
) comment 'ファイルタスク';

-- 棚ユニット
drop table if exists `iv003_shelf_unit` cascade;

create table `iv003_shelf_unit`
(
    `id`              INT(11) auto_increment not null comment '行ID',
    `shelf_unit_code` VARCHAR(10)  not null comment '棚ユニットコード',
    `warehouse_code`  VARCHAR(10)  not null comment '倉庫コード',
    `zone_code`       VARCHAR(10)  not null comment 'ゾーンコード',
    `shelf_code`      VARCHAR(10)  not null comment '棚コード',
    `shelf_unit_name` VARCHAR(128) not null comment '棚ユニット名称',
    `remark`          VARCHAR(255) comment '備考',
    `created_by`      VARCHAR(36)  not null comment '作成者',
    `created_at`      DATETIME     not null comment '作成日時',
    `modified_by`     VARCHAR(36)  not null comment '更新者',
    `modified_at`     DATETIME     not null comment '更新日時',
    `is_deleted`      INT(1) not null comment '削除フラグ',
    constraint `iv003_shelf_unit_PKC` primary key (`id`)
) comment '棚ユニット';

-- 入庫部品
drop table if exists `iv006_inbound_item` cascade;

create table `iv006_inbound_item`
(
    `id`                  INT(11) auto_increment not null comment '行ID',
    `inbound_item_code`   VARCHAR(10) not null comment '入庫部品コード',
    `inbound_code`        VARCHAR(10) not null comment '入庫コード',
    `product_sku`         VARCHAR(36) not null comment '商品SKU',
    `expected_quantity`   INT(11) not null comment '予想数量',
    `actual_quantity`     INT(11) default 0 comment '実際数量',
    `shelf_unit_code`     VARCHAR(10) comment '棚ユニットコード',
    `inbound_item_status` INT(2) not null comment '入庫部品ステータス:1:未処理, 2:一部入庫, 3:入庫済み, 4:キャンセル, 5:異常',
    `lot_number`          VARCHAR(128) comment 'ロット番号',
    `production_date`     DATE comment '製造日',
    `created_by`          VARCHAR(36) not null comment '作成者',
    `created_at`          DATETIME    not null comment '作成日時',
    `modified_by`         VARCHAR(36) not null comment '更新者',
    `modified_at`         DATETIME    not null comment '更新日時',
    `is_deleted`          INT(1) not null comment '削除フラグ',
    constraint `iv006_inbound_item_PKC` primary key (`id`)
) comment '入庫部品';

create index `idx_inbound_code`
    on `iv006_inbound_item` (`inbound_code`);

-- ユーザー
drop table if exists `mg005_user` cascade;

create table `mg005_user`
(
    `id`                INT(11) auto_increment not null comment '行ID',
    `uuid`              VARCHAR(36)  not null comment 'UUID',
    `username`          VARCHAR(128) comment 'ユーザー名',
    `email`             VARCHAR(255) not null comment 'メールアドレス',
    `password`          VARCHAR(128) comment 'パスワード',
    `company_code`      VARCHAR(10) comment '会社コード',
    `phone`             VARCHAR(128) comment '電話番号',
    `type`              INT(2) not null comment 'タイプ:1:ルートユーザー, 2:通常ユーザー',
    `verification_code` VARCHAR(36) comment '認証コード',
    `is_verified`       INT(2) not null comment 'ステータス:1:認証待ち, 2:正常, 3:ブロック',
    `picture`           TEXT comment '画像',
    `refresh_token`     VARCHAR(255) comment 'リフレッシュトークン',
    `created_by`        VARCHAR(36)  not null comment '作成者',
    `created_at`        DATETIME     not null comment '作成日時',
    `modified_by`       VARCHAR(36)  not null comment '更新者',
    `modified_at`       DATETIME     not null comment '更新日時',
    `is_deleted`        INT(1) not null comment '削除フラグ',
    constraint `mg005_user_PKC` primary key (`id`)
) comment 'ユーザー';

create unique index `idx_email`
    on `mg005_user` (`email`);

create unique index `idx_uuid`
    on `mg005_user` (`uuid`);

-- 配送方法
drop table if exists `mg009_delivery_method` cascade;

create table `mg009_delivery_method`
(
    `id`                   INT(11) auto_increment not null comment '行ID',
    `delivery_method_code` VARCHAR(10)  not null comment '配送方法コード',
    `warehouse_code`       VARCHAR(10)  not null comment '倉庫コード',
    `delivery_company`     VARCHAR(128) not null comment '配送会社',
    `delivery_method`      VARCHAR(128) not null comment '配送方法',
    `delivery_time_slot`   VARCHAR(128) comment '配送時間帯',
    `created_by`           VARCHAR(36)  not null comment '作成者',
    `created_at`           DATETIME     not null comment '作成日時',
    `modified_by`          VARCHAR(36)  not null comment '更新者',
    `modified_at`          DATETIME     not null comment '更新日時',
    `is_deleted`           INT(1) not null comment '削除フラグ',
    constraint `mg009_delivery_method_PKC` primary key (`id`)
) comment '配送方法';

create index `idx_warehouse_code`
    on `mg009_delivery_method` (`warehouse_code`);

-- ファイル
drop table if exists `mg014_file` cascade;

create table `mg014_file`
(
    `id`          INT(11) auto_increment not null comment '行ID',
    `file_code`   VARCHAR(10)  not null comment 'ファイルコード',
    `store_code`  VARCHAR(10)  not null comment 'ストアコード',
    `file_name`   VARCHAR(128) not null comment 'ファイル名称',
    `type`        INT(2) not null comment 'タイプ:1:CSV, 2:Excel',
    `category`    INT(2) not null comment '種類:1:商品, 2:受注依頼, 3:出荷依頼',
    `location`    VARCHAR(255) not null comment '場所',
    `status`      INT(2) default 1 not null comment 'ステータス:1:未処理, 2:正常処理済, 3:異常あり, 4:キャンセル',
    `created_by`  VARCHAR(128) not null comment '作成者',
    `created_at`  DATETIME     not null comment '作成日時',
    `modified_by` VARCHAR(128) not null comment '更新者',
    `modified_at` DATETIME     not null comment '更新日時',
    `is_deleted`  INT(11) not null comment '削除フラグ',
    constraint `mg014_file_PKC` primary key (`id`)
) comment 'ファイル';

-- 受注
drop table if exists `od001_order` cascade;

create table `od001_order`
(
    `id`                         INT(11) auto_increment not null comment '行ID',
    `order_code`                 VARCHAR(10) not null comment '受注コード',
    `client_code`                VARCHAR(10) not null comment '荷主コード',
    `order_source`               INT(2) not null comment '受注ソース:1:外部システム, 2:CSVインポート, 3:手動入力',
    `external_order_code`        VARCHAR(128) comment '外部受注コード',
    `order_status`               INT(2) default 1 not null comment '受注ステータス:1:未処理, 2:出荷依頼中, 3:出荷済み, 4:キャンセル, 5:受注分割, 6:受注統合',
    `warehouse_code`             VARCHAR(10) not null comment '倉庫コード',
    `shipment_code`              VARCHAR(10) comment '出荷コード',
    `order_datetime`             DATETIME comment '受注日時',
    `shipment_request_datetime`  DATETIME comment '出荷依頼日時',
    `delivery_method`            VARCHAR(128) comment '配送方法',
    `desired_delivery_date`      DATE comment '配送希望日',
    `desired_delivery_time_slot` VARCHAR(128) comment '配送希望時間帯',
    `payment_method`             VARCHAR(128) comment '支払方法',
    `total_amount`               DECIMAL(15, 2) comment '総金額',
    `total_product_amount`       DECIMAL(15, 2) comment '商品の合計金額',
    `total_tax`                  DECIMAL(15, 2) comment '税金',
    `delivery_fee`               DECIMAL(15, 2) comment '送料',
    `handling_fee`               DECIMAL(15, 2) comment '手数料',
    `discount_amount`            DECIMAL(15, 2) comment '割引金額',
    `adjustment_amount`          DECIMAL(15, 2) comment '調整金額',
    `customer_postal_code`       VARCHAR(128) comment '注文者_郵便番号',
    `customer_province`          VARCHAR(128) comment '注文者_都道府県',
    `customer_city`              VARCHAR(128) comment '注文者_市区町村',
    `customer_address_1`         VARCHAR(128) comment '注文者_住所1',
    `customer_address_2`         VARCHAR(128) comment '注文者_住所2',
    `customer_name`              VARCHAR(128) comment '注文者_名前',
    `customer_phone`             VARCHAR(128) comment '注文者_電話番号',
    `customer_email`             VARCHAR(128) comment '注文者_メールアドレス',
    `shipment_postal_code`       VARCHAR(128) comment '配送先_郵便番号',
    `shipment_province`          VARCHAR(128) comment '配送先_都道府県',
    `shipment_city`              VARCHAR(128) comment '配送先_市区町村',
    `shipment_address_1`         VARCHAR(128) comment '配送先_住所1',
    `shipment_address_2`         VARCHAR(128) comment '配送先_住所2',
    `shipment_name`              VARCHAR(128) comment '配送先_名前',
    `shipment_phone`             VARCHAR(128) comment '配送先_電話番号',
    `shipment_email`             VARCHAR(128) comment '配送先_メールアドレス',
    `remark`                     VARCHAR(255) comment '備考',
    `created_by`                 VARCHAR(36) not null comment '作成者',
    `created_at`                 DATETIME    not null comment '作成日時',
    `modified_by`                VARCHAR(36) not null comment '更新者',
    `modified_at`                DATETIME    not null comment '更新日時',
    `is_deleted`                 INT(1) not null comment '削除フラグ',
    constraint `od001_order_PKC` primary key (`id`)
) comment '受注';

create unique index `idx_order_code`
    on `od001_order` (`order_code`);

create index `idx_order_status`
    on `od001_order` (`order_status`);

-- 商品
drop table if exists `pd001_product` cascade;

create table `pd001_product`
(
    `id`                       INT(11) auto_increment not null comment '行ID',
    `product_sku`              VARCHAR(36)              not null comment '商品SKU',
    `client_code`              VARCHAR(10)              not null comment '荷主コード',
    `product_name`             VARCHAR(128)             not null comment '商品名称',
    `product_type`             INT(2) default 1 not null comment '商品タイプ:1:通常商品、2:セット商品、3:同梱品',
    `included_tax_unit_price`  DECIMAL(15, 2) default 0 not null comment '税抜き単価',
    `excluded_tax_unit_price`  DECIMAL(15, 2) default 0 not null comment '税込み単価',
    `tax`                      DECIMAL(15, 2) default 0 not null comment '税金',
    `tax_rate`                 INT(11) default 0 not null comment '税率',
    `product_category_code`    VARCHAR(10) comment '商品分類コード',
    `length`                   DECIMAL(15, 2) comment '長さ',
    `width`                    DECIMAL(15, 2) comment '幅',
    `height`                   DECIMAL(15, 2) comment '高さ',
    `volume`                   DECIMAL(15, 2) comment '容積(m³)',
    `weight`                   DECIMAL(15, 2) comment '重量(kg)',
    `is_serial_number_managed` INT(1) default 0 not null comment 'シリアル番号管理要否',
    `is_best_before_managed`   INT(1) default 0 not null comment '賞味期間管理要否',
    `best_before_period_days`  INT(11) default 0 not null comment '賞味期間（日数）',
    `product_status`           INT(2) default 1 not null comment '商品ステータス:1:有効, 2:無効, 3:アーカイブ',
    `image`                    VARCHAR(255) comment '画像:Amazon S3パス',
    `barcode`                  VARCHAR(255) comment 'バーコード:Amazon S3パス',
    `qr_code`                  VARCHAR(255) comment 'QRコード:Amazon S3パス',
    `remark`                   VARCHAR(255) comment '備考',
    `created_by`               VARCHAR(128)             not null comment '作成者',
    `created_at`               DATETIME                 not null comment '作成日時',
    `modified_by`              VARCHAR(128)             not null comment '更新者',
    `modified_at`              DATETIME                 not null comment '更新日時',
    `is_deleted`               INT(11) not null comment '削除フラグ',
    constraint `pd001_product_PKC` primary key (`id`)
) comment '商品';

create unique index `idx_client_product_code`
    on `pd001_product` (`client_code`, `product_sku`);

create index `idx_product_name`
    on `pd001_product` (`product_name`);

create index `idx_product_type`
    on `pd001_product` (`product_type`);

-- 商品分類
drop table if exists `pd004_product_category` cascade;

create table `pd004_product_category`
(
    `id`                           INT(11) auto_increment not null comment '行ID',
    `product_category_code`        VARCHAR(10)  not null comment '商品分類コード',
    `parent_product_category_code` VARCHAR(10)  not null comment '親商品分類コード:レベルが1の場合、自分のカテゴリーコードを格納する。',
    `product_category_name`        VARCHAR(128) not null comment '商品分類名称',
    `product_category_level`       INT(11) not null comment '商品分類レベル:数字が大きいほど階層が高くなり、最高レベルは1です。',
    `created_by`                   VARCHAR(36)  not null comment '作成者',
    `created_at`                   DATETIME     not null comment '作成日時',
    `modified_by`                  VARCHAR(36)  not null comment '更新者',
    `modified_at`                  DATETIME     not null comment '更新日時',
    `is_deleted`                   INT(1) not null comment '削除フラグ',
    constraint `pd004_product_category_PKC` primary key (`id`)
) comment '商品分類';

-- 出荷
drop table if exists `sp001_shipment` cascade;

create table `sp001_shipment`
(
    `id`                                   INT(11) auto_increment not null comment '行ID',
    `shipment_code`                        VARCHAR(10) not null comment '出荷コード',
    `warehouse_code`                       VARCHAR(10) not null comment '倉庫コード',
    `client_code`                          VARCHAR(10) not null comment '荷主コード',
    `order_code`                           VARCHAR(10) not null comment '受注コード',
    `sponsor_code`                         VARCHAR(10) not null comment '依頼主コード',
    `shipment_status`                      INT(2) not null comment '出荷ステータス:1:未処理, 2:出荷準備中, 3:引当済み, 4:検品済み, 5:梱包完了, 6:出荷済み, 7:出荷一時停止, 8:出荷分割, 9:出荷統合',
    `shipment_stop_reason`                 VARCHAR(255) comment '出荷一時停止理由:1:在庫不足, 2:品質問題, 3:配送問題, 4:顧客要求, 5:法的/規制上の問題, 6:出荷統合',
    `order_accept_datetime`                DATETIME comment '受注受付日時',
    `shipment_request_datetime`            DATETIME comment '出荷依頼日時',
    `scheduled_shipment_date`              DATETIME comment '予定出荷日時',
    `actual_shipment_date`                 DATETIME comment '実際出荷日時',
    `desired_delivery_date`                DATE comment '配送希望日',
    `desired_delivery_time_slot`           VARCHAR(128) comment '配送希望時間帯',
    `delivery_method`                      VARCHAR(128) comment '配送方法',
    `payment_method`                       VARCHAR(128) comment '支払方法',
    `shipment_manifest_number`             VARCHAR(128) comment '配送伝票番号',
    `shipment_manifest_integration_status` INT(2) comment '配送伝票連携ステータス',
    `total_quantity`                       INT(11) comment '合計商品数量',
    `total_price_amount`                   DECIMAL(15, 2) comment '総金額:総金額=商品合計金額+税金+送料+手数料−割引金額',
    `total_product_amount`                 DECIMAL(15, 2) comment '商品合計金額:税抜金額',
    `total_amount`                         DECIMAL(15, 2) comment '税金',
    `shipment_fee`                         DECIMAL(15, 2) comment '送料',
    `handling_fee`                         DECIMAL(15, 2) comment '手数料',
    `discount_amount`                      DECIMAL(15, 2) comment '割引金額',
    `adjustment_amount`                    DECIMAL(15, 2) comment '調整金額',
    `customer_postal_code`                 VARCHAR(128) comment '注文者_郵便番号',
    `customer_province`                    VARCHAR(128) comment '注文者_都道府県',
    `customer_city`                        VARCHAR(128) comment '注文者_市区町村',
    `customer_address_1`                   VARCHAR(128) comment '注文者_住所1',
    `customer_address_2`                   VARCHAR(128) comment '注文者_住所2',
    `customer_name`                        VARCHAR(128) comment '注文者_名前',
    `customer_phone_number`                VARCHAR(128) comment '注文者_電話番号',
    `customer_email`                       VARCHAR(128) comment '注文者_メールアドレス',
    `shipment_postal_code`                 VARCHAR(128) comment '配送先_郵便番号',
    `shipment_province`                    VARCHAR(128) comment '配送先_都道府県',
    `shipment_city`                        VARCHAR(128) comment '配送先_市区町村',
    `shipment_address_1`                   VARCHAR(128) comment '配送先_住所1',
    `shipment_address_2`                   VARCHAR(128) comment '配送先_住所2',
    `shipment_name`                        VARCHAR(128) comment '配送先_名前',
    `shipment_phone_number`                VARCHAR(128) comment '配送先_電話番号',
    `shipment_email`                       VARCHAR(128) comment '配送先_メールアドレス',
    `shipment_remark`                      VARCHAR(255) comment '出荷特記事項',
    `shipment_manifest_remark`             VARCHAR(255) comment '配送伝票特記事項',
    `delivery_note_remark`                 VARCHAR(255) comment '納品書特記事項',
    `attached_file_1`                      VARCHAR(128) comment '添付ファイル1:Amazon S3パス',
    `attached_file_2`                      VARCHAR(128) comment '添付ファイル2:Amazon S3パス',
    `attached_file_3`                      VARCHAR(128) comment '添付ファイル3:Amazon S3パス',
    `created_by`                           VARCHAR(36) not null comment '作成者',
    `created_at`                           DATETIME    not null comment '作成日時',
    `modified_by`                          VARCHAR(36) not null comment '更新者',
    `modified_at`                          DATETIME    not null comment '更新日時',
    `is_deleted`                           INT(1) not null comment '削除フラグ',
    constraint `sp001_shipment_PKC` primary key (`id`)
) comment '出荷';

create unique index `idx_shipment_code`
    on `sp001_shipment` (`shipment_code`);

create index `idx_warehouse_code`
    on `sp001_shipment` (`warehouse_code`);

create index `idx_shipment_status`
    on `sp001_shipment` (`shipment_status`);

-- 棚
drop table if exists `iv002_shelf` cascade;

create table `iv002_shelf`
(
    `id`             INT(11) auto_increment not null comment '行ID',
    `shelf_code`     VARCHAR(10)  not null comment '棚コード',
    `warehouse_code` VARCHAR(10)  not null comment '倉庫コード',
    `zone_code`      VARCHAR(10)  not null comment 'ゾーンコード',
    `shelf_name`     VARCHAR(128) not null comment '棚名称',
    `priority`       INT(11) default 999 not null comment '優先度',
    `remark`         VARCHAR(255) comment '備考',
    `created_by`     VARCHAR(36)  not null comment '作成者',
    `created_at`     DATETIME     not null comment '作成日時',
    `modified_by`    VARCHAR(36)  not null comment '更新者',
    `modified_at`    DATETIME     not null comment '更新日時',
    `is_deleted`     INT(1) not null comment '削除フラグ',
    constraint `iv002_shelf_PKC` primary key (`id`)
) comment '棚';

-- 入庫
drop table if exists `iv005_inbound` cascade;

create table `iv005_inbound`
(
    `id`                       INT(11) auto_increment not null comment '行ID',
    `inbound_code`             VARCHAR(10) not null comment '入庫コード',
    `warehouse_code`           VARCHAR(10) not null comment '倉庫コード',
    `client_code`              VARCHAR(10) not null comment '荷主コード',
    `supplier_code`            VARCHAR(10) not null comment 'サプライヤーコード',
    `request_inbound_date`     DATE        not null comment '入庫依頼日付',
    `expected_inbound_date`    DATE comment '予想入庫日付',
    `actual_inbound_date`      DATE comment '実際入庫日付',
    `inbound_status`           INT(2) not null comment '入庫ステータス:1:未処理, 2:進行中, 3:入庫済み, 4:キャンセル, 5:異常',
    `inbound_type`             INT(2) not null comment '入庫タイプ:1:通常入庫, 2:返品入庫, 3:調達入庫, 4:棚卸差異調整入庫',
    `shipment_manifest_number` VARCHAR(128) comment '配送伝票番号',
    `client_manager`           VARCHAR(128) comment '荷主側担当者',
    `warehouse_manager`        VARCHAR(128) comment '倉庫側担当者',
    `remark`                   VARCHAR(255) comment '備考',
    `created_by`               VARCHAR(36) not null comment '作成者',
    `created_at`               DATETIME    not null comment '作成日時',
    `modified_by`              VARCHAR(36) not null comment '更新者',
    `modified_at`              DATETIME    not null comment '更新日時',
    `is_deleted`               INT(1) not null comment '削除フラグ',
    constraint `iv005_inbound_PKC` primary key (`id`)
) comment '入庫';

create index `idx_warehouse_code`
    on `iv005_inbound` (`warehouse_code`);

create index `idx_inbound_code`
    on `iv005_inbound` (`inbound_code`);

-- 荷主
drop table if exists `mg003_client` cascade;

create table `mg003_client`
(
    `id`            INT(11) auto_increment not null comment '行ID',
    `client_code`   VARCHAR(10)  not null comment '荷主コード',
    `company_code`  VARCHAR(10)  not null comment '会社コード',
    `client_name`   VARCHAR(128) not null comment '荷主名称',
    `client_status` INT(2) default 3 not null comment '荷主ステータス:1:稼働中, 2:非稼働中, 3:閉鎖中',
    `created_by`    VARCHAR(128) not null comment '作成者',
    `created_at`    DATETIME     not null comment '作成日時',
    `modified_by`   VARCHAR(128) not null comment '更新者',
    `modified_at`   DATETIME     not null comment '更新日時',
    `is_deleted`    INT(11) not null comment '削除フラグ',
    constraint `mg003_client_PKC` primary key (`id`)
) comment '荷主';

-- ゾーン
drop table if exists `iv001_zone` cascade;

create table `iv001_zone`
(
    `id`             INT(11) auto_increment not null comment '行ID',
    `zone_code`      VARCHAR(10)  not null comment 'ゾーンコード',
    `warehouse_code` VARCHAR(10)  not null comment '倉庫コード',
    `zone_name`      VARCHAR(128) not null comment 'ゾーン名称',
    `zone_type`      INT(2) default 1 not null comment 'ゾーンタイプ:10:受入ゾーン, 11:検品ゾーン, 12:一時保管ゾーン, 20:棚保管ゾーン, 21:バルク保管ゾーン, 22:冷蔵ゾーン, 23:危険物保管ゾーン, 24:高価値商品ゾーン, 25:返品保管ゾーン, 30:ピッキングゾーン, 31:仕分けゾーン, 40:梱包ゾーン, 41:ラベリングゾーン, 42:出荷ゾーン',
    `remark`         VARCHAR(255) comment '備考',
    `created_by`     VARCHAR(36)  not null comment '作成者',
    `created_at`     DATETIME     not null comment '作成日時',
    `modified_by`    VARCHAR(36)  not null comment '更新者',
    `modified_at`    DATETIME     not null comment '更新日時',
    `is_deleted`     INT(1) not null comment '削除フラグ',
    constraint `iv001_zone_PKC` primary key (`id`)
) comment 'ゾーン';

-- 倉庫
drop table if exists `mg002_warehouse` cascade;

create table `mg002_warehouse`
(
    `id`               INT(11) auto_increment not null comment '行ID',
    `warehouse_code`   VARCHAR(10)  not null comment '倉庫コード',
    `company_code`     VARCHAR(10)  not null comment '会社コード',
    `warehouse_name`   VARCHAR(128) not null comment '倉庫名称',
    `warehouse_status` INT(2) default 3 not null comment '倉庫ステータス:1:稼働中, 2:非稼働中, 3:閉鎖中',
    `created_by`       VARCHAR(36)  not null comment '作成者',
    `created_at`       DATETIME     not null comment '作成日時',
    `modified_by`      VARCHAR(36)  not null comment '更新者',
    `modified_at`      DATETIME     not null comment '更新日時',
    `is_deleted`       INT(1) not null comment '削除フラグ',
    constraint `mg002_warehouse_PKC` primary key (`id`)
) comment '倉庫';

-- 会社
drop table if exists `mg001_company` cascade;

create table `mg001_company`
(
    `id`             INT(11) auto_increment not null comment '行ID',
    `company_code`   VARCHAR(10)  not null comment '会社コード',
    `company_name`   VARCHAR(128) not null comment '会社名称',
    `company_status` INT(2) default 1 not null comment '会社ステータス:1:稼働中, 2:非稼働中, 3:閉鎖中',
    `created_by`     VARCHAR(128) not null comment '作成者',
    `created_at`     DATETIME     not null comment '作成日時',
    `modified_by`    VARCHAR(128) not null comment '更新者',
    `modified_at`    DATETIME     not null comment '更新日時',
    `is_deleted`     INT(11) not null comment '削除フラグ',
    constraint `mg001_company_PKC` primary key (`id`)
) comment '会社';

-- 在庫検索ビュー
drop view if exists `vw001_inventory_search`;

create view `vw001_inventory_search` as
SELECT iv004_inventory.id,
       mg002_warehouse.warehouse_code,
       mg002_warehouse.warehouse_name,
       mg003_client.client_code,
       mg003_client.client_name,
       pd001_product.product_sku,
       pd001_product.product_name,
       pd001_product.product_type,
       iv002_shelf.shelf_code,
       iv002_shelf.shelf_name,
       iv003_shelf_unit.shelf_unit_code,
       iv003_shelf_unit.shelf_unit_name,
       iv004_inventory.actual_stock,
       iv004_inventory.available_stock,
       iv004_inventory.reserved_stock,
       iv004_inventory.back_order_stock,
       iv004_inventory.lead_time_stock,
       iv004_inventory.inventory_status,
       iv004_inventory.expiry_date,
       iv004_inventory.remark,
       iv004_inventory.is_deleted
FROM iv004_inventory
         JOIN mg002_warehouse ON iv004_inventory.warehouse_code = mg002_warehouse.warehouse_code
         JOIN mg003_client ON iv004_inventory.client_code = mg003_client.client_code
         JOIN pd001_product ON iv004_inventory.product_sku = pd001_product.product_sku
         JOIN iv003_shelf_unit ON iv004_inventory.shelf_unit_code = iv003_shelf_unit.shelf_unit_code
         JOIN iv002_shelf ON iv002_shelf.shelf_code = iv003_shelf_unit.shelf_code
;

-- 在庫移動履歴検索ビュー
drop view if exists `vw002_inventory_movement_history_search`;

create view `vw002_inventory_movement_history_search` as
SELECT il.id
     , il.warehouse_code
     , wh.warehouse_name
     , il.client_code
     , st.client_name
     , il.product_sku
     , pr.product_name
     , pr.product_type
     , il.quantity
     , su1.shelf_code
     , sh.shelf_name
     , il.source_shelf_unit_code
     , su1.shelf_unit_name AS source_shelf_unit_name
     , il.destination_shelf_unit_code
     , su2.shelf_unit_name AS destination_shelf_unit_name
     , il.operator
     , il.operation_datetime
     , il.operation_type
     , il.remark
     , il.is_deleted
FROM iv101_inventory_movement_history AS il
         INNER JOIN mg002_warehouse AS wh
                    ON il.warehouse_code = wh.warehouse_code
         INNER JOIN mg003_client AS st
                    ON il.client_code = st.client_code
         INNER JOIN pd001_product AS pr
                    ON il.product_sku = pr.product_sku
         LEFT OUTER JOIN iv003_shelf_unit AS su1
                         ON il.source_shelf_unit_code = su1.shelf_unit_code
         LEFT OUTER JOIN iv003_shelf_unit AS su2
                         ON il.destination_shelf_unit_code = su2.shelf_unit_code
         LEFT OUTER JOIN iv002_shelf AS sh
                         ON su2.shelf_code = sh.shelf_code
;

-- 商品検索ビュー
drop view if exists `vw003_product_search`;

create view `vw003_product_search` as
SELECT pd001.id
     , pd001.product_sku
     , pd001.client_code
     , mg003.client_name
     , pd001.product_name
     , pd001.product_type
     , pd001.included_tax_unit_price
     , pd001.excluded_tax_unit_price
     , pd001.tax
     , pd001.tax_rate
     , pd001.product_category_code
     , pd001.length
     , pd001.width
     , pd001.height
     , pd001.volume
     , pd001.weight
     , pd001.is_serial_number_managed
     , pd001.is_best_before_managed
     , pd001.best_before_period_days
     , pd001.product_status
     , pd001.image
     , pd001.barcode
     , pd001.qr_code
     , pd001.remark
     , pd001.is_deleted
FROM pd001_product pd001
         JOIN mg003_client mg003 ON pd001.client_code = mg003.client_code
         LEFT JOIN pd004_product_category pd004 ON pd001.product_category_code = pd004.product_category_code
;

