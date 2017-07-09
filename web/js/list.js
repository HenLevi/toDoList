
// Gets the listPage template, puts it in document, then fills it with  list
function  listPage() {
    $.ajax({
        url: "templates/list.html",
        type: "GET"
    })
            .done(function (html) {
                fillListWithData(html);
            })
            .fail(function (xhr, status, errorThrown) {
                console.log("Error: Unable to load List");
            });
}

//    //request to get all lists
function fillListWithData(template) {
    $.ajax({
        url: "api/task/",
        type: "GET",
        dataType: "json"

    })
            .done(function (json) {
                var data = json["data"];
                var list = $(".list-group");
                list.html("");
                for (var i = 0; i < data.length; i++) { //for each task
                    var list_task = data[i];
                    var tmp = $(template); //fill list template with data from database
                    tmp.find(".name").text(list_task["name"]);
                    tmp.attr("id", "listtask_" + list_task["id"]);
                    tmp.find(".editBtn").attr("onclick", "editTask('" + list_task.id + "');return false;");
                    tmp.find(".deleteBtn").attr("onclick", "delTask('" + list_task.id + "');return false;");
                    list.append(tmp);
                }
            })
            .fail(function (xhr, status, errorThrown) {
                console.log("Error: Unable to load lists");
            });
}
















