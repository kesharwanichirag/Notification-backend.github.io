<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .container{
            border-radius: 10px;
            background-image: linear-gradient( #95cfee,#f2fcfe);
            font-family: Arial, Helvetica, sans-serif;
            text-align: center;
            height: 55vh;
        }

        .header{
            width: 50%;
            border: 1px solid #705ce6;
            margin-left: 25%;
            background-color: #705ce6;
            color: #fff;
            padding: 10px 20px;
            font-size: 20px;
            font-weight: 400;
            border-top-left-radius: 20px;
            border-top-right-radius: 20px;
            box-shadow: 17px 0 10px -2px #eee;
        }

        .text-body{
            width: 50%;
            margin-left: 25%;
            border: 1px solid #ccc;
            padding: 5px 20px;
            box-shadow: 17px 15px 10px -2px #eee;

        }

        .msg{
            color: #555;
        }

        .otp{
            background-color: #fff;
            display:inline-block;
            font-size: 30px;
            padding: 10px 40px;
            border-radius: 10px;
            border: 1px solid #555;
            color: #333;
            letter-spacing: 9px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h3>
                Notification App Email
            </h3>
        </div>
        <div class="text-body">
            <h4 class="msg">Hi ${userName} To Authenticate Enter The Given OTP(One Time Password)</h4>

            <h3 class="otp">
                ${OtpMessage}
            </h3>
            <h5 class="msg">This OTP is valid for 30 Minutes</h5>
        </div>
        </div>
    </div>
</body>
</html>