var basePath = $('meta[name=context-path]').attr("content");

$(document).ready(function () {
    $('iframe').each(function () {/*fix youtube z-index*/
        var url = $(this).attr("src");
        if (url.indexOf("youtube.com") >= 0) {
            if (url.indexOf("?") >= 0) {
                $(this).attr("src", url + "&wmode=transparent");
            } else {
                $(this).attr("src", url + "?wmode=transparent");
            }
        }
    });

    $.get(basePath + 'categories', {}, function(data) {
        var navList = [{
            path: basePath,
            name:'首页',
            active: true
        }];
        var children = [];
        var blogNav = {path:"javascript:void(0)", name:"博文", children: children};
        if (data.success && data.data) {
            for (var i=0; i<data.data.length; i++) {
                children.push({path: basePath + "?categoryCode=" + data.data[i].code + "&categoryName=" + encodeURIComponent(data.data[i].name), name:data.data[i].name});
            }
        }
        navList.push(blogNav);
        navList.push({path: basePath + 'message', name:'留言区'});
        navList.push({path: basePath + 'history', name:'归档'});

        $('#nav-div').append(createNav(navList));
        $('.ddmenu li.dropdown').hover(function () {
            $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn();
        }, function () {
            $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut();
        });
    }, 'json');
});

function createNav(navList, level) {
    var nav = [];
    if (!!!level) {
        level = 1;
    }
    nav.push('<ul class="');
    if (level == 1) {
        nav.push('nav nav-pills ddmenu');
    } else if (level == 2) {
        nav.push('dropdown-menu');
    } else {
        nav.push('dropdown-menu sub-menu');
    }
    nav.push('">');
    for (var i=0; i<navList.length; i++) {
        var hasChildren = (navList[i].children && navList[i].children.length>0);
        nav.push('<li class="dropdown');
        if (navList[i].active) {
            nav.push(' active');
        }
        nav.push('"><a href="');
        if (navList[i].path) {
            nav.push(navList[i].path);
        } else {
            nav.push('javascript:void(0)');
        }
        nav.push('"');
        if (navList[i].onclick) {
            nav.push(' onclick="' + navList[i].onclick + '"');
        }
        if (hasChildren) {
            nav.push(' class="dropdown-toggle"');
        }
        nav.push('>');
        nav.push(navList[i].name);
        if (hasChildren) {
            nav.push(' <b class="caret"></b>');
        }
        nav.push('</a>');
        if (hasChildren) {
            nav.push(createNav(navList[i].children, level + 1));
        }
        nav.push('</li>');

    }
    nav.push('</ul>');
    return nav.join('');
}




