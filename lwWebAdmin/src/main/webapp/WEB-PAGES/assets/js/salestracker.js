/*
 * Refer to:
 * https://googlemaps.github.io/js-marker-clusterer/examples/simple_example.html
 * https://googlemaps.github.io/js-marker-clusterer/examples/advanced_example.html
 * 
 * Source:
 * https://github.com/googlemaps/js-marker-clusterer
 */

/* global google */

var countForQuery=[];
var dataSalesforces;
var dataDistributors;
var dataMapClusterer;
var date;
var imageSalesUrl = '../WEB-PAGES/assets/images/sales_24.png';
var imgCircle ={
    path: google.maps.SymbolPath.CIRCLE,
    fillColor: 'red',
    fillOpacity: .4,
    scale: 4.5,
    strokeColor: 'white',
    strokeWeight: 1
};

var date_diff_indays = function(date1, date2) {
    dt1 = new Date(date1);
    dt2 = new Date(date2);
    return Math.floor((Date.UTC(dt2.getFullYear(), dt2.getMonth(), dt2.getDate()) - Date.UTC(dt1.getFullYear(), dt1.getMonth(), dt1.getDate()) ) /(1000 * 60 * 60 * 24));
};

$(document).ready(function () {
    $("#navbar-title-top").show();
    $(".content-header").hide();
    $(".content").css("padding-bottom","5px");
    $("body").addClass("sidebar-collapse");
    $("#map").width($(".ibox").width() * 0.97);
    hWrapper=$("body").height() - $(".navbar-static-top").height() - $(".footer").height() - 115;
    $("#map").css("min-height",hWrapper);
    window.onresize = function (event) {
        hWrapper=$("body").height() - $(".navbar-static-top").height() - $(".footer").height() - 115;
        $("#map").css("min-height",hWrapper);
    };

    initSettings();
    from = $('#dateFrom').val();
    to = $('#dateTo').val();
    pathCoords = $.parseJSON(getDataSalesTracking($('#select-sales').val(), from, to));
    google.maps.event.addDomListener(window, 'load', initialize);
});

function updateSettings() {
    if ($('#dateFrom').val() === '') {
        swal({
            title: "Invalid Date Input",
            text: "Please fill the date first before update.",
            confirmButtonColor: "#DD6B55"
        }, function () {
            $('#modFilter').modal('show');
        });
    } else {
        from = $('#dateFrom').val();
        if($('#dateTo').val()===""||$('#dateTo').val()===undefined) to = from;
        else to = $('#dateTo').val();
        pathCoords = $.parseJSON(getDataSalesTracking($('#select-sales').val(), from, to));
        if((pathCoords!==undefined&&pathCoords.length>0)){
            initialize();
        } else {
            swal({
                title: "No Data",
                text: "No data to show, please select another option.",
                confirmButtonColor: "#DD6B55"
            }, null);
        }
    }
}

function initSettings() {
    dataDistributors = getDataDistributors();
    $("#select-distributor").append($('<option>',{value: "", text: "(ALL)"}));
    $.each(dataDistributors, function(index, item){
        $("#select-distributor").append($('<option>',{value: item.id.distributorId, text: item.id.distributorName}));
    });
    $('#select-distributor').chosen({width: "100%"});
    $('#select-distributor').on("change", function(data){
        dataSalesforces = getDataSalesforces($(this).val());
        $('#select-sales').chosen('destroy');
        $("#select-sales").html('');
        $("#select-sales").append($('<option>',{value: "", text: "(ALL)"}));
        $.each(dataSalesforces, function(index, item){
            $("#select-sales").append($('<option>',{value: item.id, text: item.username}));
        });
        $('#select-sales').chosen({width: "100%"});
    });

//    dataSalesforces = getDataSalesforces();
//    $("#select-sales").append($('<option>',{value: "", text: "(ALL)"}));
//    $.each(dataSalesforces, function(index, item){
//        $("#select-sales").append($('<option>',{value: item.id, text: item.username}));
//    });
    $('#select-sales').chosen({width: "100%"});

    $('#dateFrom').val(dateFormat(new Date(), "mm/dd/yyyy"));
    $('#dateTo').val(dateFormat(new Date(), "mm/dd/yyyy"));
}

function getDataSalesTracking(sales, timeFrom, timeTo) {
    var tmp;
    var param = "";
    if (sales !== null && sales !== undefined)
        param += "&salesId=" + sales;
    if (timeFrom !== null && timeFrom !== undefined) {
        param += "&dateFrom=" + timeFrom;
        if (timeTo !== null && timeTo !== undefined) {
            param += "&dateTo=" + timeTo;
        }
    }
    console.log("?dataGpsTracking2=" + param);
    $.ajax({
        url: "?dataGpsTracking2=" + param,
        async: false,
        success: function (data) {
            tmp = data;
            if (sales !== null && sales !== undefined && sales !== '')
                $('#stat-sales').show().text('Sales: ' + $("#select-sales option[value='" + sales + "']").text());
            else
                $('#stat-sales').hide();
            if (date !== null && date !== undefined && date !== '')
                $('#stat-date').show().text('Date: ' + dateFormat(timeFrom,"yyyy-mm-dd HH:MM:ss") + " > " + dateFormat(timeTo,"HH:MM:ss"));
            else
                $('#stat-date').hide();
        }
    });
    return tmp;
}

function getDataDistributors() {
    var tmp;
    var param = "";
    $.ajax({
        url: "?dataDistributors=" + param,
        async: false,
        dataType: "json",
        success: function (data) {
            tmp = data;
        }
    });
    return tmp;
}

function getDataSalesforces(distributorId) {
    var tmp;
    var param = "";
    if(distributorId!==''&&distributorId!==undefined) param = "&distributorId=" + distributorId;
    $.ajax({
        url: "?dataSalesforces=" + param,
        async: false,
        dataType: "json",
        success: function (data) {
            tmp = data;
        }
    });
    return tmp;
}

function initialize() {
    var t= -0.364089;
    var g= 119.607828;
    if(pathCoords[0]!==undefined){
        t=pathCoords[0].lat;
        g=pathCoords[0].lng;
    }   
    
    var map = new google.maps.Map(document.getElementById("map"), {
        center: {lat: t, lng: g},
        zoom: 11,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    autoRefresh(map);
}
function moveMarker(map, marker, latlng) {
    marker.setPosition(latlng);
    map.panTo(latlng);
}

function autoRefresh(map) {
    var bounds = new google.maps.LatLngBounds();
    var i;
    if(pathCoords[0]!==undefined){
        var no = 1;
        var d = 0;
        var lineColor=['#8d57a7','#1c84c6','#5fba7d','#fdc335','#ff00ff','#e41b17','#23c6c8'];
        dStart = pathCoords[0].date;
        dFinish = pathCoords[pathCoords.length - 1].date;
        if (date_diff_indays(dStart, dFinish)>=7) return null;
        //Data Tracker
        $.getScript("../WEB-PAGES/assets/js/markerclusterer/markerwithlabel.js#{applicationBean.version}", function(){
            for (i = 0; i < (pathCoords.length - 1); i++) {
                var latlng = new google.maps.LatLng(pathCoords[i].lat, pathCoords[i].lng);
                var latlng2 = new google.maps.LatLng(pathCoords[i + 1].lat, pathCoords[i + 1].lng);
                var lineSymbol = {};
                if(date_diff_indays(pathCoords[i].date,pathCoords[i+1].date)>0){
                    d++;
                }
                if (pathCoords[i + 1].type === 2) {
                    new google.maps.Polyline({
                        path: [latlng, latlng2],
                        geodesic: true,
                        strokeColor: '#FF0000',
                        strokeOpacity: 1.0,
                        strokeWeight: 2,
                        icons: [{
                                icon: lineSymbol,
                                offset: '100%'
                            }],
                        editable: false,
                        map: map
                    });
                } else if (pathCoords[i + 1].type === 1) {
                    new google.maps.Polyline({
                        path: [latlng, latlng2],
                        geodesic: true,
                        strokeColor: 'yellow',
                        strokeOpacity: 1.0,
                        strokeWeight: 2,
                        icons: [{
                                icon: lineSymbol,
                                offset: '100%'
                            }],
                        editable: false,
                        map: map
                    });
                } else {
                    new google.maps.Polyline({
                        path: [latlng, latlng2],
                        geodesic: true,
                        strokeColor: lineColor[d],
                        strokeOpacity: 1.0,
                        strokeWeight: 2,
                        icons: [{
                                icon: lineSymbol,
                                offset: '100%'
                            }],
                        editable: false,
                        map: map
                    });
                }

                if ((i===0||i===pathCoords.length - 2)&&pathCoords[i].date !== undefined) {
                    var marker = new MarkerWithLabel({
                        position: latlng,
                        map: map,
                        icon: {url: imageSalesUrl},
                        draggable: false,
                        raiseOnDrag: false,
                        labelContent: "" + no,
                        labelAnchor: new google.maps.Point(-3, 30),
                        labelClass: "mapIconLabel", // the CSS class for the label
                        labelInBackground: false
                    });

                    var content = "Time : " + pathCoords[i].date + "<br/>Lat: " + pathCoords[i].lat + "<br/>Lng: " + pathCoords[i].lng;
                    var infowindow = new google.maps.InfoWindow();
                    google.maps.event.addListener(marker, 'click', (function (marker, content, infowindow) {
                        return function () {
                            infowindow.setContent(content);
                            infowindow.open(map, marker);
                        };
                    })(marker, content, infowindow));
                    bounds.extend(marker.getPosition());
                    no++;
                } else {
                    var marker = new google.maps.Marker({
                        icon: imgCircle,
                        position: latlng,
                        title: "" + no + "",
                        map: map
                    });
                    var content = "Time : " + dateFormat(pathCoords[i].date, "yyyy-mm-dd HH:MM:ss") + "<br/>Lat: " + pathCoords[i].lat + "<br/>Lng: " + pathCoords[i].lng;
                    var infowindow = new google.maps.InfoWindow();
                    google.maps.event.addListener(marker, 'click', (function (marker, content, infowindow) {
                        return function () {
                            infowindow.setContent(content);
                            infowindow.open(map, marker);
                        };
                    })(marker, content, infowindow));
                    bounds.extend(marker.getPosition());
                    no++;
                }
            }
        });
    }

}

