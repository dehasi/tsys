/**
 * Created by Rafa on 07.07.2015.
 */


function GetCellValues() {
    var x = document.getElementById("cartGrid").rows.length;
    //alert("len = " + x);
    var bags = []
    for (var i = 1; i < x; i++) {
        var bag = {}
        var name = document.getElementById('name' + i).value;
        //alert(name);
        var weight = document.getElementById('weight' + i).value;
        //alert(weight);
        var loadId = document.getElementById('load' + i).value;
        //alert(loadId);
        var unloadId = document.getElementById('unload' + i).value;
        //alert(unloadId)
        bag['name'] =  name;
        bag['weight'] =  weight;
        bag['loadId'] =  loadId;
        bag['unloadId'] =  unloadId;

        bags.push(bag)
    }
    var  js =  JSON.stringify(bags);
    //alert(js);
    return js;
}

function getDriverAndTruck() {
    alert("getDriverAndTruck !!!!")
    var jsdata =  GetCellValues();
    $.ajax({
        url: "/private/manager/createorder",
        type: "POST",
        data: {do:"getStuff", jsdata:jsdata},
        success: function(data) {
            alert("success: " + data);
        }
    });
}


function validateBaggageCount (){
    var count = document.forms["create"]["bgcnt"].value;
    alert(count)
    return true
}




function getCities() {
    var cities = null;
    $.ajax({
        url: "localhost:8080/private/manager/createorder",
        data:{ do: "getCities" },
        dataType: "json"
    }).done(function (data) {
        cities = data;
    });

    return cities;
}


function addRow() {

    var myName = document.getElementById("name");
    var age = document.getElementById("age");
    var table = document.getElementById("myTableData");

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    row.insertCell(0).innerHTML= '<input type="button" value = "Delete" onClick="Javacsript:deleteRow(this)">';
    row.insertCell(1).innerHTML= myName.value;
    row.insertCell(2).innerHTML= age.value;

}

function deleteRow(obj) {

    var index = obj.parentNode.parentNode.rowIndex;
    var table = document.getElementById("myTableData");
    table.deleteRow(index);

}

function addTable() {

    var cities = getCities();
    alert(cities);
    var x = document.forms["Bcount"]["count"].value;
    var myTableDiv = document.getElementById("myDynamicTable");

    var table = document.createElement('TABLE');
    table.border='1';

    var tableBody = document.createElement('TBODY');
    table.appendChild(tableBody);

    var tr = document.createElement('TR');
    tableBody.appendChild(tr);
    var th = document.createElement('TH');
    th.appendChild(document.createTextNode("Name"))
    tr.appendChild(th);

    th = document.createElement('TH');
    th.appendChild(document.createTextNode("weight"))
    tr.appendChild(th);

    th = document.createElement('TH');
    th.appendChild(document.createTextNode("load"))
    tr.appendChild(th);

    th = document.createElement('TH');
    th.appendChild(document.createTextNode("unload"))
    tr.appendChild(th);



    for (var i=0; i<x; i++) {
        var tr = document.createElement('TR');
        tableBody.appendChild(tr);


        var td = document.createElement('TD');
        td.width='25';
        var name = document.createElement("INPUT");
        name.setAttribute("type", "text");
        name.setAttribute("placeholder","baggage name:")
        td.appendChild(name);
        tr.appendChild(td);


        td = document.createElement('TD');
        td.width='25';
        var weight = document.createElement("INPUT");
        weight.setAttribute("type", "text");
        weight.setAttribute("placeholder","baggage weight:")
        td.appendChild(weight);
        tr.appendChild(td);

        td = document.createElement('TD');
        td.width='25';
        var load = document.createElement("INPUT");
        load.setAttribute("type", "text");
        load.setAttribute("value", "${myArray}");
        td.appendChild(load);
        tr.appendChild(td);


        td = document.createElement('TD');
        td.width='75';
        var unload = document.createElement("INPUT");
        unload.setAttribute("type", "text");
        td.appendChild(unload);
        tr.appendChild(td);

    }
    myTableDiv.appendChild(table);

}

function load() {

    console.log("Page load finished");

}