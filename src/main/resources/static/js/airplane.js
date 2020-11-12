$(document).ready(function () {
    console.log("Airplane.js is being used");
    getData();
});


Airplaneapi = "http://localhost:8080/api/airplanes/";

function getData() {
    $(document).ready(function () {
        $.ajax({
            url: Airplaneapi,
            type: "get",
            success: function (data) {
                var planeTableContent = "";
                $.each(data, function (index, current) {

                    console.log("each function is initiated");
                    // Make variable which consists of the currentfuel, so we can change it later when we use the tank button
                    var fuelBlockID = "current fuel" + current.id;
                    // columnRow is being created with all the values out of the database
                    var columnRow = "<tr><td>" +
                        current.planeName + "</td><td>" +
                        current.location + "</td><td id='fuelBlockID'>" +
                        current.currentFuel + "</td><td>" +
                        "<button type='button' class='btn btn-primary' onclick='addFuel(" + current.id + ")'> Add Fuel </button>" + "</td><td>" +
                        "<button type='button' class='btn btn-primary' onclick='flyTo(" + current.id + ")'> Fly plane! </button>" + "</td><td>"
                        + "<button type='button' class='btn btn-danger' data-toggle='modal' data-target='#deletePlaneModal' id='" + current.id + "' onclick='deletePlane(" + current.id + ")'> Delete </button>" + "</td></tr>";
                    planeTableContent += columnRow;
                    console.log(columnRow);
                });
                console.log(planeTableContent);
                $("#planeTable").empty();
                $("#planeTable").append(planeTableContent);
            }
        });
    });
}

function postData() {
    // The postData function is triggered by the add new room button. This function has to post the filled in data into the table.
    // First we need to put the values of the input fields into variables
    var inputPlaneName = $("#planeName").val();
    var inputLocation = $("#location").val();
    var inputAmountOfFuel = $("#amountOfFuel").val();

    // i created a new object
    var newPlaneObject = {
        planeName: inputPlaneName,
        currentFuel: inputAmountOfFuel,
        location: inputLocation
    };
// Make the object readable for the backend by parsing it to JSON
    var newPlane = JSON.stringify(newPlaneObject);
    console.log(newPlane);

    // Save the actual data to the repository
    $.ajax({
        url: Airplaneapi,
        type: "post",
        data: newPlane,
        contentType: "application/json",
        success: function (data) {
            console.log("Success post!");
            getData();
            getAirportData();
        },
        error:function (data){
            console.log("unsuccessful put")
            if(data.responseJSON)
                alert(data.responseJSON.message)
        }

    });
};

function deletePlane(id) {

    $("#deleteThisPlane").html("Are you sure you want to delete plane #" + id + "?");
    $("#finalDelete").click(function () {
        console.log("function modalDeletePlane is being used")
        $.ajax({
            url: Airplaneapi + id,
            type: "delete",
            contentType: "application/json",
            success: function () {
                console.log("Deletion is initiated");
                $("#planeTable").html("");
                getData();
                getAirportData();
            }
        });
    });
}

function addFuel(id) {
    console.log("fuel is added");
    var fuelObject = {id: id};
    console.log(fuelObject);
    var newFuelObjectUpdate = JSON.stringify(fuelObject);
    console.log(newFuelObjectUpdate);
    $.ajax({
        url: Airplaneapi + "/refuel/" + id,
        type: "put",
        data: newFuelObjectUpdate,
        contentType: "application/json",
        success: function (data) {
            console.log("successful added fuel")
            getData();
        },
        error:function (data){
        console.log("unsuccesfull put")
        if(data.responseJSON)
            alert(data.responseJSON.message)
    }
    });
}

function flyTo(id) {
    console.log("plane flies");
    console.log(id);
    var fuelObject = {
        id: id
    };
    console.log(fuelObject);
    var newFuelObjectUpdate = JSON.stringify(fuelObject);
    console.log(newFuelObjectUpdate);

    $.ajax({
        url: Airplaneapi + "flight/" + id,
        type: "put",
        data: newFuelObjectUpdate,
        contentType: "application/json",
        success: function (data) {
            console.log("successful put")
            getData();
        },
        error:function (data){
            console.log("unsuccesfull put")
            if(data.responseJSON)
            alert(data.responseJSON.message)
        }
    });
}