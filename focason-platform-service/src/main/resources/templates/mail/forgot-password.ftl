<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Password Reset Request - Focason Lab</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            color: #333;
            margin: 0;
            padding: 0;
            background-color: #f6f9fc;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background: #ffffff;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .header {
            /* 使用橙色或蓝色调强调警示和重要性 */
            background: linear-gradient(135deg, #FF9800 0%, #FF5722 100%);
            color: white;
            padding: 40px 30px;
            text-align: center;
        }
        .header h1 {
            margin: 0;
            font-size: 28px;
            font-weight: 600;
        }
        .content {
            padding: 40px 30px;
        }
        .main-text {
            font-size: 18px;
            color: #555;
            margin-bottom: 25px;
        }
        .warning-box {
            background: #fff3cd; /* 浅黄色背景 */
            border-left: 5px solid #ffc107; /* 橙色左边框 */
            border-radius: 4px;
            padding: 15px;
            margin: 25px 0;
            color: #856404;
            font-size: 15px;
        }
        .footer {
            background: #f8f9fa;
            padding: 25px 30px;
            text-align: center;
            color: #6c757d;
            font-size: 14px;
        }
        .button {
            display: inline-block;
            /* 使用强调色作为按钮背景 */
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: #ffffff!important;
            padding: 12px 30px;
            text-decoration: none;
            border-radius: 25px;
            font-weight: 600;
            margin: 20px 0;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>🔑 Password Reset Request</h1>
    </div>

    <div class="content">
        <p class="main-text"><strong>Hello,</strong></p>

        <p>
            We received a request to reset the password for your Focason Lab account.
            If you requested this change, please click the button below to set a new password.
        </p>

        <div style="text-align: center;">
            <a href="${resetLink}" class="button">
                Reset My Password
            </a>
        </div>

        <p style="text-align: center; font-size: 14px; color: #6c757d; margin-top: 10px;">
            This link will expire soon for security reasons.
        </p>

        <div class="warning-box">
            <p style="margin: 0;">
                ⚠️ **Security Alert:** If you did NOT request a password reset, please ignore this email. Your password will remain unchanged.
            </p>
        </div>

        <p style="font-size: 14px; color: #6c757d; margin-top: 25px;">
            If you have trouble clicking the button, copy and paste the following URL into your browser: <br>
            <a href="${resetLink}" style="word-break: break-all; color: #667eea; text-decoration: none; font-size: 12px;">${resetLink}</a>
        </p>
    </div>

    <div class="footer">
        <p>&copy; 2024 Focason Lab. All rights reserved.</p>
        <p>Building the future, one innovation at a time.</p>
    </div>
</div>
</body>
</html>