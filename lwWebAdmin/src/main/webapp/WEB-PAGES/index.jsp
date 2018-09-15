
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-definition>
    <html>
        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
            <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

            <title>${title}</title>

            <link href="${pageContext.request.contextPath}/WEB-PAGES/assets/css/font-opensans.css" rel="stylesheet" type="text/css">
            <link href="${pageContext.request.contextPath}/WEB-PAGES/assets/css/ionicons-2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css">
            <link href="${pageContext.request.contextPath}/WEB-PAGES/assets/css/font-awesome-5.0.1/css/fontawesome-all.min.css" rel="stylesheet" integrity="">
            <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/WEB-PAGES/assets/images/ireload-engine-32.png" sizes="32x32">

            <!-- DEMO ONLY: Function for the proper stylesheet loading according to the demo settings -->
            <script>function _pxDemo_loadStylesheet(a, b, c){var c = c || decodeURIComponent((new RegExp(";\\s*" + encodeURIComponent("px-demo-theme") + "\\s*=\\s*([^;]+)\\s*;", "g").exec(";" + document.cookie + ";") || [])[1] || "clean"), d = "rtl" === document.getElementsByTagName("html")[0].getAttribute("dir"); document.write(a.replace(/^(.*?)((?:\.min)?\.css)$/, '<link href="$1' + (c.indexOf("dark") !== - 1 && a.indexOf("/css/") !== - 1 && a.indexOf("/themes/") === - 1?"-dark":"") + (!d || 0 !== a.indexOf("${pageContext.request.contextPath}/WEB-PAGES/assets/css") && 0 !== a.indexOf("${pageContext.request.contextPath}/WEB-PAGES/assets/demo")?"":".rtl") + '$2" rel="stylesheet" type="text/css"' + (b?'class="' + b + '"':"") + ">"))}</script>

            <!-- DEMO ONLY: Set RTL direction -->
            <script>"ltr" !== document.getElementsByTagName("html")[0].getAttribute("dir") && "1" === decodeURIComponent((new RegExp(";\\s*" + encodeURIComponent("px-demo-rtl") + "\\s*=\\s*([^;]+)\\s*;", "g").exec(";" + document.cookie + ";") || [])[1] || "0") && document.getElementsByTagName("html")[0].setAttribute("dir", "rtl");</script>

            <!-- DEMO ONLY: Load PixelAdmin core stylesheets -->
            <script>
                _pxDemo_loadStylesheet('${pageContext.request.contextPath}/WEB-PAGES/assets/css/bootstrap.min.css', 'px-demo-stylesheet-bs');
                _pxDemo_loadStylesheet('${pageContext.request.contextPath}/WEB-PAGES/assets/css/pixeladmin.min.css', 'px-demo-stylesheet-core');
                _pxDemo_loadStylesheet('${pageContext.request.contextPath}/WEB-PAGES/assets/css/widgets.min.css', 'px-demo-stylesheet-widgets');
            </script>

            <!-- DEMO ONLY: Load theme -->
            <script>
                _pxDemo_loadStylesheet("${pageContext.request.contextPath}/WEB-PAGES/assets/css/themes/silver.min.css", "px-demo-stylesheet-theme", "silver");
            </script>

            <!-- Demo assets -->
            <script>_pxDemo_loadStylesheet('${pageContext.request.contextPath}/WEB-PAGES/assets/demo/demo.css');</script>
            <!-- / Demo assets -->

            <!-- holder.js -->
            <script type="text/javascript" src="${pageContext.request.contextPath}/WEB-PAGES/assets/js/holder.js"></script>

            <!-- Pace.js -->
            <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/pace/pace.min.js"></script>
            <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/demo/demo.js"></script>

            <!-- jQuery -->
            <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->
            <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/js/jquery.min.js"></script>

            <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/js/pixeladmin.min.js"></script>

            <!-- sweetalert.js -->
            <script>_pxDemo_loadStylesheet('${pageContext.request.contextPath}/WEB-PAGES/assets/css/sweetalert/sweetalert.css');</script>
            <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/js/sweetalert/sweetalert.min.js"></script>

            <!-- chosen.jquery.js -->
            <link href="${pageContext.request.contextPath}/WEB-PAGES/assets/css/chosen/bootstrap-chosen.css" rel="stylesheet">
            <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/js/chosen/chosen.jquery.js"></script>

            <!-- jquery.autocomplete.js -->
            <link href="${pageContext.request.contextPath}/WEB-PAGES/assets/css/jquery.autocomplete.css" rel="stylesheet">
            <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/js/jquery.autocomplete.js"></script>

            <script>_pxDemo_loadStylesheet('${pageContext.request.contextPath}/WEB-PAGES/assets/css/bootcomplete.css');</script>
            <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/js/jquery.bootcomplete.js"></script>
            <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/js/general.js"></script>
            <script src="${pageContext.request.contextPath}/WEB-PAGES/assets/js/date.format.js"></script>

            <!-- Custom styling -->
            <style>
                .page-header-form .input-group-addon,
                .page-header-form .form-control {
                    background: rgba(0,0,0,.05);
                }
            </style>
            <!-- / Custom styling -->
        </head>
        <body>
            <nav class="px-nav px-nav-left">
                <button type="button" class="px-nav-toggle" data-toggle="px-nav">
                    <span class="px-nav-toggle-arrow"></span>
                    <span class="navbar-toggle-icon"></span>
                    <span class="px-nav-toggle-label font-size-11">HIDE MENU</span>
                </button>

                <ul class="px-nav-content">
                    <li class="px-nav-box p-a-3 b-b-1" id="demo-px-nav-box">
                        <button type="button" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <img src="${pageContext.request.contextPath}/WEB-PAGES/assets/demo/avatars/1.jpg" alt="" class="pull-xs-left m-r-2 border-round" style="width: 54px;height: 54px;margin-top: 8px;">
                        <div class="font-size-15"><span class="font-weight-light">Welcome, </span><br/><strong>${actionBean.userSession.fullName}</strong></div>
                        <div class="btn-group" style="margin-top: 4px;">
                            <s:link beanclass="id.co.icg.lw.web.user.MyAccountActionBean" class="btn btn-xs btn-danger btn-outline"><i class="fa fa-user"></i></s:link>
                            <s:link beanclass="id.co.icg.lw.web.credential.LoginActionBean" class="btn btn-xs btn-danger btn-outline" event="logout"><i class="fa fa-power-off"></i></s:link>
                        </div>
                    </li>
                    ${actionBean.link}
                </ul>
            </nav>

            <nav class="navbar px-navbar">
                <!-- Header -->
                <div class="navbar-header">
                    <a class="navbar-brand px-demo-brand" href="index.html">
                        <img src="${pageContext.request.contextPath}/WEB-PAGES/assets/images/ireload-engine-32.png" class="px-demo-logo" style="width: auto!important;"></img> 
                        Living World Web Admin</a>
                </div>

                <!-- Navbar togglers -->
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#px-demo-navbar-collapse" aria-expanded="false"><i class="navbar-toggle-icon"></i></button>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="px-demo-navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a class="font-size-13" href="" role="button" aria-haspopup="true" aria-expanded="false" id="now-datetime">05 Dec 2017 | 03:28:20</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                <img src="${pageContext.request.contextPath}/WEB-PAGES/assets/demo/avatars/1.jpg" alt="" class="px-navbar-image">
                                <span class="hidden-md" id="user-fullname">${actionBean.userSession.fullName}</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <s:link beanclass="id.co.icg.lw.web.user.MyAccountActionBean"><span class="label label-warning pull-xs-right"><i class="fa fa-user"></i></span>Profile</s:link>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <s:link beanclass="id.co.icg.lw.web.credential.LoginActionBean" event="logout">
                                        <i class="dropdown-icon fa fa-power-off"></i>&nbsp;&nbsp;Log Out
                                    </s:link>
                                </li>
                            </ul>
                        </li>

                    </ul>
                </div><!-- /.navbar-collapse -->
            </nav>

            <div class="px-content">
                <div class="page-header">
                    <div class="row">
                        <div class="col-md-4 text-xs-center text-md-left text-nowrap">
                            <h1>
                                <span class="text-muted font-weight-light">
                                    <i class="page-header-icon ion-arrow-right-b"></i>
                                </span>
                                ${title}
                            </h1>
                        </div>
                    </div>
                </div>
                <s:layout-component name="contents" />
            </div>

            <footer class="px-footer px-footer-bottom p-t-1 p-b-1">
                <span class="text-muted">Copyright � 2018 Living World</span>
            </footer>
            <s:messages/>
            <s:errors/>

            <script>
                // -------------------------------------------------------------------------
                // Initialize DEMO sidebar

                $(function() {
                    pxDemo.initializeDemo();
                });
            </script>

            <script type="text/javascript">
                // -------------------------------------------------------------------------
                // Initialize DEMO

                $(function() {
                var file = String(document.location).split('/').pop();
                // Remove unnecessary file parts
                file = file.replace(/(\.html).*/i, '$1');
                if (!/.html$/i.test(file)) {
                    file = 'index.html';
                }

                // Activate current nav item
                $('body > .px-nav')
                        .find('.px-nav-item > a[href="' + file + '"]')
                        .parent()
                        .addClass('active');
                $('body > .px-nav').pxNav();
                $('body > .px-footer').pxFooter();
                $('#navbar-notifications').perfectScrollbar();
                $('#navbar-messages').perfectScrollbar();
                });
            </script>

            <script type="text/javascript">
                $('.date').datepicker({
                    todayBtn: "linked",
                    keyboardNavigation: false,
                    forceParse: false,
                    calendarWeeks: true,
                    autoclose: true
                });

                // Modal Success & Error
                $(function () {
                    $('#modal-success').modal();
                    $('#modal-warning').modal();
                });

                $('.chosen-select').chosen({width: "100%"});

                $('.confirm').click(function (e, params) {
                    var localParams = params || {};
                    var href;

                    if (!localParams.send || e.currentTarget.form==undefined) {
                        href = $(this).attr('href');
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
                            if(e.currentTarget.form==undefined){
                                window.location.href = href;
                            } else {
                                $(e.currentTarget).trigger(e.type, {'send': true});
                            }
                        }
                    });
                });
            </script>
        </body>
    </html>
</s:layout-definition>