<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

        <title>Living World Web Admin - Page Not Found</title>

        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300&subset=latin" rel="stylesheet" type="text/css">
        <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/WEB-PAGES/assets/images/ireload-engine-32.png" sizes="32x32">

        <!-- DEMO ONLY: Function for the proper stylesheet loading according to the demo settings -->
        <script>function _pxDemo_loadStylesheet(a, b, c){var c = c || decodeURIComponent((new RegExp(";\\s*" + encodeURIComponent("px-demo-theme") + "\\s*=\\s*([^;]+)\\s*;", "g").exec(";" + document.cookie + ";") || [])[1] || "clean"), d = "rtl" === document.getElementsByTagName("html")[0].getAttribute("dir"); document.write(a.replace(/^(.*?)((?:\.min)?\.css)$/, '<link href="$1' + (c.indexOf("dark") !== - 1 && a.indexOf("/css/") !== - 1 && a.indexOf("/themes/") === - 1?"-dark":"") + (!d || 0 !== a.indexOf("${pageContext.request.contextPath}/WEB-PAGES/assets/css") && 0 !== a.indexOf("${pageContext.request.contextPath}/WEB-PAGES/assets/demo")?"":".rtl") + '$2" rel="stylesheet" type="text/css"' + (b?'class="' + b + '"':"") + ">"))}</script>

        <!-- DEMO ONLY: Set RTL direction -->
        <script>"ltr" !== document.getElementsByTagName("html")[0].getAttribute("dir") && "1" === decodeURIComponent((new RegExp(";\\s*" + encodeURIComponent("px-demo-rtl") + "\\s*=\\s*([^;]+)\\s*;", "g").exec(";" + document.cookie + ";") || [])[1] || "0") && document.getElementsByTagName("html")[0].setAttribute("dir", "rtl");</script>

        <!-- DEMO ONLY: Load PixelAdmin core stylesheets -->
        <script>
            _pxDemo_loadStylesheet('${pageContext.request.contextPath}/WEB-PAGES/assets/css/bootstrap.min.css', 'px-demo-stylesheet-bs');
            _pxDemo_loadStylesheet('${pageContext.request.contextPath}/WEB-PAGES/assets/css/pixeladmin.min.css', 'px-demo-stylesheet-core');
        </script>

        <!-- DEMO ONLY: Load theme -->
        <script>
            _pxDemo_loadStylesheet("${pageContext.request.contextPath}/WEB-PAGES/assets/css/themes/default.min.css", "px-demo-stylesheet-theme", "defult");
        </script>

        <!-- Demo assets -->
        <script>_pxDemo_loadStylesheet('${pageContext.request.contextPath}/WEB-PAGES/assets/demo/demo.css');</script>
        <!-- / Demo assets -->

        <!-- Pace.js -->
        <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/pace/pace.min.js"></script>

        <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/demo/demo.js"></script>

        <!-- Custom styling -->
        <style>
            .page-404-bg {
                position: absolute;
                top: 0;
                right: 0;
                bottom: 0;
                left: 0;
            }

            .page-404-header,
            .page-404-error-code,
            .page-404-subheader,
            .page-404-text,
            .page-404-form {
                position: relative;

                padding: 0 30px;

                text-align: center;
            }

            .page-404-header {
                width: 100%;
                padding: 20px 0;

                box-shadow: 0 4px 0 rgba(0,0,0,.1);
            }

            .page-404-error-code {
                margin-top: 60px;

                color: #fff;
                text-shadow: 0 4px 0 rgba(0,0,0,.1);

                font-size: 120px;
                font-weight: 700;
                line-height: 140px;
            }

            .page-404-subheader,
            .page-404-text {
                margin-bottom: 60px;

                color: rgba(0,0,0,.5);

                font-weight: 600;
            }

            .page-404-subheader {
                font-size: 50px;
            }

            .page-404-subheader:after {
                position: absolute;
                bottom: -30px;
                left: 50%;

                display: block;

                width: 40px;
                height: 4px;
                margin-left: -20px;

                content: "";

                background: rgba(0,0,0,.2);
            }

            .page-404-text {
                font-size: 20px;
            }

            .page-404-form {
                max-width: 500px;
                margin: 0 auto 60px auto;
            }

            .page-404-form * {
                margin: 0 !important;

                border: none !important;
            }

            .page-404-form .btn {
                background: rgba(0, 0, 0, .3);
            }
        </style>
        <!-- / Custom styling -->
    </head>
    <body>
        <div class="page-404-bg bg-warning"></div>
        <div class="page-404-header bg-white">
            <s:link class="px-demo-brand px-demo-brand-lg text-default" beanclass="id.co.icg.ie.web.credential.WelcomeActionBean">
                <img src="${pageContext.request.contextPath}/WEB-PAGES/assets/images/ireload-engine-32.png" style="width:32px; height:32px; background-color: white;border-radius: 16px;"></img> 
                iReload Engine
            </s:link>
        </div>
        <h1 class="page-404-error-code"><strong>404</strong></h1>
        <h2 class="page-404-subheader">OOPS!</h2>
        <h3 class="page-404-text">
            SOMETHING WENT WRONG, OR THAT PAGE DOESN'T EXIST... YET
        </h3>

        <!-- ==============================================================================
        |
        |  SCRIPTS
        |
        =============================================================================== -->

        <!-- jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/js/pixeladmin.min.js"></script>

    </body>
</html>
