/**
 * Created by Rafa on 07.07.2015.
 */


function GetCellValues() {
    var x = document.getElementById("cartGrid").rows.length;

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
            //alert("success: " + data);
            var resp = JSON.parse(data);
            //alert("trucks: " + resp["trucks"]);
            //alert("drivers: " + resp["drivers"]);
            createReportForm(resp["trucks"], resp["drivers"]);
        }
    });
}

function createReportForm(trucks, drivers) {
    var root = document.getElementById("reportArea");
    $("#reportArea").empty();

    var frm = document.createElement("DIV");
    var h4 = document.createElement("H4")    ;
    h4.innerHTML = "select truck";
    frm.appendChild(h4);

    var truckTable = document.createElement("TABLE");
    truckTable.setAttribute( 'class', 'table' );
    //truckTable.setAttribute( 'class', 'table-bordered' );
    var lastRow = 0;
    var truckHeader = truckTable.insertRow(lastRow);
        //document.createElement("TR");
 //   truckHeader.setAttribute( 'class', 'table' );
    truckHeader.setAttribute( 'class', 'active' );
    var th;
    th = document.createElement("TH");
    th.innerHTML = "#";
    truckHeader.appendChild(th);
    //truckHeader.appendChild(document.createElement("TH").innerHTML = "Id");
    th = document.createElement("TH");
    th.innerHTML = "Id";
    truckHeader.appendChild(th);

    th = document.createElement("TH");
    th.innerHTML = "dutySize";
    truckHeader.appendChild(th);

    th = document.createElement("TH");
    th.innerHTML = "Capacity";
    truckHeader.appendChild(th);

    truckTable.appendChild(truckHeader);

    for(var t in trucks) {

        var res = JSON.stringify(trucks[t]);

        var truckId = JSON.stringify(trucks[t]["id"]);
        var dutySize = JSON.stringify(trucks[t]["dutySize"]);
        var capacity = JSON.stringify(trucks[t]["capacity"]);

        var radio = document.createElement("INPUT");
        radio.setAttribute("type", "radio");
        radio.setAttribute("id",truckId );
        radio.setAttribute("name", "truckRadioGroup");
        radio.setAttribute("value", truckId);


        var truckRow = document.createElement("TR");
        truckRow.setAttribute( 'class', 'active' );
        var td;
        td = document.createElement("TD");
        td.appendChild(radio)
        truckRow.appendChild(td);

        td = document.createElement("TD");
        td.innerHTML = truckId;
        truckRow.appendChild(td);

        td = document.createElement("TD");
        td.innerHTML = dutySize;
        truckRow.appendChild(td);

        td = document.createElement("TD");
        td.innerHTML = capacity;
        truckRow.appendChild(td);
        truckTable.appendChild(truckRow);

    }
    frm.appendChild(truckTable);

    h4 = document.createElement("H4");
    h4.innerHTML = "select drivers";
    frm.appendChild(h4);
///////////////////////////////////////////////////
    var driverTable = document.createElement("TABLE");
    driverTable.setAttribute( 'class', 'table' );
    var driverHeader = document.createElement("TR");

    th = document.createElement("TH");
    th.innerHTML = "#";
    driverHeader.appendChild(th);

    th = document.createElement("TH");
    th.innerHTML = "Id";
    driverHeader.appendChild(th);


    th = document.createElement("TH");
    th.innerHTML = "name";
    driverHeader.appendChild(th);


    th = document.createElement("TH");
    th.innerHTML = "last name";
    driverHeader.appendChild(th);

    th = document.createElement("TH");
    th.innerHTML = "hours worked";
    driverHeader.appendChild(th);

    driverTable.appendChild(driverHeader);

    for(var d in drivers) {
        var res = JSON.stringify(drivers[d]);

        var driverId = JSON.stringify(drivers[d]["id"]);
        var name = JSON.stringify(drivers[d]["name"]);
        var lastName = JSON.stringify(drivers[d]["lastName"]);
        var hoursWorked = JSON.stringify(drivers[d]["hoursWorked"]);

        var checkBox = document.createElement("INPUT");
        checkBox.setAttribute("type", "checkbox");
        checkBox.setAttribute("id",driverId );
        checkBox.setAttribute("name", "driverCheckBox");
        checkBox.setAttribute("value", driverId);

        var driverRow = document.createElement("TR");
        var td;
        td = document.createElement("TD");
        td.appendChild(checkBox)
        driverRow.appendChild(td);

        td = document.createElement("TD");
        td.innerHTML = driverId;
        driverRow.appendChild(td);

        td = document.createElement("TD");
        td.innerHTML = name;
        driverRow.appendChild(td);

        td = document.createElement("TD");
        td.innerHTML = lastName;
        driverRow.appendChild(td);

        td = document.createElement("TD");
        td.innerHTML = hoursWorked;
        driverRow.appendChild(td);

        driverTable.appendChild(driverRow);
    }

    frm.appendChild(driverTable);

    var button = document.createElement("INPUT");
    button.setAttribute("type", "submit");
    button.setAttribute("id", "sendOrder");
    button.setAttribute("value", "create order");
    button.setAttribute("onclick", "createOrder()");
    button.setAttribute("class", "btn-primary");

    frm.appendChild(button);
    root.appendChild(frm);
}

function validateBaggageCount (){
    var count = document.forms["create"]["bgcnt"].value;
    //alert(count)
    return true
}

function getTruckId() {
    var manageradiorel = $("input:radio[name ='truckRadioGroup']:checked").val();
    //alert(manageradiorel);
    return manageradiorel;
}

function getDrivers() {
    var checked = []
    $("input:checkbox[name='driverCheckBox']:checked").each(function ()
    {
        checked.push($(this).val());
    });
    return JSON.stringify(checked);
}
function createOrder() {
    //alert("createOrder");
    var jsdata =  GetCellValues();
    //alert(jsdata);
    var truckId = getTruckId();
    //alert(truckId);
    var drivers = getDrivers();
    //alert(drivers);
    $.ajax({
        url: '/private/manager/createorder',
        type: 'POST',
        data : {do:"createOrder", jsdata:jsdata, truckId:truckId, drivers:drivers},
        success: function(response) {
            //...
        }
    });

}