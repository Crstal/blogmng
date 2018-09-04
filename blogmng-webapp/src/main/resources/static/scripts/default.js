
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

    $.get('/front/categories', {}, function(data) {
        var navList = [{
            path:'[[@{}]]',
            name:'首页',
            active: true
        }];
        var children = [];
        var blogNav = {path:"/", name:"博文", children: children};
        if (data.success && data.data) {
            for (var i=0; i<data.data.length; i++) {
                children.push({path: "?category=" + data.data[i].code, name:data.data[i].name});
            }
        }
        navList.push(blogNav);
        navList.push({path:'/front/message', name:'留言区'});
        navList.push({path:'/front/history', name:'归档'});

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

Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};

Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};

