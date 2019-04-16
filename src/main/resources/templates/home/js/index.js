//定义一个函数,通过此函数实现页面的跳转
    function jumpToPage() {
        //获得点击对象上class属性对应的值,根据此值
        //判定具体点击的是哪个对象(例如上一页,下一页)
        var clazz = $(this).attr("class");
        //获得pageId对象上绑定的pageCurrent对应的值
        var pageCurrent = $('#pageId').data("pageCurrent");
        //获得pageId对象上绑定的pageCount对应的值
        var pageCount = $('#pageId').data("pageCount")
        //根据class属性的值判断点击的是否是上一页
        if (clazz == 'pre' && pageCurrent > 1) {
            pageCurrent--;
        }
        //判断点击的是否是下一页
        if (clazz == "next" && pageCurrent < pageCount) {
            pageCurrent++;
        }
        //判断点击的对象是否是首页
        if (clazz == "first") {
            pageCurrent = 1;
        }
        //判定点击的对象是否是尾页
        if (clazz == "last") {
            pageCurrent = pageCount;
        }
        //重写绑定pageCurrent的值,
        $('#pageId').data("pageCurrent", pageCurrent);
        //重新执行查询操作(根据pageCurrent的值)
    }
    //定义一个函数,通过此函数实现具体页面的跳转
    function jumpToPagedetail() {
        //获得点击对象上class属性对应的值,根据此值
        //判定具体点击的是哪个对象(例如上一页,下一页)
        var clazz = $(this).attr("class");
        //获得pageId对象上绑定的pageCurrent对应的值
        var pageCurrent = $('#pageId').data("pageCurrent");
        //获得pageId对象上绑定的pageCount对应的值
        var pageCount = $('#pageId').data("pageCount")
        //根据class属性的值判断点击的是否是上一页
        if (clazz == 'pre' && pageCurrent > 1) {
            pageCurrent--;
        }
        //判断点击的是否是下一页
        if (clazz == "next" && pageCurrent < pageCount) {
            pageCurrent++;
        }
        //判断点击的对象是否是首页
        if (clazz == "first") {
            pageCurrent = 1;
        }
        //判定点击的对象是否是尾页
        if (clazz == "last") {
            pageCurrent = pageCount;
        }
        //重写绑定pageCurrent的值,
        $('#pageId').data("pageCurrent", pageCurrent);
        //重新执行查询操作(根据pageCurrent的值)
    }