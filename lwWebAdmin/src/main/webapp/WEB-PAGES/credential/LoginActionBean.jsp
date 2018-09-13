<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

        <title>Living World Web Admin - Login</title>

        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300&subset=latin" rel="stylesheet" type="text/css">
        <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/WEB-PAGES/assets/images/ireload-engine-32.png" sizes="32x32">

        <!-- DEMO ONLY: Function for the proper stylesheet loading according to the demo settings -->
        <script>
            function _pxDemo_loadStylesheet(a, b, c){var c = c || decodeURIComponent((new RegExp(";\\s*" + encodeURIComponent("px-demo-theme") + "\\s*=\\s*([^;]+)\\s*;", "g").exec(";" + document.cookie + ";") || [])[1] || "clean"), d = "rtl" === document.getElementsByTagName("html")[0].getAttribute("dir"); document.write(a.replace(/^(.*?)((?:\.min)?\.css)$/, '<link href="$1' + (c.indexOf("dark") !== - 1 && a.indexOf("/css/") !== - 1 && a.indexOf("/themes/") === - 1?"-dark":"") + (!d || 0 !== a.indexOf("${pageContext.request.contextPath}/WEB-PAGES/assets/css") && 0 !== a.indexOf("${pageContext.request.contextPath}/WEB-PAGES/assets/demo")?"":".rtl") + '$2" rel="stylesheet" type="text/css"' + (b?'class="' + b + '"':"") + ">"))}
        </script>

        <!-- DEMO ONLY: Set RTL direction -->
        <script>"ltr" !== document.getElementsByTagName("html")[0].getAttribute("dir") && "1" === decodeURIComponent((new RegExp(";\\s*" + encodeURIComponent("px-demo-rtl") + "\\s*=\\s*([^;]+)\\s*;", "g").exec(";" + document.cookie + ";") || [])[1] || "0") && document.getElementsByTagName("html")[0].setAttribute("dir", "rtl");</script>

        <!-- DEMO ONLY: Load PixelAdmin core stylesheets -->
        <script>
            _pxDemo_loadStylesheet('${pageContext.request.contextPath}/WEB-PAGES/assets/css/bootstrap.min.css', 'px-demo-stylesheet-bs');
            _pxDemo_loadStylesheet('${pageContext.request.contextPath}/WEB-PAGES/assets/css/pixeladmin.min.css', 'px-demo-stylesheet-core');
        </script>

        <!-- DEMO ONLY: Load theme -->
        <script>
            _pxDemo_loadStylesheet("${pageContext.request.contextPath}/WEB-PAGES/assets/css/themes/silver.min.css", "px-demo-stylesheet-theme", "silver");
        </script>

        <!-- Demo assets -->
        <script>_pxDemo_loadStylesheet('${pageContext.request.contextPath}/WEB-PAGES/assets/demo/demo.css');</script>
        <!-- / Demo assets -->

        <!-- Pace.js -->
        <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/pace/pace.min.js"></script>

        <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/demo/demo.js"></script>

        <!-- Custom styling -->
        <style>
            .page-signin-modal {
                position: relative;
                top: auto;
                right: auto;
                bottom: auto;
                left: auto;
                z-index: 1;
                display: block;
            }

            .page-signin-form-group { position: relative; }

            .page-signin-icon {
                position: absolute;
                line-height: 21px;
                width: 36px;
                border-color: rgba(0, 0, 0, .14);
                border-right-width: 1px;
                border-right-style: solid;
                left: 1px;
                top: 13px;
                text-align: center;
                font-size: 16px;
            }

            html[dir="rtl"] .page-signin-icon {
                border-right: 0;
                border-left-width: 1px;
                border-left-style: solid;
                left: auto;
                right: 1px;
            }

            html:not([dir="rtl"]) .page-signin-icon + .page-signin-form-control { padding-left: 50px; }
            html[dir="rtl"] .page-signin-icon + .page-signin-form-control { padding-right: 50px; }

            #page-signin-forgot-form {
                display: none;
            }

            /* Margins */

            .page-signin-modal > .modal-dialog { margin: 30px 10px; }

            @media (min-width: 544px) {
                .page-signin-modal > .modal-dialog { margin: 60px auto; }
            }
        </style>
        <!-- / Custom styling -->
    </head>
    <body>
        <div class="page-signin-modal modal">
            <div class="modal-dialog">
                <div class="modal-content col-md-8" style="margin-left: 90px">
                    <div class="box m-a-0">
                        <div class="box-row">

                            <div class="box-cell col-md-7">

                                <!-- Sign In form -->

                                <s:form class="p-a-4" id="page-signin-form" beanclass="id.co.icg.ie.web.credential.LoginActionBean">
                                    <div style="text-align:center">
                                        <img src="${pageContext.request.contextPath}/WEB-PAGES/assets/images/ireload-engine-32.png" class="" style="width: auto!important;">
                                    </div>
                                    <h4 class="m-t-0 m-b-4 text-xs-center font-weight-semibold">Log In to your Account</h4>

                                    <fieldset class="page-signin-form-group form-group form-group-lg">
                                        <div class="page-signin-icon text-muted"><i class="ion-person"></i></div>
                                        <input type="text" name="username" class="page-signin-form-control form-control" placeholder="Username">
                                    </fieldset>

                                    <fieldset class="page-signin-form-group form-group form-group-lg">
                                        <div class="page-signin-icon text-muted"><i class="ion-asterisk"></i></div>
                                        <input type="password" name="password" class="page-signin-form-control form-control" placeholder="Password">
                                    </fieldset>

                                    <div class="clearfix">
                                        <a href="#" class="font-size-12 text-muted pull-xs-right" id="page-signin-forgot-link">Forgot your password?</a>
                                    </div>

                                    <s:submit class="btn btn-block btn-lg btn-primary m-t-3" name="login" value="Login"/>
                                </s:form>     

                                <!-- / Sign In form -->

                                <!-- Reset form -->

                                <s:form class="p-a-4" id="page-signin-forgot-form" beanclass="id.co.icg.ie.web.credential.LoginActionBean">
                                    <h4 class="m-t-0 m-b-4 text-xs-center font-weight-semibold">Password reset</h4>

                                    <fieldset class="page-signin-form-group form-group form-group-lg">
                                        <div class="page-signin-icon text-muted"><i class="ion-person"></i></div>
                                        <input type="text" name="username" class="page-signin-form-control form-control" placeholder="Your User Name">
                                    </fieldset>

                                    <fieldset class="page-signin-form-group form-group form-group-lg">
                                        <div class="page-signin-icon text-muted"><i class="ion-android-phone-portrait"></i></div>
                                        <input type="text" name="phone" class="page-signin-form-control form-control" placeholder="Your Phone">
                                    </fieldset>

                                    <button type="submit" name="reset" class="btn btn-block btn-lg btn-primary m-t-3">Send New Password</button>
                                    <div class="m-t-2 text-muted">
                                        <a href="#" id="page-signin-forgot-back">&larr; Back</a>
                                    </div>
                                </s:form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <s:messages/>
        <s:errors/>

        <!-- ==============================================================================
        |
        |  SCRIPTS
        |
        =============================================================================== -->

        <!-- jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/js/pixeladmin.min.js"></script>

        <script>
            // -------------------------------------------------------------------------
            // Initialize DEMO sidebar

            $(function() {
            pxDemo.initializeDemo();
            });
        </script>

        <script>
            // -------------------------------------------------------------------------
            // Initialize page components

            $(function() {
                pxDemo.initializeBgsDemo('body', 1, '#000', function(isBgSet) {
                    $('#px-demo-signup-link, #px-demo-signup-link a')
                        .addClass(isBgSet ? 'text-white' : 'text-muted')
                        .removeClass(isBgSet ? 'text-muted' : 'text-white');
                });
                $('#page-signin-forgot-link').on('click', function(e) {
                    e.preventDefault();
                    $('#page-signin-form, #page-signin-social')
                        .css({ opacity: '1' })
                        .animate({ opacity: '0' }, 200, function() {
                            $(this).hide();
                            $('#page-signin-forgot-form')
                                .css({ opacity: '0', display: 'block' })
                                .animate({ opacity: '1' }, 200)
                                .find('.form-control').first().focus();
                            $(window).trigger('resize');
                        });
                });
                $('#page-signin-forgot-back').on('click', function(e) {
                    e.preventDefault();
                    $('#page-signin-forgot-form')
                        .animate({ opacity: '0' }, 200, function() {
                            $(this).css({ display: 'none' });
                            $('#page-signin-form, #page-signin-social')
                                .show()
                                .animate({ opacity: '1' }, 200)
                                .find('.form-control').first().focus();
                            $(window).trigger('resize');
                        });
                });
            });

            $(function () {
                $('#modal-success').modal();
                $('#modal-warning').modal();
            });

            $('.confirm').click(function (e, params) {
                var localParams = params || {};

                if (!localParams.send) {
                  e.preventDefault();
                }
                swal({
                    title: "Are you sure?",
                    type: "warning",
                    width: 300,
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes!",
                    closeOnConfirm: false
                }, function(isConfirm){
                    if (isConfirm) {
                        $(e.currentTarget).trigger(e.type, {'send': true});
                    }
                });
            });
        </script>
    </body>
</html>
