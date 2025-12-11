-- iv001_zone
insert into focason.iv001_zone(id, zone_code, warehouse_code, zone_name, zone_type, remark, created_by, created_at,
                               modified_by, modified_at, is_deleted)
values (2, N'N-A-02', N'y4a5jkzlnd', N'普通A', 1, null, N'Focason Lab Team', TIMESTAMP '2025-03-02 15:10:54.000',
        N'Focason Lab Team', TIMESTAMP '2025-03-02 16:11:16.000', 0)
     , (3, N'N-A-03', N'y4a5jkzlnd', N'test', 1, null, N'Focason Lab Team', TIMESTAMP '2025-03-02 20:47:51.000',
        N'Focason Lab Team', TIMESTAMP '2025-03-02 20:47:51.000', 0)
     , (4, N'RECV-01', N'lr9m6ndje5', N'普通B', 1, null, N'Focason Lab Team',
        TIMESTAMP '2025-03-02 21:52:00.000', N'Focason Lab Team', TIMESTAMP '2025-03-02 21:52:00.000', 0)
     , (5, N'INS-01', N'lr9m6ndje5', N'普通C', 2, null, N'Focason Lab Team', TIMESTAMP '2025-03-02 21:52:26.000',
        N'Focason Lab Team', TIMESTAMP '2025-03-02 21:52:26.000', 0)
;
-- mg001_company
insert into focason.mg001_company(id, company_code, company_name, company_status, created_by, created_at, modified_by,
                                  modified_at, is_deleted)
values (1, N'ss6zsu0qny', N'株式会社Focason', 3, N'Focason Lab Team', TIMESTAMP '2025-03-02 14:46:08.000',
        N'Focason Lab Team', TIMESTAMP '2025-03-02 14:46:08.000', 0)
;

-- mg002_warehouse
insert into focason.mg002_warehouse(id, warehouse_code, company_code, warehouse_name, warehouse_status, created_by,
                                    created_at, modified_by, modified_at, is_deleted)
values (4, N'lr9m6ndje5', N'ss6zsu0qny', N'東京倉庫', 1, N'Focason Lab Team', TIMESTAMP '2025-03-02 14:46:59.000',
        N'Focason Lab Team', TIMESTAMP '2025-03-02 14:53:39.000', 0)
     , (5, N'y4a5jkzlnd', N'ss6zsu0qny', N'千葉倉庫', 1, N'Focason Lab Team', TIMESTAMP '2025-03-02 14:54:33.000',
        N'Focason Lab Team', TIMESTAMP '2025-03-02 14:54:33.000', 0)
;

-- mg003_client
insert into focason.mg003_client(id, client_code, company_code, client_name, client_status, created_by, created_at,
                                 modified_by, modified_at, is_deleted)
values (2, N'2s6flpsk3d', N'ss6zsu0qny', N'Amazon Store', 1, N'Focason Lab Team',
        TIMESTAMP '2025-03-02 14:48:05.000', N'Focason Lab Team', TIMESTAMP '2025-03-02 14:48:05.000', 0)
     , (3, N'ns0qxr5z3m', N'ss6zsu0qny', N'Rakuten', 1, N'Focason Lab Team', TIMESTAMP '2025-03-02 14:54:49.000',
        N'Focason Lab Team', TIMESTAMP '2025-03-02 14:54:49.000', 0)
;

-- mg004_warehouse_client
insert into focason.mg004_warehouse_client(id, warehouse_code, client_code, created_by, created_at, modified_by,
                                           modified_at, is_deleted)
values (1, N'lr9m6ndje5', N'2s6flpsk3d', N'Focason Lab Team', TIMESTAMP '2025-03-02 14:53:39.000',
        N'Focason Lab Team', TIMESTAMP '2025-03-02 14:53:39.000', 0)
     , (2, N'y4a5jkzlnd', N'ns0qxr5z3m', N'Focason Lab Team', TIMESTAMP '2025-03-02 14:54:49.000',
        N'Focason Lab Team', TIMESTAMP '2025-03-02 14:54:49.000', 0)
;

-- mg005_user
insert into focason.mg005_user(id, uuid, username, email, password, company_code, phone, type, verification_code,
                               is_verified, created_by, created_at, modified_by, modified_at, is_deleted)
values (1, N'3810124a-cc43-4d00-85da-73a0417edbda', N'Guest', N'Focason Lab Team',
        N'$2a$10$UuCURPCwRx/C8qYetDqVbeapGBIFshDFajER77Z4X4kep21lPVqX.', N'ss6zsu0qny', null, 1, null, 1, N'ADMIN',
        TIMESTAMP '2025-03-02 14:42:23.000', N'ADMIN', TIMESTAMP '2025-03-02 14:42:23.000', 0)
;

-- mg006_warehouse_user
insert into focason.mg006_warehouse_user(id, uuid, warehouse_code, created_by, created_at, modified_by, modified_at,
                                         is_deleted)
values (1, N'3810124a-cc43-4d00-85da-73a0417edbda', N'lr9m6ndje5', N'ADMIN', TIMESTAMP '2025-03-02 14:48:59.000',
        N'ADMIN', TIMESTAMP '2025-03-02 14:48:59.000', 0)
     , (2, N'3810124a-cc43-4d00-85da-73a0417edbda', N'y4a5jkzlnd', N'ADMIN', TIMESTAMP '2025-03-02 16:40:02.000',
        N'ADMIN', TIMESTAMP '2025-03-02 16:40:02.000', 0)
;

-- mg007_supplier
insert into focason.mg007_supplier(id, supplier_code, client_code, supplier_name, created_by, created_at, modified_by,
                                   modified_at, is_deleted)
values (1, N'14qxz58euc', N'2s6flpsk3d', N'test', N'Focason Lab Team', TIMESTAMP '2025-03-02 20:30:22.000',
        N'Focason Lab Team', TIMESTAMP '2025-03-02 20:30:22.000', 0)
;

-- pd001_product
insert into focason.pd001_product(id, product_sku, client_code, product_name, product_type, included_tax_unit_price,
                                  excluded_tax_unit_price, tax, tax_rate, product_category_code, length, width, height,
                                  volume, weight, is_serial_number_managed, is_best_before_managed,
                                  best_before_period_days, product_status, image, barcode, qr_code, remark, created_by,
                                  created_at, modified_by, modified_at, is_deleted)
values (1, N'test', N'2s6flpsk3d', N'dddd', 1, 0.00, 0.00, 0.00, 0, null, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 0, 0, 1,
        null, null, null, null, N'Focason Lab Team', TIMESTAMP '2025-03-03 22:37:43.000', N'Focason Lab Team',
        TIMESTAMP '2025-03-03 22:37:43.000', 0)
;
