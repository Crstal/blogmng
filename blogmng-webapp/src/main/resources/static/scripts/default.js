var navList = [{
    path:'index.html',
    name:'首页',
    active: true,
    children:[]
},{
    path:'index.html',
    name:'胡侃',
    children:[{
        path:'index.html',
        name:'胡侃',
        children:[]
    },{
        path:'index.html',
        name:'胡侃',
        children:[]
    }]
}];

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

    $('#nav-div').append(createNav(navList));

    $('.ddmenu li.dropdown').hover(function () {
        $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn();
    }, function () {
        $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut();
    });

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
        nav.push(navList[i].path);
        nav.push('"');
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

