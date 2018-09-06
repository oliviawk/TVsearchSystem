$(function(){
    $("#add").click(function(){
        $(".error").hide();
        var name = $("#newname").val().trim();
        if("" == name){
            $(this).after($(".error"));
            $(".error").html("请输入名称");
            $(".error").show();
            return;
        }

        var type = $("#ctype").val();
        var fid = 0;
        if(type == 1){
            if($("#fid").val() == ""){
                $(this).after($(".error"));
                $(".error").html("请选择频道");
                $(".error").show();
                return;
            }
            else{
                fid = $("#fid").val();
            }
        }
        var _this = this;
        $.ajax({
            url: "/channel/add",
            data: {fid:fid,name:name},
            success: function (id) {
                if(id==null){
                    $(_this).after($(".error"));
                    $(".error").html("增加失败");
                    $(".error").show();
                }else{
                    $(".modal span").html("增加成功");
                    $(".modal").css({"display":"flex"});
                }
            },
            error: function (e) {
                $(_this).after($(".error"));
                $(".error").html("增加失败");
                $(".error").show();
                console.error(e);
            }

        });
    });

    $("#update").click(function(){
        $(".error").hide();
        if($("ul.skinny li.mtree-active").size() != 0){
            var id = $("ul.skinny li.mtree-active").children("a").attr("_id");
            var oldname = $("ul.skinny li.mtree-active").children("a").html();
            var rename = $("#oldname").val();
            if(id = ""){
                $(this).next().after($(".error"));
                $(".error").html("请选择频道或栏目");
                $(".error").show();
                return;
            }
            if(oldname == rename){
                $(this).next().after($(".error"));
                $(".error").html("修改前后名称一样");
                $(".error").show();
                return;
            }

            var _this = this;
            $.ajax({
                url: "/channel/edit",
                data: {id:id,name:rename},
                success: function (data) {
                    if(data == 1){
                        $(".modal span").html("改名成功");
                        $(".modal").css({"display":"flex"});
                    }
                    else if(data == 0){
                        $(_this).next().after($(".error"));
                        $(".error").html("改名失败");
                        $(".error").show();
                    }
                },
                error: function (e) {
                    $(_this).next().after($(".error"));
                    $(".error").html("改名失败");
                    $(".error").show();
                    console.error(e);
                }

            });
        }
        else{
            $(this).next().after($(".error"));
            $(".error").html("请选择频道或栏目");
            $(".error").show();
        }
    });

    $("#delete").click(function(){
            $(".error").hide();
            if($("ul.skinny li.mtree-active").size() != 0){
                var id = $("ul.skinny li.mtree-active").children("a").attr("_id");
                var _this = this;
                if(id == ""){
                    $(this).next().after($(".error"));
                    $(".error").html("请选择频道或栏目");
                    $(".error").show();
                    return;
                }
                $.ajax({
                    url: "/channel/delete",
                    data: {id:id},
                    success: function (data) {
                        if(data == "ok"){
                            $(".modal span").html("删除成功");
                            $(".modal").css({"display":"flex"});
                        }
                        else{
                            $(_this).after($(".error"));
                            $(".error").html("删除失败");
                            $(".error").show();
                        }
                    },
                    error: function (e) {
                        $(_this).after($(".error"));
                        $(".error").html("删除失败");
                        $(".error").show();
                        console.error(e);
                    }

                });
            }
            else{
                $(this).after($(".error"));
                $(".error").html("请选择频道或栏目");
                $(".error").show();
            }
        });

    $("ul.skinny a").on("click",function(){
        $("#fid").val("");
        $("#cid").val("");
        $("#oldname").val("");
        if($(this).parent().hasClass("mtree-active") && $(this).attr("_id") != ""){
            if($(this).parent().hasClass("mtree-node")){
                //频道
                $(".currentSpan").text("当前频道");
                $("#oldname").val($(this).html());
                var fid = $(this).attr("_id");
                $("#fid").val(fid);
                $("#cid").val("");

                if(fid != ""){
                    showItem($(this).parent().children("ul"),fid);
                }
            }/*else{
                //栏目
                $(".currentSpan").text("当前栏目");
                $("#cid").val($(this).attr("_id"));
                $("#fid").val($(this).parents(".mtree-node").children("a").attr("_id"));
            }*/

        }
        return false;//阻止冒泡
    });

    function showItem(ul,fid){
        $.ajax({
                url: "/channel/getByFid",
                data: {fid:fid},
                success: function (data) {
                    console.log(data);
                    ul.html("");
                    if(data.length > 0){
                        ul.html("");
                        for(var i=0;i<data.length;i++){
                            var li = $("<li>");
                            var a = $("<a>",{"_id":data[i].id});
                            a.html(data[i].name);
                            li.append(a);
                            ul.append(li);
                            a.on('click', function(e){
                                  $(this).parents(".mtree").find('.mtree-active').not($(this).parent()).removeClass('mtree-active');
                                  $(this).parent().toggleClass('mtree-active');
                                  $("#oldname").val("");
                                  if($(this).parent().hasClass("mtree-active")){
                                        $(".currentSpan").text("当前栏目");
                                        $("#oldname").val($(this).html());
                                        $("#cid").val($(this).attr("_id"));
                                        $("#fid").val($(this).parent().parent().prev().attr("_id"));
                                  }else{
                                        $("#fid").val("");
                                        $("#cid").val("");
                                  }
                            });
                        }
                    }
                },
                error: function (e) {
                    console.error(e);
                }

            });
    }

    $("#transe").click(function(){
        var cid = $("#cid").val();
        var fid = $("#csel").val();
        var ofid = $("#fid").val();
        if(cid == ""){
            $(this).after($(".error"));
            $(".error").html("请选择栏目");
            $(".error").show();
            return;
        }
        else if(fid == ""){
            $(this).after($(".error"));
            $(".error").html("请选择转入频道");
            $(".error").show();
            return;
        }
        else if(fid == ofid){
            $(this).after($(".error"));
            $(".error").html("该栏目已在当前频道");
            $(".error").show();
            return;
        }
        var _this = this;
        $.ajax({
            url: "/channel/changeFid",
            data: {fid:fid,id:cid},
            success: function (data) {
                if(data == 1){
                    $(".modal span").html("栏目转移成功");
                    $(".modal").css({"display":"flex"});
                }
                else if(data == 0){
                    $(_this).after($(".error"));
                    $(".error").html("栏目转移失败");
                    $(".error").show();
                }
                else{
                    $(_this).after($(".error"));
                    $(".error").html("移入频道不存在，请刷新");
                    $(".error").show();
                }
            },
            error: function (e) {
                $(_this).after($(".error"));
                $(".error").html("栏目转移失败");
                $(".error").show();
                console.error(e);
            }

        });
    });
})