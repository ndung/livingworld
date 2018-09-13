/* global fecthData, Notification, loading */

var lastChatId = getLastChatId().responseText;
var hWrapper;

$(document).ready(function () {
    // Window preparation
    hWrapper=$(window).height() - $(".page-header").height() - $(".px-navbar").height() - $(".chat-header").height() - $(".px-footer").height();
    $(".chat-list").css("min-height",hWrapper - 70);
    $(".chat-list").css("max-height",hWrapper - 70);
    $(".chat-content").css("min-height",hWrapper - $(".chat-text").height() - 79);
    $(".chat-content").css("max-height",hWrapper - $(".chat-text").height() - 79);
    window.onresize = function (event) {
        hWrapper=$(window).height() - $(".page-header").height() - $(".px-navbar").height() - $(".chat-header").height() - $(".px-footer").height();
        $(".chat-list").css("min-height",hWrapper - 70);
        $(".chat-list").css("max-height",hWrapper - 70);
        $(".chat-content").css("min-height",hWrapper - $(".chat-text").height() - 79);
        $(".chat-content").css("max-height",hWrapper - $(".chat-text").height() - 79);
    };

    // Open chat template dialog
    getDataChatTemplate();
    $('.shortcut-upload-file').on('click', function () {
        $(this).children().toggleClass('fa-list').toggleClass('fa-remove');
        $('.shortcut-upload-file-content').toggleClass('active');
    });


    $('#file-submit').on('click',function () {
        var topicId = $('#active-topic-id').text();
        var userFullName = $('#user-fullname').text();
        if(topicId!==""){
            $("#ajaxLoading").show();
            var file_image = $('#input-upload-image').prop('files')[0];
            var file_video = $('#input-upload-video').prop('files')[0];
            var form_data = new FormData();
            var title = $('#file-title').val();
            form_data.append("uploadFile", "");
            form_data.append("imageFileBean", file_image);
            form_data.append("videoFileBean", file_video);
            form_data.append("topicId", topicId);
            form_data.append("rsId", userFullName);
            form_data.append("title", title);
            console.log(file_image);
            console.log(file_video);
            console.log(form_data.get("title"));
            console.log(form_data.get("topicId"));
            console.log(form_data.get("rsId"));
            $.ajax({
                    url: document.URL,
                    dataType: 'json',
                    cache: false,
                    async: true,
                    contentType: false,
                    processData: false,
                    data: form_data,
                    type: 'post',
                    success: function(data){
                        console.log(data);
                        $('#chat-message-send').val(data);
                        $('#file-title').val("");
                        $(".fileinput").fileinput("clear");
                        $("#ajaxLoading").hide();
                    },
                    error: function(request, status, error){
                        $(".fileinput").fileinput("clear");
                        $('#file-title').val("");
                        $("#ajaxLoading").hide();
                        alert(error);
                    }
           });
        } else {
            alert("Pilih dulu reseller-nya cuy!");
        }
   });

    $('.shortcut-template').on('click', function () {
        $(this).children().toggleClass('fa-list').toggleClass('fa-remove');
        $('.shortcut-template-content').toggleClass('active');
    });

    // Set the audio
//    $('<audio id="chatAudio"><source src="audio/notification_small.mp3" type="audio/mpeg"></audio>').appendTo('body');

    // Start for the main code
    getDataRsByChatId(lastChatId);
    (function fecthData() {
        if (doesConnectionExist) {
            lastChatIdTmp = getLastChatId().responseText;
            if (lastChatId < lastChatIdTmp) {
                lastChatId = lastChatIdTmp;
                getDataRsByChatId(lastChatId);
                notifNewMessage();
                console.log(new Date().toLocaleString() + ":" + lastChatId);
            }
        }
        setTimeout(fecthData, 4000);
    }());

});

//Send message
$(document).ready(function () {
    $('#chat-message-send').keypress(function (e) {
        if ((e.which === 13)&&(e.shiftKey)&&($('#chat-message-send').val()!=='')) {
            pasteIntoInput(this, "\n ");
            e.preventDefault();
        } else if((e.which === 13)&&($('#chat-message-send').val()!=='')){
            e.preventDefault();
            if (doesConnectionExist) sendDataChat();
            else alert('Internet connection lost.');
        }
    });
});

//Search function
$(document).ready(function () {
    $('#search-topic-id').keypress(function (e) {
        if ((e.which === 13)&&($('#search-topic-id').val()!=='')) {
            if (doesConnectionExist) {
                getDataRsByTopic($('#search-topic-id').val());
                $("#button-global").removeClass("focus active");
            }
            else alert('Internet connection lost.');
            return false;
        }
    });
    $('#search-topic-id').on('input', function () {
        if ($('#search-topic-id').val() === '') {
            if (doesConnectionExist) {
                getDataRsByChatId(lastChatId);
                $("#button-global").removeClass("focus active");
                $('#search-topic-id').attr('onsearch', '0');
                $('#search-topic-button').removeAttr('onclick');
            }
        } else {
            if ($(".chat-user:contains(" + $('#search-topic-id').val() + ")").length > 0) {
                $('.chat-user').css('display', 'none');
                $('#search-topic-not-found').css('display', 'none');
                $('#search-topic-button').attr('onclick', 'getDataRsByTopic("' + $('#search-topic-id').val() + '")');
                $(".chat-user:contains(" + $('#search-topic-id').val() + ")").each(function () {
                    $(this).css('display', 'block');
                });
            } else {
                if ($('#search-topic-id').val() !== '') {
                    $('.chat-user').css('display', 'none');
                    $('#search-topic-not-found').css('display', 'block');
                    $('#search-topic-button').attr('onclick', 'getDataRsByTopic("' + $('#search-topic-id').val() + '")');
                } else {
                    $('.chat-user').css('display', 'block');
                    $('#search-topic-not-found').css('display', 'none');
                    $('#search-topic-button').removeAttr('onclick');
                }
            }
        }
    });
});

function getDataChatTemplate() {
    $.ajax({
        dataType: "json",
        async: true,
        url: '?dataChatTemplate=&',
        success: function (data) {
            var s="";
            var last="";
            var c=0;
            $.each(data, function (key, val) {
                if(last!==val.category){
                    if(c++>0){
                        s+='       </ul>' +
                           '</li>';
                    }
                    s+= '<li class="dd-item">' +
                        '   <a href="#"><div class="dd-handle">' + val.category + '<span class="fa arrow"></span></div></a>' +
                        '       <ul class="collapse dd-list">';
                }
                last = val.category;
                s+=     '           <li class="dd-item">' +
                        '               <div class="dd-handle chat-template-item">' + val.message + '</div>' +
                        '           </li>';
            });
            if(s!==''){
                s+= '       </ul>' +
                    '</li>';
            }
            $("#side-menu-a").html(s);
            $('#side-menu-a').metisMenu();
            $(".chat-template-item").on("click", function(event){
                $('#chat-message-send').val($('#chat-message-send').val() + $(this).text());
            });
        }
    });
}

function getDataRsByChatId(chatId) {
    $.ajax({
        dataType: "json",
        async: true,
        url: '?dataRsByChatId=&chatId=' + chatId,
        success: function (data) {
            var lastMsgDate = new Date('1900-01-01');
            var listRs = "";
            $.each(data, function (key, val) {
                var s = val.id;
                listRs +='<tr><td class="chat-user"><div class="box m-a-0 bg-transparent"><div class="page-messages-item-subject box-cell"><a id="a-' + val.id + '" onclick="getDataChatById(\'' + val.id + '\',' + val.lastid + ');" class="text-default font-weight-bold"><img src="../WEB-PAGES/assets/images/alphanumeric/alpha_' + s.substr(-1) + '.png" class="label-image m-a-0 left">' + s + '</a></div></div></td></tr>';
//                listRs += '<div class="chat-user" ' + ($('#button-global').attr('active')==='1' ? 'style="display:none;"' : '');
//                listRs += 'id="' + val.id + '" onclick="getDataChatById(\'' + val.id + '\',' + val.lastid + ');">';
//                listRs += (val.unread > 0 ? '<span class="unread pull-right label label-primary">+' + val.unread + '</span>' : '');
//                listRs += '<img class="chat-avatar img-circle" src="img/alpha_' + s.substr(-1) + '.png" alt="" >';
//                listRs += '<div class="chat-user-name">';
//                listRs += '<a id="a-' + val.id + '" href="#" ' + (($.isNumeric(val.id)||((val.id).indexOf('RX')>=0)) ? 'onclick="getDataRsDetail(\'' + val.id + '\')" data-toggle="modal" data-target="#modDetailUser"' : '') + '><strong>' + val.id + '</strong></a></div></div>';
                if (lastMsgDate < val.lastmsg)
                    lastMsgDate = val.lastmsg;
            });
            $("#last-message-date").html("Last active message: " + dateFormat(new Date(lastMsgDate), "yyyy-mm-dd HH:MM:ss"));
            $('#button-global').attr('active')==='1' ? $("#list-rs").html($("#list-rs").html() + listRs) : $("#list-rs").html(listRs);
            if ($('#search-topic-id').attr('onsearch') === '1'){
                $("td[class=chat-user]").each(function () {
                    if ($(this).not('[id*="' + $('#search-topic-id').val() + '"]').size()) {
                        $(this).css('display', 'none');
                    }
                });
            }
            if($("#active-topic-id").text()!=='') {
                if ($("#active-topic-id").text()!==data[0]['id']&&$('#button-notification').attr('active')==='1') $('#chatAudio')[0].play();
                getDataChatById($("#active-topic-id").text(), lastChatId);
                $('#' + $("#active-topic-id").text().replace('.','\\.')).find('span').remove();
                $('#list-messages').scrollTop($('#list-messages').prop("scrollHeight"));
            } else {
                if($('#button-notification').attr('active')==='1') $('#chatAudio')[0].play();
            }
            getTotalUnread();
        }
    });
}

function getDataRsByTopic(topicId) {
    $.ajax({
        dataType: "json",
        async: true,
        url: '?dataRsByTopic=&topicId=' + topicId,
        success: function (data) {
            var listRs = "";
            if (data.length <= 0) {
                $('#search-topic-not-found').css('display', 'block');
                $('.chat-user').css('display', 'none');
            } else {
                $.each(data, function (key, val) {
                    var s = val.id;
                    listRs +='<tr><td class="chat-user"><div class="box m-a-0 bg-transparent"><div class="page-messages-item-subject box-cell"><a onclick="getDataChatById(\'' + val.id + '\',' + val.lastid + ');" class="text-default font-weight-bold"><img src="../WEB-PAGES/assets/images/alphanumeric/alpha_' + s.substr(-1) + '.png" class="label-image m-a-0 left">' + s + '</a></div></div></td></tr>';
//                    listRs += '<div class="chat-user" id="' + val.id + '" onclick="getDataChatById(\'' + val.id + '\',' + val.lastid + ');">';
//                    listRs += (val.unread > 0 ? '<span class="unread pull-right label label-primary">+' + val.unread + '</span>' : '');
//                    listRs += '<img class="chat-avatar img-circle" src="img/alpha_' + s.substr(-1) + '.png" alt="" >';
//                    listRs += '<div class="chat-user-name">';
//                    listRs += '<a id="a-' + val.id + '" href="#" ' + ($.isNumeric(val.id) ? 'onclick="getDataRsDetail(\'' + val.id + '\')" data-toggle="modal" data-target="#modDetailUser"' : '') + '><strong>' + val.id + '</strong></a></div></div>';
                });
                $('#search-topic-not-found').css('display', 'none');
                $("#list-rs").html(listRs);
                $('#search-topic-id').attr('onsearch', '1');
            }
        },
        error: function(){
            alert('Connection Failed.');
        }
    });
}

function getDataChatById(topicId, chatId) {
    $.ajax({
        dataType: "json",
        async: true,
        url: '?dataChatByTopic=&topicId=' + topicId,
        success: function (data) {
            var listChat = "";
            $.each(data, function (key, val) {
                var s = val.userId;
                var arr = val.message.split(";");
                var textVideo="";
                if(arr.length===3&&arr[1].indexOf('https:')>=0&&arr[2].indexOf('https:')>=0){
                    textVideo = '<a onclick="window.open(\'' + arr[2] + '\',\'_blank\');"><img height="100px" src="' + arr[1] + '"><br/>' + arr[0] + '</a>';
                }
                listChat +='<div class="widget-chat-item ' + ((val.userId).substring(0,1) !=='0' ? 'right' : 'left') + '">';
                listChat +='<img src="../WEB-PAGES/assets/images/alphanumeric/alpha_' + ((val.userId).substring(0,1) !== '0' ? s.substring(0,1).toLowerCase() : s.substr(-1)) + '.png" alt="" class="widget-chat-avatar label-image" style="height:2.5em!important;margin-top:22px">';
                listChat +='<span class="widget-chat-date pull-right">' + dateFormat(new Date(val.created), "yyyy-mm-dd HH:MM:ss") + '</span>';
                listChat +='<div class="widget-chat-heading"><a onclick="getDataRsDetail(\'' + val.userId + '\')" title="">' + val.userId + '</a> says:</div>';
                listChat +='<div class="widget-chat-text">';
                listChat +=(textVideo!=="" ? textVideo : ((val.message).indexOf('https://goo.gl/')!==-1||(val.message).indexOf('https://ireload.icg.co.id/uploads/')!==-1||(val.message).indexOf('https://mob.icg.co.id/uploads/')!==-1 ? ('<a onclick="window.open(\'' + (val.message) + '\',\'_blank\');"><img height="100px" src="' + val.message+ '"></a>') : val.message));
                listChat +='</div>';
                listChat +='</div>';
                
//                listChat += '<div class="chat-message ' + ((val.userId).substring(0,1) !=='0' ? 'right' : 'left') + '">';
//                listChat += '<img class="chat-avatar' + ((val.userId).substring(0,1) !== '0' ? '-right' : '') + ' img-circle" src="img/alpha_' + ((val.userId).substring(0,1) !== '0' ? s.substring(0,1).toLowerCase() : s.substr(-1)) + '.png" alt="" >';
//                listChat += '<div class="message">';
//                listChat += '<a class="message-author" href="#" onclick="getDataRsDetail(\'' + val.userId + '\')" ' + ((val.userId).substring(0,1) ==='0' ? 'data-toggle="modal" data-target="#modDetailUser"' : '') + '> ' + val.userId + ' </a>';
//                listChat += '<span class="message-date"> ' + dateFormat(new Date(val.created), "yyyy-mm-dd HH:MM:ss") + '  </span>';
//                listChat += '<span class="message-content">' + (textVideo!=="" ? textVideo : ((val.message).indexOf('https://goo.gl/')!==-1||(val.message).indexOf('https://ireload.icg.co.id/uploads/')!==-1||(val.message).indexOf('https://mob.icg.co.id/uploads/')!==-1 ? ('<a onclick="window.open(\'' + (val.message) + '\',\'_blank\');"><img height="100px" src="' + val.message+ '"></a>') : val.message)) + '  </span>';
//                listChat += '</div></div>';
            });
            $("#list-messages").html(listChat);
            $("#active-topic-title").html($('#a-' + topicId.replace('.','\\.')).text() + "<div id=\"active-topic-id\" style=\"display: none;\">" + topicId + "</div>");
            $('.chat-user').css("background-color","");
            if ($('#search-topic-id').attr('onsearch') !== '1') $('.chat-user').removeAttr("style");
            $('#' + topicId.replace('.','\\.')).css("background-color", "#f6f6f6");
            if ($('#' + topicId.replace('.','\\.')).children('.unread').length>0) updateChatReadById(topicId, chatId);
            $('#list-messages').scrollTop($('#list-messages').prop("scrollHeight"));
        }
    });
}

function updateChatReadById(topicId, chatId) {
    $.ajax({
        dataType: "json",
        async: true,
        url: '?updateChatReadById=&topicId=' + topicId + '&chatId=' + chatId,
        success: function (data) {
            $('#' + topicId.replace('.','\\.')).find('span').remove();
            getTotalUnread();
        }
    });
}

function getLastChatId() {
    return $.ajax({
        dataType: "text",
        async: false,
        url: '?idLastChat=',
        error: function() {
            
        }
    });
}

function getLastGlobalId() {
    return $.ajax({
        dataType: "text",
        async: false,
        url: '?idLastGlobal='
    });
}

function getDataGlobal() {
    if($('#button-global').attr('active')==='1') {
        $('#button-global').attr('active', '0');
        $("#button-global").removeClass("focus active");
        getDataRsByChatId(lastChatId);
        $('#list-messages').html('');
    }
    else {
        $('#button-global').attr('active', '1');
        $("#button-global").addClass("focus active");
        $.ajax({
            dataType: "json",
            async: true,
            url: '?dataChatByTopic=&topicId=global',
            success: function (data) {
                var listRs = "";
                listRs +='<tr><td id="global"><div class="box m-a-0 bg-transparent"><div class="page-messages-item-subject box-cell"><a id="a-global" class="text-default font-weight-bold"><img src="../WEB-PAGES/assets/images/alphanumeric/alpha_g.png" class="label-image m-a-0 left">Global</a></div></div></td></tr>';
//                listRs += '<div class="chat-user" id="global">';
//                listRs += '<img class="chat-avatar img-circle" src="img/alpha_g.png" alt="" >';
//                listRs += '<div class="chat-user-name">';
//                listRs += '<a id="a-global"><strong>Global</strong></a></div></div>';
                $("#list-rs").html(listRs);

                var listChat = "";
                $.each(data, function (key, val) {
                    var s = val.userId;
                    var arr = val.message.split(";");
                    var textVideo="";
                    if(arr.length===3&&arr[1].indexOf('https:')>=0&&arr[2].indexOf('https:')>=0){
                        textVideo = '<a onclick="window.open(\'' + arr[2] + '\',\'_blank\');"><img height="100px" src="' + arr[1] + '"><br/>' + arr[0] + '</a>';
                    }
                    listChat +='<div class="widget-chat-item ' + ((val.userId).substring(0,1) !=='0' ? 'right' : 'left') + '">';
                    listChat +='<img src="../WEB-PAGES/assets/images/alphanumeric/alpha_' + ((val.userId).substring(0,1) !== '0' ? s.substring(0,1).toLowerCase() : s.substr(-1)) + '.png" alt="" class="widget-chat-avatar label-image" style="height:2.5em!important;margin-top:22px">';
                    listChat +='<span class="widget-chat-date pull-right">' + dateFormat(new Date(val.created), "yyyy-mm-dd HH:MM:ss") + '</span>';
                    listChat +='<div class="widget-chat-heading"><a onclick="getDataRsDetail(\'' + val.userId + '\')" title="">' + val.userId + '</a> says:</div>';
                    listChat +='<div class="widget-chat-text">';
                    listChat +=(textVideo!=="" ? textVideo : ((val.message).indexOf('https://goo.gl/')!==-1||(val.message).indexOf('https://ireload.icg.co.id/uploads/')!==-1||(val.message).indexOf('https://mob.icg.co.id/uploads/')!==-1 ? ('<a onclick="window.open(\'' + (val.message) + '\',\'_blank\');"><img height="100px" src="' + val.message+ '"></a>') : val.message));
                    listChat +='</div>';
                    listChat +='</div>';

//                    listChat += '<div class="chat-message ' + ((val.userId).substring(0,1) !=='0' ? 'right' : 'left') + '">';
//                    listChat += '<img class="chat-avatar' + ((val.userId).substring(0,1) !=='0' ? '-right' : '') + ' img-circle" src="img/alpha_' + ((val.userId).substring(0,1) !== '0' ? s.substring(0,1).toLowerCase() : s.substr(-1)) + '.png" alt="" >';
//                    listChat += '<div class="message">';
//                    listChat += '<a class="message-author" href="#" onclick="getDataRsDetail(\'' + val.userId + '\')" ' + ((val.userId).substring(0,1) ==='0' ? 'data-toggle="modal" data-target="#modDetailUser"' : '') + '> ' + val.userId + ' </a>';
//                    listChat += '<span class="message-date"> ' + dateFormat(new Date(val.created), "yyyy-mm-dd HH:MM:ss") + '  </span>';
//                    listChat += '<span class="message-content">' + (textVideo!=="" ? textVideo : ((val.message).indexOf('https://goo.gl/')!==-1||(val.message).indexOf('https://ireload.icg.co.id/uploads/')!==-1||(val.message).indexOf('https://mob.icg.co.id/uploads/')!==-1 ? ('<a onclick="window.open(\'' + (val.message) + '\',\'_blank\');"><img height="100px" src="' + val.message+ '"></a>') : val.message)) + '  </span>';
//                    listChat += '</div></div>';
                });
                $("#list-messages").html(listChat);
                $("#active-topic-title").html("<div id=\"active-topic-id\" style=\"display: none;\"></div>");
                $("#active-topic-id").html("Global");
                $("#button-global").addClass("focus active");
                $('#list-messages').scrollTop($('#list-messages').prop("scrollHeight"));
            }
        });
    }
}

function sendDataChat() {
    var msg = $('#chat-message-send').val();
    msg = msg.replace(/%/g,'%25');
    msg = msg.replace(/#/g,'%23');
    msg = msg.replace(/\+/g,'%2B');
    msg = msg.replace(/&/g,'%26');
    var topicId = $('#active-topic-id').text();
    var userFullName = $('#user-fullname').text();
    if (msg !== "" && topicId !== "" && userFullName !== "") {
        $.ajax({
            dataType: "text",
            async: true,
            url: '?sendGcmServer=&topicId=' + topicId + '&message=' + msg + '&rsId=' + userFullName,
            success: function (data) {
                console.log(data);
            }
        });
        $('#chat-message-send').val('');
        getDataChatById(topicId, lastChatId);
    }
}

function getTotalUnread() {
    var totalUnread = 0;
    $('.unread').each(function(){
        totalUnread += parseFloat($(this).text());  // Or this.innerHTML, this.innerText
    });
    (totalUnread>0) ? $("#chat-unread-total").html(totalUnread).addClass("label-warning") : $("#chat-unread-total").html('').removeClass("label-warning");
    return totalUnread;
}

function getDataRsDetail(topicId) {
    $.ajax({
        dataType: "json",
        async: false,
        url: '?dataRsDetail=&topicId=' + topicId,
        success: function (data) {
            $("#mod-detail-name").html(data.name);
            $("#mod-detail-id").html(data.id);
            $("#mod-detail-location").html(data.location + ", " + data.province);
            $("#mod-detail-gender").html((data.gender === "L" ? "Male" : (data.gender === "P" ? "Female" : "-")));
            $("#mod-detail-upline-name").html(data.uplineName === null ? "-" : data.uplineName);
            $("#mod-detail-upline").html(data.upline === null ? "-" : data.upline);
            $("#mod-detail-balance").html(addCommas(data.balance));
            $("#mod-detail-trxamount").html(addCommas(data.trxAmount));
            $("#mod-detail-points").html(addCommas(data.points));
            $("#mod-detail-datejoin").html(dateFormat(new Date(data.dateTimeJoin), "yyyy-mm-dd HH:MM:ss"));
            $("#mod-detail-lastlogin").html(dateFormat(new Date(data.lastLogin), "yyyy-mm-dd HH:MM:ss"));
        }
    });
}

function convertDate(date) {
    var yyyy = date.getFullYear().toString();
    var mm = (date.getMonth() + 1).toString();
    var dd = date.getDate().toString();

    var mmChars = mm.split('');
    var ddChars = dd.split('');

    var result = yyyy + '-' + (mmChars[1] ? mm : "0" + mmChars[0]) + '-' + (ddChars[1] ? dd : "0" + ddChars[0]);
    return result;
}

function changeNotifSound() {
    return $.ajax({
        dataType: "text",
        async: false,
        url: '?changeNotifSound=',
        success: function(data) {
            if (data==='true') {
                $("#button-notification").removeClass("glyphicon-volume-up");
                $("#button-notification").addClass("glyphicon-volume-off");
                $('#button-notification').attr('active','0');
            } else {
                $("#button-notification").removeClass("glyphicon-volume-off");
                $("#button-notification").addClass("glyphicon-volume-up");
                $('#button-notification').attr('active','1');
            }
        }
    });
}

function addCommas(nStr)
{
    nStr += '';
    x = nStr.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    return x1 + x2;
}

function pasteIntoInput(el, text) {
    el.focus();
    if (typeof el.selectionStart === "number"
            && typeof el.selectionEnd === "number") {
        var val = el.value;
        var selStart = el.selectionStart;
        el.value = val.slice(0, selStart) + text + val.slice(el.selectionEnd);
        el.selectionEnd = el.selectionStart = selStart + text.length;
    } else if (typeof document.selection !== "undefined") {
        var textRange = document.selection.createRange();
        textRange.text = text;
        textRange.collapse(false);
        textRange.select();
    }
}

function doesConnectionExist() {
    var xhr = new XMLHttpRequest();
    var file = "img/dot.gif"; //image path in your project
    var randomNum = Math.round(Math.random() * 10000);
    xhr.open('HEAD', file + "?rand=" + randomNum, false);
    try {
        xhr.send(null);
        if (xhr.status >= 200 && xhr.status < 304) {
            return true;
        } else {
            return false;
        }
    } catch (e) {
        return false;
    }
}

function notifNewMessage() {
    var chck = Notification.permission;
    if( chck === 'granted' ){
        notifNewMessageContent();
    }
    else if( chck === 'default' ){
        Notification.requestPermission(function(permission){
            if (permission==='granted'){
                notifNewMessageContent();
            } else if (permission ==='denied') {
                notifNewMessageToAllow();
            }
        });
    }
    else if( chck === 'denied' ){
        notifNewMessageToAllow();
    }
}

function notifNewMessageToAllow() {
    if(!(navigator.userAgent.match(/Android/i)
        || navigator.userAgent.match(/webOS/i)
        || navigator.userAgent.match(/iPhone/i)
        || navigator.userAgent.match(/iPad/i)
        || navigator.userAgent.match(/iPod/i)
        || navigator.userAgent.match(/BlackBerry/i)
        || navigator.userAgent.match(/Windows Phone/i))){
        $('#modReqNotification').modal('show');
    }
}

function notifNewMessageContent() {
    var visible = vis();
    if ((!visible)||(!document.hasFocus())) {
        var unread = parseFloat(getTotalUnread()) + 1;
        n = new Notification( 'Mondelez Loyalty Messenger', {
            body: 'Hi ' + $('#user-fullname').text() + '!\nYou have ' + unread + ' new message(s).\nPlease respond them.',
            icon: "../images/ireload-engine-64.png",
            tag:'new_message'
        });
        n.onshow = function() { setTimeout(n.close, 60000); };
    }
}

var vis = (function(){
    var stateKey, eventKey, keys = {
        hidden: "visibilitychange",
        webkitHidden: "webkitvisibilitychange",
        mozHidden: "mozvisibilitychange",
        msHidden: "msvisibilitychange"
    };
    for (stateKey in keys) {
        if (stateKey in document) {
            eventKey = keys[stateKey];
            break;
        }
    }
    return function(c) {
        if (c) document.addEventListener(eventKey, c);
        return !document[stateKey];
    };
})();
