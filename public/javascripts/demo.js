var connection="http://localhost:9000/";

$(function(){
    $("#wrapper").toggleClass("toggled");
});
$("#jsonGet").click(function(){
        $.ajax({
            url:connection+'jsonGet',
            type:"GET",
            dataType : "json",
            success:function(data) {
                loadJson(data);
            },
            error: function(){
                alert("error");
            }
        });
})

$("#jsonPost").click(function(){
    $.ajax({
        url:connection+'jsonPost',
        type:"POST",
        cache:false,
        dataType: "application/json",
        data: {"name" : "Nuthanger Farm", "location" : {"lat" : 51.235685, "long" : -1.309197},"residents" : [ {"name" : "Fiver", "age" : 4, "role" : null}, {"name" : "Bigwig", "age" : 6, "role" : "Owsla"} ]},
        success:function(data) {
            alert(data);
        },
        error: function(){
            alert("error");
        }
    });
})
//loadJson et afficher sur la page
function loadJson(getJson){
    var place = "<ul>";
    place += "<li>PlaceNmae:"+getJson["name"]+"</li>";
    $.each(getJson["residents"],function(i,n){
        place += "<li>ResidentsName:"+n["name"]+"</li>";
    });
    place += "</ul>";
    $('#resultJson').append(place);
}