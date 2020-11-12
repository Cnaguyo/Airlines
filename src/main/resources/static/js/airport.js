// use ajax to load the backend  data to jquery

let airportApi = 'http://localhost:8080/api/airports/';

$(document).ready(function () {
    console.log("Airport.js is being used");
    getAirportData();
});


function getAirportData() {
    $(document).ready(function () {

        $.ajax({
            url: airportApi,
            type: "get",
            success: function (data) {
                var airportTableContent = "";
                $('#location').empty();
                $.each(data, function (index, current) {
                    console.log("each function is initiated");
                    $('#location').append($('<option>', {
                        value: current.airportName,
                        text : current.airportName
                    }));
                    // columnRow is being created with all the values out of the database
                  var columnRow = "<tr><td>" +
                        current.airportName + "</td><td>" +
                        current.airplanes.length + "</td><td>" +
                       "</td><td>"
                        + "<button type='button' class='btn btn-danger' data-toggle='modal' data-target='#deleteAirportModal' id='" + current.id + "' onclick='deleteAirport(" + current.id + ")'> Delete </button>" + "</td></tr>";

                    airportTableContent += columnRow;
                    console.log(columnRow);
                });
                console.log(airportTableContent);
                $("#airportTable").empty();
                $("#airportTable").append(airportTableContent);
            }
        });

    });
}

function postAirportData() {
    // The postData function is triggered by the add new airport button. This function has to post the filled in data into the table.
//  add the values of the input fields into variables

    var inputAirportName = $("#airportName").val();
    var inputMaxAmountOfFlight = parseInt($("#maxAmountOfFlight").val());

    // i created a new object
    var newAirportObject = {
        airportName: inputAirportName,
        numOfMaxFlights: inputMaxAmountOfFlight,
    };
// Make the object readable for the backend by parsing it to JSON
    var newAirport = JSON.stringify(newAirportObject);
    console.log(newAirport);

    // Save the actual data to the repository
    $.ajax({
        url: airportApi,
        type: "post",
        data: newAirport,
        contentType: "application/json",
        success: function (data) {
            console.log("Successful !");
            getAirportData();
        }
    });
};

function deleteAirport(id) {
    $("#deleteThisAirport").html("Are you sure you want to delete airport #" + id + "?");
    $("#finalAirportDelete").click(function () {
        console.log("function modalDeleteAirport is being used")
        $.ajax({
            url: airportApi + id,
            type: "delete",
            contentType: "application/json",
            success: function () {
                console.log("Deletion is initiated");
                $("#airportTable").html("");
                getData();
                getAirportData();
            }
        });
    });
}
