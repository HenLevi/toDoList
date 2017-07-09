
var id;
//this function is take care when the browser is load at the beginning
$(document).ready(function () {
    listPage();
});

// this  function when I clicked on "enter" and if yes enter to  addTask() function.
function onEnter(key) {
    if (key === 13) {
        addTask();
        return false;
    }
}

//this function for add task
function addTask() {

    var toAdd = JSON.stringify({
        "name": $("#nameList").val(),
        "check": "0"
    });
    console.log(toAdd);
    $.ajax({
        url: "api/task/addTask",
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data: toAdd
    })
            .done(function (html) {     //if secsess load again listPage
                $("#nameList").val("");
                listPage(html);
            })
            .fail(function (xhr, status, errorThrown) {
                console.log("Error: to add list");
            });
}

function delTask(id) {
    console.log(id);
    //request to delete the post with id postid from the database
    $.ajax({
        url: "api/task/deleteTask/" + id,
        type: "DELETE",
        dataType: "json"
    })
            .done(function (result) {
                if (result["status"] !== -1) { //if succeeds
                    id = result["data"];
                    var task = $("#task" + id);
                    $('button[name="remove_levels"]').on('click', function (e) {
                        var $form = $(this).closest('form');
                        e.preventDefault();
                        $('#confirm').modal({
                            backdrop: 'static',
                            keyboard: false
                        })
                                .one('click', '#delete', function (e) {
                                    $form.trigger('submit');
                                });
                    });
                    task.hide(); //hide the specific task from list 
                    listPage(result);
                }
            })
            .fail(function (xhr, status, errorThrown) {
                console.log("Error: Unable to delete task");
            });
}

function editTask(id) {
    console.log(id);
    var elem = $("#listtask_" + id);
    console.log("id of task:" + id);
    //for edit
    var obj = elem.find(".name");
    var switchToSpan = function ()
    {
        if (jQuery(this).val())
            var $text = jQuery(this).val();

        var $span = $("<span>", {
            text: $text
        });
        $span.addClass("name");
        $(this).replaceWith($span);

        var dataToSend = {
            "id": id,
            "name": elem.find(".name").text(),
            "check": "0"
        };
        update(dataToSend).then(function (data) {
//            elem.toggleClass("list-group-item-success");
        });
    };

    var $input = $("<input>", {
        val: $(elem.find(".name")).text(),
        type: "text",
        width: "83%",
        rel: jQuery(obj).text()
    });
    $input.addClass("loadNum");
    $(obj).replaceWith($input);

    $input.keyup(function (e) {
        if (e.which === 13) {
            this.blur();
        }
    });
    $input.on("click", function (e) {       //Allow clicking inside the input field
        e.preventDefault();
    });
    $input.on("blur", switchToSpan);
    $input.select();
}

function update(todo) {
    return $.ajax({

        url: "api/task/editTask/",
        type: "PUT",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(todo)
    })
            .done(function (html) {
                console.log("chen chen chen chen chen");
            })
            .fail(function (xhr, status, errorThrown) {
                console.log("Error: Unable to load tasks");
            });
}



