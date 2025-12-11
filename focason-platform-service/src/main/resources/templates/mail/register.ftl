<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Focason Lab</title>
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
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
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
        .welcome-text {
            font-size: 18px;
            color: #555;
            margin-bottom: 25px;
        }
        .verification-box {
            background: #f8f9fa;
            border: 2px dashed #dee2e6;
            border-radius: 8px;
            padding: 20px;
            text-align: center;
            margin: 25px 0;
        }
        .verification-code {
            font-size: 32px;
            font-weight: bold;
            color: #667eea;
            letter-spacing: 3px;
            margin: 10px 0;
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
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: #ffffff!important;
            padding: 12px 30px;
            text-decoration: none;
            border-radius: 25px;
            font-weight: 600;
            margin: 20px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>🎉 Welcome to Focason Lab!</h1>
    </div>

    <div class="content">
        <p class="welcome-text"><strong>Hello Friend!</strong>,</p>
        <p>We're thrilled to have you join our community! Your account has been successfully created.</p>

        <div class="verification-box">
            <p style="margin: 0 0 15px 0; color: #6c757d;">Your verification code:</p>
            <div class="verification-code">${verificationCode}</div>
            <p style="margin: 15px 0 0 0; color: #6c757d; font-size: 14px;">
                This code will expire in 10 minutes
            </p>
        </div>

        <p>Use the verification code above to complete your email verification process.</p>

        <p style="font-size: 14px; color: #6c757d; margin-top: 25px;">
            If you didn't create an account with us, please ignore this email.
        </p>
    </div>

    <div class="footer">
        <p>&copy; 2024 Focason Lab. All rights reserved.</p>
        <p>Building the future, one innovation at a time.</p>
    </div>
</div>
</body>
</html>